/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * mainDriver.java - Main driver class for the Mission: Math! application. This class handles all scene switching and
 * event handling for the application, as well as the main method.
 * Produced: 3/25/2023
 *
 * @author Kevin Pinto
 * */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;


public class mainDriver extends Application {

    //declaring all the scenes and database operations
    private final loginScene loginScene;
    private final accountCreation accountCreation;
    private final landingScene landingScene;
    private final practiceFITB practiceFITB;
    private final beginningScene beginningScene;
    private final databaseOps dbOps;
    private final avatarSelection selectionScene;

    //constructor to initialize all the scenes and dbOps
    public mainDriver() {
        this.loginScene = new loginScene();
        this.landingScene = new landingScene();
        this.practiceFITB = new practiceFITB();
        this.accountCreation = new accountCreation();
        this.beginningScene = new beginningScene();
        this.dbOps = new databaseOps();
        this.selectionScene = new avatarSelection();
    }

    //method to switch scenes
    public void switchScene(final Stage stage, final Scene scene) {
        stage.setScene(scene);
    }

    //main method of JavaFX application
    @Override
    public void start(final Stage stage) {


        //fixed size for the stage and title
        stage.setTitle("Mission: Math!");
        stage.setResizable(false);

        //loading our custom font first so the rest of the program can use it
        Font.loadFont("file:resources/font/SpaceMission.otf", 32);

        //adding different size ship icons to the stage for the window icon, mouse cursor, and taskbar icon
        final Image smShip = new Image("file:resources/assets/smallSpaceship.png");
        final Image px64Ship = new Image("file:resources/assets/64pxsmallSpaceship.png");
        final Image px48Ship = new Image("file:resources/assets/48pxsmallSpaceship.png");
        final Image asteroid = new Image("file:resources/assets/asteroid2.png");
        final Image asteroidSmall = new Image("file:resources/assets/Asteroid2-100x100.png");
        final Image comet = new Image("file:resources/assets/Comet-PNG2.png");
        final Image comet2 = new Image("file:resources/assets/Comet-PNG-File.png");
        stage.getIcons().addAll(smShip, px64Ship, px48Ship, asteroid, asteroidSmall, comet, comet2);


        //start at the beginning scene and handle click events
        stage.setScene(this.beginningScene.getScene());
//        stage.setScene(this.practiceFITB.getScene());


        this.beginningScene.create.setOnMouseClicked(e -> this.switchScene(stage, this.accountCreation.getScene()));
        this.beginningScene.login.setOnMouseClicked(e -> this.switchScene(stage, this.loginScene.getScene()));


        final String[] userN = {""};
        final String[] passW = {""};
        //account creation

        this.accountCreation.register.setOnAction(e -> {
            if (this.accountCreation.password.getText().length() >= 8) {
                {
                    userN[0] = this.accountCreation.username.getText();
                    passW[0] = this.accountCreation.password.getText();
                    this.switchScene(stage, this.selectionScene.getScene());
                }
            }
        });


        this.selectionScene.confirmBtn.setOnAction(e -> {
            try {
                if (this.dbOps.addUser(userN[0], passW[0], this.selectionScene.avatar)) {
                    this.switchScene(stage, this.landingScene.getScene());
                } else {
                    final Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Account Creation Error");
                    alert.setHeaderText("Account Creation Error");
                    alert.setContentText("An account with this username already exists. Please try again.");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.selectionScene.exitButton.setOnAction(e -> {
            this.switchScene(stage, this.beginningScene.getScene());
        });

        //once login is successful, switch to the landing scene
        this.loginScene.loginButton.setOnAction(e -> {

            if (!this.dbOps.verifyLogin(this.loginScene.username.getText(), this.loginScene.password.getText())) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Login");
                alert.setContentText("Please enter a valid username and password.");
                alert.showAndWait();

                this.loginScene.username.setPromptText("Error! Please enter username");
                this.loginScene.password.setPromptText("Error! Please enter password");
            } else {
                System.out.println("Login Successful");
                Image avatar = dbOps.getAvatar(this.loginScene.username.getText());
                this.landingScene.setAvatarImage(avatar);
                this.switchScene(stage, this.landingScene.getScene());


            }
        });



        /* This is the code for the exit button on the login and account creation screens. Both of these screens can only return to the beginning scene.
         */
        this.loginScene.exitButton.setOnAction(e -> this.switchScene(stage, this.beginningScene.getScene()));
        this.accountCreation.exitButton.setOnAction(e -> this.switchScene(stage, this.beginningScene.getScene()));

        /* Confirm  */
        this.landingScene.getExitButton().setOnAction(e -> {


            final Alert logoutConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            logoutConfirmation.setTitle("Logout and Return to Login Screen");
            logoutConfirmation.setHeaderText("Are you sure you want to logout?");
            logoutConfirmation.setContentText("You will be returned to the login screen.");


            logoutConfirmation.showAndWait().ifPresent(response -> {
                if (response.getText().equals("OK")) {
                    this.switchScene(stage, this.loginScene.getScene());
                }
            });
        });


        this.landingScene.getAsteroidHomeButton().setOnAction(e -> this.switchScene(stage, this.landingScene.getScene()));


        // landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceExamScene.getScene()));
        //  landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceTF.getScene()));
        this.landingScene.getHelpButton().setOnAction(e -> this.switchScene(stage, this.practiceFITB.getScene()));


        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }

}