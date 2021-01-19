package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DungeonScreen {
    private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;
    private int levelNumber;
    
    public DungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        this.title = "The Legend of Ood";
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("1.json");
        this.controller = dungeonLoader.loadController();
        this.levelNumber = 1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(this.controller);
        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.requestFocus();
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    

	public DungeonController getController() {
		return this.controller;
	}
    
	
    public void reload(String jsonName) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(jsonName);
        // Creates new controller to load in a fresh dungeon
        DungeonController newDungeonController = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(this.controller);
        Parent root = loader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.requestFocus();
        // Pass the old controller's subMenuLink to the new one before updating the
        // controller to the new one
        newDungeonController.setSubMenuScreen(controller.getSubMenuScreen());
        newDungeonController.setWinScreen(controller.getWinScreen());
        newDungeonController.setLoseScreen(controller.getLoseScreen());
        this.controller = newDungeonController;
        
        // Note: if you don't execute the same code twice, levels cannot change
        dungeonLoader = new DungeonControllerLoader(jsonName);
        newDungeonController = dungeonLoader.loadController();
        loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(this.controller);
        root = loader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.requestFocus();
        newDungeonController.setSubMenuScreen(controller.getSubMenuScreen());
        newDungeonController.setWinScreen(controller.getWinScreen());
        newDungeonController.setLoseScreen(controller.getLoseScreen());
        this.controller = newDungeonController;

    }

	public int getLevelNumber() {
		// TODO Auto-generated method stub
		return levelNumber;
	}
	
	public void incremementLevel() {
		levelNumber ++;
	}

	public void setLevel(int i) {
		levelNumber = i;
	}

}
