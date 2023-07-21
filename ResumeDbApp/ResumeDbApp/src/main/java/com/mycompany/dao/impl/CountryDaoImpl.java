/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.entity.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("birthplace");
        String nationality = rs.getString("nationality");

        return new Country(id, name, nationality);
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * FROM country");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Country us = getCountry(rs);
                result.add(us);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateCountry(Country country) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,nationality=? where id=?");

            stmt.setString(1, country.getName());
            stmt.setString(2, country.getNationality());
            stmt.setInt(3, country.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addCountry(Country country) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into country(birthplace,nationality) values(?,?)");
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getNationality());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeCountry(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from country where id=" + id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
