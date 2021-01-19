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

public class VictoryController {
	@FXML
	private Button menuReturn;
	
	@FXML
	private ImageView backgroundImage;
	
	@FXML
	private ImageView title;
	
	private MenuScreen menuScreen;

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
    
    public void initialize() {
		Background b = getBackground("/button_unhover.png");
		menuReturn.setBackground(b);
		menuReturn.setBackground(b);
		title.setImage(new Image("/victory_title.png"));
		backgroundImage.setImage(new Image("/win_screen.png"));
	}
	
	private Background getBackground(String file) {
		BackgroundImage bImage = new BackgroundImage( new Image( getClass().getResource(file).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		return new Background(bImage);
	}
	
	@FXML
	public void handleMenuReturn() {
		menuScreen.start();
	}
    
}
