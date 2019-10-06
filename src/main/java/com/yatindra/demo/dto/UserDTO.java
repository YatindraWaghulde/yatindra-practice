package com.yatindra.demo.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yatindra.demo.constant.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 9067919025422558883L;

	@Id
	@GeneratedValue
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
