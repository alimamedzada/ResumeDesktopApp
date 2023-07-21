/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.inter;

import com.mycompany.entity.Skill;
import com.mycompany.entity.UserSkill;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface SkillDaoInter {

    public List<Skill> getAll();

    public boolean updateSkill(Skill u);

    public boolean insertSkill(Skill u);

    public boolean removeSkill(int id);

}
