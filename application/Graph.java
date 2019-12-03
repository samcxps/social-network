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
   * @param Person person to add to graph
   * 
   * @return true if node was added, false if not

   */
  @Override
  public boolean addNode(Person person) {
    // check if person is null or if person already exists
    if (person == null || this.people.containsKey(person)) {
      return false;
    }
    
    // add new person to HashMap
    this.people.put(person, new LinkedList<Person>());
    
    return true;
  }

  /**
   * Remove a person and all associated friendships from the graph.
   * 
   * @param Person person to remove from graph
   * 
   * @return true if node was removed, false if not
   */
  @Override
  public boolean removeNode(Person person) {
    // check if person is null or if person does not exist
    if (person == null || !this.people.containsKey(person)) {
      return false;
    }

    // iterate through all people and remove friendships with specified person
    for (Person p : this.people.keySet()) { // uses keySet() to get all keys in a Set so we can
                                            // use enhanced for loop
      this.people.get(p).remove(p);
    }

    // remove vertex from graph
    this.people.remove(person);
    
    return true;
  }

  /**
   * Add an edge (friendship) from person1 to person2 to this graph.
   * 
   * @param Person person1 person to add friendship from
   * @param Person person2 person to add friendship to
   * 
   * @return true if edge was added, false if not
   */
  @Override
  public boolean addEdge(Person person1, Person person2) {
    // make sure both persons are not null
    // should be impossible but still good to check
    if (person1 == null || person2 == null) {
      return false;
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
    
    return true;
  }

  /**
   * Remove the friendship from person1 to person2 from this graph.
   * 
   * Valid argument conditions: 
   *    1. neither person is null 
   *    2. both friendships are in the graph 
   *    3. the edge from person1 to person2 is in the graph
   * 
   * @param Person person1 person to remove friendship from
   * @param Person person2 person to remove friendship of
   * 
   * @return true if edge was removed, false if not
   */
  @Override
  public boolean removeEdge(Person person1, Person person2) {
    // make sure both persons are not null
    // should be impossible but still good to check
    if (person1 == null || person2 == null) {
      return false;
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
    
    return true;
  }
  
  /**
   * TODO: I don't know what this method is for
   */
  public Set<Person> getNeighbors(Person person) {
    return null;
  }
  
  /**
   * Returns the Person object for a specified node
   * 
   * @param user the username of the node to get
   * 
   * @return Person the person object of the node
   */
  public Person getNode(String user) {    
    // get person object from graph with specified username
    // if they exist
    for (Person p: this.people.keySet()) {
      if (p.getUsername().equals(user)) {
        return p;
      }
    }
    
    return null;
  }

  /**
   * Returns a Set that contains all the nodes in graph
   * 
   * @return Set<Person> set of nodes
   */
  @Override
  public Set<Person> getAllNodes() {
    return this.people.keySet();
  }

  /**
   * Returns the number of edges in this graph.
   * 
   * @return int the number of edges
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
   * Returns the number of nodes in this graph.
   * 
   * @return int the number of nodes
   */
  @Override
  public int order() {
    return this.people.size();
  }
  
}
