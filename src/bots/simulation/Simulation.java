package bots.simulation;

import java.util.ArrayList;
import bots.simulation.individual.*;
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


    private double[] startingRatioFPCS = new double[4];
    private int[] simulationCosts;

    private long simulationSteps = 0;



    public Simulation(int popNum, double[] fpcsRatios, int[] costs)
    {
        // give simulation its Id
        this.simID = Simulation.simCounter;
        Simulation.simCounter++;
        // assign population values
        this.populationCount = popNum;
        this.startingRatioFPCS[0] = fpcsRatios[0] * fpcsRatios[1];
        this.startingRatioFPCS[1] = fpcsRatios[0] * (1 - fpcsRatios[1]);
        this.startingRatioFPCS[2] = (1 - fpcsRatios[0]) * fpcsRatios[2];
        this.startingRatioFPCS[3] = (1 - fpcsRatios[0]) * (1 - fpcsRatios[2]);
        this.simulationCosts = costs;
        this.log = new Logg("Simulation-" + this.simID, "Simulation-"+ this.simID);
        // populate simulation
        this.populationArray = populator();
        this.countF = (int) Math.round(popNum * this.startingRatioFPCS[0]);
        this.countP = (int) Math.round(popNum * this.startingRatioFPCS[1]);
        this.countC = (int) Math.round(popNum * this.startingRatioFPCS[2]);
        this.countS = (int) Math.round(popNum * this.startingRatioFPCS[3]);
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
                if (i <= Math.round(this.startingRatioFPCS[j]*50))
                {
                    bar[j] = bar[j] + ("/");
                }
                else
                {
                    bar[j] = bar[j] + ("_");
                }
            }
            bar[j] = bar[j] + ("] " + Math.round(this.startingRatioFPCS[j]*100) + "%\n");
        }
        this.log.logMessage("\n--------------- Current Info ---------------\n\nStarting Population: " + this.populationArray.size() + "\n\nRatios of FPCS:\n" + bar[0] + bar[1] + bar[2] + bar[3]);
    }

    public void oneLineInfo(){
        this.log.logMessage("Iteration count: " + this.simulationSteps + " - Population number: " + this.populationCount + " - FPCS Ratios: " +(double) this.countF/100 + " " +(double) this.countP/100 + " " +(double) this.countC/100 + " " +(double) this.countS/100);

    }

    private ArrayList<Individual> populator()
    {
        ArrayList<Individual> res = new ArrayList<>();
        int[][] type = {{0, 0}, {0, 1}, {1, 2}, {1, 3}};

        for (int j = 0; j < 4; j++)
        {
            for (int i = 0; i < Math.round(this.populationCount * this.startingRatioFPCS[j]); i++)
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
        while (isStable())
        {

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


