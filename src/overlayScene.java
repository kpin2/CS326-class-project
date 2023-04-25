import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class overlayScene extends Scene {

    public Scene overlayScene;


    public overlayScene() {
        super(new Pane(), 1366, 768);

        StackPane rootStackPane = new StackPane();
        overlayScene = new Scene(rootStackPane, 1366, 768);

        Pane overlayPane = new Pane();
        Pane underlayPane = new Pane();

        overlayPane.setPrefSize(1366, 768);
        underlayPane.setPrefSize(600, 350);

        overlayPane.setStyle("-fx-background-color: TRANSPARENT;");
        landingScene landingScene = new landingScene();
        overlayPane.getChildren().addAll(landingScene.asteroidHomeButton, landingScene.helpButton, landingScene.exitButton);

        Text text = new Text("Overlay");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 30px;");
        text.setLayoutX(200);
        text.setLayoutY(200);
        overlayPane.getChildren().add(text);

        Text text2 = new Text("Underlay");
        text2.setFill(Color.BLACK);
        text2.setStyle("-fx-font-size: 38px;");
        text2.setLayoutX(200);
        text2.setLayoutY(200);
        overlayPane.getChildren().add(text2);

        /*underlayPane.setTranslateX(383);
        underlayPane.setTranslateY(209);*/

        landingScene.asteroidHomeButton.setOnAction(e -> {
            System.out.println("asteroidHomeButton pressed");
//            rootStackPane.getChildren().remove(overlayPane);

            rootStackPane.getChildren().remove(underlayPane);
            rootStackPane.getChildren().add(landingScene.getScene().getRoot());

        });

//        underlayPane.toFront();
        overlayPane.toFront();

        underlayPane.setStyle("-fx-background-color: rgb(253,18,18);");


        rootStackPane.getChildren().addAll(underlayPane, overlayPane);


    }
}
