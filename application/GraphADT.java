package application;

import java.util.List;
import java.util.Set;

/**
 * Filename:   GraphADT.java
 * Authors:    Samson Cain
 * 
 * A simple graph interface for use in the Social Network Viewer
 * 
 * Based off of Deppeler's GraphADT but edited for this project
 */
public interface GraphADT {

    /**
     * Add new person to the graph.
     *
     * If person is null or already exists,
     * method ends without adding a person or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. person is non-null
     * 2. person is not already in the graph 
     * 
     * @param person the person to be added
     */
    public void addPerson(Person person);

    
    /**
     * Remove a person and all associated 
     * friendships from the graph.
     * 
     * If person is null or does not exist,
     * method ends without removing a person, friendships, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. person is non-null
     * 2. person is not already in the graph 
     *  
     * @param vertex the vertex to be removed
     */
    public void removePerson(Person person);

    
    /**
     * Add the friendship from person1 to person2
     * to this graph. (edge is undirected and unweighted)
     *
     * If the friendship exists in the graph,
     * no friendship is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither person is null
     * 2. both persons are in the graph 
     * 3. the friendship is not in the graph
     *  
     * @param person1 the first person
     * @param person2 the second person
     */
    public void addFriendship(Person person1, Person person2);

    
    /**
     * Remove the friendship from person1 to person2
     * from this graph.  (edge is undirected and unweighted)
     * 
     * If either person does not exist,
     * or if an edge from person1 to person2 does not exist,
     * no friendship is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither person is null
     * 2. both persons are in the graph 
     * 3. the friendship from person1 to person2 is in the graph
     *  
     * @param person1 the first vertex
     * @param person2 the second vertex
     */
    public void removeEdge(Person person1, Person person2);
    
        
    /**
     * Returns a Set that contains all the people in graph
     * 
     * @return a Set<Person> which contains all the people in the graph
     */
    public Set<Person> getAllPeople();
    
    
    /**
     * Get all the friends of a person
     * 
     * @param person the specified person
     * 
     * @return a List<Person> of all the friends of the specified person
     */
    public List<Person> getFriendsOf(Person person);
    

    /**
     * Returns the number of friendships in this graph.
     * 
     * @return number of friendships in the graph.
     */
    public int size();
    
    
    /**
     * Returns the number of people in this graph.
     * 
     * @return number of people in graph.
     */
    public int order();

}
