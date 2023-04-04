import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class LoginScreen extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene loginScene = new Scene(root, 960, 540);

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png",960,540,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        Text txt1 = new Text(250, 320, "Username:");
        txt1.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt1.setFill(Color.RED);

        TextField username = new TextField();
        username.setPromptText("Enter username");
        username.setPrefColumnCount(20);
        username.setLayoutX(400);
        username.setLayoutY(300);
        username.getText();

        Text txt2 = new Text(250, 370, "Password:");
        txt2.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        txt2.setFill(Color.RED);

        PasswordField password = new PasswordField();
        password.setPromptText("Enter password");
        password.setPrefColumnCount(20);
        password.setLayoutX(400);
        password.setLayoutY(350);
        password.getText();

        Text testIt = new Text(250, 500, "");
        testIt.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 28));
        testIt.setFill(Color.WHITE);

        ToolBar toolBar = new ToolBar();
        Button loginButton = new Button("Login");
        toolBar.setLayoutX(450);
        toolBar.setLayoutY(425);
        toolBar.getItems().add(loginButton);
        loginButton.setOnAction(e -> {
            String userN = "";
            String passW = "";

            if((username.getText() != null) && (username.getText().length() >= 8) && (password.getText() != null) && (password.getText().length() >= 8)){
                userN = username.getText();
                passW = password.getText();
            }
            else{
                username.setPromptText("Error! Please enter username");
                password.setPromptText("Error! Please enter password");
            }
            testIt.setText("Username: "+userN+" Password: "+passW);
        });

//        Font font = new Font("Space Mission", 48);
//        Font.loadFont(getClass().getResourceAsStream("resources/font/SpaceMission-rgyw9.otf"), 20);

        Text text = new Text(75, 100, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 108));
        text.setFill(Color.RED);
        root.getChildren().addAll(text, txt1, username, txt2, password, toolBar, testIt);
        stage.setTitle("Login");
        stage.setScene(loginScene);
        stage.show();
    }


}
