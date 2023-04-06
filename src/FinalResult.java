import javafx.animation.KeyValue;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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

    public static Scene getScene(){
        return FinalResult;
    }


}
