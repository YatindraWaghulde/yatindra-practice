package com.yatindra.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.yatindra.demo.constant.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 9067919025422558883L;

	private Long userSeq;

	@NotNull(message = Constants.USER_NAME_REQUIRED)
	@Size(min = 2, max = 20, message = Constants.USER_NAME_LENGTH)
	private String userName;

	@NotNull(message = Constants.PASSWORD_REQUIRED)
	@Size(min = 2, max = 15, message = Constants.PASSWORD_LENGTH)
	private String password;

	@NotNull(message = Constants.USER_TYPE_REQUIRED)
	private String userType;


}
