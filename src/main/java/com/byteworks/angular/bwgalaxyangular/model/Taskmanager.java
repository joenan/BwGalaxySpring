/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bankole
 */
@Entity
@Table(name = "taskmanager")
@NamedQueries({
    @NamedQuery(name = "Taskmanager.findAll", query = "SELECT t FROM Taskmanager t")
    , @NamedQuery(name = "Taskmanager.findByTaskmanagerid", query = "SELECT t FROM Taskmanager t WHERE t.taskmanagerid = :taskmanagerid")
    , @NamedQuery(name = "Taskmanager.findByName", query = "SELECT t FROM Taskmanager t WHERE t.name = :name")
    , @NamedQuery(name = "Taskmanager.findByEmail", query = "SELECT t FROM Taskmanager t WHERE t.email = :email")})
public class Taskmanager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskmanagerid")
    private Integer taskmanagerid;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "managerid")
    @JsonIgnore
    private List<Task> taskList;

    public Taskmanager() {
    }

    public Taskmanager(Integer taskmanagerid) {
        this.taskmanagerid = taskmanagerid;
    }

    public Taskmanager(Integer taskmanagerid, String name, String email) {
        this.taskmanagerid = taskmanagerid;
        this.name = name;
        this.email = email;
    }

    public Integer getTaskmanagerid() {
        return taskmanagerid;
    }

    public void setTaskmanagerid(Integer taskmanagerid) {
        this.taskmanagerid = taskmanagerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskmanagerid != null ? taskmanagerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taskmanager)) {
            return false;
        }
        Taskmanager other = (Taskmanager) object;
        if ((this.taskmanagerid == null && other.taskmanagerid != null) || (this.taskmanagerid != null && !this.taskmanagerid.equals(other.taskmanagerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.byteworks.angular.bwgalaxyangular.model.Taskmanager[ taskmanagerid=" + taskmanagerid + " ]";
    }

}
