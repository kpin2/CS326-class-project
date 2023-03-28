/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * mainDriver.java - Driver program for the tutoring software/game of Mission: Math! Calls the  JavaFX application start method and launches the software.
 *
 * @author Kevin Pinto - Wrote initial code with a basic setup for the main driver.
* */
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainDriver extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

//      root is the highest level pane and the root node of the scene graph
        Pane root = new Pane();
        primaryStage.setTitle("Mission: Math!");

        Scene initialScene = new Scene(root, 800, 600);
        primaryStage.setScene(initialScene);
        primaryStage.show();

        //Create a simple toolbar with start and exit buttons
        ToolBar toolBar = new ToolBar();
        toolBar.setPrefSize(800, 40);
        Button startButton = new Button("Start");
        Button exitButton = new Button("Exit");

        //Add the buttons to the toolbar
        toolBar.getItems().addAll(startButton, exitButton);
        //Add the toolbar to the root pane
        root.getChildren().add(toolBar);

    }

    public static void main(String[] args) {
        launch(args);
    }


}
