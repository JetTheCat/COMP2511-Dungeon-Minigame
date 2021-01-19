package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class ChangeLevelController {

	@FXML
	private Button level1Button;
	
	@FXML
	private Button level2Button;
	
	@FXML
	private Button level3Button;
	
	@FXML
	private Button level4Button;
	
	@FXML
	private Button returnToMenuButton;
	
	@FXML
	private ImageView backgroundImage;
	
	@FXML
	private ImageView changeLevelTitle;
	
	private MenuScreen menuScreen;
	
	private MessageScreen messageScreen;
	
	private DungeonScreen dungeonScreen;
	
	public void initialize() {
		Background b = getBackground("/button_unhover.png");
		level1Button.setBackground(b);
		level2Button.setBackground(b);
		level3Button.setBackground(b);
		level4Button.setBackground(b);
		returnToMenuButton.setBackground(b);
		changeLevelTitle.setImage(new Image("/change_level_title.png"));
		backgroundImage.setImage(new Image("/menu_screen.png"));
	}
	
	
	/**
	 * Retrieves the background image given a file name
	 * @param file - The name of the file in String format
	 * @return Background object containing the image stored in the file
	 */
	private Background getBackground(String file) {
		BackgroundImage bImage = new BackgroundImage( new Image( getClass().getResource(file).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		return new Background(bImage);
	}
	
	@FXML
	public void handleLevel1Button() throws IOException {
		changeToMessageScreen(1);
	}
	
	@FXML
	public void handleLevel2Button() throws IOException {
		changeToMessageScreen(2);
	}
	
	@FXML
	public void handleLevel3Button() throws IOException {
		changeToMessageScreen(3);

	}
	
	@FXML
	public void handleLevel4Button() throws IOException {
		changeToMessageScreen(4);

	}
	
	
	/**
	 * Function to change and load in a new dungeon level
	 * @param levelNumber - The id/name of the dungeon level in Integer
	 * @throws JSONException 
	 * @throws IOException
	 */
	private void changeToMessageScreen(Integer levelNumber) throws JSONException, IOException {
		messageScreen.getController().setGoalMessage(levelNumber);
		dungeonScreen.setLevel(levelNumber);
		dungeonScreen.reload(levelNumber + ".json");
		messageScreen.start();
	}
	
	@FXML
	public void handleReturnToMenuButton() {
		menuScreen.start();
	}
	
    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
    
    public void setMessageScreen(MessageScreen messageScreen) {
        this.messageScreen = messageScreen;
    }
}
