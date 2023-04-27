/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * mainDriver.java - Driver program for the tutoring software/game of Mission: Math! Calls the JavaFX application start method and launches the software.
 *
 * @author Kevin Pinto - Wrote initial code with a basic setup for the main driver.
 * */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;


public class mainDriver extends Application {

    private final loginScene loginScene;
    private final accountCreation accountCreation;
    private final FinalResult finalResult;
    private final practiceExamScene practiceExamScene;
    private final landingScene landingScene;
    private final practiceTF practiceTF;
    private final practiceFITB practiceFITB;
    private final beginningScene beginningScene;
    private final databaseOps dbOps;
    private final overlayScene overlayScene;
    private final avatarSelection selectionScene;

    public mainDriver() {
        //initializing the scenes
        loginScene = new loginScene();
        finalResult = new FinalResult();
        practiceExamScene = new practiceExamScene();
        selectionScene = new avatarSelection();
        landingScene = new landingScene();
        practiceTF = new practiceTF();
        practiceFITB = new practiceFITB();
        accountCreation = new accountCreation();
        beginningScene = new beginningScene();
        dbOps = new databaseOps();
        overlayScene = new overlayScene();
    }

    //method to switch scenes
    public void switchScene(Stage stage, Scene scene) {
        stage.setScene(scene);
    }
    //method to check if the login information is correct
    public boolean correctLogin(TextField username, PasswordField password) {
        if (username.getText() != null && password.getText() != null) {
            return username.getText().length() >= 6 && password.getText().length() >= 8;
        } else {
            loginScene.username.setPromptText("Error! Please enter username");
            loginScene.password.setPromptText("Error! Please enter password");
            return false;
        }
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


        //start at the beginning scene and handle click events
        stage.setScene(beginningScene.getScene());

        //stage.setScene(overlayScene.overlayScene);

        beginningScene.create.setOnMouseClicked(e -> switchScene(stage, accountCreation.getScene()));
        beginningScene.login.setOnMouseClicked(e -> switchScene(stage, loginScene.getScene()));

        String userN = "";
        String passW = "";
        //account creation
        accountCreation.register.setOnAction(e -> {
            if (accountCreation.password.getText().length() >= 8) {
                if(accountCreation.password.getText().equals(accountCreation.confirm.getText()))
                {
                    userN.equals(accountCreation.username.getText());
                    passW.equals(accountCreation.password.getText());
                    switchScene(stage, selectionScene.getScene());
                }
            }
        });

        selectionScene.nextSelect.setOnAction(e-> {

        });

        selectionScene.prevSelect.setOnAction(e-> {

        });

        selectionScene.confirmBtn.setOnAction(e-> {
            dbOps.addUser(userN, passW, selectionScene.avatar);
        });

        selectionScene.exitButton.setOnAction(e-> {
            switchScene(stage, beginningScene.getScene());
        });



        //once login is successful, switch to the landing scene
        loginScene.loginButton.setOnAction(e -> {
            /*
            try {

                Image avatar = dbOps.getAvatar(loginScene.username.getText());
                loginScene.avatarImage.setImage(avatar);
                System.out.println(loginScene.avatarImage.getImage());
                System.out.println(avatar);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            */

            if (dbOps.verifyLogin(loginScene.username.getText(), loginScene.password.getText())) {
                switchScene(stage, landingScene.getScene());
            }
            else {
                loginScene.username.setPromptText("Error! Please enter username");
                loginScene.password.setPromptText("Error! Please enter password");
            }
        });


        Alert logoutConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        logoutConfirmation.setTitle("Logout and Return to Login Screen");
        logoutConfirmation.setHeaderText("Are you sure you want to logout?");
        logoutConfirmation.setContentText("You will be returned to the login screen.");

        loginScene.exitButton.setOnAction(e-> {
            switchScene(stage, beginningScene.getScene());
        });

        /*
        accountCreation.register.setOnAction(e->{
            switchScene(stage, landingScene.getScene());
        });
         */

        accountCreation.exitButton.setOnAction(e->{
            switchScene(stage, beginningScene.getScene());
        });

        landingScene.exitButton.setOnAction(e ->
                logoutConfirmation.showAndWait().ifPresent(response -> {
                    if (response.getText().equals("OK")) {
                        switchScene(stage, loginScene.getScene());
                    }
                }));


        landingScene.asteroidHomeButton.setOnAction(e -> switchScene(stage, landingScene.getScene()));
      /*
      In order to see the different style of questions(for now) you have to comment out the lines that have
      the question types that you DO NOT want to see. Here is a small list to see the question types:
      Multiple choice:  landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceExamScene.getScene()));
      True/False: landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceTF.getScene()));
      Fill in the Blank: landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceFITB.getScene()));
      */

        // landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceExamScene.getScene()));
        //  landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceTF.getScene()));
        landingScene.helpButton.setOnAction(e -> switchScene(stage, practiceFITB.getScene()));


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}