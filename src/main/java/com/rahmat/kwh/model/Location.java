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
@Table(	name = "location", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "loc_name"),
		})
@Data
public class Location {
	@Id
	@NotBlank
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String loc_name;
	
	@NotBlank
	@BooleanFlag
	private Boolean loc_status = false;
	
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
