package com.ouiuo.timetable.dao;

import com.ouiuo.timetable.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findGroupByGroupNameAndGroupNumberAndIsPracticum(String groupName, Long groupNumber, Boolean isPracticum);
}
