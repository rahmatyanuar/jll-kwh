package com.rahmat.kwh.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jdk.jfr.BooleanFlag;
import lombok.Data;

@Entity
@Table(	name = "customer", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "cust_name"),
	@UniqueConstraint(columnNames = "cust_contact")
})
@Data
public class Customer {
	@Id
	@NotBlank
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cust_id;
	
	@NotBlank
	@Size(max = 100)
	private String cust_name;
	
	@NotBlank
	@Size(max=50)
	private String cust_contact;
	
	@NotBlank
	@BooleanFlag
	private Boolean cust_status = false;
	
	@NotBlank
	private Timestamp insert_dt = new Timestamp(System.currentTimeMillis());
	
	@Column
	private Timestamp update_dt;
	
	@NotBlank
	@Size(max = 20)
	private String insert_by;
	
	@Size(max = 20)
	private String update_by;
}
