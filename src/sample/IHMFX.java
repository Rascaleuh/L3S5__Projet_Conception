package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

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


