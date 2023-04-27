import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class beginningScene extends Scene {

    private final Scene beginning;
    private accountCreation createAccount;
    private loginScene loginS;
    public Text create;
    public Text login;



    public beginningScene() {

        super(new Pane(), 1366, 768);

        Pane root = new Pane();

        beginning = new Scene(root, 1366, 768);

        Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.RED);
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 120));

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        login = new Text(663, 387, "Login");
        login.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 60));
        login.setFill(Color.WHITE);


        create = new Text(513, 457, "Create Account");
        create.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 60));
        create.setFill(Color.WHITE);


        root.getChildren().addAll(text, login, create);
    }


    public Scene getScene() {
        return beginning;
    }


}
