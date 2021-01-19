package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SubMenuScreen {
    private Stage stage;
    private String title;
    private SubMenuController controller;
    private Scene scene;
    
    public SubMenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        this.title = "The Legend of Ood";
        this.controller = new SubMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SubMenuView.fxml"));
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
    
    public SubMenuController getController() {
        return controller;
    }
}
