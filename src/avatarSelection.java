import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class avatarSelection extends Scene {

    private final Scene selectionScene;
    
    public Button confirmBtn;
    public Button nextSelect;
    public Button prevSelect;
    public Button exitButton;
    public Image image1;
    public Image image2;
    public Image image3;
    public Image avatar;
    public Button selectI1, selectI2,selectI3;
    public ImageView imageV1, imageV2, imageV3;

    public avatarSelection() {

        super(new Pane(), 1366, 768);

        Pane root = new Pane();

        selectionScene = new Scene(root, 1366, 768);

        Text text = new Text(300, 100, "Mission: Math!");
        text.setFill(Color.RED);
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 120));

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        Rectangle avatarSelector = new Rectangle(600, 400, Color.rgb(123, 5, 1));
        avatarSelector.setArcWidth(20);
        avatarSelector.setArcHeight(20);
        // Set the stroke color and width
        avatarSelector.setStroke(Color.WHITE);
        avatarSelector.setStrokeWidth(2);

        avatarSelector.setOpacity(0.75);

        avatarSelector.setLayoutX(420);
        avatarSelector.setLayoutY(200);

        Label boxTitle = new Label("Account Creation");
        boxTitle.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 48));
        boxTitle.setTextFill(Color.WHITE);
        boxTitle.setLayoutX(520);
        boxTitle.setLayoutY(230);

        Rectangle labelBorder = new Rectangle(580, 12.5, Color.WHITE);
        labelBorder.setLayoutX(430);
        labelBorder.setLayoutY(300);

        exitButton = new Button("Exit");
        Image exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        exitButton.setGraphic(new ImageView(exitButtonImage));

        confirmBtn = new Button("Confirm");
        confirmBtn.setLayoutX(683);
        confirmBtn.setLayoutY(530);

        nextSelect = new Button(">");
        nextSelect.setLayoutX(863);
        nextSelect.setLayoutY(530);
     
        prevSelect = new Button("<");
        prevSelect.setLayoutX(543);
        prevSelect.setLayoutY(530);
        
        image1 = new Image("file:", 100, 100, false, false);
        image2 = new Image("file:", 100, 100, false, false);
        image3 = new Image("file:", 100, 100, false, false);

        imageV1 = new ImageView(image1);

        selectI1 = new Button("", imageV1);
        selectI1.setLayoutX(453);
        selectI1.setLayoutY(375);
        selectI1.setOnAction(e -> {
            avatar = image1;
        });

        imageV2 = new ImageView(image2);

        selectI2 = new Button("", imageV2);
        selectI2.setLayoutX(653);
        selectI2.setLayoutY(375);
        selectI2.setOnAction(e -> {
            avatar = image2;
        });

        imageV3 = new ImageView(image3);

        selectI3 = new Button("", imageV3);
        selectI3.setLayoutX(863);
        selectI3.setLayoutY(375);
        selectI3.setOnAction(e -> {
            avatar = image3;
        });

        root.getChildren().addAll(text, avatarSelector, boxTitle, labelBorder, confirmBtn, exitButton, prevSelect, nextSelect, selectI1, selectI2, selectI3);
    }


    public Scene getScene(){return selectionScene;}

}