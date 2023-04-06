import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FinalResult extends Scene {

    private static Scene FinalResult;

    public FinalResult() {
        super(new Pane(), 960, 540);

        Pane root = new Pane();

        //tell loginScene to use the pane root
        FinalResult = new Scene(root, 960, 540);

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1200, 800, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));


        Image image = new Image("file:resources/assets/Astronaut_Finish transparent bgpng.png");
        ImageView imageView = new ImageView(image);

        Scale scaleTransformation = new Scale();
        scaleTransformation.setX(0.40);
        scaleTransformation.setY(0.40);
        scaleTransformation.setPivotX(600);
        scaleTransformation.setPivotY(980);

        imageView.getTransforms().add(scaleTransformation);

        KeyValue keyValue = new KeyValue(imageView.translateYProperty(), -190);

        // over the course of 5 seconds
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), keyValue);
        Timeline timeline = new Timeline(keyFrame);


        Text text = new Text(75, 30, "Mission Complete!");

        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 36));
        text.setFill(Color.RED);




        root.getChildren().addAll(imageView, text);
        timeline.play();

    }

    public Scene getScene(){
        return FinalResult;
    }


}
