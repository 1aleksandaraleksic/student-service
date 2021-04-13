package it.engineering.faculty.exception;

public class MyApplicationException extends Exception{

	private static final long serialVersionUID = 1L;

	public MyApplicationException(String message) {
		super(message);
	}
}
