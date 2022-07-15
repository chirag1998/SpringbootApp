package com.xoriant.pojo;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolesPOJO {
	
	@NotNull
	private String username;

	@NotNull
	private String[] roles;

}
