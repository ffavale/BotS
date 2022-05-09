package bots.simulation;

import java.util.ArrayList;
import bots.simulation.individual.*;
import bmath.*;
import logg.*;


public class Simulation extends Thread
{
    private static int simCounter = 0;

    private int simID;
    private Logg log;
    private ArrayList<Individual> populationArray;

    private int populationCount;
    private int countF = 0;
    private int countP = 0;
    private int countC = 0;
    private int countS = 0;


    private double[] ratiosFPCS = new double[4];
    private int[] simulationCosts;

    private long simulationSteps = 0;



    public Simulation(int popNum, double[] fpcsRatios, int[] costs)
    {
        // give simulation its Id
        this.simID = Simulation.simCounter;
        Simulation.simCounter++;
        // assign population values
        this.populationCount = popNum;
        this.ratiosFPCS[0] = fpcsRatios[0] * fpcsRatios[1];
        this.ratiosFPCS[1] = fpcsRatios[0] * (1 - fpcsRatios[1]);
        this.ratiosFPCS[2] = (1 - fpcsRatios[0]) * fpcsRatios[2];
        this.ratiosFPCS[3] = (1 - fpcsRatios[0]) * (1 - fpcsRatios[2]);
        this.simulationCosts = costs;
        this.log = new Logg("Simulation-" + this.simID, "Simulation-"+ this.simID);
        // populate simulation
        this.populationArray = populator();
        this.countF = (int) Math.round(popNum * this.ratiosFPCS[0]);
        this.countP = (int) Math.round(popNum * this.ratiosFPCS[1]);
        this.countC = (int) Math.round(popNum * this.ratiosFPCS[2]);
        this.countS = (int) Math.round(popNum * this.ratiosFPCS[3]);
    }

    public void info()
    {
        String[] info = {"Faithful   ", "Philanderer", "Coy        ", "Fast       "};
        String[] bar = new String[4];

        for (int j = 0; j < 4; j++)
        {
            bar[j] = (info[j] + " - [");

            for (int i = 1; i < 51; i++)
            {
                if (i <= Math.round(this.ratiosFPCS[j]*50))
                {
                    bar[j] = bar[j] + ("/");
                }
                else
                {
                    bar[j] = bar[j] + ("_");
                }
            }
            bar[j] = bar[j] + ("] " + Math.round(this.ratiosFPCS[j]*100) + "%\n");
        }
        this.log.logMessage("\n--------------- Current Info ---------------\nPopulation: " + this.populationArray.size() + "\nRatios of FPCS:\n" + bar[0] + bar[1] + bar[2] + bar[3]);
    }

    public void oneLineInfo(){
        this.log.logMessage("Iteration count: " + this.simulationSteps +
        " - Population number: " + this.populationCount +
        " - FPCS Ratios: " + String.valueOf(Double.valueOf(this.countF) / Double.valueOf(this.populationArray.size())) +
        " " + String.valueOf(Double.valueOf(this.countP) / Double.valueOf(this.populationArray.size())) +
        " " + String.valueOf(Double.valueOf(this.countC) / Double.valueOf(this.populationArray.size())) +
        " " + String.valueOf(Double.valueOf(this.countS) / Double.valueOf(this.populationArray.size())));
    }

    private ArrayList<Individual> populator()
    {
        ArrayList<Individual> res = new ArrayList<>();
        int[][] type = {{0, 0}, {0, 1}, {1, 2}, {1, 3}};

        for (int j = 0; j < 4; j++)
        {
            for (int i = 0; i < Math.round(this.populationCount * this.ratiosFPCS[j]); i++)
            {
                res.add(new Individual(type[j][0], type[j][1], this.log));
            }
        }
        return res;
    }

    public boolean isStable()
    {
        if (false)
        {
            return true;
        }
        return false;
    }

    @Override
    public void run()
    {
        this.log.logMessage("Simulation has started");
        this.info(); this.oneLineInfo();
        /* simulation loop goes here to be in a seperate thread */
        while (this.isStable())
        {
            // create this loop's event
            // put individuals in said event
            // get back a bunch of couples and make them reproduce (async?)
            // save snapshot of the simulation
            // check stability
        }
    }

    public class Snapshot
    {
        public final int populationCount;
        public final double ratio_FoM;
        public final double ratio_FoP;
        public final double ratio_CoS;

        private Snapshot(int i_popCount, double i_ratio_FoM, double i_ratio_FoP, double i_ratio_CoS)
        {
            this.populationCount = i_popCount;
            this.ratio_FoM = i_ratio_FoM;
            this.ratio_FoP = i_ratio_FoP;
            this.ratio_CoS = i_ratio_CoS;
        }
    }
}
