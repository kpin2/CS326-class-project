import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;
import java.util.Random;

public class practiceFITB extends Scene {

    private final Scene practiceFITB;
    private scoreboard score;

    public int indx;

   public String randomQuestion;





    public practiceFITB() {

        super(new Pane(),1366, 768);
        Pane root = new Pane();
        practiceFITB = new Scene(root, 1366, 768);

        score = new scoreboard();

        Alert difficulty = new Alert(Alert.AlertType.CONFIRMATION);
        difficulty.setTitle("Select difficulty");
        difficulty.setHeaderText("Select difficulty");
        difficulty.setContentText("Choose your option.");

        char grade = '3';
        Questions questions1 = new Questions(grade);

        Label questionLabel= new Label(questions1.getQuestion());
        questionLabel.setLayoutX(580);
        questionLabel.setLayoutY(300);
        TextField answerField = new TextField();
        answerField.setLayoutX(580);
        answerField.setLayoutY(330);
        root.getChildren().add(answerField);


        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        root.getChildren().add(questionLabel);

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
        ToolBar toolBar = new ToolBar();

        Button exitButton = new Button("Exit");
        Button resetButton = new Button("Reset");

        Image exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        exitButton.setGraphic(new ImageView(exitButtonImage));

        questionLabel = new Label(randomQuestion);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

//        TextField a = new TextField();





        Button submitButton = new Button("Submit");


        Label finalQuestionLabel = questionLabel;
        submitButton.setOnAction(event -> {

           /* String ansString=a.getText();

            if (ansString.equals(answers[indx])) {
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
               indx = getrandnum(questions.length);
               randomQuestion = questions[indx];
               finalQuestionLabel.setText(randomQuestion);

            } else {
               randomQuestion = score.getresult();
               finalQuestionLabel.setText(randomQuestion);
           }*/


        //    questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        });


        exitButton.setOnAction(e -> {
        });

        toolBar.getItems().addAll(resetButton, exitButton);
        root.getChildren().addAll(toolBar, text, questionLabel,  submitButton);
        double sceneWidth = practiceFITB.getWidth();
        double sceneHeight = practiceFITB.getHeight();

     /*   questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        a.setLayoutX(sceneWidth / 2 - a.getWidth() / 2);
        a.setLayoutY(sceneHeight / 2 - 50);*/
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
        return practiceFITB;
    }
    // Gives a random variable
    public int getrandnum(int num){
        Random rand = new Random();
        return (rand.nextInt(num));
    }


}