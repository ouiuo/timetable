package com.ouiuo.timetable.dao;

import com.ouiuo.timetable.model.TrainingPair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassesRepository extends CrudRepository<TrainingPair, Long> {

    void deleteByGroupId(UUID groupId);
}
