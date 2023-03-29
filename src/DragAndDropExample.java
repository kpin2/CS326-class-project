import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragAndDropExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

        /*Font font = Font.loadFont(getClass().getResourceAsStream("resources/font/SpaceMission-rgyw9.otf"), 48);
        Text text2 = new Text(75, 60, "Drag and Drop");
        text2.setFont(font);
        text2.setFill(Color.GREEN);

        Text text = new Text(75, 30, "Mission: Math!");
        text.setFont(Font.loadFont(getClass().getResourceAsStream("resources/font/SpaceMission-rgyw9.otf"), 36));
        text.setFill(Color.RED);*/

        Text text = new Text(75, 30, "Mission: Math!");
        Text text2 = new Text(75, 90, "Drag and Drop");

        text.setFont(Font.loadFont("file:resources/font/SpaceMission-rgyw9.otf", 36));
        text.setFill(Color.RED);

        text2.setFont(Font.loadFont("file:resources/font/SpaceMission-rgyw9.otf", 48));
        text2.setFill(Color.GREEN);

        root.getChildren().addAll(text, text2);



        Circle source = new Circle(150, 150, 25);
        Circle target = new Circle(300, 200, 50);
        source.setFill(Color.RED);
        target.setFill(Color.BLUE);

        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);
                event.consume();
            }
        });
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    target.setFill(Color.GREEN);
                }
                event.consume();
            }
        });
        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    success = true;
                    target.setFill(Color.BLACK);
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
        target.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                target.setFill(Color.YELLOW);
                event.consume();
            }
        });

        ToolBar toolBar = new ToolBar();
        Button resetButton = new Button("Reset");
        toolBar.getItems().add(resetButton);
        resetButton.setOnAction(e -> {
            source.setFill(Color.RED);
            target.setFill(Color.BLUE);
        });

        root.getChildren().addAll(source, target, toolBar);
        stage.setScene(scene);
        stage.show();
    }


}