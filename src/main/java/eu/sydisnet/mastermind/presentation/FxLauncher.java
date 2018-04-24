package eu.sydisnet.mastermind.presentation;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Launches the application in JavaFx Mode.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class FxLauncher extends Application
{
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    /**
     * Activates Double Buffering or not.
     */
    private static final boolean UX_DOUBLE_BUFFERING = true;

    /**
     * Sets AntiAliasing mode.
     */
    private static final SceneAntialiasing UX_ANTI_ALIASING = SceneAntialiasing.BALANCED;

    /**
     * Builds a launcher instance.
     */
    public FxLauncher()
    {
        String name = Thread.currentThread().getName();
        LOG.log(Level.INFO, "FxLauncher built from Thread: {0}", name);
    }

    /**
     * Builds model, view and presenter (controller).
     */
    @Override
    public void init()
    {
        String name = Thread.currentThread().getName();
        LOG.log(Level.INFO, "init() method runs from Thread: {0}", name);

        // Model
        // TODO: Sets the model

        // View
        // TODO: Sets the view
        // Applies styles
        // TODO: Sets Style

        // Controller
        // TODO: Sets Controller

    }

    /**
     * Builds the scene that will be included in theater/stage.
     *
     * @param stage the JavaFX stage
     */
    @Override
    public void start(final Stage stage)
    {
        String name = Thread.currentThread().getName();
        LOG.log(Level.INFO, "start() method runs from Thread: {0}", name);

        // Builds the scene wrapping view
        Parent rootNode = new GridPane();
        ((GridPane) rootNode).setHgap(5);
        ((GridPane) rootNode).setVgap(5);
        Text msg = new Text("Hello JavaFX");
        ((GridPane) rootNode).getChildren().add(msg);
        Scene scene = new Scene(rootNode, 320, 480, UX_DOUBLE_BUFFERING, UX_ANTI_ALIASING);

        // Builds the theater
        stage.setScene(scene);
        stage.setTitle("Mastermind FX");
        stage.show();
    }

    /**
     * Releases all resources.
     */
    @Override
    public void stop()
    {
        String name = Thread.currentThread().getName();
        LOG.log(Level.INFO, "stop() method runs from Thread: {0}", name);
    }

    /**
     * Main-Class in executable JAR.
     *
     * @param args an array of params
     */
    public static void main(final String... args)
    {
        Application.launch(FxLauncher.class, args);
    }
}
