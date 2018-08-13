package servlets.exception;

public class ServletException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
     * Constructeurs
     */
    public ServletException( String message ) {
        super( message );
    }

    public ServletException( String message, Throwable cause ) {
        super( message, cause );
    }

    public ServletException( Throwable cause ) {
        super( cause );
    }

}
