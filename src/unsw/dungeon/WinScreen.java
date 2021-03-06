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

public class WinScreen {
	private Stage stage;
    private String title;
    private WinController controller;
    private Scene scene;
    
    public WinScreen(Stage stage) throws IOException {
    	this.stage = stage;
    	this.title = "The Legend of Ood";
    	this.controller = new WinController();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("WinView.fxml"));
        loader.setController(this.controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }
    
    public void start() {
    	if (controller.getLevelNumber() == 4) {
    		controller.changeToVictoryScreen();
    	} else {
	        stage.setTitle(title);
	        stage.setScene(scene);
	        stage.show();
    	}
    }

    public WinController getController() {
        return controller;
    }

}
