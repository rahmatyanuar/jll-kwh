package com.rahmat.kwh.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@NoArgsConstructor(force = true)
@Data
@Accessors(fluent = true, chain = true)
@RedisHash("AccountModel")
public class Account implements Serializable {
	public static final long serialVersionUID = 1L;
	@NonNull
	public UUID uuid = UUID.randomUUID();

	@NonNull
	public enum Gender {
		MALE, FEMALE, NON_BINARY
	}
	
	public Gender gender;

	@NonNull
	public static Boolean isActive = false;
	@Id
	@NonNull
	public String username;
	@NonNull
	public String firstName;
	@NonNull
	public String email;
	public String lastName;
	public String birthPlace;
	public String birthDate;
	public String password;
	public String passwordExpiry;
	public Boolean approved = false;
	public String bankName;
	public String bankAccountNo;
	public String motherMaidenName;
	public String address;
	public String mobile;
	public Integer priority = 0;
	public String approverUsername;
	public String registrationDateTime = LocalDateTime.now().toString();
	public String approvalDateTime;
	public String updateDateTime;
}
