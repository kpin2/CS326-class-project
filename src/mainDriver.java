/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * mainDriver.java - Driver program for the tutoring software/game of Mission: Math! Calls the  JavaFX application start method and launches the software.
 *
 * @author Kevin Pinto - Wrote initial code with a basic setup for the main driver.
 * */

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
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
        stage.setResizable(false);

        //root is the highest level pane
        Pane root = new Pane();

        //landingScene is the homepage/landing page of the software
        Scene landingScene = new Scene(root, 1360, 750);

        //loading our custom font first so the rest of the program can use it
        Font.loadFont("file:resources/font/SpaceMission.otf", 32);

        //loading the stylesheet(s)
        landingScene.getStylesheets().add(("stylesheet.css"));

        //setting the background image
        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1360, 800, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.rgb(243, 5, 1));
        text.setId("title");

        //loading images for buttons and other assets and creating image views
        Image exitButtonImage, helpButtonImage, asteroidHomeImage, smallShipImage, andromedaPlanetImage, galaxyImage, cosmicCountingImage;
        exitButtonImage = new Image("file:resources/assets/exit.png");
        asteroidHomeImage = new Image("file:resources/assets/Asteroid.png");
        helpButtonImage = new Image("file:resources/assets/help2.png");
        smallShipImage = new Image("file:resources/assets/small spaceship.png");
        andromedaPlanetImage = new Image("file:resources/assets/planet 200x200.png");
        galaxyImage = new Image("file:resources/assets/galaxy-transparent-24.png");
        cosmicCountingImage = new Image("file:resources/assets/planet 300x250.png");

        ImageView exitButtonImageView, helpButtonImageView, asteroidHomeImageView, smallShipImageView, andromedaPlanetImageView, galaxyImageView, cosmicCountingImageView;

        //exit and help buttons
        exitButtonImageView = new ImageView(exitButtonImage);
        exitButtonImageView.setFitHeight(96);
        exitButtonImageView.setFitWidth(96);
        helpButtonImageView = new ImageView(helpButtonImage);
        helpButtonImageView.setFitHeight(96);
        helpButtonImageView.setFitWidth(96);
        Button exitButton = new Button("", exitButtonImageView);
        Button helpButton = new Button("", helpButtonImageView);
        helpButton.setLayoutX(96);
        helpButton.setLayoutY(5);
        exitButton.setLayoutX(-5);
        exitButton.setLayoutY(5);
        exitButton.setStyle("-fx-background-color: transparent;");
        helpButton.setStyle("-fx-background-color: transparent;");

        //tooltips for the help and exit buttons
        Tooltip help = new Tooltip("Need Help? Click here!");
        helpButton.setTooltip(help);
        Tooltip exit = new Tooltip("Return to Login Screen");
        exitButton.setTooltip(exit);

        //help button action, will switch the scene to the tutorial/practice section
        helpButton.setOnAction(e -> {
            System.out.println("Help button pressed");
          /*
            stage.setScene(helpScene);*/
        });

        //exit button, leads back to log in screen?
        exitButton.setOnAction(e -> {
//            stage.setScene(loginScene);
            stage.close();
        });


        //small spaceship that starts on top of the 'home' asteroid
        smallShipImageView = new ImageView(smallShipImage);
        smallShipImageView.setLayoutX(95);
        smallShipImageView.setLayoutY(152);
        smallShipImageView.setRotate(-25);


        //home button/asteroid that returns the user to the landing screen
        asteroidHomeImageView = new ImageView(asteroidHomeImage);
        Button asteroidHomeButton = new Button("", asteroidHomeImageView);
        asteroidHomeButton.setLayoutX(-100);
        asteroidHomeButton.setLayoutY(200);
        asteroidHomeButton.setStyle("-fx-background-color: transparent;");

        asteroidHomeButton.setOnAction(e -> {
            System.out.println("Asteroid Home button pressed");
            stage.setScene(landingScene);
        });

        //tooltip for the asteroid/home button
        Tooltip home = new Tooltip("Return to Home-screen!");
        asteroidHomeButton.setTooltip(home);


        //andromeda arithmetic
        andromedaPlanetImageView = new ImageView(andromedaPlanetImage);
        andromedaPlanetImageView.setFitHeight(250);
        andromedaPlanetImageView.setFitWidth(250);
        andromedaPlanetImageView.setLayoutX(200);
        andromedaPlanetImageView.setLayoutY(400);
        Button andromedaArithmetic = new Button("", andromedaPlanetImageView);
        andromedaArithmetic.setLayoutX(200);
        andromedaArithmetic.setLayoutY(400);
        andromedaArithmetic.setStyle("-fx-background-color: transparent;");
        Text andromedaText = new Text(75, 675, "Andromeda Arithmetic");
        andromedaText.setFill(Color.rgb(97, 197, 184));
        andromedaText.setId("andromedaText");
        Tooltip andromeda = new Tooltip("Andromeda Arithmetic");
        andromedaArithmetic.setTooltip(andromeda);
        andromedaArithmetic.setOnAction(e -> {
            System.out.println("Andromeda Arithmetic button pressed");
            //stage.setScene(andromedaScene);
        });


        galaxyImageView = new ImageView(galaxyImage);
        galaxyImageView.setFitHeight(379);
        galaxyImageView.setFitWidth(800);
        galaxyImageView.setLayoutX(300);
        galaxyImageView.setLayoutY(130);

        cosmicCountingImageView = new ImageView(cosmicCountingImage);
        cosmicCountingImageView.setFitHeight(250);
        cosmicCountingImageView.setFitWidth(300);
        cosmicCountingImageView.setLayoutX(1000);
        cosmicCountingImageView.setLayoutY(400);


        root.getChildren().addAll(exitButton, text, helpButton, smallShipImageView, asteroidHomeButton, galaxyImageView, cosmicCountingImageView, andromedaArithmetic, andromedaText);
        stage.setScene(landingScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
