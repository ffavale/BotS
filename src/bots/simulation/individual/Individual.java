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
    private int age = 1;
    private double lifeChance = 1;
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
    }

    private double loopCost()
    {
        return atan(this.age - this.expAge) + (Math.PI*0.5);
    }

    public boolean isDead ()
    {
        boolean ret = (lifeChance < Individual.rng.nextDouble()); 
        this.lifeChance = this.lifeChance*(this.lifeChance - this.loopCost());
        incrementAge();
        // if (ret) {this.log.logQuietMessage("Individual-" + String.valueOf(this.id) + " is dead at age " + String.valueOf(this.age));}
        return ret;
    }
}
