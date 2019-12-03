package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
  private static Graph graph;

  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 500;
  private static final String APP_TITLE = "Social Network Viewer";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Set stage title
    primaryStage.setTitle(APP_TITLE);
    
    // Create new Graph
    // DOES NOTHING RIGHT NOW
    graph = new Graph();
    
    // Create Vertical box for main screen layout
    VBox mainPane = createMainPane();
    
    // Create scene, add the main pane, and add the scene to our stage
    Scene scene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    
    // Show stage
    primaryStage.show();
  }
  
  private VBox createMainPane() {
    VBox main = new VBox();
    
    // Create and add toolbar (HBox) to top
    main.getChildren().add(createToolBar());
    
    // Create and add Label for current users first and last name
    main.getChildren().add(createNameLabel());
    
    // Create and add ScrollPane to the main pane to hold the Graph
    main.getChildren().add(createScrollPane());
    
    // Create and add counter for friends
    main.getChildren().add(createFriendCounter());
    
    // Create and add button for managing friends
    main.getChildren().add(createFriendManagementButton());
   
    return main;
  }

  private HBox createToolBar() {
    HBox main = new HBox();
    
    main.setPadding(new Insets(20, 12, 15, 12));
    main.setSpacing(50);
    main.setStyle("-fx-background-color: #336699");
    
    Button settingsButton = new Button("Settings");
    settingsButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        createSettingsWindow();
      }

    });
    // I made a method to create the drop shadow effect on buttons to clean up code
    createStylizedButton(settingsButton);
    
    settingsButton.setMaxWidth(Double.MAX_VALUE);
    HBox.setHgrow(settingsButton, Priority.ALWAYS);
    
    main.getChildren().add(settingsButton);
    return main;
  }

  private void createSettingsWindow() {    
    // Set stage title
    Stage mainStage = new Stage();
    mainStage.setTitle("Settings");

    // Use vertical box as main pane
    VBox mainPane = new VBox();
    mainPane.setSpacing(10);
    
    //////////////////////////////////////////////////////////
    //              Clear Graph Button Code                 //
    //////////////////////////////////////////////////////////
    Button clearGraphButton = new Button("Clear Graph");
    clearGraphButton.setMaxWidth(Main.WINDOW_WIDTH);
    createStylizedButton(clearGraphButton);
    //////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////
    //              Load File Button Code                   //
    //////////////////////////////////////////////////////////
    Button loadFileButton = new Button("Load from File");
    loadFileButton.setMaxWidth(Main.WINDOW_WIDTH);
    createStylizedButton(loadFileButton);
    //////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////
    //                 Export Button Code                   //
    //////////////////////////////////////////////////////////
    Button exportButton = new Button("Export to File");
    exportButton.setMaxWidth(Main.WINDOW_WIDTH);
    createStylizedButton(exportButton);
    //////////////////////////////////////////////////////////
    
    // Add buttonHbox to mainPane Vbox
    mainPane.getChildren().addAll(clearGraphButton, loadFileButton, exportButton);
    
    // Create scene, add the main pane, and add the scene to our stage
    Scene scene = new Scene(mainPane, 450, 100);
    mainStage.setScene(scene);
    
    // Show stage
    mainStage.show();
  }

  private HBox createNameLabel() {
    HBox main = new HBox();
    
    main.setPadding(new Insets(20, 12, 15, 12));
    
    Label nameLabel = new Label("Welcome Samson Cain");
    
    main.getChildren().add(nameLabel);
    return main;
  }

  private ScrollPane createScrollPane() {
    ScrollPane main = new ScrollPane();
    main.setFitToHeight(true);
    main.setPrefHeight(this.WINDOW_HEIGHT);
    return main;
  }
  
  private HBox createFriendCounter() {
    HBox main = new HBox();
    
    main.setPadding(new Insets(20, 12, 15, 12));
    
    Label nameLabel = new Label("You currently have 32 friends");
    
    main.getChildren().add(nameLabel);
    return main;
  }
  
  private HBox createFriendManagementButton() {
    HBox main = new HBox();
    
    main.setPadding(new Insets(20, 12, 15, 12));
    main.setSpacing(50);
    main.setStyle("-fx-background-color: #336699");
    
    Button friendManagementButton = new Button("View All Friends");
    friendManagementButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        createFriendsListWindow();
      }

    });
    createStylizedButton(friendManagementButton);
    
    friendManagementButton.setMaxWidth(Double.MAX_VALUE);
    HBox.setHgrow(friendManagementButton, Priority.ALWAYS);
    
    main.getChildren().add(friendManagementButton);
    return main;
  }
  
  private void createFriendsListWindow() {
    // Set stage title
    Stage mainStage = new Stage();
    mainStage.setTitle("Friends List");

    // Use vertical box as main pane
    VBox mainPane = new VBox();
    
    //////////////////////////////////////////////////////////
    //             Friends List Toolbar Code                //
    //////////////////////////////////////////////////////////
    HBox buttonHbox = new HBox();
    buttonHbox.setPadding(new Insets(20, 12, 15, 12));
    buttonHbox.setSpacing(50);
    buttonHbox.setStyle("-fx-background-color: #336699");
    
    Button addFriendButton = new Button("Add Friend");
    Button removeFriendButton = new Button("Remove Friend");
    Button openFriendButton = new Button("Open Friend");
    
    createStylizedButton(addFriendButton);
    createStylizedButton(removeFriendButton);
    createStylizedButton(openFriendButton);
    
    addFriendButton.setMaxWidth(Double.MAX_VALUE);
    removeFriendButton.setMaxWidth(Double.MAX_VALUE);
    openFriendButton.setMaxWidth(Double.MAX_VALUE);

    HBox.setHgrow(addFriendButton, Priority.ALWAYS);
    HBox.setHgrow(removeFriendButton, Priority.ALWAYS);
    HBox.setHgrow(openFriendButton, Priority.ALWAYS);

    
    // Add clearGraphButton to HBox
    buttonHbox.getChildren().addAll(addFriendButton, removeFriendButton, openFriendButton);
    //////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////
    //            Friends List Scrollable Code              //
    //////////////////////////////////////////////////////////
    ListView listView = new ListView();
    for(int i = 0; i < 100; i++) {
      listView.getItems().add("Item" + i);
    }
    //////////////////////////////////////////////////////////
    
    // Add buttonHbox and scrollable list to mainPane Vbox
    mainPane.getChildren().addAll(buttonHbox, listView);
    
    // Create scene, add the main pane, and add the scene to our stage
    Scene scene = new Scene(mainPane, 450, 450);
    mainStage.setScene(scene);
    
    // Show stage
    mainStage.show();
  }
  
  /**
   * Adds style to a selected Button object
   * 
   * Made this into a separate method to clean up code and avoid reusing the same code
   * for every new button
   * 
   * @param button to add style to
   */
  private void createStylizedButton(Button button) {
    DropShadow shadow = new DropShadow();
    button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent> () {

      @Override
      public void handle(MouseEvent arg0) {
        button.setEffect(shadow);
      }
      
    });
    button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent> () {

      @Override
      public void handle(MouseEvent arg0) {
        button.setEffect(null);
      }
      
    });
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch();
  }
}
