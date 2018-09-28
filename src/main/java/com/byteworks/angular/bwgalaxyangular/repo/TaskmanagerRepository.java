/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.repo;

import com.byteworks.angular.bwgalaxyangular.model.Taskmanager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bankole
 */
public interface TaskmanagerRepository extends JpaRepository<Taskmanager, Integer> {

    @Query("select e from Taskmanager e where e.taskmanagerid = ?1")  //number of new task
    public Taskmanager findTaskManagerById(Integer id);

    @Query("select e from Taskmanager e where e.name = ?1")   
    public Taskmanager findTaskManagerByName(String name);

}
