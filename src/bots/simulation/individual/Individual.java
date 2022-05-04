package bots.simulation.individual;

import logg.*;

public class Individual {

    private static int entityCounter = 1;
    private int id;
    private String sex;
    private String type;
    private boolean isAvailable = true;
    private int age = 0;
    private double deathChance = 0.01;
    private Logg log;

    public Individual(String sex, String type, Logg i_log){
        this.id = Individual.entityCounter;
        this.sex = sex;
        this.type = type;
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
