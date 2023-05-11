import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

public class practiceFITB extends Scene {

    private final Scene practiceFITB;
    private Pane root;
    private scoreboard score;

    public int indx;

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
    public practiceFITB() {

        super(new Pane(),1366, 768);
        root = new Pane();
        practiceFITB = new Scene(root, 1366, 768);

        score = new scoreboard();


        difficulty = new Alert(Alert.AlertType.CONFIRMATION);
        difficulty.setTitle("Select difficulty");
        difficulty.setHeaderText("Select difficulty");
        difficulty.setContentText("Choose your option.");

        grade = 'k';
        questions1 = new Questions(grade);
        randomQuestion = questions1.getQuestion();
//        System.out.println("practiceFITB constructor and question is : " + randomQuestion);

        answerField = new TextField();
        answerField.setLayoutX(580);
        answerField.setLayoutY(330);
        root.getChildren().add(answerField);

        ButtonType buttonTypeOne = new ButtonType("Easy");
        ButtonType buttonTypeTwo = new ButtonType("Medium");
        ButtonType buttonTypeThree = new ButtonType("Hard");

        difficulty.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);
//        difficulty.showAndWait();






        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);
//        ToolBar toolBar = new ToolBar();


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

        questionLabel = new Label(randomQuestion);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        questionLabel.setText(randomQuestion);
        questionLabel.setLayoutX(580);
        questionLabel.setLayoutY(300);


        submitButton = new Button("Submit");


        //Label finalQuestionLabel = questionLabel;


        root.getChildren().add(questionLabel);
        submitButton.setOnAction(event -> {

            String ansString=answerField.getText();
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
            questions1 = new Questions(grade);
            randomQuestion = questions1.getQuestion();
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
            answerField.clear();

        });



        //finalQuestionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        //finalQuestionLabel.setLayoutX(580);
        //finalQuestionLabel.setLayoutY(300);

//        toolBar.getItems().addAll(resetButton,exitButton);
        root.getChildren().addAll(resetButton, exitButton, text,  submitButton);

        double sceneWidth = practiceFITB.getWidth();
        double sceneHeight = practiceFITB.getHeight();

     /*   questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        a.setLayoutX(sceneWidth / 2 - a.getWidth() / 2);
        a.setLayoutY(sceneHeight / 2 - 50);*/
        submitButton.setLayoutX(sceneWidth / 2 - submitButton.getWidth() / 2);
        submitButton.setLayoutY(sceneHeight / 2 + 100);






    }


    public Scene getScene(){
        return practiceFITB;
    }


    public int getScore() {
        return score.getScore();
    }
    // Gives a random variable
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
        if (score.getTrys() < 5){
            Questions newQuestion = new Questions(grade);
            randomQuestion = newQuestion.getQuestion();
            questionLabel.setText(randomQuestion);
            answerField.clear();
        } else {
            randomQuestion = score.getresult();
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");
            root.getChildren().removeAll(submitButton,answerField);
        }
    }


}