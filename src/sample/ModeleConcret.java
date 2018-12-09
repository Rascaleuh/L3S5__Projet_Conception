package sample;

import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class ModeleConcret implements Modele {
    private Niveau actuel;
    private ArrayList<Niveau> liste_niveaux;
    private int numNiveau;

    ModeleConcret(){
        actuel = new Niveau("Sokoban");
        numNiveau = 0;
    }

    void setActuel(int n){
        if ((numNiveau+n >= 0) && (numNiveau+n < liste_niveaux.size())){
            actuel = new Niveau(liste_niveaux.get(numNiveau+n));
            numNiveau += n;
            System.out.println("Nouveau lvl : "+numNiveau);
            actuel.afficher();
        }
    }

    public Niveau getNiveau() {
        return actuel;
    }

    public void move(KeyCode c) {
        if( c == KeyCode.UP ){
            actuel.move(-1, 0);
        }else if (c == KeyCode.DOWN ){
            actuel.move(+1, 0);
        }else if (c == KeyCode.RIGHT){
            actuel.move(0, +1);
        }else if (c == KeyCode.LEFT){
            actuel.move(0, -1);
        }
    }

    @Override
    public void reset() {
        actuel = new Niveau( liste_niveaux.get(numNiveau) );
        System.out.println("NIVEAU RESET");
        actuel.afficher();
    }

    void set_liste_niveaux(String chemin) {
        this.liste_niveaux = new ArrayList<>();
        ArrayList<ArrayList<Integer>> level = new ArrayList<>();
        ArrayList<Integer> ligne = new ArrayList<>();

        File fichier = new File(chemin);
        try {
            FileReader fr = new FileReader(fichier);

            try {
                int index_x=0, index_y=0, index_niveau=0, px=0, py=0;
                int c;
                boolean lecture_level = true;
                boolean lecture_saut_de_ligne = false;
                boolean lecture_nom_level = false;
                String nom = "";

                do {
                    c = fr.read();
                    if (c != -1 ) {
                        if (c == ';') {
                            lecture_level = false;
                            lecture_nom_level = true;
                            index_y=0;
                        }
                        if(lecture_level) {
                            //Detection de la position du personnage
                            if (c == '@' || c == '+') {
                                px = index_x;
                                py = index_y;
                            }

                            if (c == '\n') {
                                level.add(index_y, (ArrayList<Integer>) ligne.clone());
                                ligne.clear();
                                index_y++;
                                index_x = 0;
                            }
                            else {
                                ligne.add(index_x, c);
                                index_x++;
                            }

                        }
                        else if (lecture_nom_level) {
                            if (c != '\n')
                                nom = nom + (char) c;
                            else {
                                if (nom.charAt(0) == ';') nom = nom.substring(2);
                                lecture_saut_de_ligne = true;
                                lecture_nom_level = false;
                                liste_niveaux.add(index_niveau, new Niveau(nom, (ArrayList<ArrayList<Integer>>) level.clone(), px, py));
                                nom = "";
                                level.clear();
                                index_niveau++;
                                //Ajout saut de ligne
                                fr.skip(1);
                            }
                        }
                        else if (lecture_saut_de_ligne) {
                            lecture_saut_de_ligne = false;
                            lecture_level = true;
                        }
                    }
                } while (c != -1);
            }
            catch (IOException e) {
                System.out.println("Erreur : lecture du fichier : " + e.getMessage());
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Erreur : Le fichier n'a pas été trouvé : " + e.getMessage());
        }

        numNiveau = 0;
        setActuel(0);
    }

    public boolean win() {
        return actuel.win();
    }
}
