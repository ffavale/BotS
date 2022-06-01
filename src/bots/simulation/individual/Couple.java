package bots.simulation.individual;

import java.util.Random;
import logg.*;

public class Couple {
    private Logg log;
    private Individual father;
    private Individual mother;

    public Couple(Individual Father, Individual Mother, Logg i_log)
    /*
    Takes as input two individuals covering the role as father and mother
    also takes the log of the simulation to keep track of every movement
     */
    {
        this.log = i_log;
        this.father = Father;
        this.mother = Mother;

        this.father.isAvailable = false;
        this.mother.isAvailable = false;
        // this.log.logMessage("Individual-" + this.father.getId() + " and " +
        //         "Individual-" + this.mother.getId() + " are now a couple");
    }

    private void free()
    /*
    Sets the status of the parents to Available
     */
    {
        this.father.isAvailable = true;
        this.mother.isAvailable = true;
    }

    private int applyParentCosts(int[] costs)
    {
        int flatLockRate = 100;
        if (this.father.type == Individual.Alignment.FAITHFUL && this.mother.type == Individual.Alignment.COY)
        {
            this.father.lockFor((costs[1] / 2) + costs[2]);
            this.mother.lockFor((costs[1] / 2) + costs[2]);
            flatLockRate = flatLockRate - (2 * costs[2]);
        } else if (this.father.type == Individual.Alignment.FAITHFUL && this.mother.type == Individual.Alignment.FAST)
        {
            this.father.lockFor(costs[1] / 2);
            this.mother.lockFor(costs[1] / 2);
            flatLockRate = flatLockRate - (costs[2]);
        } else if (this.father.type == Individual.Alignment.PHILANDERER && this.mother.type == Individual.Alignment.FAST)
        {
            this.mother.lockFor(costs[1]);
        }
        return flatLockRate;
    }

    public Individual procreation(int expAge, int[] simCosts)
    /*
    Through this method a new individual can be generated with a 50%
    chance of either being male or female and an 85% chance of inheritance
    of the parent's alignment, also takes in input the expected age for the new
    individual that has to be generated
     */
    {
        int evoBenefit = this.applyParentCosts(simCosts);
        Random rng = new Random();
        // 50% chance of gender
        if (rng.nextInt(100) > 49){
            // Male
            if (this.father.type == Individual.Alignment.FAITHFUL) {
                //85% chance of inheritance of father's gene
                if (rng.nextInt(100) < 85){
                    this.free();


                    Individual child = new Individual(0, 0, expAge, evoBenefit, this.log);
                    return child;
                }
            }
            else {
                //85% chance of inheritance of father's
                if (rng.nextInt(100) < 85){
                    this.free();
                    Individual child = new Individual(0, 0, expAge, evoBenefit, this.log);
                    return child;
                }
            }
        }
        else{
            //Female
            if (this.mother.type == Individual.Alignment.COY) {
                //85% chance of inheritance of mother's gene
                if (rng.nextInt(100) < 85){
                    this.free();
                    Individual child = new Individual(0, 0, expAge, evoBenefit, this.log);
                    return child;
                }
            }
            else {
                //85% chance of inheritance of mother's gene
                if (rng.nextInt(100) < 85){
                    this.free();
                    Individual child = new Individual(0, 0, expAge, evoBenefit, this.log);
                    return child;
                }
            }
        }
        Individual child = new Individual(0, 0, expAge, evoBenefit, this.log);
        return child;
    }
}
