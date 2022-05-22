package bots.simulation.individual;

import logg.*;

import java.util.Random;

import static java.lang.Math.*;

public class Individual {
    public enum Gender {MALE, FEMALE};
    public enum Alignment {FAITHFUL, PHILANDERER, COY, FAST};

    private static int entityCounter = 1;
    private int id;
    public final Gender sex;
    public final Alignment type;
    public boolean isAvailable = true;
    private int age = 0;
    private double lifeChance = 1;
    private double loopCost;
    private int expAge;
    private Logg log;
    private static final Random rng = new Random();

    public Individual(int sex, int type, int expAge, Logg i_log){
        this.id = Individual.entityCounter;
        if (sex == 0) {
            this.sex = Gender.MALE;
            if (type == 0) {
                this.type = Alignment.FAITHFUL;
            }
            else{
                this.type = Alignment.PHILANDERER;
            }
        }
        else {
            this.sex = Gender.FEMALE;
            if (type == 2){
                this.type = Alignment.COY;
            }
            else{
                this.type = Alignment.FAST;
            }
        }
        this.log = i_log;
        this.expAge = expAge;
        this.loopCost = -atan(this.age - expAge) + (Math.PI*0.5);
        Individual.entityCounter++;
    }

    public int getId()
    {
        return this.id;
    }

    public int getAge()
    {
        return this.age;
    }

    public void incrementAge()
    {
        this.age++;
        // this.log.logMessage("The age of Individual-" + String.valueOf(this.id) + " has been incremented to " + String.valueOf(this.age), "Individual-" + String.valueOf(this.id));
    }

    public boolean isDead ()
    {
        if (this.lifeChance < (Individual.rng.nextDouble()))
        {
            return false;
        }
        else
        {
            this.lifeChance = this.lifeChance*(this.lifeChance - this.loopCost);
            incrementAge();
            return true;
        }
    }
}
