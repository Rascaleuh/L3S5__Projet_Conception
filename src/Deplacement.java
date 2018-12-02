import java.util.ArrayList;

public class Deplacement {

    public Deplacement() {

    }

        //up 1, right 2, down 3, left 4, mur 0
        // sol 1, perso 2, goal 3, caisse 4, caisse sur goal 5, perso sur goal 6

        public static void move(int dir, ArrayList<ArrayList<Integer>> niveau, int pos_x ,int pos_y){
            int x=0;
            int y=0;
            if(dir==1){
                x=-1;
                y=0;
            }
            if (dir==2){
                x=0;
                y=1;
            }
            if(dir==3){
                x=1;
                y=0;
            }
            if(dir==4){
                x=0;
                y=-1;
            }

            if(x==0&&y==0){return;}

            //détermine l'augmentation de l'abs/ordo

            //mur
            if(niveau.get(pos_x+x).get(pos_y+y)==0){
            }
            else if(niveau.get(pos_x+x).get(pos_y+y)==1){//déplacement simple
                int a=niveau.get(pos_x+x).get(pos_y+y);

                if(niveau.get(pos_x).get(pos_y)==2){niveau.get(pos_x).set(pos_y,1);}//depuis "sol"
                else if(niveau.get(pos_x).get(pos_y)==6){niveau.get(pos_x).set(pos_y,3);}//depuis "goal"
            }

            else if(niveau.get(pos_x+x).get(pos_y+y)==3){//marche sur un goal
                niveau.get(pos_x+x).set(pos_y+y,6);
                if(niveau.get(pos_x).get(pos_y)==2){niveau.get(pos_x).set(pos_y,1);}//depuis "sol"
                else if(niveau.get(pos_x).get(pos_y)==6){niveau.get(pos_x).set(pos_y,3);}//depuis "goal"

            }

            else if(niveau.get(pos_x+x).get(pos_y+y)==4){//pousse une boite vers:
                //déplacement impossible
                if(niveau.get(pos_x+x+x).get(pos_y+y+y)==4 || niveau.get(pos_x+x+x).get(pos_y+y+y)==5 || niveau.get(pos_x+x+x).get(pos_y+y+y)==0) {

                }
                else {
                    if (niveau.get(pos_x + x + x).get(pos_y + y + y) == 1) {//vers sol
                        if (niveau.get(pos_x).get(pos_y) == 2) {
                            niveau.get(pos_x).set(pos_y,1);
                            niveau.get(pos_x + x).set(pos_y + y,2);
                            niveau.get(pos_x + x + x).set(pos_y + y + y,4);
                        }//depuis "sol"
                        else if (niveau.get(pos_x).get(pos_y) == 6) {
                            niveau.get(pos_x).set(pos_y,3);
                            niveau.get(pos_x + x).set(pos_y + y,2);
                            niveau.get(pos_x + x + x).set(pos_y + y + y,4);
                        }//depuis "goal"


                    } else if (niveau.get(pos_x + x + x).get(pos_y + y + y) == 3) {//vers goal
                        if (niveau.get(pos_x).get(pos_y) == 2) {
                            niveau.get(pos_x).set(pos_y,1);
                            niveau.get(pos_x + x).set(pos_y + y,2);
                            niveau.get(pos_x + x + x).set(pos_y + y + y,5);
                        }//depuis "sol"
                        else if (niveau.get(pos_x).get(pos_y) == 6) {
                            niveau.get(pos_x).set(pos_y,3);
                            niveau.get(pos_x + x).set(pos_y + y,2);
                            niveau.get(pos_x + x + x).set(pos_y + y + y,5);
                        }//depuis "goal"
                    }
                }
            }

            else if(niveau.get(pos_x+x).get(pos_y+y)==5){//pousse une boite vers:
                //déplacement impossible
                if(niveau.get(pos_x+x+x).get(pos_y+y+y)==4 || niveau.get(pos_x+x+x).get(pos_y+y+y)==5 || niveau.get(pos_x+x+x).get(pos_y+y+y)==0) {

                }
                else{
                    if(niveau.get(pos_x+x+x).get(pos_y+y+y)==1){//vers sol
                        if(niveau.get(pos_x).get(pos_y)==2){
                            niveau.get(pos_x).set(pos_y,1);
                            niveau.get(pos_x+x).set(pos_y+y,6);
                            niveau.get(pos_x+x+x).set(pos_y+y+y,4);
                        }//depuis "sol"
                        else if(niveau.get(pos_x).get(pos_y)==6){
                            niveau.get(pos_x).set(pos_y,3);
                            niveau.get(pos_x+x).set(pos_y+y,6);
                            niveau.get(pos_x+x+x).set(pos_y+y+y,4);
                        }//depuis "goal"


                    }else if(niveau.get(pos_x+x+x).get(pos_y+y+y)==3){//vers goal
                        if(niveau.get(pos_x).get(pos_y)==2){
                            niveau.get(pos_x).set(pos_y,1);
                            niveau.get(pos_x+x).set(pos_y+y,6);
                            niveau.get(pos_x+x+x).set(pos_y+y+y,5);
                        }//depuis "sol"
                        else if(niveau.get(pos_x).get(pos_y)==6){
                            niveau.get(pos_x).set(pos_y,3);
                            niveau.get(pos_x+x).set(pos_y+y,6);
                            niveau.get(pos_x+x+x).set(pos_y+y+y,5);
                        }//depuis "goal"

                    }
                }
            }
        }
}
