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



    public practiceFITB() {

        super(new Pane(),1366, 768);
        Pane root = new Pane();
        practiceFITB = new Scene(root, 1366, 768);

        Alert difficulty = new Alert(Alert.AlertType.CONFIRMATION);
        difficulty.setTitle("Select difficulty");
        difficulty.setHeaderText("Select difficulty");
        difficulty.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Easy");
        ButtonType buttonTypeTwo = new ButtonType("Medium");
        ButtonType buttonTypeThree = new ButtonType("Hard");

        difficulty.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);
//        difficulty.showAndWait();


        String[] questions = {
                "What does 2+2 equal?",
                "How many sides does a quadrilateral have?",
                "What does 15/3 equal?",
                "How many sides does a triangle have?",
                "what does 10-4 equal?"
        };
        String[] answers = {
                "4",
                "4",
                "5",
                "3",
                "6"
        };
        Random rand = new Random();
        int indx =rand.nextInt(questions.length);

        String randomQuestion = questions[indx];

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

        Label questionLabel = new Label(randomQuestion);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        TextField a = new TextField();




        Button submitButton = new Button("Submit");



        submitButton.setOnAction(event -> {

            String ansString=a.getText();

            if (ansString.equals(answers[indx])) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong!");
                alert.showAndWait();
            }
        });


        exitButton.setOnAction(e -> {
        });

        toolBar.getItems().addAll(resetButton, exitButton);
        root.getChildren().addAll(toolBar, text, questionLabel, a,  submitButton);
        double sceneWidth = practiceFITB.getWidth();
        double sceneHeight = practiceFITB.getHeight();

        questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        a.setLayoutX(sceneWidth / 2 - a.getWidth() / 2);
        a.setLayoutY(sceneHeight / 2 - 50);
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


}