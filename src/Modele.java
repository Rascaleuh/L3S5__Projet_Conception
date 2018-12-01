import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Modele {
    private ArrayList<Niveau> liste_niveaux;
    private Niveau actuel;

    Modele(String chemin_fichier) {
        this.liste_niveaux = get_liste_niveaux(chemin_fichier);
        this.actuel = null;
    }

    //retourne une liste de niveaux en fonction d'un fichier .xsb
    ArrayList<Niveau> get_liste_niveaux(String chemin_fichier) {

        ArrayList<Niveau> liste_niveaux = new ArrayList<Niveau>();
        ArrayList<ArrayList<Integer>> level = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> ligne = new ArrayList<Integer>();

        File fichier = new File(chemin_fichier);
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

        return liste_niveaux;
    }
}

