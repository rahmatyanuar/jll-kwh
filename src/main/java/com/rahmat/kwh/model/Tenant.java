package com.rahmat.kwh.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table( name = "tenant",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "tnt_name")
})
@Data
public class Tenant {
	@Id
	@NotBlank
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tnt_id;
	
	@NotBlank
	@Size(max = 100)
	private String tnt_name;
	
	@NotBlank
	@Size(max = 20)
	private String loc_name; 
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "tenant_customer", 
				joinColumns = @JoinColumn(name = "tnt_id"),
				inverseJoinColumns = @JoinColumn(name = "cust_id"))
	private Set<Customer> roles = new HashSet<>();
	
	@Column
	private Boolean tnt_status = false;
	
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
