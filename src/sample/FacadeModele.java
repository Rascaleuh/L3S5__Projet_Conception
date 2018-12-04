package sample;

import javafx.scene.input.KeyCode;

public class FacadeModele {
    ModeleConcret modele = new ModeleConcret();

    public void move(KeyCode c) {
        modele.move(c);
    }

    public void reset() {
        modele.reset();
    }

    public Niveau getNiveau() {
        return modele.getNiveau();
    }

    public void set_liste_niveaux(String chemin) {
        modele.set_liste_niveaux(chemin);
    }

    public void niveauPrécédent() {
        modele.setActuel(-1);
    }

    public void niveauSuivant(){
        modele.setActuel(1);
    }
}
