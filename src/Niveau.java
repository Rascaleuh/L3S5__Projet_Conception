import java.util.ArrayList;

public class Niveau {
    private String nom;
    private ArrayList<ArrayList<Integer>> level;
    private int px;
    private int py;

    Niveau(String _nom, ArrayList<ArrayList<Integer>> _level, int _px, int _py) {
        this.nom = _nom;
        this.level = _level;
        this.px = _px;
        this.py = _py;
    }

    /* getters */
    String get_nom() { return this.nom; }
    ArrayList<ArrayList<Integer>> get_level() { return this.level; }
    int get_px() { return this.px; }
    int get_py() { return this.py; }

    /* setters */
    void set_px(int _px) { this.px = _px; }
    void set_py(int _py) { this.py = _py; }

    /* reset position */
    void resetPos() {
        for (int i = 0; i < this.level.size(); i++)
            for (int j = 0; j < this.level.get(i).size(); j++)
                if( this.level.get(i).get(j) == 43 || this.level.get(i).get(j) == 64 ) {
                    this.px = j;
                    this.py = i;
                }

    }

}

