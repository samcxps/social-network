package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph implements GraphADT {

  /**
   * Define people Map<String, List<String>>
   * 
   * The key will be the Person and the value will be an LinkedList of Person objects 
   * which will be the key's (person's) friends
   */
  private Map<Person, List<Person>> people;
  
  /*
   * Default no-argument constructor
   * 
   * All this does is initialize the HashMap
   */
  public Graph() {
    this.people = new HashMap<Person, List<Person>>();
  }
  
  /**
   * Add new person to the graph.
   *
   * If person is null or already exists, method ends without adding a person or throwing an
   * exception.
   * 
   * Valid argument conditions: 
   *    1. person is non-null 
   *    2. person is not already in the graph
   * 
   * @param Person person to add to graph
   */
  @Override
  public void addPerson(Person person) {
    // check if person is null or if person already exists
    if (person == null || this.people.containsKey(person)) {
      return;
    }
    
    // add new person to HashMap
    this.people.put(person, new LinkedList<Person>());
  }

  /**
   * Remove a person and all associated friendships from the graph.
   * 
   * If person is null or does not exist, method ends without removing a person, friendship, or throwing
   * an exception.
   * 
   * Valid argument conditions: 
   *    1. person is non-null 
   *    2. person is not already in the graph
   * 
   * @param Person person to remove from graph
   */
  @Override
  public void removePerson(Person person) {
    // check if person is null or if person does not exist
    if (person == null || !this.people.containsKey(person)) {
      return;
    }

    // iterate through all people and remove friendships with specified person
    for (Person p : this.people.keySet()) { // uses keySet() to get all keys in a Set so we can
                                            // use enhanced for loop
      this.people.get(p).remove(p);
    }

    // remove vertex from graph
    this.people.remove(person);
  }

  /**
   * Add the friendship from person1 to person2 to this graph.
   * 
   * If either person does not exist, add person, and add friendship, no exception is thrown. 
   * If the edge friendship in the graph, no friendship is added and no exception is thrown.
   * 
   * Valid argument conditions: 
   *    1. neither person is null 
   *    2. both persons are in the graph 
   *    3. the friendship is not in the graph
   * 
   * @param Person person1 person to add friendship from
   * @param Person person2 person to add friendship to
   */
  @Override
  public void addFriendship(Person person1, Person person2) {
    // make sure both persons are not null
    if (person1 == null || person2 == null) {
      return;
    }
    
    // check if person1 is not in graph and add if necessary
    if (!this.people.containsKey(person1)) {
      this.addPerson(person1);
    }
    
    // check if person2 is not in graph and add if necessary
    if (!this.people.containsKey(person2)) {
      this.addPerson(person2);
    }
    
    // we need to add both friendship from person1 -> person2 and person2 -> person1
    // because it is an undirected graph
    
    // check friendship person1 -> person2 and add if it does not exist
    if (!this.people.get(person1).contains(person2)) {
      this.people.get(person1).add(person2);
    }
    
    // check friendship person2 -> person1 and add if it does not exist
    if (!this.people.get(person2).contains(person1)) {
      this.people.get(person2).add(person1);
    }
  }

  /**
   * Remove the friendship from person1 to person2 from this graph.
   * 
   * If either person does not exist, or if a friendship from person1 to person2 does not exist, no friendship is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 
   *    1. neither person is null 
   *    2. both friendships are in the graph 
   *    3. the edge from person1 to person2 is in the graph
   * 
   * @param Person person1 person to remove friendship from
   * @param Person person2 person to remove friendship of
   */
  @Override
  public void removeEdge(Person person1, Person person2) {
    // make sure both persons are not null
    if (person1 == null || person2 == null) {
      return;
    }
    
    // check if person1 and person2 are in graph, return if they are not
    if (!this.people.containsKey(person1) || !this.people.containsKey(person1)) {
      return;
    }
    
    // we need to remove both friendship from person1 -> person2 and person2 -> person1
    // because it is an undirected graph
    
    // check friendship person1 -> person2 and remove if it exists
    if (this.people.get(person1).contains(person2)) {
      this.people.get(person1).remove(person2);
    }
    
    // check friendship person1 -> person2 and remove if it exists
    if (this.people.get(person2).contains(person1)) {
      this.people.get(person2).remove(person1);
    }
  }

  /**
   * Returns a Set that contains all the people in graph
   * 
   * @return Set<Person> set of people
   */
  @Override
  public Set<Person> getAllPeople() {
    return this.people.keySet();
  }

  /**
   * Get all the friends of a person
   * 
   * @param Person person the person you want to get friends of
   * 
   * @return List<Person> list of friends
   */
  @Override
  public List<Person> getFriendsOf(Person person) {
    return this.people.get(person);
  }

  /**
   * Returns the number of friendships in this graph.
   * 
   * @return int the number of friendships
   */
  @Override
  public int size() {
    int edges = 0;
    
    // iterate through all vertices and sum the amount of edges for each
    for (Person p: this.people.keySet()) {
      edges += this.people.get(p).size();
    }
    
    return edges;
  }

  /**
   * Returns the number of people in this graph.
   * 
   * @return int the number of people
   */
  @Override
  public int order() {
    return this.people.size();
  }
  
  
  
}
