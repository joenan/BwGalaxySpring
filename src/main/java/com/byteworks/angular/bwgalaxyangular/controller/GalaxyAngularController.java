/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.controller;

import com.byteworks.angular.bwgalaxyangular.model.Roles;
import com.byteworks.angular.bwgalaxyangular.model.Task;
import com.byteworks.angular.bwgalaxyangular.model.Taskmanager;
import com.byteworks.angular.bwgalaxyangular.model.Taskreport;
import com.byteworks.angular.bwgalaxyangular.model.Users;
import com.byteworks.angular.bwgalaxyangular.services.ApplicationService;
import dto.ApiResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bankole
 */
@RestController
@RequestMapping("/api/galaxy")
public class GalaxyAngularController {

    @Autowired
    ApplicationService service;

    ///////////////////////////////CRUD METHODS FOR TASK/////////////////////////////
    @PostMapping("/task/assign/{user}/{id}")
    public ResponseEntity<?> assignTaskToUser(@PathVariable("user") String username, @PathVariable("id") Long id, @RequestBody Task task) {
        Users user = service.getUserService().findUserByUsername(username);
        task.setUserid(user);

        Task persistTask = service.getTaskService().save(task);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Task has been saved with ID:" + persistTask));
    }

    @PostMapping("/task/assign/{manager}/{id}")
    public ResponseEntity<?> assignTaskToManager(@PathVariable("manager") String manager, @PathVariable("id") Integer id, @RequestBody Task task) {
        Taskmanager tm = service.getTaskmanagerService().findTaskManagerByName(manager);
        task.setManagerid(tm);

        Task persistTask = service.getTaskService().save(task);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Task has been saved with ID:" + persistTask));
    }

    @PostMapping("/task/assign")
    public ResponseEntity<?> assignTaskByUser(@RequestParam("username") String selectedUser, @RequestParam("task") String objectives) {
        Users user = service.getUserService().findUserByUsername(selectedUser);
        Task taskRetrieved = service.getTaskService().findTaskByObjectives(objectives);
        taskRetrieved.setUserid(user);
        taskRetrieved.setStatus("On Going");
        Task persistTask = service.getTaskService().save(taskRetrieved);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Task has been Updated with ID:" + persistTask));
    }

    @PostMapping("/task")
    public ResponseEntity<?> saveTask(@RequestBody Task task) {
        Task id = service.getTaskService().save(task);
        return ResponseEntity.ok().body(new ApiResponse<Task>(HttpStatus.CREATED.toString(), "Task created", id));
    }

    @GetMapping("/task/newtask")
    public List<Task> retrieveAllToDoTask() {
        List<Task> list = service.getTaskService().findTaskByStatus("New Task");
        return list;
    }

    @GetMapping("/task/ongoing")
    public List<Task> retrieveAllOngoingTask() {
        List<Task> list = service.getTaskService().findTaskByStatus("On Going");
        return list;
    }

    @GetMapping("/task/completed")
    public List<Task> retrieveAllCompletedTask() {
        List<Task> list = service.getTaskService().findTaskByStatus("Completed");
        return list;
    }

    @GetMapping("/task/incomplete")
    public List<Task> retrieveAllUncompletedTask() {
        List<Task> list = service.getTaskService().findTaskByStatus("Incomplete");
        return list;
    }

    @GetMapping("/task/unassigned")
    public List<Task> retrieveAllUnassignedTask() {
        List<Task> list = service.getTaskService().findUnassignedByTask(null);
        return list;
    }

    @GetMapping("/task/list")
    public List<Task> retrieveAllTask() {
        List<Task> list = service.getTaskService().findAllTask();
        list.parallelStream().forEach((t) -> {
            if (t.getManagerid() != null) {
                t.setManagerid(service.getTaskmanagerService().findTaskManagerById(t.getManagerid().getTaskmanagerid()));
            }

            if (t.getUserid() != null) {
                t.setUserid(service.getUserService().findUserById(t.getUserid().getUserid()));
            }

        });

        return list;
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Integer id) {
        Task task = service.getTaskService().findTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") long id, @RequestBody Task task) {

        service.getTaskService().save(task);
        return ResponseEntity.ok().body("Task has been successfully updated.");
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Integer id) {
        Task task = service.getTaskService().findTaskById(id);
        service.getTaskService().delete(task);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Task has been successfully deleted." + task.getTaskid()));
    }

//    ///////////////////////////////END OF CRUD METHODS FOR TASK/////////////////////////////
//    ///////////////////////////////CRUD METHODS FOR TASK REPORT/////////////////////////////
    @PostMapping("/taskreport")
    public ResponseEntity<?> saveTaskReport(@RequestParam("objective") String objective, @RequestParam("taskstatus") String status, @RequestParam("description") String description) {
        Task task = service.getTaskService().findTaskByObjectives(objective);
        task.setStatus(status);

        Taskreport report = new Taskreport();
        report.setReportdescription(description);
        report.setTaskid(task);
        report.setUserid(task.getUserid());

        Taskreport persist = service.getTaskreportService().save(report);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Task has been saved with ID:" + persist));
    }

    @GetMapping("/taskreport/list")
    public List<Taskreport> retrieveAllTaskReport() {
        return service.getTaskreportService().findAll();
    }

    @GetMapping("/taskreport/{id}")
    public ResponseEntity<Taskreport> getTaskReport(@PathVariable("id") Integer id) {
        Taskreport task = service.getTaskreportService().findTaskReportById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    @PutMapping("/taskreport/{id}")
    public ResponseEntity<?> updateTaskReport(@PathVariable("id") long id, @RequestBody Taskreport task) {

        service.getTaskreportService().save(task);
        return ResponseEntity.ok().body("Task has been successfully updated.");
    }

    @DeleteMapping("/taskreport/{id}")
    public ResponseEntity<?> deleteTaskReport(@PathVariable("id") Integer id) {
        Taskreport task = service.getTaskreportService().findTaskReportById(id);
        service.getTaskreportService().delete(task);
        return ResponseEntity.ok().body("Task has been successfully deleted.");
    }

    ///////////////////////////////END OF CRUD METHODS FOR TASK  REPORT /////////////////////////////
    ///////////////////////////////CRUD METHODS FOR TASK REPORT/////////////////////////////
    @PostMapping("/taskmanager")
    public ResponseEntity<?> saveTaskReport(@RequestBody Taskmanager task) {
        Taskmanager id = service.getTaskmanagerService().save(task);
        return ResponseEntity.ok().body("Task Manager has been created with ID:" + id);
    }

    @GetMapping("/taskmanager/list")
    public List<Taskmanager> retrieveAllTaskManagers() {
        return service.getTaskmanagerService().findAll();
    }

    @GetMapping("/taskmanager/{id}")
    public ResponseEntity<Taskmanager> getTaskManager(@PathVariable("id") Integer id) {
        Taskmanager task = service.getTaskmanagerService().findTaskManagerById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    @PutMapping("/taskmanager/{id}")
    public ResponseEntity<?> updateTaskManager(@PathVariable("id") long id, @RequestBody Taskmanager task) {

        service.getTaskmanagerService().save(task);
        return ResponseEntity.ok().body("Task Manager has been successfully updated.");
    }

    @DeleteMapping("/taskmanager/{id}")
    public ResponseEntity<?> deleteTaskManager(@PathVariable("id") Integer id) {
        Taskmanager task = service.getTaskmanagerService().findTaskManagerById(id);
        service.getTaskmanagerService().delete(task);
        return ResponseEntity.ok().body("Task Manager has been successfully deleted.");
    }

    ///////////////////////////////END OF CRUD METHODS FOR TASK  REPORT /////////////////////////////
    ///////////////////////////////CRUD METHODS FOR USERS/////////////////////////////
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody Users user) {
        Users id = service.getUserService().save(user);
        return ResponseEntity.ok().body("User has been created with ID:" + id);
    }

    @GetMapping("/auth/user")
    public ResponseEntity<?> getAuthUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("Username = " + username + " Password = " + password);

        Users id = service.getUserService().findAuthenticatedUser(username, password);
        if (id == null) {
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Such user does not exists"));
        }
        return ResponseEntity.ok().body(Collections.singletonMap("message", "success"));
    }

    @GetMapping("/user/list")
    public List<Users> retrieveAllUsers() {
        return service.getUserService().findAll();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Users> getUser(@PathVariable("username") String username) {
        Users user = service.getUserService().findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
        service.getUserService().save(user);
        return ResponseEntity.ok().body("User has been successfully updated.");
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        Users task = service.getUserService().findUserByUsername(username);
        service.getUserService().delete(task);
        return ResponseEntity.ok().body("Task Manager has been successfully deleted.");
    }

    ///////////////////////////////END OF CRUD METHODS FOR USER /////////////////////////////
    ///////////////////////////////CRUD METHODS FOR ROLE/////////////////////////////
    @PostMapping("/roles")
    public ResponseEntity<?> saveUser(@RequestBody Roles roles) {
        Roles id = service.getRolesService().save(roles);
        return ResponseEntity.ok().body("Roles has been created with ID:" + id);
    }

    @GetMapping("/roles/list")
    public List<Roles> retrieveAllRoles() {
        return service.getRolesService().findAll();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Roles> getRoles(@PathVariable("id") Integer id) {
        Roles roles = service.getRolesService().findRolesById(id);
        if (roles == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(roles);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> updateRoles(@PathVariable("id") long id, @RequestBody Roles user) {
        service.getRolesService().save(user);
        return ResponseEntity.ok().body("Roles has been successfully updated.");
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRoles(@PathVariable("id") Integer id) {
        Roles roles = service.getRolesService().findRolesById(id);
        service.getRolesService().delete(roles);
        return ResponseEntity.ok().body("Roles has been successfully deleted.");
//    }
        ///////////////////////////////END OF CRUD METHODS FOR ROLE /////////////////////////////
    }

    ///////////////////////////////CRUD METHODS FOR DASHBOARD/////////////////////////////
    @GetMapping("/dashboard/newtask")
    public ResponseEntity getDashboardNewTask() {
        Integer newTask = service.getTaskService().findTaskByNumber("New Task");
        return ResponseEntity.ok().body(newTask);
    }

    @GetMapping("/dashboard/ongoingtask")
    public ResponseEntity getDashboardOngoingTask() {
        Integer newTask = service.getTaskService().findTaskByNumber("On Going");
        return ResponseEntity.ok().body(newTask);
    }

    @GetMapping("/dashboard/completedtask")
    public ResponseEntity getDashboardCompletedTask() {
        Integer newTask = service.getTaskService().findTaskByNumber("Completed");
        return ResponseEntity.ok().body(newTask);
    }

    @GetMapping("/dashboard/incompleted")
    public ResponseEntity getDashboardIncompletedTask() {
        Integer newTask = service.getTaskService().findTaskByNumber("Incomplete");
        return ResponseEntity.ok().body(newTask);
    }

}
