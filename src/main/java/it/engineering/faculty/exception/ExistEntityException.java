package it.engineering.faculty.exception;

public class ExistEntityException extends MyApplicationException {

	private static final long serialVersionUID = 1L;
	
	private final Object entity;
	
	public ExistEntityException(String message, Object entity) {
		super(message);
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}
}
