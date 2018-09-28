/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author bankole
 */
@Entity
@Table(name = "taskreport")
@NamedQueries({
    @NamedQuery(name = "Taskreport.findAll", query = "SELECT t FROM Taskreport t")
    , @NamedQuery(name = "Taskreport.findByTaskreportid", query = "SELECT t FROM Taskreport t WHERE t.taskreportid = :taskreportid")
    , @NamedQuery(name = "Taskreport.findByReportdescription", query = "SELECT t FROM Taskreport t WHERE t.reportdescription = :reportdescription")})
public class Taskreport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskreportid")
    private Integer taskreportid;
    @Size(max = 255)
    @Column(name = "reportdescription")
    private String reportdescription;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private Users userid;
    @JoinColumn(name = "taskid", referencedColumnName = "taskid")
    @ManyToOne
    private Task taskid;

    public Taskreport() {
    }

    public Taskreport(Integer taskreportid) {
        this.taskreportid = taskreportid;
    }

    public Integer getTaskreportid() {
        return taskreportid;
    }

    public void setTaskreportid(Integer taskreportid) {
        this.taskreportid = taskreportid;
    }

    public String getReportdescription() {
        return reportdescription;
    }

    public void setReportdescription(String reportdescription) {
        this.reportdescription = reportdescription;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Task getTaskid() {
        return taskid;
    }

    public void setTaskid(Task taskid) {
        this.taskid = taskid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskreportid != null ? taskreportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taskreport)) {
            return false;
        }
        Taskreport other = (Taskreport) object;
        if ((this.taskreportid == null && other.taskreportid != null) || (this.taskreportid != null && !this.taskreportid.equals(other.taskreportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.byteworks.angular.bwgalaxyangular.model.Taskreport[ taskreportid=" + taskreportid + " ]";
    }
    
}
