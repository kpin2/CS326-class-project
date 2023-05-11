import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class practiceTF extends Scene {

    private final Scene practiceTF;
    private Pane root;
    private scoreboard score;
    private Button exitButton;
    private Button resetButton;
    private Button submitButton;
    private RadioButton choice1, choice2;
    private Label questionLabel;
    private String randomQuestion;
    private char grade;


    private String[] questions = {
            "2 + 2 equals 4",
            "A quadrilateral has 3 sides",
            "16/4 equals 4",
            "A triangle has 3 sides",
            "4 - 2 equals 6"
    };
    private String[] answers = {
            "True",
            "False",
            "True",
            "True",
            "False"
    };

    private int indx;

    public Alert difficulty;
    public practiceTF() {

        super(new Pane(),1366, 768);
        root = new Pane();
        practiceTF = new Scene(root, 1366, 768);
        score = new scoreboard();
        Random rand = new Random();


        difficulty = new Alert(Alert.AlertType.CONFIRMATION);
        difficulty.setTitle("Select difficulty");
        difficulty.setHeaderText("Select difficulty");
        difficulty.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Easy");
        ButtonType buttonTypeTwo = new ButtonType("Medium");
        ButtonType buttonTypeThree = new ButtonType("Hard");
        difficulty.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);

        indx =rand.nextInt(questions.length);

        randomQuestion = questions[indx];

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);
        ToolBar toolBar = new ToolBar();

        exitButton = new Button("Exit");
        resetButton = new Button("Reset");

        Image exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        exitButton.setGraphic(new ImageView(exitButtonImage));

        questionLabel = new Label(randomQuestion);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice1 = new RadioButton("True");
        choice2 = new RadioButton("False");


        ToggleGroup choicesGroup = new ToggleGroup();
        choicesGroup.getToggles().addAll(choice1, choice2);
        submitButton = new Button("Submit");

        // Setting the font and color for the answers
        choice1.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice2.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");



        submitButton.setOnAction(event -> {
            RadioButton selectedChoice = (RadioButton) choicesGroup.getSelectedToggle();
            String ansString=selectedChoice.getText();
            checkAns(ansString);
            updateQuestionLabel();
        });


        exitButton.setOnAction(e -> {
        });

        resetButton.setOnAction(e -> {
            score.setScore(0);
            score.setTrys(0);
            updateQuestionLabel();
            root.getChildren().addAll(choice1, choice2, submitButton);
        });

        toolBar.getItems().addAll(resetButton, exitButton);
        root.getChildren().addAll(toolBar, text, questionLabel, choice1, choice2, submitButton);
        double sceneWidth = practiceTF.getWidth();
        double sceneHeight = practiceTF.getHeight();

        questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        choice1.setLayoutX(sceneWidth / 2 - choice1.getWidth() / 2);
        choice1.setLayoutY(sceneHeight / 2 - 50);
        choice2.setLayoutX(sceneWidth / 2 - choice2.getWidth() / 2);
        choice2.setLayoutY(sceneHeight / 2 - 20);
        submitButton.setLayoutX(sceneWidth / 2 - submitButton.getWidth() / 2);
        submitButton.setLayoutY(sceneHeight / 2 + 100);


   /*     WebView webview = new WebView();
        webview.getEngine().load(

                "https://www.youtube.com/embed/lBCIGY3dWXQ" //be sure to get the YouTube embed URL
        );
        webview.setPrefSize(640, 390);
        webview.setLayoutX(0);
        webview.setLayoutY(0);
        root2.getChildren().add(webview);
        scene.setRoot(root2);*/


    }

    public Scene getScene(){
        return practiceTF;
    }
    public Button getExitButton(){

        return exitButton;
    }

    public Button getResetButton(){

        return resetButton;
    }

    public String getScoreResult() {
        if(score.getTrys() > 0) {
            return "Result : " + score.getScore() + " / " + score.getTrys();
        }
        else {
            return "";
        }
    }

    public int getScore() {
        return score.getScore();
    }

    // Gives a random variable
    public int getrandnum(int num){
        Random rand = new Random();
        return (rand.nextInt(num));
    }
    public void checkAns(String ans) {
        if (ans.equals(answers[indx])) {
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
        if (score.getTrys() < 5){
            indx = getrandnum(questions.length);
            randomQuestion = questions[indx];
            questionLabel.setText(randomQuestion);


        } else {
            randomQuestion = score.getresult();
            questionLabel.setText(randomQuestion);
            root.getChildren().removeAll(choice1, choice2, submitButton);
        }
    }

    public void setGrade(char gr) {
        this.grade = gr;
    }

}

