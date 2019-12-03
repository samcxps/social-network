package application;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Filename:   SocialNetworkADT.java
 * Authors:    Samson Cain
 * 
 * A simple social network interface
 */
public interface SocialNetworkADT {

    /**
     * Add new person to the network.
     *
     * If person is null or already exists,
     * method ends without adding a person or 
     * throwing an exception.
     * 
     * @param user the user to be added
     * 
     * @throws InvalidUsernameException if username is invalid
     * @throws UserAlreadyExistsException is user already exists
     */
    public boolean addUser(String user) throws InvalidUsernameException, UserAlreadyExistsException;

    
    /**
     * Remove a person and all associated 
     * friendships from the network.
     * 
     * If person is null or does not exist,
     * method ends without removing a person, friendships, 
     * or throwing an exception.
     * 
     * @param user the user to be removed
     * 
     * @throws UserNotFoundException if user does not exist
     */
    public boolean removeUser(String user) throws UserNotFoundException;

    
    /**
     * Add a friendship between user1 and user2
     *
     * If the friendship exists in the network,
     * no friendship is added and no exception is thrown.
     *  
     * @param user1 the first user
     * @param user2 the second user
     * 
     * @throws UserNotFoundException if a user does not exist
     */
    public boolean addFriend(String user1, String user2) throws UserNotFoundException;

    
    /**
     * Remove a friendship between user1 and user2
     * 
     * If either person does not exist,
     * or if a friendship from user1 to user2 does not exist,
     * no friendship is removed and no exception is thrown.
     *  
     * @param user1 the first user
     * @param user2 the second user
     * 
     * @throws UserNotFoundException if a user does not exist
     */
    public boolean removeFriend(String user1, String user2) throws UserNotFoundException;
    
        
    /**
     * Returns a Set that contains all the users in network
     * 
     * @return a Set<Person> which contains all the users in the network
     */
    public Set<Person> getAllUsers();
    
    
    /**
     * Get all the friends of a user
     * 
     * @param user the specified user
     * 
     * @return a List<Person> of all the friends of the specified person
     */
    public List<Person> getFriendsOf(String user);
    
    /**
     * Get all of the mutual friends between two users
     * 
     * @param user1 the first user
     * @param user2 the second user
     * 
     * @return List<Person> list of the mutual friends between to two users
     */
    public List<Person> getMutualFriends(String user1, String user2);
    
    /**
     * 
     * @return
     */
    public List<Person> getShortestPath();
    
    /**
     * 
     * @return
     */
    public Set<Graph> getConnectedComponents();
    
    /**
     * Loads a file and constructs a social network from the file
     * 
     * @param file the file to load from
     */
    public void loadNetworkFromFile(File file);
    
    /**
     * Saves a social network to a file
     * 
     * @param file the file to save to
     */
    public void saveNetworkToFile(File file);

}
