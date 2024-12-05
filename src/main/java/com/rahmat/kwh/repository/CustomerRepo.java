package com.rahmat.kwh.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rahmat.kwh.model.Customer;

@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Long>{
//	List<Customer> findByCust_name(String cust_name);
//	List<Customer> findByCust_contact(String cust_contact);
//	List<Customer> findByCust_status(Boolean cust_status);
}
