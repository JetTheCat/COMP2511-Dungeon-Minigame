package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

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

public class WinController {
	
	@FXML
	private Button menuReturn;
	
	@FXML 
	private Button nextLevelButton;
	
	@FXML
	private ImageView backgroundImage;
	
	@FXML
	private ImageView title;
	
	private MenuScreen menuScreen;
	
	private MessageScreen messageScreen;
	
	private DungeonScreen dungeonScreen;
	
	private VictoryScreen victoryScreen;
	
	public void initialize() {
		Background b = getBackground("/button_unhover.png");
		menuReturn.setBackground(b);
		nextLevelButton.setBackground(b);
		title.setImage(new Image("/win_title.png"));
		backgroundImage.setImage(new Image("/win_screen.png"));
	}
	
	private Background getBackground(String file) {
		BackgroundImage bImage = new BackgroundImage( new Image( getClass().getResource(file).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		return new Background(bImage);
	}
	
	@FXML
	public void handleMenuReturn(ActionEvent event) {
		menuScreen.start();
	}
	
	@FXML
	public void handleNextLevelButton(ActionEvent event) throws IOException {
		int nextLevel = dungeonScreen.getLevelNumber() + 1;
		if (nextLevel > 4) {
			return;
		}
		messageScreen.getController().setGoalMessage(nextLevel);
		dungeonScreen.setLevel(nextLevel);
		dungeonScreen.reload(nextLevel + ".json");
		messageScreen.start();
	}
	
	public void setMenuScreen(MenuScreen obj) {
		this.menuScreen = obj;
	}

	public void setDungeonScreen(DungeonScreen obj) {
		this.dungeonScreen = obj;
	}
	
	public void setMessageScreen(MessageScreen obj) {
		this.messageScreen = obj;
	}
	
	public void setVictoryScreen(VictoryScreen obj) {
		this.victoryScreen = obj;
	}
	
	public Integer getLevelNumber() {
		return dungeonScreen.getLevelNumber();
	}
	
	public void changeToVictoryScreen() {
		victoryScreen.start();
	}
}
