package unsw.dungeon;

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

public class SubMenuController {

	@FXML
	Button returnToGameButton;
	
	@FXML
	Button returnToMainMenuButton;
	
	@FXML
	Button restartLevelButton;
	
	@FXML
	ImageView backgroundImage;
	
	private DungeonScreen dungeonScreen;
	
	private MessageScreen messageScreen;
	
	private MenuScreen menuScreen;
	
	@FXML
	public void handleReturnToGameButton(ActionEvent event) {
		dungeonScreen.start();
	}
	
	@FXML
	public void handleReturnToMainMenuButton(ActionEvent event) {
		menuScreen.start();
		
	}
	
	public void initialize() {
		Background b = getBackground("/button_unhover.png");
		returnToGameButton.setBackground(b);
		returnToMainMenuButton.setBackground(b);
		restartLevelButton.setBackground(b);
		backgroundImage.setImage(new Image("/submenu_screen.png"));
	}
	
	private Background getBackground(String file) {
		BackgroundImage bImage = new BackgroundImage( new Image( getClass().getResource(file).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		return new Background(bImage);
	}
	
	@FXML
	public void handleRestartLevelButton(ActionEvent event) throws IOException {
		dungeonScreen.reload(dungeonScreen.getLevelNumber() + ".json");
		messageScreen.start();
	}

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
    
    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
    
    public void setMessageScreen(MessageScreen messageScreen) {
        this.messageScreen = messageScreen;
    }
}
