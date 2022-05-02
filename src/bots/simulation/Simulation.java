package bots.simulation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import bots.simulation.individual.*;
import java.util.Random;
import java.math.*;
import logg.*;


public class Simulation {

    private static int simCounter = 0;
    private int simID;
    private ArrayList<Individual> populationArray;
    private int startingPopulation;
    private double[] startingRatioFPCS;
    private int[] simulationCosts;

    private int simulationSteps = 0;



    public Simulation(int popNum, double[] fpcsRatios, int[] costs){
        this.simID = Simulation.simCounter;
        Simulation.simCounter++;
        this.startingPopulation = popNum;
        this.startingRatioFPCS = fpcsRatios;
        this.simulationCosts = costs;
        this.populationArray = populator();
    }

    public void info(){
        Logg log = new Logg("Simulation" + this.simID, "Simulation"+ this.simID);
        String[] info = {"Faithful   ", "Philanderer", "Coy        ", "Fast       "};
        String[] bar = new String[4];
        for (int j = 0; j < 4; j++){
            bar[j] = (info[j] + " - [");
            for (int i = 1; i < 51; i++){
                if (i <= Math.round(this.startingRatioFPCS[j]*50)){
                    bar[j] = bar[j] + ("/");
                }
                else {
                    bar[j] = bar[j] + ("_");
                }
            }
            bar[j] = bar[j] + ("] " + Math.round(this.startingRatioFPCS[j]*100) + "%\n");
        }
        log.logMessage("\n--------------- Current Info ---------------\n\nStarting Population: " + this.populationArray.size() + "\n\nRatios of FPCS:\n" + bar[0] + bar[1] + bar[2] + bar[3]);

    }


    private ArrayList<Individual> populator(){
        ArrayList<Individual> Res = new ArrayList<>();
        String[][] type = {{"Male", "Faithful"}, {"Male", "Philanderer"}, {"Female", "Coy"}, {"Female", "Fast"}};
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < Math.round(this.startingPopulation * this.startingRatioFPCS[j]); i++) {
                Res.add(new Individual(type[j][0], type[j][1]));
            }
        }
        return Res;
    }

    public void StartSim(){
        boolean forceStop = true;
        while (forceStop){

        }
    }



}
