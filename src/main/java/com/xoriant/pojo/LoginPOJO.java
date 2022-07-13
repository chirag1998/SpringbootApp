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
public class LoginPOJO {

	@NotNull
	private String emailid;

	@NotNull
	private String password;
}
