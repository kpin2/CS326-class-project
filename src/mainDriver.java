/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * mainDriver.java - Driver program for the tutoring software/game of Mission: Math! Calls the  JavaFX application start method and launches the software.
 *
 * @author Kevin Pinto - Wrote initial code with a basic setup for the main driver.
 * */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainDriver extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        //stage is the top level container, setting title displays the title in the title bar
        stage.setTitle("Mission: Math!");
//        stage.setResizable(false);

        //root is the highest level pane and the root node of the scene graph
        Pane root = new Pane();

        //scene is the container for all content so far
        Scene landingScene = new Scene(root, 1200, 675);

        //setting the background image
        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1200, 800, true, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(180, 100, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission-rgyw9.otf", 120));
        text.setFill(Color.RED);

        //loading images for buttons and other assets and creating image views
        Image exitButtonImage, helpButtonImage, asteroidHomeImage, smallShipImage, andromedaPlanetImage, galaxyImage, cosmicCountingImage;
        exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        asteroidHomeImage = new Image("file:resources/assets/Asteroid.png");
        helpButtonImage = new Image("file:resources/assets/help2-64px.png");
        smallShipImage = new Image("file:resources/assets/small spaceship.png");
        andromedaPlanetImage = new Image("file:resources/assets/planet 200x200.png");
        galaxyImage = new Image("file:resources/assets/galaxy-transparent-24.png");
        cosmicCountingImage = new Image("file:resources/assets/planet 300x250.png");
        ImageView exitButtonImageView, helpButtonImageView, asteroidHomeImageView, smallShipImageView, andromedaPlanetImageView, galaxyImageView, cosmicCountingImageView;
        exitButtonImageView = new ImageView(exitButtonImage);
        helpButtonImageView = new ImageView(helpButtonImage);
        Button exitButton = new Button("", exitButtonImageView);
        Button helpButton = new Button("", helpButtonImageView);
        helpButton.setLayoutX(60);
        helpButton.setLayoutY(5);
        exitButton.setLayoutX(-5);
        exitButton.setLayoutY(5);
        exitButton.setStyle("-fx-background-color: transparent;");
        helpButton.setStyle("-fx-background-color: transparent;");

        //help button action, will switch scenes to the tutorials/practice
        helpButton.setOnAction(e -> {
            System.out.println("Help button pressed");
          /*  Scene helpScene = null;
            stage.setScene(helpScene);*/
        });

        exitButton.setOnAction(e -> {
            stage.close();
        });


        smallShipImageView = new ImageView(smallShipImage);
        smallShipImageView.setFitHeight(200);
        smallShipImageView.setFitWidth(200);
        smallShipImageView.setLayoutX(100);
        smallShipImageView.setLayoutY(150);

        asteroidHomeImageView = new ImageView(asteroidHomeImage);
        asteroidHomeImageView.setFitHeight(200);
        asteroidHomeImageView.setFitWidth(200);
        asteroidHomeImageView.setLayoutX(0);
        asteroidHomeImageView.setLayoutY(200);

        andromedaPlanetImageView = new ImageView(andromedaPlanetImage);
        andromedaPlanetImageView.setFitHeight(250);
        andromedaPlanetImageView.setFitWidth(250);
        andromedaPlanetImageView.setLayoutX(175);
        andromedaPlanetImageView.setLayoutY(380);

        galaxyImageView = new ImageView(galaxyImage);
        galaxyImageView.setFitHeight(379);
        galaxyImageView.setFitWidth(800);
        galaxyImageView.setLayoutX(150);
        galaxyImageView.setLayoutY(130);

        cosmicCountingImageView = new ImageView(cosmicCountingImage);
        cosmicCountingImageView.setFitHeight(300);
        cosmicCountingImageView.setFitWidth(275);
        cosmicCountingImageView.setLayoutX(900);
        cosmicCountingImageView.setLayoutY(300);


        root.getChildren().addAll(exitButton, text, helpButton, smallShipImageView, asteroidHomeImageView, andromedaPlanetImageView, galaxyImageView, cosmicCountingImageView);
        stage.setScene(landingScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
