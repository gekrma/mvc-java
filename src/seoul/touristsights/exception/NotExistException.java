package seoul.touristsights.exception;

public class NotExistException extends Exception {
	public NotExistException() {}
	
	public NotExistException( String msg ) {
		super( msg );
	}
}
