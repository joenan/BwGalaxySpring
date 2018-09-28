/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.repo;

import com.byteworks.angular.bwgalaxyangular.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bankole
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select count(e) from Task e where e.status = ?1")  //number of new task
    Integer findTaskByNumber(String task);
//

    @Query("SELECT c from Task c where c.taskid=?1")  //number of new task
    Task findTaskById(Integer id);
//    

    @Query("select c from Task c")
    List<Task> findAllTask();

    @Query("select c from Task c where c.status = ?1")
    List<Task> findTaskByStatus(String status);

    @Query("select c FROM Task c where c.userid IS NULL")
    List<Task> findUnassignedByTask(Object object);

    @Query("select c from Task c where c.objective = ?1")
    Task findTaskByObjectives(String objectives);

}
