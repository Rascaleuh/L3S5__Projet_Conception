package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Controller {

    VueIHMFX vue;
    Button selectFile;
    IHMFX ihm;

    Controller(VueIHMFX a_vue, IHMFX a_ihm) {
        this.vue = a_vue;
        vue.myButton.setOnAction(new ActionSelectFile());
        ihm = a_ihm;
    }

/* Event pour la selection du fichier */
    class ActionSelectFile implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {

             FileChooser fileChooser = new FileChooser();

             FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Fichier Sokoban", "*.xsb"); //Filtre pour n'accepter que les fichiers ".xsb"
             fileChooser.getExtensionFilters().setAll(extensionFilter);
             fileChooser.setSelectedExtensionFilter(extensionFilter);

             String currentPath = Paths.get(".").toAbsolutePath().normalize().toString(); // amene directement au dossier de l'executable
             fileChooser.setInitialDirectory(new File(currentPath));
             File file = fileChooser.showOpenDialog(vue.myButton.getScene().getWindow());

             if (file != null) {

                 /******   Jouer ! ******/
                 System.out.println("chemin du fichier : " + file.getAbsolutePath());
                 Modele modele = Modele.getModele();
                 modele.set_liste_niveaux(file.getAbsolutePath());
                 ArrayList<Niveau> n = modele.getListeNiveaux();
                 if(n==null) System.out.println("null");
                 ihm.setVueNiveaux();

                 /***********************/

             }
        }
    }
}




