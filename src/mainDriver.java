/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * mainDriver.java - Driver program for the tutoring software/game of Mission: Math! Calls the JavaFX application start method and launches the software.
 *
 * @author Kevin Pinto - Wrote initial code with a basic setup for the main driver.
 * */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class mainDriver extends Application {

    private final loginScene loginScene;
    private final accountCreation accountCreation;
    private final landingScene landingScene;
    private final practiceFITB practiceFITB;
    private final practiceTF practiceTF;
    private final practiceExamScene practiceExamScene;
    private final beginningScene beginningScene;
    private final databaseOps dbOps;
    //private final avatarSelection selectionScene;


    public mainDriver() {
        //initializing the scenes
        this.loginScene = new loginScene();
        this.landingScene = new landingScene();
        this.practiceFITB = new practiceFITB();
        this.practiceTF = new practiceTF();
        this.practiceExamScene = new practiceExamScene();
        this.accountCreation = new accountCreation();
        this.beginningScene = new beginningScene();
        this.dbOps = new databaseOps();
        //this.selectionScene = new avatarSelection();
    }

    //method to switch scenes
    public void switchScene(final Stage stage, final Scene scene) {
        stage.setScene(scene);
    }


    @Override
    public void start(final Stage stage) {

        //stage is the top level container, setting title displays the title in the title bar
        stage.setTitle("Mission: Math!");
        stage.setResizable(false);

        //loading our custom font first so the rest of the program can use it
        Font.loadFont("file:resources/font/SpaceMission.otf", 32);

        //adding different size ship icons to the stage
        final Image smShip = new Image("file:resources/assets/smallSpaceship.png");
        final Image px64Ship = new Image("file:resources/assets/64pxsmallSpaceship.png");
        final Image px48Ship = new Image("file:resources/assets/48pxsmallSpaceship.png");
        stage.getIcons().addAll(smShip, px64Ship, px48Ship);


        //start at the beginning scene and handle click events
        stage.setScene(this.beginningScene.getScene());
//        stage.setScene(this.landingScene.getScene());



        this.beginningScene.create.setOnMouseClicked(e -> this.switchScene(stage, this.accountCreation.getScene()));
        //this.beginningScene.login.setOnMouseClicked(e -> this.switchScene(stage, this.loginScene.getScene()));
        this.beginningScene.login.setOnMouseClicked(e -> this.switchScene(stage, this.landingScene.getScene()));

        final String[] userN = {""};
        final String[] passW = {""};
        //account creation

        this.accountCreation.register.setOnAction(e -> {
            if (this.accountCreation.password.getText().length() >= 8) {
                {
                    userN[0] = this.accountCreation.username.getText();
                    passW[0] = this.accountCreation.password.getText();
                    //this.switchScene(stage, this.selectionScene.getScene());
                }
            }
        });

/*
        this.selectionScene.confirmBtn.setOnAction(e-> {
            if (this.dbOps.addUser(userN[0], passW[0], this.selectionScene.avatar)) {
                this.switchScene(stage, this.landingScene.getScene());
            }
            else {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account Creation Error");
                alert.setHeaderText("Account Creation Error");
                alert.setContentText("An account with this username already exists. Please try again.");
                alert.showAndWait();
            }
        });

        this.selectionScene.exitButton.setOnAction(e-> {
            this.switchScene(stage, this.beginningScene.getScene());
        });
*/
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
        this.landingScene.getIntergalacticAlgebra().setOnAction(e -> this.switchScene(stage, this.practiceFITB.getScene()));
        this.landingScene.getCosmicCountingButton().setOnAction(e -> this.switchScene(stage, this.practiceTF.getScene()));
        this.landingScene.getAndromedaArithmetic().setOnAction(e -> this.switchScene(stage, this.practiceExamScene.getScene()));

        // landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceExamScene.getScene()));
        //  landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceTF.getScene()));
        this.landingScene.getHelpButton().setOnAction(e -> this.switchScene(stage, this.practiceFITB.getScene()));
        this.practiceFITB.getExitButton().setOnAction(e -> this.switchScene(stage, this.landingScene.getScene()));

        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }

}