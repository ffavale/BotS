package bots_test.simulation_test;

import logg.Logg;
import bots.simulation.*;
import java.util.*;
import bots.simulation.individual.*;

public class TestEvent
{
    public static void eventCreation()
    {
        Logg log = new Logg("eventCreation", "eventCreation");
        ArrayList<Individual> pop = new ArrayList<Individual>();

        for (int i = 0; i < 100; i++)
        {
            pop.add(new Individual("Male", "Philanderer", log));
            pop.add(new Individual("Female", "Fast", log));
        }

        for (Individual ind : pop)
        {
            for (int i = 0; i < 3500; i++)
                ind.incrementAge();
        }

        Event event1 = new Event(69420, pop, log);

        System.out.println("");
        System.out.println("");
    }

    public static void universalEventCreation()
    {
        Logg log = new Logg("universalEventCreation", "universalEventCreation");
        ArrayList<Individual> pop = new ArrayList<Individual>();

        for (int i = 0; i < 7; i++)
        {
            pop.add(new Individual("Male", "Philanderer", log));
            pop.add(new Individual("Female", "Fast", log));
        }

        for (Individual ind : pop)
        {
            for (int i = 0; i < 3500; i++)
                ind.incrementAge();
        }

        Event event1 = new Event(8008135, pop, log, true);

        System.out.println("");
        System.out.println("");
    }
}