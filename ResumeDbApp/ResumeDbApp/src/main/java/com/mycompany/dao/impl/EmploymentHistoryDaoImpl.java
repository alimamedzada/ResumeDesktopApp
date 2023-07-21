/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.UserSkill;
import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.entity.EmploymentHistory;
import com.mycompany.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_describtion");
        int userId = rs.getInt("user_id");
        return new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * from employment_history where user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                EmploymentHistory u = getEmploymentHistory(rs);

                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory history) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update employment_history set header=?, begin_date=?, end_date=?,job_description=?, user_id=? where id=?");

            stmt.setString(1, history.getHeader());
            stmt.setDate(2, history.getBeginDate());
            stmt.setDate(3, history.getEndDate());
            stmt.setString(4, history.getJobDescription());
            stmt.setInt(5, history.getUser().getId());
            stmt.setInt(6, history.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addEmploymentHistory(EmploymentHistory history) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into country(header, begin_date, end_date,job_description, user_id) values(?,?,?,?,?)");
            stmt.setString(1, history.getHeader());
            stmt.setDate(2, history.getBeginDate());
            stmt.setDate(3, history.getEndDate());
            stmt.setString(4, history.getJobDescription());
            stmt.setInt(5, history.getUser().getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeEmploymentHistory(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from employmeny_history where id=" + id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
