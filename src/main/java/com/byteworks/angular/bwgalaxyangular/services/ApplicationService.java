/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.services;

import com.byteworks.angular.bwgalaxyangular.model.Task;
import com.byteworks.angular.bwgalaxyangular.repo.RolesRepository;
import com.byteworks.angular.bwgalaxyangular.repo.TaskRepository;
import com.byteworks.angular.bwgalaxyangular.repo.TaskmanagerRepository;
import com.byteworks.angular.bwgalaxyangular.repo.TaskreportRepository;
import com.byteworks.angular.bwgalaxyangular.repo.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bankole
 */
@Service
@Transactional
public class ApplicationService {

    @Autowired
    RolesRepository rolesService;

    @Autowired
    TaskRepository taskService;

    @Autowired
    TaskmanagerRepository taskmanagerService;

    @Autowired
    TaskreportRepository taskreportService;

    @Autowired
    UsersRepository userService;

    public RolesRepository getRolesService() {
        return rolesService;
    }

    public void setRolesService(RolesRepository rolesService) {
        this.rolesService = rolesService;
    }

    public TaskRepository getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskRepository taskService) {
        this.taskService = taskService;
    }

    public TaskmanagerRepository getTaskmanagerService() {
        return taskmanagerService;
    }

    public void setTaskmanagerService(TaskmanagerRepository taskmanagerService) {
        this.taskmanagerService = taskmanagerService;
    }

    public TaskreportRepository getTaskreportService() {
        return taskreportService;
    }

    public void setTaskreportService(TaskreportRepository taskreportService) {
        this.taskreportService = taskreportService;
    }

    public UsersRepository getUserService() {
        return userService;
    }

    public void setUserService(UsersRepository userService) {
        this.userService = userService;
    }

}
