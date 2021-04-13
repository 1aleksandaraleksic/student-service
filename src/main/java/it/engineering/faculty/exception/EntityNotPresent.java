package it.engineering.faculty.exception;

public class EntityNotPresent extends MyApplicationException {

	private static final long serialVersionUID = 1L;
	
	private final Object entity;

	public EntityNotPresent(Object entity, String message) {
		super(message);
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}

		
}
