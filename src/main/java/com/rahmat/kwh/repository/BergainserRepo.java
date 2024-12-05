package com.rahmat.kwh.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rahmat.kwh.model.Bergainser;

@Transactional
public interface BergainserRepo extends JpaRepository<Bergainser, Long>{
//	List<Bergainser> findByBgr_name(String bgr_name);
//	List<Bergainser> findByBgr_status(Boolean bgr_status);
//	List<Bergainser> findByBgr_type(String bgr_type);
}
