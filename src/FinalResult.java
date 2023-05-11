import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.util.Duration;


public class FinalResult extends Scene {

    //declare Scene variable to allow getScene() to return the scene
    private final Scene FinalResult;
    private Timeline timeline;
    public FinalResult() {
        super(new Pane(), 960, 540);

        Pane root = new Pane();

        //tell loginScene to use the pane root
        FinalResult = new Scene(root, 1366, 768);

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        FinalResult.getStylesheets().add(("styles/stylesheet.css"));

        Image image = new Image("file:resources/assets/Astronaut_Finish transparent bgpng.png");
        ImageView imageView = new ImageView(image);

        Scale scaleTransformation = new Scale();
        scaleTransformation.setX(0.90);
        scaleTransformation.setY(0.90);
        scaleTransformation.setPivotX(4000);
        scaleTransformation.setPivotY(6000);

        imageView.getTransforms().add(scaleTransformation);

        KeyValue keyValue = new KeyValue(imageView.translateYProperty(), -190);

        // over the course of 5 seconds
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), keyValue);
        timeline = new Timeline(keyFrame);


        Text text = new Text(250, 100, "Mission Complete!");
        text.setFill(Color.rgb(243, 5, 1));
        text.setId("title");

        root.getChildren().addAll(imageView, text);

    }

    public Scene getScene(){
        timeline.play();
        return FinalResult;
    }


}
