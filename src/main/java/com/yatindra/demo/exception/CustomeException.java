package com.yatindra.demo.exception;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CustomeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3345832140100102874L;
	
	private int errCode;
	private List<String> errMessages;
}
