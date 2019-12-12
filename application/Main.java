package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author samsoncain
 */
public class Main extends Application {

	private static SocialNetwork socialNetwork = new SocialNetwork();

	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 500;
	private static final String APP_TITLE = "Social Network Viewer";
	private String currentUser; // TODO
	Stage firstStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		firstStage = primaryStage; // TODO

		// Set stage title
		primaryStage.setTitle(APP_TITLE);

		// Create Vertical box for main screen layout
		VBox mainPane = createMainPane();

		// Create scene, add the main pane, and add the scene to our stage
		Scene scene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setOnCloseRequest(closeWindowHandler);
		primaryStage.setScene(scene);

		// Show stage
		primaryStage.show();
	}

	private EventHandler<WindowEvent> closeWindowHandler = event -> {
		ButtonType save = new ButtonType("Save and Exit", ButtonBar.ButtonData.OK_DONE);
		ButtonType dontSave = new ButtonType("Exit Without Saving", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save before exiting?", save,
				dontSave);

		Optional<ButtonType> result = closeConfirmation.showAndWait();

		/**
		 * TODO
		 * 
		 * add line to save once that code is implemented
		 */
		if (result.orElse(dontSave) == save) {
			// socialNetwork.save()
		}
	};

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

	// HELPER METHOD FOR createMainPane()
	private HBox createToolBar() {
		HBox main = new HBox();

		// Stylize the HBox a little bit
		main.setPadding(new Insets(20, 12, 15, 12));
		main.setSpacing(50);
		main.setStyle("-fx-background-color: #336699");

		// Create settingsButton and handler
		Button settingsButton = new Button("Settings");
		createStylizedButton(settingsButton);
		settingsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				createSettingsWindow();
			}

		});

		// stretch the settingsButton to window width
		settingsButton.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(settingsButton, Priority.ALWAYS);

		// add settings button to the HBox
		main.getChildren().add(settingsButton);

		return main;
	}

	private void createSettingsWindow() {
		// Set settings window title
		Stage mainStage = new Stage();
		mainStage.setTitle("Settings");

		// Use vertical box as main pane
		VBox mainPane = new VBox();
		mainPane.setSpacing(10);

		//////////////////////////////////////////////////////////
		// Clear Graph Button Code //
		//////////////////////////////////////////////////////////
		Button clearGraphButton = new Button("Clear Network");
		clearGraphButton.setMaxWidth(Main.WINDOW_WIDTH);
		createStylizedButton(clearGraphButton);
		clearGraphButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { // TODO
				socialNetwork = new SocialNetwork();
				currentUser = null;
				mainStage.close();
				try {
					start(firstStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////
		// Load File Button Code //
		//////////////////////////////////////////////////////////
		Button loadFileButton = new Button("Load Network from File");
		loadFileButton.setMaxWidth(Main.WINDOW_WIDTH);
		createStylizedButton(loadFileButton);
		loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				File file = fileChooser.showOpenDialog(mainStage);
				if (file != null) {
					socialNetwork.loadNetworkFromFile(file);
				}
			}
		});
		//////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////
		// Export Button Code //
		//////////////////////////////////////////////////////////
		Button exportButton = new Button("Export Network to File");
		exportButton.setMaxWidth(Main.WINDOW_WIDTH);
		createStylizedButton(exportButton);
		exportButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();

				// Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);

				// Show save file dialog
				File file = fileChooser.showSaveDialog(mainStage);

				if (file != null) {
					socialNetwork.saveNetworkToFile(file);
				}
			}
		});

		//////////////////////////////////////////////////////////

		// Add buttonHbox to mainPane Vbox
		mainPane.getChildren().addAll(clearGraphButton, loadFileButton, exportButton);

		// Create scene, add the main pane, and add the scene to our stage
		Scene scene = new Scene(mainPane, 450, 100);
		mainStage.setScene(scene);

		// Show stage
		mainStage.show();
	}

	// HELPER METHOD FOR createMainPane()
	private HBox createNameLabel() {
		HBox main = new HBox();

		main.setPadding(new Insets(20, 12, 15, 12));

		if (currentUser == null) { // TODO
			Label nameLabel = new Label("Welcome!");
			main.getChildren().add(nameLabel);
		} else {
			Label nameLabel = new Label("Welcome " + currentUser + "!");
			main.getChildren().add(nameLabel);
		}
		return main;
	}

	// HELPER METHOD FOR createMainPane()
	private ScrollPane createScrollPane() {
		VBox root = new VBox(); // TODO
		if (currentUser != null) {
			try {
				Label yourFriends = new Label("Your Friends:");
				yourFriends.setTextFill(Color.BLUE);
				yourFriends.setStyle("-fx-font: 17 arial;");
				root.getChildren().add(yourFriends);
				root.setPadding(new Insets(10));
				for (int i = 0; i < socialNetwork.getFriendsOf(currentUser).size(); i++) {
					root.getChildren().add(new Label(socialNetwork.getFriendsOf(currentUser).get(i).getUsername()));
					root.setSpacing(10);
					root.setPadding(new Insets(10));
				}
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}
		}
		ScrollPane sp = new ScrollPane();
		sp.setContent(root);
		sp.setFitToHeight(true);
		sp.setPrefHeight(WINDOW_HEIGHT);
		return sp;

//        String[] optionsList = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};
//        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(optionsList));
//        rootBorderPane.setLeft(comboBox);

//        ScrollPane main = new ScrollPane();
//        main.setFitToHeight(true);
//        main.setPrefHeight(WINDOW_HEIGHT);
//        return main;
	}

	// HELPER METHOD FOR createMainPane()
	private HBox createFriendCounter() {
		HBox main = new HBox();

		main.setPadding(new Insets(20, 12, 15, 12));

		int friendsAmount = 0; // TODO
		if (currentUser != null) {
			try {
				friendsAmount = socialNetwork.getFriendsOf(currentUser).size();
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (friendsAmount == 1) {
			Label nameLabel = new Label("You currently have " + friendsAmount + " friend.");
			main.getChildren().add(nameLabel);
		} else {
			Label nameLabel = new Label("You currently have " + friendsAmount + " friends.");
			main.getChildren().add(nameLabel);
		}

		return main;
	}

	// HELPER METHOD FOR createMainPane()
	private HBox createFriendManagementButton() {
		HBox main = new HBox();

		main.setPadding(new Insets(20, 12, 15, 12));
		main.setSpacing(50);
		main.setStyle("-fx-background-color: #336699");

		Button friendManagementButton = new Button("View All Users");
		createStylizedButton(friendManagementButton);
		friendManagementButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				createFriendsListWindow();
			}
		});

		friendManagementButton.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(friendManagementButton, Priority.ALWAYS);

		main.getChildren().add(friendManagementButton);
		return main;
	}

	private void createFriendsListWindow() {
		// Set stage title
		Stage mainStage = new Stage();
		mainStage.setTitle("Functionality");

		// Use vertical box as main pane
		VBox mainPane = new VBox();

		//////////////////////////////////////////////////////////
		// User Toolbar Code //
		//////////////////////////////////////////////////////////
		HBox userHBox = new HBox();
		userHBox.setPadding(new Insets(20, 12, 15, 12));
		userHBox.setSpacing(20);
		userHBox.setStyle("-fx-background-color: #336699");

		TextField usernameTextField = new TextField("Username...");
		usernameTextField.textProperty().addListener((ob, old, newValue) -> {
			if (newValue.matches("^[a-zA-Z0-9_']*$") && !newValue.isEmpty()) {
				usernameTextField.setStyle("-fx-control-inner-background: #2ecc71");
			} else {
				usernameTextField.setStyle("-fx-control-inner-background: #e74c3c");
			}
		});

		Button addUserButton = new Button("Add User");
		createStylizedButton(addUserButton);
		addUserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					socialNetwork.addUser(usernameTextField.getText());
					socialNetwork.queue.add(socialNetwork.command.add + " " + usernameTextField.getText());
				} catch (InvalidUsernameException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Username Error");
					alert.setContentText(e.getMessage());
					alert.showAndWait();

				} catch (UserAlreadyExistsException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Username Error");
					alert.setContentText("Username already exists!");
					alert.showAndWait();

				}
				mainStage.close();
				createFriendsListWindow();
			}

		});
		Button removeUserButton = new Button("Remove User");
		createStylizedButton(removeUserButton);
		removeUserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { // TODO
				if (currentUser != null) {
					try {
						socialNetwork.removeFriend(currentUser, usernameTextField.getText());
						socialNetwork.removeUser(usernameTextField.getText());
						socialNetwork.queue.add(socialNetwork.command.remove + " " + usernameTextField.getText());
					} catch (UserNotFoundException e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Username Error");
						alert.setContentText("User does not exist!");
						alert.showAndWait();
					}
				} else {
					try {
						socialNetwork.removeUser(usernameTextField.getText());
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				}
				mainStage.close();
				createFriendsListWindow();
			}

		});

		Button focusUserButton = new Button("Open User");
		createStylizedButton(focusUserButton);
		focusUserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { // TODO
				firstStage.close();
				if (socialNetwork.getPersonByName(usernameTextField.getText()) == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Username Error");
					alert.setContentText("User does not exist!");
					alert.showAndWait();
				} else {
					currentUser = usernameTextField.getText();
					try {
						start(firstStage);
						mainStage.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});

		// Stretch all buttons
		addUserButton.setMaxWidth(Double.MAX_VALUE);
		removeUserButton.setMaxWidth(Double.MAX_VALUE);
		focusUserButton.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(addUserButton, Priority.ALWAYS);
		HBox.setHgrow(removeUserButton, Priority.ALWAYS);
		HBox.setHgrow(focusUserButton, Priority.ALWAYS);

		// Add all buttons to HBox
		userHBox.getChildren().addAll(usernameTextField, addUserButton, removeUserButton, focusUserButton);
		//////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////
		// Friendship Toolbar Code //
		//////////////////////////////////////////////////////////
		HBox friendshipHBox = new HBox();
		friendshipHBox.setPadding(new Insets(20, 12, 15, 12));
		friendshipHBox.setSpacing(20);
		friendshipHBox.setStyle("-fx-background-color: #336699");

		TextField user1TextField = new TextField("User 1...");
		user1TextField.textProperty().addListener((ob, old, newValue) -> {
			if (newValue.matches("^[a-zA-Z0-9_']*$") && !newValue.isEmpty()) {
				user1TextField.setStyle("-fx-control-inner-background: #2ecc71");
			} else {
				user1TextField.setStyle("-fx-control-inner-background: #e74c3c");
			}
		});

		TextField user2TextField = new TextField("User 2...");
		user2TextField.textProperty().addListener((ob, old, newValue) -> {
			if (newValue.matches("^[a-zA-Z0-9_']*$") && !newValue.isEmpty()) {
				user2TextField.setStyle("-fx-control-inner-background: #2ecc71");
			} else {
				user2TextField.setStyle("-fx-control-inner-background: #e74c3c");
			}
		});

		Button addFriendButton = new Button("Add Friendship");
		createStylizedButton(addFriendButton);
		addFriendButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				if (user1TextField.getText().matches("^[a-zA-Z0-9_']*$")
						&& user2TextField.getText().matches("^[a-zA-Z0-9_']*$") && !user1TextField.getText().isEmpty()
						&& !user2TextField.getText().isEmpty()) {
					try {
						socialNetwork.addFriend(user1TextField.getText(), user2TextField.getText());
						socialNetwork.queue.add(socialNetwork.command.add + " " + user1TextField.getText() + " " + user2TextField.getText());
					} catch (UserNotFoundException e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Username Error");
						alert.setContentText("User does not exist!");
						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Username Error");
					alert.setContentText("Username can only contain letters, digits, underscores, and apostrophes!");
					alert.showAndWait();
				}
				mainStage.close();
				createFriendsListWindow();
			}

		});
		Button removeFriendButton = new Button("Remove Friendship");
		createStylizedButton(removeFriendButton);
		removeFriendButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				if (user1TextField.getText().matches("^[a-zA-Z0-9_']*$")
						&& user2TextField.getText().matches("^[a-zA-Z0-9_']*$") && !user1TextField.getText().isEmpty()
						&& !user2TextField.getText().isEmpty()) {
					try {
						socialNetwork.removeFriend(user1TextField.getText(), user2TextField.getText());
						socialNetwork.queue.add(socialNetwork.command.add + " " + user1TextField.getText() + " " + user2TextField.getText());
					} catch (UserNotFoundException e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Username Error");
						alert.setContentText("User does not exist!");
						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Username Error");
					alert.setContentText("Username can only contain letters, digits, underscores, and apostrophes!");
					alert.showAndWait();
				}
				mainStage.close();
				createFriendsListWindow();
			}

		});

		// Stretch all buttons
		addFriendButton.setMaxWidth(Double.MAX_VALUE);
		removeFriendButton.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(addFriendButton, Priority.ALWAYS);
		HBox.setHgrow(removeFriendButton, Priority.ALWAYS);

		// Add all buttons to HBox
		friendshipHBox.getChildren().addAll(user1TextField, user2TextField, addFriendButton, removeFriendButton);
		//////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////
		// Friends List Scrollable Code //
		//////////////////////////////////////////////////////////
		ListView<String> listView = new ListView<String>();

		for (Person p : socialNetwork.getAllUsers()) {
			listView.getItems().add(p.getUsername()); // TODO
		}
		//////////////////////////////////////////////////////////

		// Add buttonHbox and scrollable list to mainPane Vbox
		mainPane.getChildren().addAll(userHBox, friendshipHBox, listView);

		// Create scene, add the main pane, and add the scene to our stage
		Scene scene = new Scene(mainPane, 700, 450);
		mainStage.setScene(scene);

		// Show stage
		mainStage.show();
	}

	/**
	 * Adds style to a selected Button object
	 * 
	 * Made this into a separate method to clean up code and avoid reusing the same
	 * code for every new button
	 * 
	 * @param button to add style to
	 */
	private void createStylizedButton(Button button) {
		DropShadow shadow = new DropShadow();
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				button.setEffect(shadow);
			}

		});
		button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

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
