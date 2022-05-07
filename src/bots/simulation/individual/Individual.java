package bots.simulation.individual;

import logg.*;

public class Individual {
    public enum Gender {MALE, FEMALE};
    public enum Alignment {FAITHFUL, PHILANDERER, COY, FAST};

    private static int entityCounter = 1;
    private int id;
    public final Gender sex;
    public final Alignment type;
    private boolean isAvailable = true;
    private int age = 0;
    private double deathChance = 0.01;
    private Logg log;

    public Individual(int sex, int type, Logg i_log){
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
}
