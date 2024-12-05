package com.rahmat.kwh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahmat.kwh.model.Location;

public interface LocationRepo extends JpaRepository<Location, Long>{
//	List<Location> findByLoc_name(String loc_name);
}
