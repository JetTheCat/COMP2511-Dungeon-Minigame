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

public class MenuScreen {
    private Stage stage;
    private String title;
    private MenuController controller;
    private Scene scene;
    
    public MenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        this.title = "The Legend of Ood";
        this.controller = new MenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
        loader.setController(this.controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }
    
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    public void stop() {
    	stage.close();
    }
    
    public MenuController getController() {
        return controller;
    }
    
}
