/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.repo;

import com.byteworks.angular.bwgalaxyangular.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bankole
 */
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    @Query("select e from Roles e where e.roleid = ?1")  // Roles by ID
    public Roles findRolesById(Integer id);

}
