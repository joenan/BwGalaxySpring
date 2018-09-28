/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bankole
 */
@Entity
@Table(name = "task")
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findByTaskid", query = "SELECT t FROM Task t WHERE t.taskid = :taskid")
    , @NamedQuery(name = "Task.findByObjective", query = "SELECT t FROM Task t WHERE t.objective = :objective")
    , @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description")
    , @NamedQuery(name = "Task.findByStatus", query = "SELECT t FROM Task t WHERE t.status = :status")
    , @NamedQuery(name = "Task.findByDatecreated", query = "SELECT t FROM Task t WHERE t.datecreated = :datecreated")
    , @NamedQuery(name = "Task.findByDuedate", query = "SELECT t FROM Task t WHERE t.duedate = :duedate")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskid")
    private Integer taskid;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "objective")
    private String objective;
    
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "detaileddescription")
    private String detaileddescription;
    
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    
    @Column(name = "datecreated")
    @Temporal(TemporalType.DATE)
    private Date datecreated;
    
    @Column(name = "duedate")
    @Temporal(TemporalType.DATE)
    private Date duedate;
    
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private Users userid;
    
    @JoinColumn(name = "managerid", referencedColumnName = "taskmanagerid")
    @ManyToOne
    private Taskmanager managerid;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "taskid")
    @JsonIgnore
    private List<Taskreport> taskreportList;

    public Task() {
    }

    public Task(Integer taskid) {
        this.taskid = taskid;
    }

    public Task(Integer taskid, String objective, String detaileddescription) {
        this.taskid = taskid;
        this.objective = objective;
        this.detaileddescription = detaileddescription;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetaileddescription() {
        return detaileddescription;
    }

    public void setDetaileddescription(String detaileddescription) {
        this.detaileddescription = detaileddescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Taskmanager getManagerid() {
        return managerid;
    }

    public void setManagerid(Taskmanager managerid) {
        this.managerid = managerid;
    }

    public List<Taskreport> getTaskreportList() {
        return taskreportList;
    }

    public void setTaskreportList(List<Taskreport> taskreportList) {
        this.taskreportList = taskreportList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskid != null ? taskid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskid == null && other.taskid != null) || (this.taskid != null && !this.taskid.equals(other.taskid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.byteworks.angular.bwgalaxyangular.model.Task[ taskid=" + taskid + " ]";
    }
    
}
