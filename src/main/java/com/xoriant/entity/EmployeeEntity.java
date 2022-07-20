package com.xoriant.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity {
	
	public EmployeeEntity(String firstName2, String lastName2, String email2, LocalDate date2) {
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.email = email2;
		this.date = date2;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "date_of_birth")
	private LocalDate date; 
//	{
//	    "myDateTime": "2018-12-10T13:45:00.000Z"
//	}

}
