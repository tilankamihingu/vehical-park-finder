package com.tilanka.parkcomp.repository;

import com.tilanka.parkcomp.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {
    @Query("select s from Park s where name like %?1%")
    List<Park> findByName(String name);
}
