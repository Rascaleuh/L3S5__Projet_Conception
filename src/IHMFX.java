import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class IHMFX extends Application {

    private VueIHMFX vueIHMFX;
    private VueListeNiveaux vueListeNiveaux;
    private VueNiveau vueNiveau;
    private ControllerVueListeNiveaux controllerVueListeNiveaux;
    Stage stage;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vueNiveau.draw();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        vueIHMFX = new VueIHMFX();
        Controller controleur = new Controller(vueIHMFX, this);
        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        Scene scene = monteurScene.
                setCentre(vueIHMFX.boutonSelectFile).
                setLargeur(800).
                setHauteur(800).
                retourneSceneMenu();

        this.stage.setScene(scene);

        this.stage.setTitle("Sokoban");
        this.stage.show();
    }

    //Remplace la vue de selection de fichier par la vue de selection de niveaux quand l'utilisateur a choisi un fichier .xsb
    public void setVueListeNiveaux() {

        Modele modele = Modele.getModele();
        vueListeNiveaux = new VueListeNiveaux(modele.getListeNiveaux());

        controllerVueListeNiveaux = new ControllerVueListeNiveaux( this, vueListeNiveaux );
        MonteurScene monteurScene = new MonteurScene();

        monteurScene.setCentre(vueListeNiveaux.vBox);

        this.stage.setScene(monteurScene.retourneSceneMenuNiveau());
        this.stage.show();
    }

    public void setVueNiveau() throws FileNotFoundException {


        Modele modele = Modele.getModele();
        vueNiveau = new VueNiveau(modele.getNiveauActuel());

        MonteurScene monteurScene = new MonteurScene();
        monteurScene.setCentre(vueNiveau.gridPane);
        this.stage.setScene(monteurScene.retourneSceneNiveau());

        ControllerDeplacement controllerDeplacement = new ControllerDeplacement(this);

        this.stage.show();
    }

    public void lance() {
        launch();
    }

    public VueListeNiveaux getVueListeNiveaux() { return vueListeNiveaux; }

    public VueIHMFX getVueIHMFX() { return this.vueIHMFX; }

    public VueNiveau getVueNiveau() { return vueNiveau; }

}



