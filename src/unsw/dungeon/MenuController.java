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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	private Button startButton;
	
	@FXML
	private Button changeLevelButton;
	
	@FXML
	private Button closeGameButton;
	
	@FXML
	private ImageView backgroundImage;
	
	@FXML
	private Button guideButton;
	
	@FXML
	private ImageView title;
	
	private MessageScreen messageScreen;
	
	private ChangeLevelScreen changeLevelScreen;
	
	private GuideScreen guideScreen;
	
	public void initialize() {
		Background b = getBackground("/button_unhover.png");
		startButton.setBackground(b);
		changeLevelButton.setBackground(b);
		closeGameButton.setBackground(b);
		guideButton.setBackground(b);
		title.setImage(new Image("menu_title.png"));
		backgroundImage.setImage(new Image("/menu_screen.png"));
	}
	
	private Background getBackground(String file) {
		BackgroundImage bImage = new BackgroundImage( new Image( getClass().getResource(file).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		return new Background(bImage);
	}
	
	@FXML
	public void handleStartButton(ActionEvent event) throws IOException {
		changeLevelScreen.getController().handleLevel1Button();
	}
	
	@FXML
	public void handleChangeLevelButton(ActionEvent event) {
		changeLevelScreen.start();
	}

	@FXML
	public void handleCloseGameButton(ActionEvent event) {
		 Stage stage = (Stage) closeGameButton.getScene().getWindow();
		 stage.close();
	}
	
	@FXML
	public void handleGuideButton(ActionEvent event) {
		guideScreen.start();
	}

    public void setMessageScreen(MessageScreen messageScreen) {
        this.messageScreen = messageScreen;
    }

    public void setChangeLevelScreen(ChangeLevelScreen changeLevelScreen) {
        this.changeLevelScreen = changeLevelScreen;
    }
    
    public void setGuideScreen(GuideScreen guideScreen) {
        this.guideScreen = guideScreen;
    }
    
}
