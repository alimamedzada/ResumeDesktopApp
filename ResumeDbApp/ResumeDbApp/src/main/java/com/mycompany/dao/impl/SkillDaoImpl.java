/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.SkillDaoInter;
import com.mycompany.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id, name);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * FROM skill");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Skill us = getSkill(rs);
                result.add(us);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateSkill(Skill s) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update skill set name=? where id=?");
            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertSkill(Skill s) {
        boolean b = true;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert skill(name) values(?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, s.getName());
            b = stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                s.setId(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return b;
    }

    @Override
    public boolean removeSkill(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from skill where id=?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
