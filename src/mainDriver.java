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
    private final IntergallacticAlgebra intergallacticScene;
    private final CosmicCountingScene cosmicScene;

    private final beginningScene beginningScene;
    private final FinalResult finalResult;
    private final databaseOps dbOps;
    private final avatarSelection selectionScene;
    private final AndromedaArithmeticScene andromedaScene;

    //constructor to initialize all the scenes and dbOps
    public mainDriver() {
        this.loginScene = new loginScene();
        this.landingScene = new landingScene();
        this.intergallacticScene = new IntergallacticAlgebra();
        this.cosmicScene = new CosmicCountingScene();
        this.accountCreation = new accountCreation();
        this.beginningScene = new beginningScene();
        this.finalResult = new FinalResult();
        this.dbOps = new databaseOps();
        this.selectionScene = new avatarSelection();
        this.andromedaScene = new AndromedaArithmeticScene();
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

        //setting the stage to the beginning scene
        stage.setScene(this.beginningScene.getScene());

        //event handling for beginning scene
        this.beginningScene.create.setOnMouseClicked(e -> this.switchScene(stage, this.accountCreation.getScene()));
        this.beginningScene.login.setOnMouseClicked(e -> this.switchScene(stage, this.loginScene.getScene()));

        //account creation
        final String[] userN = {""};
        final String[] passW = {""};

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


        selectionScene.back.setOnAction(e -> {
            userN[0] = "";
            passW[0] = "";
            selectionScene.avatar = null;
            switchScene(stage, accountCreation.getScene());
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

        //event handler to go to Landing Scene when home planet is clicked
        this.landingScene.getAsteroidHomeButton().setOnAction(e -> {
            this.landingScene.setCosmicCountingResultTxt(this.cosmicScene.getScoreResult());
            this.landingScene.setIntergalacticAlgebraResultText(this.intergallacticScene.getScoreResult());
            this.landingScene.setAnrdomedaResultTxt(this.andromedaScene.getScoreResult());
            this.switchScene(stage, this.landingScene.getScene());
        });

        //event handler to go to Intergallactic Scene when planet is clicked on landing scene
        this.landingScene.getIntergalacticAlgebra().setOnAction(e -> {
            this.intergallacticScene.difficulty.showAndWait().ifPresent(response -> {
                if (response.getText().equals("Easy")) {
                    this.intergallacticScene.setGrade('k');
                } else if (response.getText().equals("Medium")) {
                    this.intergallacticScene.setGrade('1');
                } else if (response.getText().equals("Hard")) {
                    this.intergallacticScene.setGrade('3');
                }
            });
            this.intergallacticScene.updateQuestionLabel();
            this.switchScene(stage, this.intergallacticScene.getScene());
        });

        //event handler to go to Cosmic Scene when planet is clicked on landing scene
        this.landingScene.getCosmicCountingButton().setOnAction(e -> {
            this.cosmicScene.difficulty.showAndWait().ifPresent(response -> {
                if (response.getText().equals("Easy")) {
                    this.cosmicScene.setGrade('k');
                } else if (response.getText().equals("Medium")) {
                    this.cosmicScene.setGrade('2');
                } else if (response.getText().equals("Hard")) {
                    this.cosmicScene.setGrade('3');
                }
            });
            this.cosmicScene.updateQuestionLabel();

            this.switchScene(stage, this.cosmicScene.getScene());
        });

        //event handler to go to Andromeda Scene when planet is clicked on landing scene
        this.landingScene.getAndromedaArithmetic().setOnAction(e -> {
            this.andromedaScene.difficulty.showAndWait().ifPresent(response -> {
                if (response.getText().equals("Easy")) {
                    this.andromedaScene.setGrade('k');
                } else if (response.getText().equals("Medium")) {
                    this.andromedaScene.setGrade('2');
                } else if (response.getText().equals("Hard")) {
                    this.andromedaScene.setGrade('3');
                }
            });
            this.andromedaScene.updateQuestionLabel();

            this.switchScene(stage, this.andromedaScene.getScene());
        });


        this.landingScene.getHelpButton().setOnAction(e -> this.switchScene(stage, this.intergallacticScene.getScene()));

        //Event handler for exit button on IntergalacticAlgebra Scene
        this.intergallacticScene.getExitButton().setOnAction(e -> {

            if ((this.cosmicScene.getScore() == 5) && (this.intergallacticScene.getScore() == 5) && (this.andromedaScene.getScore() == 5)) {
                this.switchScene(stage, this.finalResult.getScene());
            } else {
                this.landingScene.setCosmicCountingResultTxt(this.cosmicScene.getScoreResult());
                this.landingScene.setIntergalacticAlgebraResultText(this.intergallacticScene.getScoreResult());
                this.landingScene.setAnrdomedaResultTxt(this.andromedaScene.getScoreResult());
                this.switchScene(stage, this.landingScene.getScene());
            }

        });

        //Event handler for exit button on CosmicCounting Scene
        this.cosmicScene.getExitButton().setOnAction(e -> {

            if ((this.cosmicScene.getScore() == 5) && (this.intergallacticScene.getScore() == 5) && (this.andromedaScene.getScore() == 5)) {
                this.switchScene(stage, this.finalResult.getScene());
            } else {
                this.landingScene.setCosmicCountingResultTxt(this.cosmicScene.getScoreResult());
                this.landingScene.setIntergalacticAlgebraResultText(this.intergallacticScene.getScoreResult());
                this.landingScene.setAnrdomedaResultTxt(this.andromedaScene.getScoreResult());
                this.switchScene(stage, this.landingScene.getScene());
            }

        });


        //Event handler for exit button on AndromedaArithmetic Scene
        this.andromedaScene.getExitButton().setOnAction(e -> {

            if ((this.cosmicScene.getScore() == 5) && (this.intergallacticScene.getScore() == 5) && (this.andromedaScene.getScore() == 5)) {
                this.switchScene(stage, this.finalResult.getScene());
            } else {
                this.landingScene.setCosmicCountingResultTxt(this.cosmicScene.getScoreResult());
                this.landingScene.setIntergalacticAlgebraResultText(this.intergallacticScene.getScoreResult());
                this.landingScene.setAnrdomedaResultTxt(this.andromedaScene.getScoreResult());
                this.switchScene(stage, this.landingScene.getScene());
            }

        });


        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }

}