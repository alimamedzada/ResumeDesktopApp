/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.inter;

import com.mycompany.entity.User;
import com.mycompany.entity.UserSkill;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface UserSkillDaoInter {

    public List<UserSkill> getAllSkillByUserId(int userId);

    public boolean updateUser(UserSkill u);

    public boolean insertUserSkill(UserSkill u);

    public boolean removeUser(int id);
}
