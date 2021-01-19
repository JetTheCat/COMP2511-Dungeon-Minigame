package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class GuideController {
	@FXML
	private ImageView backgroundImage;
	@FXML
	private Button page1Button;
	@FXML
	private Button page2Button;
	
	@FXML 
	private Button returnToMenuButton;
	
	private MenuScreen menuScreen;
	
	public void initialize() {
		Background b = getBackground("/guideButton.png");
		returnToMenuButton.setBackground(b);
		b = getBackground("/pageButton.png");
		page1Button.setBackground(b);
		page2Button.setBackground(b);
		backgroundImage.setImage(new Image("/GuidePage1_new.png"));
	}
	
	
	@FXML
	public void handleReturnToMenuButton(ActionEvent event) {
		menuScreen.start();
	}
	
	@FXML
	public void handlePage1Button(ActionEvent event) {
		backgroundImage.setImage(new Image("/GuidePage1_new.png"));
	}
	
	@FXML
	public void handlePage2Button(ActionEvent event) {
		backgroundImage.setImage(new Image("/GuidePage2_new.png"));
	}
	
	private Background getBackground(String file) {
		BackgroundImage bImage = new BackgroundImage( new Image( getClass().getResource(file).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		return new Background(bImage);
	}
	
	public void setMenuScreen(MenuScreen obj) {
		this.menuScreen = obj;
	}
}
