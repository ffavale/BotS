package bots.simulation.individual;

import java.util.Random;
import logg.*;

public class Couple {
    private Logg log;
    private Individual father;
    private Individual mother;

    public Couple(Individual Father, Individual Mother, Logg i_log)
    {
        this.log = i_log;
        this.father = Father;
        this.mother = Mother;
    }

    public Individual procreation(){
        Random rng = new Random;
        // 50% chance of gender
        if (rng.nextInt(100) > 49){
            // Male
            if (this.father.type == Individual.Alignment.FAITHFUL) {
                //85% chance of inheritance of father's gene
                if (rng.nextInt(100) < 85){
                    return new Individual(0, 0, this.log);
                }
            }
            else {
                //85% chance of inheritance of father's
                if (rng.nextInt(100) < 85){
                    return new Individual(0, 1, this.log);
                }
            }
        }
        else{
            //Female
            if (this.mother.type == Individual.Alignment.COY) {
                //85% chance of inheritance of mother's gene
                if (rng.nextInt(100) < 85){
                    return new Individual(1, 2, this.log);
                }
            }
            else {
                //85% chance of inheritance of mother's gene
                if (rng.nextInt(100) < 85){
                    return new Individual(1, 3, this.log);
                }
            }
        }
    }


}
