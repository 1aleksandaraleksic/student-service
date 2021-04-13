package it.engineering.faculty.exception;

public class ApiRequestHandler extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ApiRequestHandler() {
        super();
    }

    public ApiRequestHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiRequestHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestHandler(String message) {
        super(message);
    }

    public ApiRequestHandler(Throwable cause) {
        super(cause);
    }
}
