package com.uur.vetmanagement.repository;

import com.uur.vetmanagement.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {
}
