/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.inter;

import com.mycompany.entity.Country;
import com.mycompany.entity.User;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface CountryDaoInter {

    public List<Country> getAll();

    public boolean updateCountry(Country u);

    public boolean addCountry(Country u);

    public boolean removeCountry(int id);
}
