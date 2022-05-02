package bots.simulation.individual;

public class Individual {

    private static int entitycounter = 0;
    private int id;
    private String sex;
    private String type;
    private boolean isavailable = true;
    private int age = 0;
    private double deathchance = 0.01;

    public Individual(String sex, String type){
        this.id = this.entitycounter;
        this.sex = sex;
        this.type = type;
        this.entitycounter++;
    }









}
