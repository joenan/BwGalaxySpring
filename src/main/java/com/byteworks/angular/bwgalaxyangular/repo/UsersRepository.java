/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byteworks.angular.bwgalaxyangular.repo;

import com.byteworks.angular.bwgalaxyangular.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bankole
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("select e from Users e where e.username = ?1")
    public Users findUserByUsername(String username);

    @Query("SELECT c from Users c where c.userid = ?1")
    public Users findUserById(Integer userid);

    @Query("select e from Users e where e.userid = ?1")
    public Users findUserByEmail(String email);

    @Query("select c from Users c where c.email = ?1 AND c.password = ?2")
    Users findAuthenticatedUser(String username, String password);

}
