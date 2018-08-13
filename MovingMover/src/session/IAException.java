package session;

public class IAException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
     * Constructeurs
     */
	
	public IAException() {
        super("");
    }
	
    public IAException( String message ) {
        super( message );
    }

    public IAException( String message, Throwable cause ) {
        super( message, cause );
    }

    public IAException( Throwable cause ) {
        super( cause );
    }

}
