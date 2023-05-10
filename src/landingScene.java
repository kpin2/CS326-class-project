/* CSCI362 Software Engineering
 * Mission: Math! application
 * landingScene.java - Landing scene for the Mission: Math! application. This scene is the main menu
 * for the application and allows the user to select a math standard to play and then a difficulty level.
 * Each of the planets is a button that allows the user to travel to the planet and begin play.
 * Produced: 4/8/2023
 *
 * @author: Kevin Pinto
 */


import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class landingScene extends Scene {

    //declaring class variables
    private final Scene landingScene;
    private String username;
    private Button exitButton;
    private Button helpButton;
    private Button asteroidHomeButton;
    private Button andromedaArithmetic;
    private Button cosmicCountingButton;
    private Button intergalacticAlgebra;
    private Image avatarImage;
    private ImageView avatarImageView;
    private Button avatarButton;

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
        Text text = new Text(250, 100, "Mission: Math!");
        text.setFill(Color.rgb(243, 5, 1));
        text.setId("title");

        //creating the rectangle for the avatar display
        Rectangle avatarDisplay = new Rectangle(250, 275, Color.rgb(0, 0, 0));
        avatarDisplay.setArcWidth(20);
        avatarDisplay.setArcHeight(20);
        avatarDisplay.setStroke(Color.rgb(160, 33, 0));
        avatarDisplay.setStrokeWidth(1);
        avatarDisplay.setOpacity(.6);
        avatarDisplay.setLayoutX(1100);
        avatarDisplay.setLayoutY(15);

        avatarImage = new Image("file:resources/assets/avatars/Astronaut Cat 500px removebg.png");
        avatarImageView = new ImageView(avatarImage);
        avatarImageView.setFitHeight(250);
        avatarImageView.setFitWidth(250);
        avatarImageView.setLayoutX(1100);
        avatarImageView.setLayoutY(15);


        //creating the avatar button
        avatarButton = new Button("", avatarImageView);
        avatarButton.setLayoutX(1100);
        avatarButton.setLayoutY(15);
        avatarButton.setStyle("-fx-background-color: transparent");
        avatarButton.setOnAction(e -> {
            System.out.println("Avatar button pressed");
        });

        //creating the text for the username
        Text usernameText = new Text(1100, 300, username);
        usernameText.setFill(Color.rgb(243, 5, 1));
        usernameText.setId("username");


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
        setExitButton(new Button("", exitButtonImageView));
        setHelpButton(new Button("", helpButtonImageView));
        getHelpButton().setLayoutX(96);
        getHelpButton().setLayoutY(5);
        getExitButton().setLayoutX(-5);
        getExitButton().setLayoutY(5);
        getExitButton().setStyle("-fx-background-color: transparent;");
        getHelpButton().setStyle("-fx-background-color: transparent;");

        //tooltips for the help and exit buttons
        Tooltip help = new Tooltip("Need Help? Click here!");
        getHelpButton().setTooltip(help);
        Tooltip exit = new Tooltip("Return to Login Screen");
        getExitButton().setTooltip(exit);

        //help button action, will switch the scene to the tutorial/practice section
        getHelpButton().setOnAction(e -> {
            System.out.println("Help button pressed");
        });

        //exit button, leads back to log in screen
        getExitButton().setOnAction(e -> {
            System.out.println("Exit button pressed");
        });


        //small spaceship that starts on top of the 'home' asteroid
        smallShipImageView = new ImageView(smallShipImage);
        smallShipImageView.setLayoutX(95);
        smallShipImageView.setLayoutY(152);
        smallShipImageView.setRotate(-25);


        //home button/asteroid that returns the user to the landing screen
        asteroidHomeImageView = new ImageView(asteroidHomeImage);
        setAsteroidHomeButton(new Button("", asteroidHomeImageView));
        getAsteroidHomeButton().setLayoutX(-100);
        getAsteroidHomeButton().setLayoutY(200);
        getAsteroidHomeButton().setStyle("-fx-background-color: transparent;");

        getAsteroidHomeButton().setOnAction(e -> {
            System.out.println("Asteroid Home button pressed");
        });

        //tooltip for the asteroid/home button
        Tooltip home = new Tooltip("Return to Home-screen!");
        getAsteroidHomeButton().setTooltip(home);


        //andromeda arithmetic
        andromedaPlanetImageView = new ImageView(andromedaPlanetImage);
        andromedaPlanetImageView.setFitHeight(250);
        andromedaPlanetImageView.setFitWidth(250);

        setAndromedaArithmetic(new Button("", andromedaPlanetImageView));
        getAndromedaArithmetic().setLayoutX(275);
        getAndromedaArithmetic().setLayoutY(460);
        getAndromedaArithmetic().setStyle("-fx-background-color: transparent;");
        Text andromedaText = new Text(75, 715, "Andromeda Arithmetic");
        andromedaText.setFill(Color.rgb(97, 197, 184));
        andromedaText.setId("andromedaText");
        Tooltip andromeda = new Tooltip("Andromeda Arithmetic");
        getAndromedaArithmetic().setTooltip(andromeda);
        getAndromedaArithmetic().setOnAction(e -> {
            System.out.println("Andromeda Arithmetic button pressed");

        });


        //Galactic Geometry assets
        galaxyImageView = new ImageView(galaxyImage);
        galaxyImageView.setFitHeight(379);
        galaxyImageView.setFitWidth(800);
        Text intergalacticAlgebraText = new Text(500, 490, "Intergalactic Algebra");
        intergalacticAlgebraText.setFill(Color.rgb(189, 182, 190));
        intergalacticAlgebraText.setId("intergalacticAlgebraText");
        setIntergalacticAlgebra(new Button("", galaxyImageView));
        getIntergalacticAlgebra().setLayoutX(300);
        getIntergalacticAlgebra().setLayoutY(110);
        getIntergalacticAlgebra().setStyle("-fx-background-color: transparent;");
        Tooltip intergalacticTooltip = new Tooltip("Intergalactic Algebra");
        getIntergalacticAlgebra().setTooltip(intergalacticTooltip);

        getIntergalacticAlgebra().setOnAction(e -> {
            System.out.println("Intergalactic Algebra button pressed");
        });

        //Cosmic Counting assets
        cosmicCountingImageView = new ImageView(cosmicCountingImage);
        cosmicCountingImageView.setFitHeight(250);
        cosmicCountingImageView.setFitWidth(300);
        setCosmicCountingButton(new Button("", cosmicCountingImageView));
        getCosmicCountingButton().setLayoutX(1000);
        getCosmicCountingButton().setLayoutY(430);
        getCosmicCountingButton().setStyle("-fx-background-color: transparent;");
        Text cosmicCountingText = new Text(850, 715, "Cosmic Counting");
        cosmicCountingText.setFill(Color.rgb(224, 171, 76));
        cosmicCountingText.setId("cosmicCountingText");
        Tooltip cosmicCounting = new Tooltip("Cosmic Counting");
        getCosmicCountingButton().setTooltip(cosmicCounting);

        getCosmicCountingButton().setOnAction(e -> {
            System.out.println("Cosmic Counting button pressed");
        });

        //adding all the nodes to the root
        root.getChildren().addAll(getExitButton(), text, getHelpButton(), smallShipImageView, getAsteroidHomeButton(), getCosmicCountingButton(), getAndromedaArithmetic(), andromedaText, getIntergalacticAlgebra(), intergalacticAlgebraText, cosmicCountingText, avatarDisplay, avatarImageView, avatarButton);
    }


    public Scene getScene() {
        return landingScene;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }

    public Button getHelpButton() {
        return helpButton;
    }

    public void setHelpButton(Button helpButton) {
        this.helpButton = helpButton;
    }

    public Button getAsteroidHomeButton() {
        return asteroidHomeButton;
    }

    public void setAsteroidHomeButton(Button asteroidHomeButton) {
        this.asteroidHomeButton = asteroidHomeButton;
    }

    public Button getAndromedaArithmetic() {
        return andromedaArithmetic;
    }

    public void setAndromedaArithmetic(Button andromedaArithmetic) {
        this.andromedaArithmetic = andromedaArithmetic;
    }

    public Button getCosmicCountingButton() {
        return cosmicCountingButton;
    }

    public void setCosmicCountingButton(Button cosmicCountingButton) {
        this.cosmicCountingButton = cosmicCountingButton;
    }

    public Button getIntergalacticAlgebra() {
        return intergalacticAlgebra;
    }

    public void setIntergalacticAlgebra(Button intergalacticAlgebra) {
        this.intergalacticAlgebra = intergalacticAlgebra;
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Image avatarImage) {
        this.avatarImage = avatarImage;
    }

}
