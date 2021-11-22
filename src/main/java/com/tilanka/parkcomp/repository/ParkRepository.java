package com.tilanka.parkcomp.repository;

import com.tilanka.parkcomp.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

}
