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

public class GuideScreen {
	private Stage stage;
    private String title;
    private GuideController controller;
    private Scene scene;
    
    public GuideScreen(Stage stage) throws IOException {
    	this.stage = stage;
    	this.title = "The Legend of Ood";
    	this.controller = new GuideController();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("GuideView.fxml"));
        loader.setController(this.controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }
    
    public void start() {
    	controller.initialize();
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();

    }

    public GuideController getController() {
        return controller;
    }

    
}
