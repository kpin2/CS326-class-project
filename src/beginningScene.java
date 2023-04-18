import javafx.event.EventHandler;
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

public class beginningScene extends Scene {

    private final Scene beginning;
    private accountCreation createAccount;
    private loginScene loginS;

    public void switchScene(Stage stage, Scene scene) {
        stage.setScene(scene);
    }

    public beginningScene() {

        super(new Pane(), 1366, 768);

        Stage stage = new Stage();

        Pane root = new Pane();

        beginning = new Scene(root, 1366, 768);

        Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.RED);
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 120));

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        Text login = new Text(663, 387, "Login");
        login.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 60));
        login.setFill(Color.WHITE);

        login.setOnMouseClicked(e -> {
            loginS = new loginScene();
            Scene loginSc = loginS.getScene();
            switchScene(stage, loginSc);
        });

        Text create = new Text(513, 457, "Create Account");
        create.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 60));
        create.setFill(Color.WHITE);

        create.setOnMouseClicked(e -> {
            createAccount = new accountCreation();
            Scene createA = createAccount.getScene();
            switchScene(stage, createA);
        });

        root.getChildren().addAll(text, login, create);
    }



    public Scene getScene() {
        return beginning;
    }


}