package com.yatindra.demo.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(CustomeException.class)
    public ResponseEntity<ExceptionDTO> customHandleException(CustomeException ex, WebRequest request) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(LocalDateTime.now(),ex.getErrCode(),ex.getErrMessages());
        HttpHeaders headers = new HttpHeaders(); 

        return new ResponseEntity<>(exceptionDTO,headers,getHttpStatus(ex.getErrCode()));

    }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage())
				.collect(Collectors.toList());

		ExceptionDTO exceptionDTO = new ExceptionDTO(LocalDateTime.now(), status.value(), errors);

		return new ResponseEntity<>(exceptionDTO, headers, getHttpStatus(status.value()));

	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleExceptionALL(Exception ex, WebRequest request) {
		log.error("### Exception occur",ex);
		ExceptionDTO exceptionDTO = new ExceptionDTO(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(),Arrays.asList("Internar Error."));
		HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(exceptionDTO,headers,getHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()));

    }
	
//	@Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers,
//                                                                  HttpStatus status, WebRequest request) {
//
//        Map<String, Object> body = new LinkedHashMap();
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
//
//        //Get all errors
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, headers, status);
//
//    }
//	@ExceptionHandler({
//
//	})
//	public final ResponseEntity<Object> handleCustomeException(Exception ex, WebRequest request) {
//		HttpHeaders headers = new HttpHeaders();
//		ExceptionDTO exceptionDTO = new ExceptionDTO();
//		if (ex instanceof CustomeException) {
//			exceptionDTO = new ExceptionDTO(new Date(), ((CustomeException) ex).getErrCode(),
//					((CustomeException) ex).getErrMessage());
//			return new ResponseEntity<Object>(exceptionDTO, headers, HttpStatus.NOT_FOUND);
//		}
//		else if (ex instanceof NotFoundException) {
//			exceptionDTO = new ExceptionDTO(new Date(), HttpStatus.NOT_FOUND.value(),
//					((CustomeException) ex).getErrMessage());
//			return new ResponseEntity<Object>(exceptionDTO, headers, HttpStatus.NOT_FOUND);
//		}
//		
//		exceptionDTO.setTimeStamp(new Date());
//		exceptionDTO.setErrCode("SYSTEM_ERROR");
//		exceptionDTO.setErrMessage("System Error.");
//		return new ResponseEntity<Object>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	
	private HttpStatus getHttpStatus(int code) {
		switch(code) {
		
		 case 400:
			 return HttpStatus.BAD_REQUEST;
		 case 403:
			 return HttpStatus.FORBIDDEN;
		 case 404:
			 return HttpStatus.NOT_FOUND;
		 case 201:
			 return HttpStatus.CREATED;
		 default:
			 return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
	}
}
