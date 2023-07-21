/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.Country;
import com.mycompany.entity.User;
import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public User getById(int id) {
        User result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT"
                    + "	u.*, "
                    + "	 n.nationality, "
                    + "	 c.birthplace   "
                    + " FROM "
                    + "	`user` u "
                    + "	LEFT JOIN country n ON u.nationality_id = n.id "
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where u.id=" + id);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from user where id=" + id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        int nationalityId = rs.getInt("nationality_id");
        int birthPlaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");
        Date birthDate = rs.getDate("birthdate");
        String profileDescription = rs.getString("profile_description");
        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthPlaceId, birthPlaceStr, null);

        return new User(id, name, surname, phone, email, birthDate, profileDescription, nationality, birthPlace);
    }

    @Override

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user");
            stmt.execute("SELECT"
                    + "	u.*, "
                    + "	 n.nationality, "
                    + "	 c.birthplace "
                    + " FROM "
                    + "	`user` u "
                    + "	LEFT JOIN country n ON u.nationality_id = n.id "
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);

                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?,email=?,profile_description=?,birthdate=?,birthplace_id=? where id=?");

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDescription());
            stmt.setDate(6, u.getBirthDate());
            stmt.setInt(7, u.getBirthPlace().getId());

            stmt.setInt(8, u.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,email,phone,profile_description) values(?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
