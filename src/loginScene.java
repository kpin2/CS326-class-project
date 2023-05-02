import com.sun.javafx.menu.MenuItemBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
        import javafx.scene.paint.Color;
        import javafx.scene.text.Font;
        import javafx.scene.text.Text;


/**
 All the other classes are going to be extensions of Scene and implemented in this same format!!
 */


public class loginScene extends Scene {

    //declare variable
    private final Scene loginScene;
    public Button loginButton;
    public Button exitButton;
    public TextField username;
    public PasswordField password;
    public ImageView avatarImage;

    //constructor
    public loginScene() {
        //call the super class constructor
        super(new Pane(), 1366, 768);

        //create a new pane
        Pane root = new Pane();

        //tell loginScene to use the pane root
        loginScene = new Scene(root, 1366, 768);


        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        Text txt1 = new Text(540, 407, "Username:");
        txt1.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt1.setFill(Color.RED);

        /*TextField*/ username = new TextField();
        username.setPromptText("Enter username");
        username.setPrefColumnCount(20);
        username.setLayoutX(683);
        username.setLayoutY(384);
        username.getText();

        Text txt2 = new Text(540, 437, "Password:");
        txt2.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt2.setFill(Color.RED);

        /*PasswordField*/ password = new PasswordField();
        password.setPromptText("Enter password");
        password.setPrefColumnCount(20);
        password.setLayoutX(683);
        password.setLayoutY(414);
        password.getText();

        ToolBar toolBar = new ToolBar();

        exitButton = new Button("Exit");
        Image exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        exitButton.setGraphic(new ImageView(exitButtonImage));

        loginButton = new Button("Login");
        toolBar.setLayoutX(683);
        toolBar.setLayoutY(530);
        toolBar.getItems().add(loginButton);

        landingScene landingScene = new landingScene();
        exitButton = landingScene.exitButton;
        exitButton.setStyle("-fx-background-color: transparent;");

        avatarImage = new ImageView();
        avatarImage.setFitWidth(500);
        avatarImage.setFitHeight(500);
        avatarImage.setLayoutX(400);
        avatarImage.setLayoutY(200);


        Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.rgb(243, 5, 1));
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 120));
        root.getChildren().addAll(text, txt1, username, txt2, password, exitButton, toolBar, avatarImage);



    }

    public Scene getScene() {
        return loginScene;
    }
}
