package com.ouiuo.timetable.dao;

import com.ouiuo.timetable.model.Group;
import com.ouiuo.timetable.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findAll();


    @Query("SELECT DISTINCT u.group FROM users u")
    List<Group> findDistinctGroups();

}
