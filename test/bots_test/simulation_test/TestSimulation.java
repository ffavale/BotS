package bots_test.simulation_test;
import bots.simulation.*;


public class TestSimulation {

    public static void populationInfoTest(){
        double[] ratio = {0.23, 0.09, 0.30, 0.38};
        int[] costs = {10, 15, 3};
        Simulation a = new Simulation(100, ratio, costs);
        a.info();
        a.oneLineInfo();
    }

}
