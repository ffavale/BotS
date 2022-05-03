package bots.simulation.individual;

public class Individual {

    private static int entityCounter = 1;
    private int id;
    private String sex;
    private String type;
    private boolean isAvailable = true;
    private int age = 0;
    private double deathChance = 0.01;

    public Individual(String sex, String type){
        this.id = Individual.entityCounter;
        this.sex = sex;
        this.type = type;
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
}
