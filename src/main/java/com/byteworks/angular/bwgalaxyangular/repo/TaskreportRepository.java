/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.repo;

import com.byteworks.angular.bwgalaxyangular.model.Taskreport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bankole
 */
public interface TaskreportRepository extends JpaRepository<Taskreport, Integer> {

    @Query("select e from Taskreport e where e.taskreportid = ?1")  //number of new task report
    public Taskreport findTaskReportById(Integer id);

}
