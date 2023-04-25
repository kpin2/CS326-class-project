import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class overlayScene extends Scene {

    public Scene overlayScene;


    public overlayScene() {
        super(new Pane(), 1366, 768);

        //Overlay implementation using StackPane as the root, and two panes, one for the overlay, and one for the underlay, and one scene.
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
        text.setStyle("-fx-font-size: 36px;");
        text.setLayoutX(200);
        text.setLayoutY(200);
        overlayPane.getChildren().add(text);

        Text text2 = new Text("Underlay");
        text2.setFill(Color.BLACK);
        text2.setStyle("-fx-font-size: 48px;");
        text2.setLayoutX(200);
        text2.setLayoutY(200);
        underlayPane.getChildren().add(text2);

        /*underlayPane.setTranslateX(383);
        underlayPane.setTranslateY(209);*/

        landingScene.asteroidHomeButton.setOnAction(e -> {
            System.out.println("asteroidHomeButton pressed");
//            rootStackPane.getChildren().remove(overlayPane);

            if (rootStackPane.getChildren().contains(underlayPane)) {
                rootStackPane.getChildren().remove(underlayPane);
                rootStackPane.getChildren().addAll(landingScene.getScene().getRoot(), new landingScene().asteroidHomeButton, new landingScene().helpButton, new landingScene().exitButton);


            }
        });

//        underlayPane.toFront();
        overlayPane.toFront();

        underlayPane.setStyle("-fx-background-color: rgb(160,33,0);");


        rootStackPane.getChildren().addAll(underlayPane, overlayPane);


    }
}
