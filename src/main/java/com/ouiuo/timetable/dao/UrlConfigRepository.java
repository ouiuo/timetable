package com.ouiuo.timetable.dao;

import com.ouiuo.timetable.model.UrlConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlConfigRepository extends JpaRepository<UrlConfig, Long> {
    Optional<UrlConfig> findByCode(Integer code);
}
