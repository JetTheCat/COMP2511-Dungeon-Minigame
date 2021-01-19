package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class MessageController {
	@FXML
	private ImageView backgroundImage;
	
	@FXML
	Button startLevelButton;
	
	@FXML
	Button returnToMenuButton;
	
	private DungeonScreen dungeonScreen;
	
	private MenuScreen menuScreen;
	
	public void initialize() {
		Background b = getBackground("/button_unhover.png");
		startLevelButton.setBackground(b);
		returnToMenuButton.setBackground(b);
		backgroundImage.setImage(new Image("/menu_screen.png"));
	}
	
	private Background getBackground(String file) {
		BackgroundImage bImage = new BackgroundImage( new Image( getClass().getResource(file).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		return new Background(bImage);
	}
	
	@FXML
	public void handleReturnToMenuButton(ActionEvent event) {
		menuScreen.start();
	}
	
	@FXML
	public void handleStartLevelButton(ActionEvent event) throws IOException{
		dungeonScreen.start();
	}
	
	
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
    
    public void setGoalMessage(Integer levelNumber) throws JSONException, FileNotFoundException {
    	backgroundImage.setImage(new Image("/message" + levelNumber + "_new.png"));
    }
}
