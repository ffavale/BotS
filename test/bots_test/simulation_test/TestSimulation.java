package bots_test.simulation_test;
import bots.simulation.*;


public class TestSimulation {

    public static void populationInfoTest(){
        double[] ratio = {0.5, 0, 0, 0.5};
        int[] costs = {10, 15, 3};
        Simulation a = new Simulation(99, ratio, costs);
        a.info();
    }

}
