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
        pop.add(new Individual("Male", "Phil"));
        pop.add(new Individual("Female", "Fast"));
        Event event1 = new Event(69420, pop, log);

        System.out.println("");
        System.out.println("");
    }

}
