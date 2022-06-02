package bots_test.simulation_test;
import bots.simulation.*;


public class TestSimulation {

    public static void populationInfoTest(){
        double[] ratio = {0.23, 0.09, 0.30, 0.38};
        int[] costs = {10, 15, 3};
        Simulation a = new Simulation(100, 10, 100, 15, ratio, costs, false);
        a.info();
        a.oneLineInfo();
    }

    public static void universalityTest() {
        double[] ratio = {0.5, 0.5, 0.5};
        int[] costs = {10, 15, 3};
        Simulation uu = new Simulation(1000, 10, 50, 15, ratio, costs, true);
        Simulation nu = new Simulation(1000, 10, 50, 15, ratio, costs, false);
        uu.start();
        nu.start();
    }

}
