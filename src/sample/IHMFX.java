package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class IHMFX extends Application {

    VueIHMFX vue;
    Vue vueJeu;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vueJeu.draw();
                //vue.dessine();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        vue = new VueIHMFX();
        vueJeu = new Vue();
        Controller controleur = new Controller(vue);
        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        vueJeu.gridPane.getColumnConstraints().addAll(DoubleStream.of(0, 0, 0).mapToObj(width -> {
            ColumnConstraints c = new ColumnConstraints();
            c.setPercentWidth(width);
            c.setFillWidth(true);
            return c;
        }).toArray(ColumnConstraints[]::new));

        //vueJeu.gridPane.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.01));
        Scene scene = monteurScene.
                setCentre(vueJeu.gridPane).
                setLargeur(800).
                setHauteur(800).
                retourneScene();
        primaryStage.setScene(scene);


        primaryStage.setTitle("Sokoban");
        primaryStage.show();
    }

    public void lance() {
        launch(new String[]{});
    }
}


