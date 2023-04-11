/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * mainDriver.java - Driver program for the tutoring software/game of Mission: Math! Calls the  JavaFX application start method and launches the software.
 *
 * @author Kevin Pinto - Wrote initial code with a basic setup for the main driver.
 * */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class mainDriver extends Application {

    //loginScene is the login screen
    private final loginScene loginScene;
    private final FinalResult finalResult;
    private final practiceExamScene practiceExamScene;
    private final landingScene landingScene;


    public mainDriver() {
        //initializing the loginScene
        loginScene = new loginScene();
        finalResult = new FinalResult();
        practiceExamScene = new practiceExamScene();
        landingScene = new landingScene();
    }

    public void switchScene(Stage stage, Scene scene) {
        stage.setScene(scene);
    }

    public boolean correctLogin(TextField username, PasswordField password) {
        if (username.getText() != null && password.getText() != null) {
            if (username.getText().length() >= 6 && password.getText().length() >= 8) {
                return true;
            } else {
                return false;
            }
        } else {
            loginScene.username.setPromptText("Error! Please enter username");
            loginScene.password.setPromptText("Error! Please enter password");
            return false;
        }
    }


    //method to switch to the login scene
    public void switchToLoginScene(Stage stage) {
        //getting the scene from the loginScene object
        Scene scene = loginScene.getScene();
        stage.setScene(scene);
    }

    public void switchToFinalResult(Stage stage) {
        //getting the scene from the loginScene object
        Scene scene = finalResult.getScene();
        stage.setScene(scene);
    }

    public void switchToPracticeExamScene(Stage stage) {
        //getting the scene from the loginScene object
        Scene scene = practiceExamScene.getScene();
        stage.setScene(scene);
    }


    @Override
    public void start(Stage stage) throws Exception {

        //stage is the top level container, setting title displays the title in the title bar
        stage.setTitle("Mission: Math!");
        stage.setResizable(false);

        //loading our custom font first so the rest of the program can use it
        Font.loadFont("file:resources/font/SpaceMission.otf", 32);

        //adding different size ship icons to the stage
        Image smShip = new Image("file:resources/assets/smallSpaceship.png");
        Image px64Ship = new Image("file:resources/assets/64pxsmallSpaceship.png");
        Image px48Ship = new Image("file:resources/assets/48pxsmallSpaceship.png");
        stage.getIcons().addAll(smShip, px64Ship, px48Ship);

        Alert logout = new Alert(Alert.AlertType.CONFIRMATION);
        logout.setTitle("Logout and Return to Login Screen");
        logout.setHeaderText("Are you sure you want to logout?");
        logout.setContentText("You will be returned to the login screen.");


        //once login is successful, switch to the landing scene
        loginScene.loginButton.setOnAction(e -> {
            if (correctLogin(loginScene.username, loginScene.password)) {
                switchScene(stage, landingScene.getScene());
            }
        });

        landingScene.exitButton.setOnAction(e -> switchScene(stage, loginScene.getScene()));

        landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceExamScene.getScene()));
        landingScene.asteroidHomeButton.setOnAction(e -> switchScene(stage, landingScene.getScene()));


        stage.setScene(loginScene.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
