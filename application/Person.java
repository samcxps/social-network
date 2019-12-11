package application;

/**
 * Represents a person used in the social network
 * 
 * @author samsoncain
 */
public class Person {
  
  /**
   * Person objects username
   */
  private String username;
  
  /**
   * Person objects visited status
   */
  private Boolean visited;
  
  /**
   * Only constructor. Takes the username as a String.
   * 
   * @param username username for the Person
   */
  public Person(String username) {
    this.username = username;
  }
  
  /**
   * Returns this Person objects username
   * 
   * @return objects username
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Get visited status
   * 
   * @return true if visited, false if not
   */
  public Boolean getVisited() {
    return visited;
  }

  /**
   * Set visited status
   * 
   * @param visited
   */
  public void setVisited(Boolean visited) {
    this.visited = visited;
  }

}
