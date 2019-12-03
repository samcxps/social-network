package application;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class SocialNetwork implements SocialNetworkADT {
  
  Graph network;
  
  public SocialNetwork() {
    this.network = new Graph();
  }

  /**
   * Makes sure characters besides letters, numbers, underscores, and apostrophes
   * are not used in the username
   * 
   * @param user to check username of
   * 
   * @return true if username is valid, false if not
   * @throws InvalidUsernameException 
   */
  private boolean validateUsername(String user) throws InvalidUsernameException {
    // make sure username provided is not null
    if (user == null || user == "") {
      throw new InvalidUsernameException("Username cannot be blank");
    }

    return user.matches("^[a-zA-Z0-9_']*$");
  }
  
  /**
   * Add a user to the social network
   * 
   * @param user the user to be added
   * 
   * @return true if user was added, false if not
   * 
   * @throws InvalidUsernameException if username is invalid
   * @throws UserAlreadyExistsException if user already exists
   */
  @Override
  public boolean addUser(String user) throws InvalidUsernameException, UserAlreadyExistsException {
    // make sure user does not already exist
    if (this.network.getNode(user) != null) {
      throw new UserAlreadyExistsException();
    }
    
    // make sure username is valid and add it
    if (this.validateUsername(user)) {
      return this.network.addNode(new Person(user));
    } else { // throw invalid username if username is bad
      throw new InvalidUsernameException("Username can only contain letters, digits, underscores, and apostrophes.");
    }
  }

  /**
   * Remove a user from the social network
   * 
   * @param user the user to be removed
   * 
   * @return true if user was removed, false if not
   * @throws UserNotFoundException if user does not exist
   */
  @Override
  public boolean removeUser(String user) throws UserNotFoundException {
    // get Person object from graph
    Person person = this.network.getNode(user);
    
    // make sure user exists
    if (person == null) {
      throw new UserNotFoundException();
    }
    
    // remove it
    return this.network.removeNode(person);
  }

  /**
   * Add a friendship between user1 and user2
   *
   * If the friendship exists in the network,
   * no friendship is added and no exception is thrown.
   *  
   * @param user1 the first user
   * @param user2 the second user
   * 
   * @throws UserNotFoundException if one of the users does not exist
   */
  @Override
  public boolean addFriend(String user1, String user2) throws UserNotFoundException {
    // get Person object from graph for user1
    Person person1 = this.network.getNode(user1);
    if (person1 == null) {
      throw new UserNotFoundException("User " + "'" + user1 + "'" + " does not exist");
    }
    
    // get Person object from graph for user2
    Person person2 = this.network.getNode(user2);
    if (person2 == null) {
      throw new UserNotFoundException("User " + "'" + user2 + "'" + " does not exist");
    }
    
    // add friendship
    return this.network.addEdge(person1, person2);
  }

  /**
   * Remove a friendship between user1 and user2
   * 
   * If either person does not exist,
   * or if a friendship from user1 to user2 does not exist,
   * no friendship is removed and UserNotFoundException is thrown 
   *  
   * @param user1 the first user
   * @param user2 the second user
   * 
   * @throws UserNotFoundException if a user does not exist
   */
  @Override
  public boolean removeFriend(String user1, String user2) throws UserNotFoundException {
    // get Person object from graph for user1
    Person person1 = this.network.getNode(user1);
    if (person1 == null) {
      throw new UserNotFoundException("User " + "'" + user1 + "'" + " does not exist");
    }
    
    // get Person object from graph for user2
    Person person2 = this.network.getNode(user2);
    if (person2 == null) {
      throw new UserNotFoundException("User " + "'" + user2 + "'" + " does not exist");
    }
    
    // remove friendship
    return this.network.removeEdge(person1, person2);
  }

  /**
   * Returns a Set that contains all the users in network
   * 
   * @return a Set<Person> which contains all the users in the network
   */
  @Override
  public Set<Person> getAllUsers() {
    return this.network.getAllNodes();
  }

  /**
   * Get all the friends of a user
   * 
   * @param user the specified user
   * 
   * @return a List<Person> of all the friends of the specified person
   */
  @Override
  public List<Person> getFriendsOf(String user) {
    Person person = this.network.getNode(user);
    return null;
  }

  @Override
  public List<Person> getMutualFriends(String user1, String user2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void loadNetworkFromFile(File file) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void saveNetworkToFile(File file) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Person> getShortestPath() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Graph> getConnectedComponents() {
    // TODO Auto-generated method stub
    return null;
  }

}
