/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.dao.inter.SkillDaoInter;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.dao.inter.UserSkillDaoInter;
import com.mycompany.entity.User;

/**
 *
 * @author Asus
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CountryDaoInter countryDao = Context.instanceCountryDao();
        System.out.println(countryDao.getAll());
        System.out.println("------------------------------------------------------------------------------------------");

        EmploymentHistoryDaoInter EmpHisDao = Context.instanceEmploymentHistoryDao();
        System.out.println(EmpHisDao.getAllEmploymentHistoryByUserId(9));
        System.out.println("------------------------------------------------------------------------------------------");

        SkillDaoInter skillDao = Context.instanceSkillDao();
        System.out.println(skillDao.getAll());
        System.out.println("------------------------------------------------------------------------------------------");

        UserDaoInter userDao = Context.instanceUserDao();
        System.out.println(userDao.getAll());
        System.out.println("------------------------------------------------------------------------------------------");

        UserSkillDaoInter userSkillDao = Context.instanceUserSkillDao();
        System.out.println(userSkillDao.getAllSkillByUserId(9));
        System.out.println("------------------------------------------------------------------------------------------");
    }
}
