/* CSCI362 Software Engineering
 * Mission: Math! application
 * landingScene.java - Separate class for the landing scene to allow the mainDriver more functionality in scene switching
 * and to allow implementation of an overlay/Heads Up Display (HUD) for the game.
 *
 * @author: Kevin Pinto - Wrote the entire landing scene and all functionality within, initially as part of mainDriver, then separated it into its own class.
 */


import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class landingScene extends Scene {

    //declare Scene variable to allow getScene() to return the scene
    private final Scene landingScene;
    public Button exitButton, helpButton, asteroidHomeButton, andromedaArithmetic,cosmicCountingButton, galacticGeometryButton;

    public landingScene() {

        super(new Pane(), 1366, 768);
        Pane root = new Pane();

        //landingScene is the homepage/landing page of the software
        landingScene = new Scene(root, 1366, 768);

        //loading the stylesheet(s)
        landingScene.getStylesheets().add(("styles/stylesheet.css"));

        //setting the background image
        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));


        //setting the cursor to the rocket ship
        Image smShip = new Image("file:resources/assets/smallSpaceship.png");
        ImageCursor cursor = new ImageCursor(smShip, 0, 0);
        landingScene.setCursor(cursor);

        //Setting the font
        Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.rgb(243, 5, 1));
        text.setId("title");

        //loading images for buttons and other assets and creating image views
        Image exitButtonImage, helpButtonImage, asteroidHomeImage, smallShipImage, andromedaPlanetImage, galaxyImage, cosmicCountingImage;
        exitButtonImage = new Image("file:resources/assets/exit.png");
        asteroidHomeImage = new Image("file:resources/assets/Asteroid.png");
        helpButtonImage = new Image("file:resources/assets/help2.png");
        smallShipImage = new Image("file:resources/assets/smallSpaceship2.png");
        andromedaPlanetImage = new Image("file:resources/assets/planet 200x200.png");
        galaxyImage = new Image("file:resources/assets/galaxy-transparent-24.png");
        cosmicCountingImage = new Image("file:resources/assets/planet 300x250.png");

        //image views for the buttons and other assets
        ImageView exitButtonImageView, helpButtonImageView, asteroidHomeImageView, smallShipImageView, andromedaPlanetImageView, galaxyImageView, cosmicCountingImageView;

        //exit and help buttons
        exitButtonImageView = new ImageView(exitButtonImage);
        exitButtonImageView.setFitHeight(96);
        exitButtonImageView.setFitWidth(96);
        helpButtonImageView = new ImageView(helpButtonImage);
        helpButtonImageView.setFitHeight(96);
        helpButtonImageView.setFitWidth(96);
        exitButton = new Button("", exitButtonImageView);
        helpButton = new Button("", helpButtonImageView);
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
        });

        //exit button, leads back to log in screen
        exitButton.setOnAction(e -> {
            System.out.println("Exit button pressed");
        });


        //small spaceship that starts on top of the 'home' asteroid
        smallShipImageView = new ImageView(smallShipImage);
        smallShipImageView.setLayoutX(95);
        smallShipImageView.setLayoutY(152);
        smallShipImageView.setRotate(-25);


        //home button/asteroid that returns the user to the landing screen
        asteroidHomeImageView = new ImageView(asteroidHomeImage);
        asteroidHomeButton = new Button("", asteroidHomeImageView);
        asteroidHomeButton.setLayoutX(-100);
        asteroidHomeButton.setLayoutY(200);
        asteroidHomeButton.setStyle("-fx-background-color: transparent;");

        asteroidHomeButton.setOnAction(e -> {
            System.out.println("Asteroid Home button pressed");
        });

        //tooltip for the asteroid/home button
        Tooltip home = new Tooltip("Return to Home-screen!");
        asteroidHomeButton.setTooltip(home);


        //andromeda arithmetic
        andromedaPlanetImageView = new ImageView(andromedaPlanetImage);
        andromedaPlanetImageView.setFitHeight(250);
        andromedaPlanetImageView.setFitWidth(250);

        andromedaArithmetic = new Button("", andromedaPlanetImageView);
        andromedaArithmetic.setLayoutX(275);
        andromedaArithmetic.setLayoutY(460);
        andromedaArithmetic.setStyle("-fx-background-color: transparent;");
        Text andromedaText = new Text(75, 715, "Andromeda Arithmetic");
        andromedaText.setFill(Color.rgb(97, 197, 184));
        andromedaText.setId("andromedaText");
        Tooltip andromeda = new Tooltip("Andromeda Arithmetic");
        andromedaArithmetic.setTooltip(andromeda);
        andromedaArithmetic.setOnAction(e -> {
            System.out.println("Andromeda Arithmetic button pressed");
//            andromedaArithmetic.setDisable(true);

        });


        //Galactic Geometry assets
        galaxyImageView = new ImageView(galaxyImage);
        galaxyImageView.setFitHeight(379);
        galaxyImageView.setFitWidth(800);
        Text galacticGeometryText = new Text(500, 490, "Galactic Geometry");
        galacticGeometryText.setFill(Color.rgb(189, 182, 190));
        galacticGeometryText.setId("galacticGeometryText");
        galacticGeometryButton = new Button("", galaxyImageView);
        galacticGeometryButton.setLayoutX(300);
        galacticGeometryButton.setLayoutY(110);
        galacticGeometryButton.setStyle("-fx-background-color: transparent;");
        Tooltip galacticGeometry = new Tooltip("Galactic Geometry");
        galacticGeometryButton.setTooltip(galacticGeometry);

        galacticGeometryButton.setOnAction(e -> {
            System.out.println("Galactic Geometry button pressed");
            //stage.setScene(galacticGeometryScene);
        });

        //Cosmic Counting assets
        cosmicCountingImageView = new ImageView(cosmicCountingImage);
        cosmicCountingImageView.setFitHeight(250);
        cosmicCountingImageView.setFitWidth(300);
        cosmicCountingButton = new Button("", cosmicCountingImageView);
        cosmicCountingButton.setLayoutX(1000);
        cosmicCountingButton.setLayoutY(430);
        cosmicCountingButton.setStyle("-fx-background-color: transparent;");
        Text cosmicCountingText = new Text(850, 715, "Cosmic Counting");
        cosmicCountingText.setFill(Color.rgb(224,171,76));
        cosmicCountingText.setId("cosmicCountingText");
        Tooltip cosmicCounting = new Tooltip("Cosmic Counting");
        cosmicCountingButton.setTooltip(cosmicCounting);

        cosmicCountingButton.setOnAction(e -> {
            System.out.println("Cosmic Counting button pressed");
        });

        //adding all the nodes to the root
        root.getChildren().addAll(exitButton, text, helpButton, smallShipImageView, asteroidHomeButton, cosmicCountingButton, andromedaArithmetic, andromedaText, galacticGeometryButton, galacticGeometryText, cosmicCountingText);


    }


    public Scene getScene() {
        return landingScene;
    }

}
