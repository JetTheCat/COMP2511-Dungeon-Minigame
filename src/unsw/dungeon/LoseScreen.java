package unsw.dungeon;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoseScreen {
	
	private Stage stage;
	private Scene scene;
	private LoseController controller;
	private String title;
	
	public LoseScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.title = "You've Lost!";
		this.controller = new LoseController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoseView.fxml"));
		loader.setController(this.controller);
		
		// Put loader into a parent node named root
		Parent root = loader.load();
		this.scene = new Scene(root);
	}
	
	public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
	}
	
	// Need this to set the screen that the LoseController is going to manage
	public LoseController getController() {
		return controller;
	}

}
