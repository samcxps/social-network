package application;

/**
 * 
 * @author samsoncain
 */
@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception {

  /**
   * Default no-arg constructor 
   */
  public InvalidUsernameException() {}
  
  /**
   * Constructor with ability to use custom error message
   * 
   * @param errorMessage custom error message
   */
  public InvalidUsernameException(String errorMessage) {
    super(errorMessage);
  }
  
}