package bots.simulation;

import java.util.ArrayList;
import bots.simulation.individual.*;
import bmath.*;
import logg.*;


public class Simulation extends Thread
{
    private static int simCounter = 0;
    private final int minLoopCount;
    private final int maxLoopCount;

    private int simID;
    private boolean isUniversal;
    private Logg log;
    private ArrayList<Individual> populationArray;

    private int countF = 0;
    private int countP = 0;
    private int countC = 0;
    private int countS = 0;

    private int avgAge;

    private double[] ratiosFPCS = new double[4];
    private int[] simulationCosts;

    private long simulationSteps = 0;

    private ArrayList<Snapshot> snapshotArray = new ArrayList<Snapshot>();

    public Simulation(int popNum, int minSimLoops, int maxSimLoops, int avgAge, double[] fpcsRatios, int[] costs, boolean i_isUniversal)
    {
        // give simulation its Id
        this.simID = Simulation.simCounter;
        Simulation.simCounter++;
        this.isUniversal = i_isUniversal;
        this.minLoopCount = minSimLoops;
        this.maxLoopCount = maxSimLoops;
        // assign population values
        this.ratiosFPCS[0] = fpcsRatios[0] * fpcsRatios[1];
        this.ratiosFPCS[1] = fpcsRatios[0] * (1 - fpcsRatios[1]);
        this.ratiosFPCS[2] = (1 - fpcsRatios[0]) * fpcsRatios[2];
        this.ratiosFPCS[3] = (1 - fpcsRatios[0]) * (1 - fpcsRatios[2]);
        this.simulationCosts = costs;
        this.log = new Logg("Simulation-" + this.simID, "Simulation-"+ this.simID);
        // populate simulation
        this.avgAge = avgAge;
        this.populationArray = populator(avgAge, popNum);
        this.countF = (int) Math.round(popNum * this.ratiosFPCS[0]);
        this.countP = (int) Math.round(popNum * this.ratiosFPCS[1]);
        this.countC = (int) Math.round(popNum * this.ratiosFPCS[2]);
        this.countS = (int) Math.round(popNum * this.ratiosFPCS[3]);
    }

    public void info()
    {
        double[] tempFPCS =  {
        (double) this.countF / this.populationArray.size(),
        (double) this.countP / this.populationArray.size(),
        (double) this.countC / this.populationArray.size(),
        (double) this.countS / this.populationArray.size()};
        String[] info = {"Faithful   ", "Philanderer", "Coy        ", "Fast       "};
        String[] bar = new String[4];

        for (int j = 0; j < 4; j++)
        {
            bar[j] = (info[j] + " - [");

            for (int i = 1; i < 51; i++)
            {
                if (i <= Math.round(tempFPCS[j]*50))
                {
                    bar[j] = bar[j] + ("/");
                }
                else
                {
                    bar[j] = bar[j] + ("_");
                }
            }
            bar[j] = bar[j] + ("] " + Math.round(tempFPCS[j]*100) + "%\n");
        }
        this.log.logMessage("--------------- Current Info ---------------\nIteration: " + String.valueOf(this.simulationSteps) +"\nPopulation: " + this.populationArray.size() + "\nRatios of FPCS:\n" + bar[0] + bar[1] + bar[2] + bar[3]);
    }

    public void oneLineInfo(){
        this.log.logQuietMessage("Iteration count: " + this.simulationSteps +
        " - Population number: " + this.populationArray.size() +
        " - FPCS Ratios: " + String.valueOf(Double.valueOf(this.countF) / Double.valueOf(this.populationArray.size())) +
        " " + String.valueOf(Double.valueOf(this.countP) / Double.valueOf(this.populationArray.size())) +
        " " + String.valueOf(Double.valueOf(this.countC) / Double.valueOf(this.populationArray.size())) +
        " " + String.valueOf(Double.valueOf(this.countS) / Double.valueOf(this.populationArray.size())));
    }

    private ArrayList<Individual> populator(int expAge, int i_initPop)
    {
        ArrayList<Individual> res = new ArrayList<>();
        int[][] type = {{0, 0}, {0, 1}, {1, 2}, {1, 3}};

        for (int j = 0; j < 4; j++)
        {
            for (int i = 0; i < Math.round(i_initPop * this.ratiosFPCS[j]); i++)
            {
                res.add(new Individual(type[j][0], type[j][1], expAge, 0, this.log, true));
            }
        }
        return res;
    }

    // double satisfactoryCount = 0;
    //
    // for (int i = 1; i < snapSample.size(); i++)
    // {
    //     double ratio = (double) snapSample.get(i).populationCount/snapSample.get(i-1).populationCount;
    //     double diff = 0.2;
    //     if (snapSample.get(i-1).populationCount * (1-diff) <= ratio && ratio <= snapSample.get(i-1).populationCount * (1+diff))
    //     {
    //         satisfactoryCount++;
    //     }
    // }
    //
    // boolean ret = (satisfactoryCount/snapSample.size()> 0.8);
    // if (ret) {this.log.logQuietMessage("Simulation population is stable");}
    // return ret;

    private boolean popIsStable(ArrayList<Snapshot> snapSample)
    {
        // if (ret) {this.log.logQuietMessage("Simulation population is stable");}
        return true;
    }

    private boolean fIsStable(ArrayList<Snapshot> snapSample)
    {
        return true;
    }

    private boolean pIsStable(ArrayList<Snapshot> snapSample)
    {
        return true;
    }

    private boolean cIsStable(ArrayList<Snapshot> snapSample)
    {
        return true;
    }

    private boolean sIsStable(ArrayList<Snapshot> snapSample)
    {
        return true;
    }

    private boolean isStable()
    {
        ArrayList<Snapshot> snapSample = Bmath.sampleGenerator(this.snapshotArray);

        if (this.simulationSteps < this.minLoopCount ||
                (this.simulationSteps < this.maxLoopCount) &&
                this.popIsStable(snapSample) &&
                this.fIsStable(snapSample) &&
                this.pIsStable(snapSample) &&
                this.cIsStable(snapSample) &&
                this.sIsStable(snapSample))
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
            Event loopEvent;
            if (this.isUniversal)
            {
                loopEvent = new Event(this.simulationSteps, this.populationArray, this.log, this.isUniversal);
            } else
            {
                loopEvent = new Event(this.simulationSteps, this.populationArray, this.avgAge, this.log);
            }
            // get back a bunch of couples and make them reproduce (async?)
            ArrayList<Couple> loopCouples = loopEvent.createCouples();
            for (Couple coup : loopCouples)
            {
                Individual tempIndividual = coup.procreation(this.avgAge, this.simulationCosts);
                switch (tempIndividual.type)
                {
                    case FAITHFUL:
                        {
                            countF++;
                            break;
                        }
                    case PHILANDERER:
                        {
                            countP++;
                            break;
                        }
                    case COY:
                        {
                            countC++;
                            break;
                        }
                    case FAST:
                        {
                            countS++;
                            break;
                        }

                }
                populationArray.add(tempIndividual);
            }

            // save snapshot of the simulation
            snapshotArray.add(new Snapshot(
                        this.simulationSteps,
                        this.populationArray.size(),
                        (double) this.countF / this.populationArray.size(),
                        (double) this.countP / this.populationArray.size(),
                        (double) this.countC / this.populationArray.size(),
                        (double) this.countS / this.populationArray.size()));
            // print info
            this.simulationSteps++;
            this.oneLineInfo();
            if (this.simulationSteps % 10 == 0) {this.info();}
            // check who is dead
            ArrayList<Individual> toRemove = new ArrayList<Individual>();
            for (Individual ind : this.populationArray)
            {
                if (ind.isDead())
                {
                    toRemove.add(ind);
                }
            }
            for (Individual ind : toRemove)
            {
                switch (ind.type)
                {
                    case FAITHFUL:
                        {
                            countF--;
                            break;
                        }
                    case PHILANDERER:
                        {
                            countP--;
                            break;
                        }
                    case COY:
                        {
                            countC--;
                            break;
                        }
                    case FAST:
                        {
                            countS--;
                            break;
                        }
                }

                this.populationArray.remove(ind);
            }

        }
    }

    public class Snapshot
    {
        public final long steps;
        public final int populationCount;
        public final double ratio_F;
        public final double ratio_P;
        public final double ratio_C;
        public final double ratio_S;

        private Snapshot(long i_steps, int i_popCount, double i_ratio_F, double i_ratio_P, double i_ratio_C, double i_ratio_S)
        {
            this.steps = i_steps;
            this.populationCount = i_popCount;
            this.ratio_F = i_ratio_F;
            this.ratio_P = i_ratio_P;
            this.ratio_C = i_ratio_C;
            this.ratio_S = i_ratio_S;
        }
    }
}
