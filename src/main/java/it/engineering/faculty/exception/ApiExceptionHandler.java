package it.engineering.faculty.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ApiExceptionHandler extends Throwable{

	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler({ExistEntityException.class})
    private ResponseEntity<ApiException> handleException(Exception exception){
		
        ApiException error =
                new ApiException(
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(),
                        System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler
    private ResponseEntity<ApiException> handleException(NoSuchElementException exception){
		
        ApiException error =
                new ApiException(
                        HttpStatus.CONFLICT.value(),
                        exception.getMessage(),
                        System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    
    @SuppressWarnings("deprecation")
	@ExceptionHandler()
    private ResponseEntity<ApiException> handleException(MethodArgumentTypeMismatchException exception){

		ApiException error =
                new ApiException(
                		HttpStatus.METHOD_FAILURE.value(),
                        exception.getMessage(),
                        System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.METHOD_FAILURE);

    }


}
