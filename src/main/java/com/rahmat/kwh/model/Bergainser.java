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

import jdk.jfr.BooleanFlag;
import lombok.Data;

@Entity
@Table(	name = "bergainser", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "bgr_name")
		})
@Data
public class Bergainser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bgr_id;
	
	@NotBlank
	@Size(max = 20)
	private String bgr_name;
	
	@NotBlank
	@Size(max = 20)
	private String bgr_type;
	
	@NotBlank
	@BooleanFlag
	private Boolean bgr_status = false;
	
	@NotBlank
	private Timestamp insert_dt = new Timestamp(System.currentTimeMillis());
	
	@NotBlank
	@Size(max = 20)
	private String insert_by;
	
	@Size(max = 20)
	private String update_by;
	
	@Column
	private Timestamp update_dt;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "bergainser_raiser", 
				joinColumns = @JoinColumn(name = "bgr_id"),
				inverseJoinColumns = @JoinColumn(name = "rs_id"))
	private Set<Raiser> raiser = new HashSet<>();
}
