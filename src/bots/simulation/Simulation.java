package bots.simulation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import bots.simulation.individual.*;
import java.util.Random;
import java.math.*;


public class Simulation {

    private ArrayList<Individual> PopArray;
    private int starting_pop;
    private double[] starting_fpcs;
    private int[] sim_costs;

    private int SimSteps = 0;



    public Simulation(int PopNum, double[] fpcsRatios, int[] Costs){
        this.starting_pop = PopNum;
        this.starting_fpcs = fpcsRatios;
        this.sim_costs = Costs;
        this.PopArray = populator();
    }

    public void info(){
        System.out.println("--------------- Current Info ---------------");
        System.out.println("");
        System.out.println("Starting Population: " + this.starting_pop);
        System.out.println("");
        System.out.println("Ratios of FPCS:");
        System.out.print("Faithful - [");
        for (int i = 1; i < 21; i++){
            if (i <= Math.round(this.starting_fpcs[0]*20)){
                System.out.print("█");
            }
            else {
                System.out.print("░");
            }
        }
        System.out.println("] " + Math.round(this.starting_fpcs[0]*100) + "%");
        System.out.print("Philanderers - [");
        for (int i = 1; i < 21; i++){
            if (i <= Math.round(this.starting_fpcs[1]*20)){
                System.out.print("█");
            }
            else {
                System.out.print("░");
            }
        }
        System.out.println("] " + Math.round(this.starting_fpcs[1]*100) + "%");
        System.out.print("Coy - [");
        for (int i = 1; i < 21; i++){
            if (i <= Math.round(this.starting_fpcs[2]*20)){
                System.out.print("█");
            }
            else {
                System.out.print("░");
            }
        }
        System.out.println("] " + Math.round(this.starting_fpcs[2]*100) + "%");
        System.out.print("Fast - [");
        for (int i = 1; i < 21; i++){
            if (i <= Math.round(this.starting_fpcs[3]*20)){
                System.out.print("█");
            }
            else {
                System.out.print("░");
            }
        }
        System.out.println("] " + Math.round(this.starting_fpcs[3]*100) + "%");

    }


    private ArrayList<Individual> populator(){
        ArrayList<Individual> Res = new ArrayList<>();
        for (int i = 0; i < Math.round(this.starting_pop*this.starting_fpcs[0]); i++){
            Res.add(new Individual("Male", "Faithful"));
        }
        for (int i = 0; i < Math.round(this.starting_pop*this.starting_fpcs[1]); i++){
            Res.add(new Individual("Male", "Philanderer"));
        }
        for (int i = 0; i < Math.round(this.starting_pop*this.starting_fpcs[2]); i++){
            Res.add(new Individual("Female", "Coy"));
        }
        for (int i = 0; i < Math.round(this.starting_pop*this.starting_fpcs[3]); i++){
            Res.add(new Individual("Female", "Fast"));
        }
        return Res;
    }

    public void StartSim(){
        boolean ForceStop = true;
        while (ForceStop){

        }
    }



}
