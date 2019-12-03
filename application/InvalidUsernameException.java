package application;


@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception {

  public InvalidUsernameException() {}
  
  public InvalidUsernameException(String errorMessage) {
    super(errorMessage);
  }
    
}