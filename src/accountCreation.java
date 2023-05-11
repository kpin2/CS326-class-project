import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class accountCreation extends Scene {

    //declare variable

    private final Scene creationScene;

    public Button register;
    public Button exitButton;
    public TextField username;
    public PasswordField password;
    public PasswordField confirm;
    public Image avatarImage;

    //constructor

    public accountCreation() {

        super(new Pane(), 1366, 768);

        final Pane root = new Pane();

        this.creationScene = new Scene(root, 1366, 768);

        final Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.RED);
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 120));

        final BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        final Rectangle accountCreation = new Rectangle(600, 400, Color.rgb(123, 5, 1));
        accountCreation.setArcWidth(20);
        accountCreation.setArcHeight(20);
        // Set the stroke color and width
        accountCreation.setStroke(Color.WHITE);
        accountCreation.setStrokeWidth(2);

        accountCreation.setOpacity(0.75);

        accountCreation.setLayoutX(420);
        accountCreation.setLayoutY(200);

        final Label boxTitle = new Label("Account Creation");
        boxTitle.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 48));
        boxTitle.setTextFill(Color.WHITE);
        boxTitle.setLayoutX(520);
        boxTitle.setLayoutY(230);

        final Rectangle labelBorder = new Rectangle(580, 12.5, Color.WHITE);
        labelBorder.setLayoutX(430);
        labelBorder.setLayoutY(300);

        final Text txt1 = new Text(540, 407, "Username:");
        txt1.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt1.setFill(Color.WHITE);

        this.username = new TextField();
        this.username.setPromptText("Enter Username");
        this.username.setPrefColumnCount(20);
        this.username.setLayoutX(683);
        this.username.setLayoutY(384);
        this.username.getText();

        final Text txt2 = new Text(540, 437, "Password:");
        txt2.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt2.setFill(Color.WHITE);

        this.password = new PasswordField();
        this.password.setPromptText("Enter Password");
        this.password.setPrefColumnCount(20);
        this.password.setLayoutX(683);
        this.password.setLayoutY(414);
        this.password.getText();

        final Text txt3 = new Text(440, 467, "Confirm Password:");
        txt3.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 26));
        txt3.setFill(Color.WHITE);

        this.confirm = new PasswordField();

        confirm.setPromptText("Confirm Password");
        confirm.setPrefColumnCount(20);
        confirm.setLayoutX(683);
        confirm.setLayoutY(444);
        confirm.getText();

        final ToolBar toolBar = new ToolBar();
        Image exitButtonImage = new Image("file:resources/assets/exit.png");
        ImageView exitButtonImageView = new ImageView(exitButtonImage);
        exitButtonImageView.setFitHeight(96);
        exitButtonImageView.setFitWidth(96);
        exitButton = new Button("", exitButtonImageView);
        exitButton.setLayoutX(-5);
        exitButton.setLayoutY(-5);
        exitButton.setStyle("-fx-background-color: transparent;");
        this.register = new Button("Next");
        toolBar.setLayoutX(683);
        toolBar.setLayoutY(530);
        toolBar.getItems().add(this.register);

        final landingScene landingScene = new landingScene();
        this.exitButton = landingScene.getExitButton();
        toolBar.getItems().add(this.exitButton);


        this.avatarImage = new Image("file:resources/assets/Astronaut Cat 500px removebg.png", 500, 500, false, true);

        this.register = new Button("Next");
        register.setLayoutX(683);
        register.setLayoutY(530);

        root.getChildren().addAll(text, accountCreation, boxTitle, labelBorder, txt1, this.username, txt2, this.password, txt3, this.confirm, this.exitButton, this.register);
    }


    public Scene getScene() {
        return this.creationScene;
    }

}
