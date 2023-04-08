/* CSCI362 Software Engineering
 * Mission: Math! application
 * landingScene.java - Separate class for the landing scene to allow the mainDriver more functionality in scene switching
 * and to allow implementation of an overlay/Heads Up Display (HUD) for the game.
 *
 * @author: Kevin Pinto - Wrote the entire landing scene and all functionality within, initially as part of mainDriver, then separated it into its own class.
 */


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class landingScene extends Scene {

    //declare Scene variable to allow getScene() to return the scene
    private final Scene landingScene;

    public landingScene() {
        super(new Pane(), 1366, 768);

        Pane root = new Pane();

        landingScene = new Scene(root, 1366, 768);


    }
}
