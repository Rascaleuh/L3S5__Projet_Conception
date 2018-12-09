package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class IHMFX extends Application implements Observateur {
    VueIHMFX vue;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vue.dessine();
            }
        });
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controleur controleur = Controleur.getControleur();
        controleur.abonne(this);

        vue = new VueIHMFX(controleur);
        vue.gridPane.setAlignment(Pos.CENTER);
        ControleurIHMFX controleurIHMFX = new ControleurIHMFX(controleur,vue);


        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        //TODO AFFICHER / SUPPRIMER LES BOUTONS PAS ACCESSIBLE
        Scene scene = monteurScene.
                ajoutHaut(vue.labelNom).
                setCentre(vue.gridPane).
                ajoutBas(controleurIHMFX.selectFichier).
                ajoutBas(controleurIHMFX.précédent).
                ajoutBas(controleurIHMFX.suivant).
                ajoutBas(controleurIHMFX.reset).
                setLargeur(800).
                setHauteur(800).
                retourneScene();

        primaryStage.setScene(scene);

        primaryStage.setTitle("Sokoban");
        primaryStage.show();

        // Prise en charge des entrées clavier
        scene.setOnKeyPressed(new ControleurIHMFX.ActionMove());
    }


    public void lance() {
        launch(new String[]{});
    }
}

