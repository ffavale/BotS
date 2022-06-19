package bots.simulation.individual;

import logg.*;
import java.util.*;
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
    private int expAge;
    private Logg log;
    private static final Random rng = new Random();
    private int lockedFor = 0;
    private int benefitPoints = 0;

    public Individual(int sex, int type, int expAge, Logg i_log)
    /*
    Takes as input two integers, one representing the gender and the alignment
    male and female (0 and 1) and FPCS (0123), also takes in input the expected age
    of the individual that determines his survivability, and the log to report any event
    there's also a unique ID for each individual, an age to determine how old he/she is
    and a chance of surviving in each step of the simulation that can be increased or decreased
     */
    {
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


    public Individual(int sex, int type, int expAge, int i_reproductionWait, Logg i_log, boolean i_randAge)
    {
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
        this.age = rng.nextInt(expAge);
        Individual.entityCounter++;
    }

    public boolean isLocked()
    {
        return (0 < this.lockedFor);
    }

    public void lock(int lockCount)
    {
        if (lockCount < 0)
        {
            this.lockedFor = 0;
        } else {
            this.lockedFor = lockCount;
        }
    }

    public int getId()
    {
        return this.id;
    }

    public int getAge()
    {
        return this.age;
    }

    public int getExpAge()
    {
        return this.expAge;
    }

    private void incrementAge()
    {
        if (this.lockedFor > 0) {this.lockedFor--;}
        this.age++;
    }

    public boolean isDead ()
    /*
    Checks if the individual dies by "throwing a dice" and seeing if it's higher
    than his/her survival chance, if he/she survives then the chance of dying increases
    and in the end it increases the age
     */
    {
        double deathChance = (atan((this.age - this.expAge)) + (Math.PI*0.5)) * (1/(Math.PI));
        boolean ret = (deathChance > Individual.rng.nextDouble());
        incrementAge();
        if (ret) {this.log.logQuietMessage("Individual is dead at age " + String.valueOf(this.age) + " and a death chance of " + String.valueOf(deathChance), "Individual-" + String.valueOf(this.id));}
        return ret;
    }
}
