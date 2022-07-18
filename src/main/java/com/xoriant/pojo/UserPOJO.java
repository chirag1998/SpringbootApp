package com.xoriant.pojo;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPOJO {

	private String displayName;
	@NotNull(message = "Display name field is required")
	private String userName;
	@NotNull(message = "Display name field is required")
	private String password;
	private String email;
	private String mobileNumber;
	private String[] roles;

}
