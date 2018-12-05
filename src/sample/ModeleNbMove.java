package sample;

import javafx.scene.input.KeyCode;

public class ModeleNbMove implements  Modele{
    Modele modele;
    int nbMove;
    int nbPoussée;

    public ModeleNbMove(ModeleConcret modeleConcret) {
        this.modele = modeleConcret;
    }

    @Override
    public Niveau getNiveau() {
        return modele.getNiveau();
    }

    @Override
    public int move(KeyCode c) {
        int rt = modele.move(c);
        System.out.println("RT MODELENBMOVE = " + rt);
        if (rt == 1){
            nbMove++;
        }else if (rt == 2){
            nbMove++;
            nbPoussée++;
        }

        System.out.println("nbMove= " + nbMove);
        System.out.println("nbPousée= "+ nbPoussée);

        return 0;
    }

    @Override
    public void set_liste_niveaux(String chemin) {
        modele.set_liste_niveaux(chemin);
    }

    @Override
    public boolean win() {
        return modele.win();
    }

    @Override
    public void reset() {
        nbPoussée = 0;
        nbMove = 0;
        modele.reset();
    }

    @Override
    public void setActuel(int n) {
        modele.setActuel(n);
    }

}
