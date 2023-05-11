import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

/**
 * Class: IntergallacticAlgebraScene
 * Generates the scene for Fill in the blank Questions
 */

public class IntergallacticAlgebra extends Scene {

    private final Scene intergallacticScene;
    private Pane root;
    // Scoreboard class used to track number of questions answered and correct responses
    private scoreboard score;

    public String randomQuestion;

    private Questions questions1;
    private char grade;
    //private Label questionLabel;
    private String finalQuestion, finalAnswer;

    private Button exitButton;
    private Button resetButton;
    private Button submitButton;
    private TextField answerField;
    private Label questionLabel;
    public Alert difficulty;
    ImageView imageView;

    public IntergallacticAlgebra() {

        super(new Pane(), 1366, 768);
        root = new Pane();
        intergallacticScene = new Scene(root, 1366, 768);

        score = new scoreboard();


        difficulty = new Alert(Alert.AlertType.CONFIRMATION);
        difficulty.setTitle("Select difficulty");
        difficulty.setHeaderText("Select difficulty");
        difficulty.setContentText("Choose your option.");

        grade = 'k';
        questions1 = new Questions(grade);
        randomQuestion = questions1.getQuestion();

        answerField = new TextField();
        answerField.setLayoutX(580);
        answerField.setLayoutY(330);
        root.getChildren().add(answerField);

        ButtonType buttonTypeOne = new ButtonType("Easy");
        ButtonType buttonTypeTwo = new ButtonType("Medium");
        ButtonType buttonTypeThree = new ButtonType("Hard");

        difficulty.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);


        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/cosmic_background.jpg", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
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


        Image resetImage = new Image("file:resources/assets/reset1-removebg-preview.png");
        ImageView resetImageView = new ImageView(resetImage);
        resetImageView.setFitHeight(96);
        resetImageView.setFitWidth(96);
        resetButton = new Button("", resetImageView);
        resetButton.setLayoutX(250);
        resetButton.setLayoutY(10);
        resetButton.setStyle("-fx-background-color: transparent;");


        questionLabel = new Label(randomQuestion);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        questionLabel.setText(randomQuestion);
        questionLabel.setLayoutX(580);
        questionLabel.setLayoutY(300);

        submitButton = new Button("Submit");

        landingScene landingScene = new landingScene();
        Button homeButton = landingScene.getAsteroidHomeButton();
        homeButton.setLayoutX(-100);
        homeButton.setLayoutY(200);
        homeButton.setStyle("-fx-background-color: transparent;");

        Image exitButtonImage = new Image("file:resources/assets/exit.png");
        ImageView exitButtonImageView = new ImageView(exitButtonImage);
        exitButtonImageView.setFitHeight(96);
        exitButtonImageView.setFitWidth(96);
        exitButton = new Button("", exitButtonImageView);
        exitButton.setLayoutX(-5);
        exitButton.setLayoutY(-5);
        exitButton.setStyle("-fx-background-color: transparent;");

        ImageView smallShip = landingScene.getSmallShipImageView();
        smallShip.setLayoutX(95);
        smallShip.setLayoutY(152);
        smallShip.setRotate(-25);

        Image helpButtonImage = new Image("file:resources/assets/help2.png");
        ImageView helpButtonImageView = new ImageView(helpButtonImage);
        helpButtonImageView.setFitHeight(96);
        helpButtonImageView.setFitWidth(96);
        Button helpButton = new Button("", helpButtonImageView);
        helpButton.setLayoutX(96);
        helpButton.setLayoutY(5);
        helpButton.setStyle("-fx-background-color: transparent;");
        helpButton.setVisible(false);

        root.getChildren().addAll(smallShip, homeButton, helpButton);



        root.getChildren().add(questionLabel);
        submitButton.setOnAction(event -> {

            String ansString = answerField.getText();
            Integer newAnswer = questions1.getAnswer();
            checkAns(ansString, newAnswer.toString());
            updateQuestionLabel();
            /*
            if (ansString.equals(newAnswer.toString())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct!");
                alert.showAndWait();
                score.addScore();
                score.addTrys();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong!");
                alert.showAndWait();
                score.addTrys();
            }
          //These lines generate a new random question each time Submit is pressed
           if (score.getTrys() < 5){
               questions1 = new Questions(grade);
               randomQuestion = questions1.getQuestion();
               finalQuestionLabel.setText(randomQuestion);
               answerField.clear();

            } else {
               randomQuestion = score.getresult();
               finalQuestionLabel.setText(randomQuestion);
               finalQuestionLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");
               root.getChildren().removeAll(submitButton,answerField);
           }

             */
        });


        resetButton.setOnAction(e -> {
            score.setScore(0);
            score.setTrys(0);
            updateQuestionLabel();
        });


        root.getChildren().addAll(resetButton, exitButton, text, submitButton);

        double sceneWidth = intergallacticScene.getWidth();
        double sceneHeight = intergallacticScene.getHeight();

     /*   questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        a.setLayoutX(sceneWidth / 2 - a.getWidth() / 2);
        a.setLayoutY(sceneHeight / 2 - 50);*/
        submitButton.setLayoutX(sceneWidth / 2 - submitButton.getWidth() / 2);
        submitButton.setLayoutY(sceneHeight / 2 + 100);

    }


    public Scene getScene() {
        return intergallacticScene;
    }


    public int getScore() {
        return score.getScore();
    }

    // Gives a random variable
    public Button getExitButton() {
        return exitButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public String getScoreResult() {
        if (score.getTrys() > 0) {
            return "Result : " + score.getScore() + " / " + score.getTrys();
        } else {
            return "";
        }
    }

    public void setGrade(char gr) {
        this.grade = gr;
    }


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

    public void updateQuestionLabel() {
        //These lines generate a new random question each time Submit is pressed
        if (score.getTrys() < 5) {
            Questions newQuestion = new Questions(grade);
            randomQuestion = newQuestion.getQuestion();
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
            answerField.clear();
            if(!root.getChildren().contains(submitButton)) {
                root.getChildren().add(submitButton);
            }
            if(!root.getChildren().contains(answerField)) {
                root.getChildren().add(answerField);
            }
            if(root.getChildren().contains(imageView)) {
                root.getChildren().remove(imageView);
            }
            //root.getChildren().addAll(submitButton, answerField);
        } else {
            randomQuestion = score.getresult();
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");
            root.getChildren().removeAll(submitButton, answerField);

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