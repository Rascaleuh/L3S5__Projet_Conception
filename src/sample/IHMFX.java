package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHMFX extends Application {

    VueIHMFX vue;
    VueListeNiveaux vueListeNiveaux;
    Stage stage;
    MonteurScene monteurScene;
    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                vue.dessine();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        vue = new VueIHMFX();
        Controller controleur = new Controller(vue, this);
        /* montage de la scene */
        monteurScene = new MonteurScene();

        Scene scene = monteurScene.
                setCentre(vue.myButton).
                setLargeur(800).
                setHauteur(800).
                retourneSceneMenu();

        this.stage.setScene(scene);

        this.stage.setTitle("Sokoban");
        this.stage.show();
    }

    //Remplace la vue de selection de fichier par la vue de selection de niveaux quand l'utilisateur a choisi un fichier .xsb
    public void setVueNiveaux() {

        Modele modele = Modele.getModele();
        vueListeNiveaux = new VueListeNiveaux(modele.getListeNiveaux());
        VueListeNiveaux vueLN = new VueListeNiveaux(modele.getListeNiveaux());
        monteurScene.setCentre(vueLN.vBox);

        this.stage.setScene(monteurScene.retourneSceneMenuNiveau());
        this.stage.show();
    }

    public void lance() {
        launch();
    }
}


