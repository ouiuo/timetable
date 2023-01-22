package com.ouiuo.timetable.dao;

import com.ouiuo.timetable.model.TrainingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<TrainingPair, Long> {

}
