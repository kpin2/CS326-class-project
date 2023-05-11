import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class: AndromedaArithmeticScene
 * Generate the Scene for multiple choice questions
 */
public class AndromedaArithmeticScene extends Scene {

    private final Scene andromedaScene;
    private Pane root;
    // Scoreboard class used to track number of questions answered and correct responses
    private scoreboard score;
    // Variable to hold the randomly generated question
    public String randomQuestion;

    //Class that generates random questions
    private Questions questions1;
    //Variable to tell us the level of student
    private char grade;

    // Toolbar buttons to exit or reset the scene
    private Button exitButton, resetButton;
    // Button used to indicate response is ready
    private Button submitButton;
    //  Radio Buttons to capture the answers
    private RadioButton choice1, choice2, choice3, choice4;
    //Variable to display the question
    private Label questionLabel;
    //Pop up Alert box to ask for difficulty level
    public Alert difficulty;
    //Container to hold 4 answers to the question
    Map<String, String> answers;
    ImageView imageView;

    /**
     * Medthod: Class constructor.
     * Responsible to build the scene
     * Event Handle for submit button
     */
    public AndromedaArithmeticScene() {

        super(new Pane(),1366, 768);
        root = new Pane();
        andromedaScene = new Scene(root, 1366, 768);
        answers = new HashMap<>();
        score = new scoreboard();

        //Build AlertBox for difficulty
        difficulty = new Alert(Alert.AlertType.CONFIRMATION);
        difficulty.setTitle("Select difficulty");
        difficulty.setHeaderText("Select difficulty");
        difficulty.setContentText("Choose your option.");
        ButtonType buttonTypeOne = new ButtonType("Easy");
        ButtonType buttonTypeTwo = new ButtonType("Medium");
        ButtonType buttonTypeThree = new ButtonType("Hard");

        difficulty.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);


        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //imageView to hold medals image
        imageView = new ImageView();
        Scale scaleTransformation = new Scale();
        scaleTransformation.setX(0.70);
        scaleTransformation.setY(0.70);
        scaleTransformation.setPivotX(600);
        scaleTransformation.setPivotY(400);
        imageView.getTransforms().add(scaleTransformation);
        imageView.getTransforms().add(scaleTransformation);
        imageView.getTransforms().add(scaleTransformation);

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);


        resetButton = new Button("Reset");
        resetButton.setLayoutX(110);
        resetButton.setLayoutY(10);

        Image exitButtonImage = new Image("file:resources/assets/exit.png");
        ImageView exitButtonImageView = new ImageView(exitButtonImage);
        exitButtonImageView.setFitHeight(96);
        exitButtonImageView.setFitWidth(96);
        exitButton = new Button("", exitButtonImageView);
        exitButton.setLayoutX(-5);
        exitButton.setLayoutY(-5);
        exitButton.setStyle("-fx-background-color: transparent;");
//        resetButton.setStyle("-fx-background-color: transparent;");

        //Setup to get random question
        grade = 'k';
        questions1 = new Questions(grade);
        randomQuestion = questions1.getQuestion();

        questionLabel = new Label(randomQuestion);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        questionLabel.setText(randomQuestion);
        questionLabel.setLayoutX(580);
        questionLabel.setLayoutY(300);

        String answer = new String(String.valueOf(questions1.getAnswer()));
        Random random = new Random();

        // Generate three random incorrect answers
        int incorrect1 = random.nextInt(10) + 1; // generates a random number between 1 and 10
        int incorrect2 = random.nextInt(10) + 1; // generates a different random number between 1 and 10
        while (incorrect2 == incorrect1) { // make sure the second incorrect answer is different from the first
            incorrect2 = random.nextInt(10) + 1;
        }
        int incorrect3 = random.nextInt(10) + 1; // generates a third different random number between 1 and 10
        while (incorrect3 == incorrect1 || incorrect3 == incorrect2) { // make sure the third answer is different from the first two
            incorrect3 = random.nextInt(10) + 1;
        }

// Add the correct and incorrect answers to the radio buttons
        choice1 = new RadioButton(answer);
        choice2 = new RadioButton(String.valueOf(incorrect1));
        choice3 = new RadioButton(String.valueOf(incorrect2));
        choice4 = new RadioButton(String.valueOf(incorrect3));

        ToggleGroup choicesGroup = new ToggleGroup();
        choicesGroup.getToggles().addAll(choice1, choice2, choice3, choice4);

        // Setting the font and color for the answers
        choice1.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice2.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice3.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice4.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        //add questionLabel to scene
        root.getChildren().add(questionLabel);


        submitButton = new Button("Submit");

        //Event handler when submit Button is pressed
        //Check the answer and update scoreboard
        //Generate a new question
        submitButton.setOnAction(event -> {
            RadioButton selectedChoice = (RadioButton) choicesGroup.getSelectedToggle();
            String ansString=selectedChoice.getText();
            Integer newAnswer = questions1.getAnswer();
            checkAns(ansString, newAnswer.toString());
            updateQuestionLabel();
        });

        //Event handler when reset button is pressed
        //Clears the scoreboard to 0
        //Generates a new question
        resetButton.setOnAction(e -> {
            score.setScore(0);
            score.setTrys(0);
            updateQuestionLabel();
        });

        //add buttons and header text to scene
        root.getChildren().addAll(resetButton, exitButton, text,  choice1, choice2, choice3, choice4, submitButton);

        double sceneWidth = andromedaScene.getWidth();
        double sceneHeight = andromedaScene.getHeight();
        questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        choice1.setLayoutX(sceneWidth / 2 - choice1.getWidth() / 2);
        choice1.setLayoutY(sceneHeight / 2 - 50);
        choice2.setLayoutX(sceneWidth / 2 - choice2.getWidth() / 2);
        choice2.setLayoutY(sceneHeight / 2 - 20);
        choice3.setLayoutX(sceneWidth / 2 - choice3.getWidth() / 2);
        choice3.setLayoutY(sceneHeight / 2 + 10);
        choice4.setLayoutX(sceneWidth / 2 - choice4.getWidth() / 2);
        choice4.setLayoutY(sceneHeight / 2 + 40);
        submitButton.setLayoutX(sceneWidth / 2 - submitButton.getWidth() / 2);
        submitButton.setLayoutY(sceneHeight / 2 + 100);

        submitButton.setLayoutX(sceneWidth / 2 - submitButton.getWidth() / 2);
        submitButton.setLayoutY(sceneHeight / 2 + 100);

    }

    /**
     * Helper method to get handle to the scene
     * @return Scene handle to fill in the blank scene
     */
    public Scene getScene(){
        return andromedaScene;
    }

    /**
     * Helper method to find the score of player
     * @return Integer showing how many questions answered were correct
     */
    public int getScore() {
        return score.getScore();
    }

    /**
     * Helper method to get handle to exit button
     * @return Button handle to exit button
     */
    public Button getExitButton(){

        return exitButton;
    }

    /**
     * Helper method to get handle to reset button
     * @return handle to reset button
     */
    public Button getResetButton(){

        return resetButton;
    }

    /**
     * Method returning a String showing the Result/Progress of player
     * @return String to be placed beside the planets
     */
    public String getScoreResult() {
        if(score.getTrys() > 0) {
            return "Result : " + score.getScore() + " / " + score.getTrys();
        }
        else {
            return "";
        }
    }

    /**
     * Helper method to set grade variable
     * @param gr charater for grade level
     */
    public void setGrade(char gr) {
        this.grade = gr;
    }

    /**
     * Method to check if the answer provided is correct
     * Updates the scoreboard with information
     * @param ans : User provided answer
     * @param newAns : Actual correct answer
     */
    public void checkAns(String ans, String newAns) {
        if (ans.equals(newAns)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct!");
            alert.showAndWait();
            score.addScore();
            score.addTrys();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong!");
            alert.showAndWait();
            score.addTrys();
        }
    }

    /**
     * Method to generate a new question and update the questionLabel Text node
     */
    public void updateQuestionLabel() {
        //These lines generate a new random question each time Submit is pressed
        if (score.getTrys() < 5){
            Random random = new Random();
            Questions newQuestion = new Questions(grade);
            randomQuestion = newQuestion.getQuestion();
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
            // Generate three random incorrect answers
            int incorrect1 = random.nextInt(10) + 1; // generates a random number between 1 and 10
            int incorrect2 = random.nextInt(10) + 1; // generates a different random number between 1 and 10
            while (incorrect2 == incorrect1) { // make sure the second incorrect answer is different from the first
                incorrect2 = random.nextInt(10) + 1;
            }
            int incorrect3 = random.nextInt(10) + 1; // generates a third different random number between 1 and 10
            while (incorrect3 == incorrect1 || incorrect3 == incorrect2) { // make sure the third answer is different from the first two
                incorrect3 = random.nextInt(10) + 1;
            }
            choice1.setText(String.valueOf(newQuestion.getAnswer()));
            choice2.setText(String.valueOf(incorrect1));
            choice3.setText(String.valueOf(incorrect2));
            choice4.setText(String.valueOf(incorrect3));
            //deselect all options
            choice1.setSelected(false);
            choice2.setSelected(false);
            choice3.setSelected(false);
            choice4.setSelected(false);
            if(!root.getChildren().contains(submitButton)) {
                root.getChildren().add(submitButton);
            }
            if(!root.getChildren().contains(choice1)) {
                root.getChildren().add(choice1);
            }
            if(!root.getChildren().contains(choice2)) {
                root.getChildren().add(choice2);
            }
            if(!root.getChildren().contains(choice3)) {
                root.getChildren().add(choice3);
            }
            if(!root.getChildren().contains(choice4)) {
                root.getChildren().add(choice4);
            }
            if(root.getChildren().contains(imageView)) {
                root.getChildren().remove(imageView);
            }

        } else {
            randomQuestion = score.getresult();
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");
            root.getChildren().removeAll(submitButton, choice1, choice2, choice3, choice4);

            Image medalBronze = new Image("file:resources/assets/medalbronze.png");
            //ImageView imageViewBronze = new ImageView(medalBronze);

            Image medalSilver = new Image("file:resources/assets/medalsilver.png");
            //ImageView imageViewSilver = new ImageView(medalSilver);

            Image medalGold = new Image("file:resources/assets/medalgold.png");
            //ImageView imageViewGold = new ImageView(medalGold);

            if(root.getChildren().contains(imageView)) {
                root.getChildren().remove(imageView);
            }


            if(score.getScore() == 3){
                imageView.setImage(medalBronze);
                root.getChildren().add(imageView);
            }
             if(score.getScore() == 4){
                 imageView.setImage(medalSilver);
                 root.getChildren().add(imageView);
            }
            if(score.getScore() == 5){
                imageView.setImage(medalGold);
                root.getChildren().add(imageView);
            }
        }
    }
}
