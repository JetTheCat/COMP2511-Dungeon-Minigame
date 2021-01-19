package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        MenuScreen menuScreen = new MenuScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        SubMenuScreen subMenuScreen = new SubMenuScreen(primaryStage);
        WinScreen winScreen = new WinScreen(primaryStage);
        LoseScreen loseScreen = new LoseScreen(primaryStage);
        MessageScreen messageScreen = new MessageScreen(primaryStage);
        ChangeLevelScreen changeLevelScreen = new ChangeLevelScreen(primaryStage);
        VictoryScreen victoryScreen = new VictoryScreen(primaryStage);
        GuideScreen guideScreen = new GuideScreen(primaryStage);
        // Controllers need to know about other screens that link together.
        
        dungeonScreen.getController().setSubMenuScreen(subMenuScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        dungeonScreen.getController().setLoseScreen(loseScreen);
        menuScreen.getController().setMessageScreen(messageScreen);
        menuScreen.getController().setGuideScreen(guideScreen);
        guideScreen.getController().setMenuScreen(menuScreen);
        messageScreen.getController().setDungeonScreen(dungeonScreen);
        messageScreen.getController().setMenuScreen(menuScreen);
        subMenuScreen.getController().setDungeonScreen(dungeonScreen);
        subMenuScreen.getController().setMessageScreen(messageScreen);
        subMenuScreen.getController().setMenuScreen(menuScreen);
        winScreen.getController().setMenuScreen(menuScreen);
        winScreen.getController().setDungeonScreen(dungeonScreen);
        winScreen.getController().setMessageScreen(messageScreen);
        winScreen.getController().setVictoryScreen(victoryScreen);
        loseScreen.getController().setMenuScreen(menuScreen);
        loseScreen.getController().setDungeonScreen(dungeonScreen);
        loseScreen.getController().setMessageScreen(messageScreen);
        changeLevelScreen.getController().setDungeonScreen(dungeonScreen);
        changeLevelScreen.getController().setMessageScreen(messageScreen);
        menuScreen.getController().setChangeLevelScreen(changeLevelScreen);
        changeLevelScreen.getController().setMenuScreen(menuScreen);
        victoryScreen.getController().setMenuScreen(menuScreen);
        menuScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
