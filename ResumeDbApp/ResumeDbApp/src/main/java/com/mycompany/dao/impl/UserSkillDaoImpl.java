/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.Skill;
import com.mycompany.entity.User;
import com.mycompany.entity.UserSkill;
import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int userSkillId = rs.getInt("userSkillId");

        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + " us.id as userSkillId, "
                    + "	u.*,"
                    + "	us.skill_id,"
                    + "	s.`name` AS skill_name,"
                    + "	us.power "
                    + "FROM"
                    + "	user_skill us"
                    + "	LEFT JOIN USER u ON us.user_id = u.id"
                    + "	LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE"
                    + "	us.user_id =?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                UserSkill u = getUserSkill(rs);

                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(UserSkill u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user_skill set skill_id=?,user_id=?,power=? where id=?");
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());
            stmt.setInt(4, u.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("INSERT user_skill(user_id,skill_id,power) VALUES (?,?,?)");
            stmt.setInt(1, u.getUser().getId());
            stmt.setInt(2, u.getSkill().getId());
            stmt.setInt(3, u.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from user_skill where id=?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
