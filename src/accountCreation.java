import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 All the other classes are going to be extensions of Scene and implemented in this same format!!
 */


public class accountCreation extends Scene{

    //declare variable

    private Scene creationScene;

    public Button register;

    public TextField username;
    public PasswordField password;


    //constructor

    public accountCreation() {

        super(new Pane(), 1366, 768);

        Pane root = new Pane();

        creationScene = new Scene(root, 1366, 768);

        Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.RED);
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 120));

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        Rectangle accountCreation = new Rectangle(600, 400, Color.rgb(123, 5, 1));
        accountCreation.setArcWidth(20);
        accountCreation.setArcHeight(20);
        // Set the stroke color and width
        accountCreation.setStroke(Color.WHITE);
        accountCreation.setStrokeWidth(2);

        accountCreation.setOpacity(0.75);

        accountCreation.setLayoutX(420);
        accountCreation.setLayoutY(200);

        Label boxTitle = new Label("Account Creation");
        boxTitle.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 48));
        boxTitle.setTextFill(Color.WHITE);
        boxTitle.setLayoutX(520);
        boxTitle.setLayoutY(230);

        Rectangle labelBorder = new Rectangle(580, 12.5, Color.WHITE);
        labelBorder.setLayoutX(430);
        labelBorder.setLayoutY(300);

        Text txt1 = new Text(540, 407, "Username:");
        txt1.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt1.setFill(Color.WHITE);

        username = new TextField();
        username.setPromptText("Enter Username");
        username.setPrefColumnCount(20);
        username.setLayoutX(683);
        username.setLayoutY(384);
        username.getText();

        Text txt2 = new Text(540, 437, "Password:");
        txt2.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt2.setFill(Color.WHITE);

        password = new PasswordField();
        password.setPromptText("Enter Password");
        password.setPrefColumnCount(20);
        password.setLayoutX(683);
        password.setLayoutY(414);
        password.getText();

        Text txt3 = new Text(440, 467, "Confirm Password:");
        txt3.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 26));
        txt3.setFill(Color.WHITE);

        PasswordField confirm = new PasswordField();
        confirm.setPromptText("Confirm Password");
        confirm.setPrefColumnCount(20);
        confirm.setLayoutX(683);
        confirm.setLayoutY(444);
        confirm.getText();

        ToolBar toolBar = new ToolBar();

        register = new Button("Next");
        toolBar.setLayoutX(683);
        toolBar.setLayoutY(530);
        toolBar.getItems().add(register);

        register.setOnAction(e -> {
            // Remove the existing items from the pane
            root.getChildren().clear();

            Button confirmBtn = new Button("Next");
            confirmBtn.setLayoutX(683);
            confirmBtn.setLayoutY(530);
            // Create some new text nodes to add to the pane
            root.getChildren().addAll(text, accountCreation, boxTitle, labelBorder, confirmBtn);
        });

        root.getChildren().addAll(text, accountCreation, boxTitle, labelBorder, txt1, username, txt2, password, txt3, confirm, toolBar);
    }


    public Scene getScene() {
        return creationScene;
    }

}
