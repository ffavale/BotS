package bots.simulation.individual;

import java.util.*;
import logg.*;

public class Couple extends Thread {
    private static int coupleCounter = 0;
    private int id;
    private Logg log;
    private Individual father;
    private Individual mother;
    private ArrayList<Individual> children;
    private Random rng = new Random();

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

    private void applyParentCosts()
    {
        // int k = (int) Math.round(((this.father.expAge+this.mother.expAge)/4.0)/20.0);
        // this.log.logMessage(String.valueOf(k));
        int k = (int) Math.round(((this.father.getExpAge()+this.mother.getExpAge()) * 0.5) * 0.04);
        if (this.father.type == Individual.Alignment.FAITHFUL && this.mother.type == Individual.Alignment.COY)
        {
            // A - (B/2) - C --> -((A - (B/2) - C) - A) --> -(-(B/2) - C ) --> (B/2) + C
            this.father.lock(k * ((this.simCosts[1]/2) + this.simCosts[2]));
            this.mother.lock(k * ((this.simCosts[1]/2) + this.simCosts[2]));
        } else
        if (this.father.type == Individual.Alignment.FAITHFUL && this.mother.type == Individual.Alignment.FAST)
        {
            this.father.lock(k * (this.simCosts[1]/2));
            this.mother.lock(k * (this.simCosts[1]/2));
        } else
        if (this.father.type == Individual.Alignment.PHILANDERER && this.mother.type == Individual.Alignment.FAST)
        {
            this.mother.lock(k * this.simCosts[1]);
        }
    }

    private int childExpAge;
    private float evoPressure;
    private int[] simCosts;

    public void setParams(int i_expAge, float i_evoPressure, int[] i_simCosts)
    {
        this.childExpAge = i_expAge;
        this.evoPressure = i_evoPressure;
        this.simCosts = i_simCosts;
    }

    @Override
    public void run()
    {
        this.children = this.makeChildren();
    }

    public ArrayList<Individual> getChildren()
    {
        return this.children;
    }

    private ArrayList<Individual> makeChildren()
    {
        int childCount = this.rng.nextInt(3) + 1;
        ArrayList<Individual> childList = new ArrayList<Individual>();

        this.applyParentCosts();
        for (int i = 0; i < childCount; i++)
        {
            childList.add(this.procreation());
        }

        this.free();
        return childList;
    }

    private Individual procreation()
    /*
    Through this method a new individual can be generated with a 50%
    chance of either being male or female and an 85% chance of inheritance
    of the parent's alignment, also takes in input the expected age for the new
    individual that has to be generated
     */
    {
        Individual child = new Individual(0, 0, this.childExpAge, this.log);

        float selector = this.rng.nextFloat();
        float typeSelector = this.rng.nextFloat();

        if (selector < 0.50)
        {
            if (this.father.type == Individual.Alignment.FAITHFUL)
            {
                if (typeSelector < this.evoPressure)
                {
                    child = new Individual(0, 0, this.childExpAge, this.log);
                }
                else
                {
                    child = new Individual(0, 1, this.childExpAge, this.log);
                }
            }
            else
            {
                if (typeSelector < this.evoPressure)
                {
                    child = new Individual(0, 1, this.childExpAge, this.log);
                }
                else
                {
                    child = new Individual(0, 0, this.childExpAge, this.log);
                }
            }
        }
        else
        {
            if (this.mother.type == Individual.Alignment.COY)
            {
                if (typeSelector < this.evoPressure)
                {
                    child = new Individual(1, 2, this.childExpAge, this.log);
                }
                else
                {
                    child = new Individual(1, 3, this.childExpAge, this.log);
                }
            }
            else
            {
                if (typeSelector < this.evoPressure)
                {
                    child = new Individual(1, 3, this.childExpAge, this.log);
                }
                else
                {
                    child = new Individual(1, 2, this.childExpAge, this.log);
                }
            }
        }

        this.log.logQuietMessage("A child has been born", "Couple-" + String.valueOf(this.id));
        return child;
    }
}
