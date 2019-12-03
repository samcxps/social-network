package application;

public class TestClass {

  static SocialNetwork network;
  
  
  public static void main(String[] args) {
    network = new SocialNetwork();
    
    try {
      System.out.println(network.addUser("sam"));
      System.out.println(network.addUser("chris"));
      
      System.out.println(network.getAllUsers().size());
      
      System.out.println(network.addFriend("sam", "chris"));
      
      System.out.println(network.getAllUsers().size());
    } catch (InvalidUsernameException | UserAlreadyExistsException | UserNotFoundException e) {
      System.out.println(e);
    }
    

  }
  

}
