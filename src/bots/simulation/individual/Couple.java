package bots.simulation.individual;

import java.util.Random;
import logg.*;

public class Couple {
    private static int coupleCounter = 0;
    private int id;
    private Logg log;
    private Individual father;
    private Individual mother;

    public Couple(Individual i_father, Individual i_mother, Logg i_log)
    /*
    Takes as input two individuals covering the role as father and mother
    also takes the log of the simulation to keep track of every movement
     */
    {
        this.log = i_log;
        this.father = i_father;
        this.mother = i_mother;
        this.id = Couple.coupleCounter; Couple.coupleCounter++;

        this.father.isAvailable = false;
        this.mother.isAvailable = false;
        this.log.logQuietMessage("Individual-" + this.father.getId() + " and " +
                "Individual-" + this.mother.getId() + " are a couple", "Couple-" + String.valueOf(this.id));
        // this.log.logQuietMessage("Father/Mother: " + this.father.type + "/" + this.mother.type);
    }

    private void free()
    /*
    Sets the status of the parents to Available
     */
    {
        this.father.isAvailable = true;
        this.mother.isAvailable = true;
    }

    private void applyParentCosts(int[] costs)
    {
        // int k = (int) Math.round(((this.father.expAge+this.mother.expAge)/4.0)/20.0);
        // this.log.logMessage(String.valueOf(k));
        int k = (int) Math.round(((this.father.getExpAge()+this.mother.getExpAge()) * 0.5) * 0.02);
        if (this.father.type == Individual.Alignment.FAITHFUL && this.mother.type == Individual.Alignment.COY)
        {
            // A - (B/2) - C --> -((A - (B/2) - C) - A) --> -(-(B/2) - C ) --> (B/2) + C
            this.father.lock(k * ((costs[1]/2) + costs[2]));
            this.mother.lock(k * ((costs[1]/2) + costs[2]));
        } else
        if (this.father.type == Individual.Alignment.FAITHFUL && this.mother.type == Individual.Alignment.FAST)
        {
            this.father.lock(k * (costs[1]/2));
            this.mother.lock(k * (costs[1]/2));
        } else
        if (this.father.type == Individual.Alignment.PHILANDERER && this.mother.type == Individual.Alignment.FAST)
        {
            this.mother.lock(k * costs[1]);
        }
    }

    public Individual procreation(int expAge, int[] simCosts)
    /*
    Through this method a new individual can be generated with a 50%
    chance of either being male or female and an 85% chance of inheritance
    of the parent's alignment, also takes in input the expected age for the new
    individual that has to be generated
     */
    {
        this.applyParentCosts(simCosts);
        Random rng = new Random();
        // 50% chance of gender

        Individual child = new Individual(0, 0, expAge, this.log);

        // if (rng.nextInt(100) < 49){
        //     // Male
        //     if (this.father.type == Individual.Alignment.PHILANDERER) {
        //         child = new Individual(0, 1, expAge, this.log);
        //     }
        //     else {
        //         child = new Individual(0, 0, expAge, this.log);
        //     }
        // }
        // else{
        //     //Female
        //     if (this.mother.type == Individual.Alignment.FAST) {
        //         child = new Individual(1, 3, expAge, this.log);
        //     }
        //     else {
        //         child = new Individual(1, 2, expAge, this.log);
        //     }
        // }

        // if (rng.nextInt(100) > 49){
        //     // Male
        //     if (this.father.type == Individual.Alignment.FAITHFUL) {
        //         //85% chance of inheritance of father's gene
        //         if (rng.nextInt(100) < 85){
        //             child = new Individual(0, 0, expAge, this.log);
        //         }
        //         else
        //         {
        //             child = new Individual(0, 1, expAge, this.log);
        //         }
        //     }
        //     else {
        //         //85% chance of inheritance of father's
        //         if (rng.nextInt(100) < 85){
        //             child = new Individual(0, 1, expAge, this.log);
        //         }
        //         else
        //         {
        //             child = new Individual(0, 0, expAge, this.log);
        //         }
        //     }
        // }
        // else{
        //     //Female
        //     if (this.mother.type == Individual.Alignment.COY) {
        //         //85% chance of inheritance of mother's gene
        //         if (rng.nextInt(100) < 85){
        //             child = new Individual(1, 2, expAge, this.log);
        //         }
        //         else
        //         {
        //             child = new Individual(1, 3, expAge, this.log);
        //         }
        //     }
        //     else {
        //         //85% chance of inheritance of mother's gene
        //         if (rng.nextInt(100) < 85){
        //             child = new Individual(1, 3, expAge, this.log);
        //         }
        //         else
        //         {
        //             child = new Individual(1, 2, expAge, this.log);
        //         }
        //     }
        // }

        float selector = rng.nextFloat();
        float typeSelector = rng.nextFloat();
        // TODO - set this as an input parameter to the sim (1-(a/100))
        float pInheritance = 0.9f;

        if (0 <= selector && selector < 0.50)
        {
            if (this.father.type == Individual.Alignment.FAITHFUL)
            {
                if (typeSelector < pInheritance)
                {
                    child = new Individual(0, 0, expAge, this.log);
                }
                else
                {
                    child = new Individual(0, 1, expAge, this.log);
                }
            }
            else
            {
                if (typeSelector < pInheritance)
                {
                    child = new Individual(0, 1, expAge, this.log);
                }
                else
                {
                    child = new Individual(0, 0, expAge, this.log);
                }
            }
        }
        if (0.50 < selector && selector < 1.00)
        {
            if (this.mother.type == Individual.Alignment.COY)
            {
                if (typeSelector < pInheritance)
                {
                    child = new Individual(1, 2, expAge, this.log);
                }
                else
                {
                    child = new Individual(1, 3, expAge, this.log);
                }
            }
            else
            {
                if (typeSelector < pInheritance)
                {
                    child = new Individual(1, 3, expAge, this.log);
                }
                else
                {
                    child = new Individual(1, 2, expAge, this.log);
                }
            }
        }

        // int selector = rng.nextInt(100);
        //
        // if (0 <= selector && selector < 25)
        // {
        //     child = new Individual(0, 0, expAge, this.log);
        // }
        // if (25 <= selector && selector < 50)
        // {
        //     child = new Individual(0, 1, expAge, this.log);
        // }
        // if (50 <= selector && selector < 75)
        // {
        //     child = new Individual(1, 2, expAge, this.log);
        // }
        // if (75 < selector && selector < 100)
        // {
        //     child = new Individual(1, 3, expAge, this.log);
        // }

        this.free();

        this.log.logQuietMessage("A child has been born", "Couple-" + String.valueOf(this.id));
        return child;
    }
}
