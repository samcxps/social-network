package application;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
    
}
