package application;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

  /**
   * Default no-arg constructor 
   */
  public UserNotFoundException() {}

  /**
   * Constructor with ability to use custom error message
   * 
   * @param errorMessage custom error message
   */
  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
    
}
