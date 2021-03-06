package bots;

import bots.simulation.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;
import java.util.*;
import java.lang.Runtime;
import logg.*;

public class Main
{
    enum u {YES, NO, BOTH};
    private static final int CPUCOUNT = Runtime.getRuntime().availableProcessors();
    private static final int permitConcSims = (int) CPUCOUNT*4/5;

    private static ArrayList<Blueprint> simBlueprintArrayList = new ArrayList<Blueprint>();

    public static void splashScreen(Logg i_log)
    {
        i_log.logMessage("\n#####################################################################################\n/ // // // // // // // // // // // // // // // // // // // // // // // // // // // //\n#####################################################################################\n\n          ________            ____        __  __  __              ____  \n         /_  __/ /_  ___     / __ )____ _/ /_/ /_/ /__     ____  / __/ \n          / / / __ \\/ _ \\   / __  / __ `/ __/ __/ / _ \\   / __ \\/ /_\n         / / / / / /  __/  / /_/ / /_/ / /_/ /_/ /  __/  / /_/ / __/\n        /_/ /_/ /_/\\___/  /_____/\\__,_/\\__/\\__/_/\\___/   \\____/_/\n                __  __            _____\n               / /_/ /_  ___     / ___/___  _  _____  _____\n              / __/ __ \\/ _ \\    \\__ \\/ _ \\| |/_/ _ \\/ ___/\n             / /_/ / / /  __/   ___/ /  __/>  </  __(__  )\n             \\__/_/ /_/\\___/   /____/\\___/_/|_|\\___/____/\n\n\n#####################################################################################\n// // // // // // // // // // // // // // // // // // // // // // // // // // // // /\n#####################################################################################\nCPUs: " + String.valueOf(CPUCOUNT)+ "\nConcurrent sims: " + String.valueOf(permitConcSims));
    }

    /* Program starts here */
    public static void main(String[] args)
    {
        // init main log
        Logg log = new Logg("main", "main");

        // print splash screen
        splashScreen(log);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document;

            try {
                if (args.length > 0)
                {
                    document = builder.parse(args[0]);
                } else {
                    document = builder.parse("setting.xml");
                }
            } catch (FileNotFoundException e)
            {
                System.out.println("Settings file not found; Exiting...");
                return;
            }

            document.getDocumentElement().normalize();

            NodeList simNodeList = document.getElementsByTagName("simulation");

            for (int i = 0; i < simNodeList.getLength(); i++)
            {
                Node simNode = simNodeList.item(i);
                if (simNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element simElement = (Element) simNode;
                    // System.out.println(String.valueOf(i) + " pop: " + simElement.getAttribute("population"));
                    NodeList simDetailsNodeList = simElement.getChildNodes();

                    int simPopPassthrough = 1;
                    int minSimLoops = 0;
                    int maxSimLoops = 0;
                    int avgAge = 0;
                    double[] simFPCSPassthrough = new double[3];
                    int[] simCostPassthrough = new int[3];
                    u universality = u.YES;
                    boolean mutAllowed = true;

                    for (int j = 0; j < simDetailsNodeList.getLength(); j++)
                    {
                        Node simDetail = simDetailsNodeList.item(j);

                        if (simDetail.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element simDetailElement = (Element) simDetail;
                            switch (simDetailElement.getTagName())
                            {
                                case "population":
                                    {
                                        simPopPassthrough = Integer.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "minSimLoops":
                                    {
                                        minSimLoops = Integer.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "maxSimLoops":
                                    {
                                        maxSimLoops = Integer.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "averageAge":
                                    {
                                        avgAge = Integer.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "mfRatio":
                                    {
                                        simFPCSPassthrough[0] = Double.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "fpRatio":
                                    {
                                        simFPCSPassthrough[1] = Double.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "csRatio":
                                    {
                                        simFPCSPassthrough[2] = Double.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "a":
                                    {
                                        simCostPassthrough[0] = Integer.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "b":
                                    {
                                        simCostPassthrough[1] = Integer.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "c":
                                    {
                                        simCostPassthrough[2] = Integer.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                                case "isUniversal":
                                    {
                                        switch (simDetailElement.getTextContent())
                                        {
                                            case "0":
                                                {
                                                    universality = u.NO;
                                                    break;
                                                }
                                            case "1":
                                                {
                                                    universality = u.YES;
                                                    break;
                                                }
                                            case "2":
                                                {
                                                    universality = u.BOTH;
                                                    break;
                                                }
                                        }
                                    }
                                case "mutAllowed":
                                    {
                                        mutAllowed = Boolean.valueOf(simDetailElement.getTextContent());
                                        break;
                                    }
                            }
                        }
                    }
                    log.logMessage("Starting simulation " + String.valueOf(i)+ " with -> " +
                        "population: " + String.valueOf(simPopPassthrough) + " | " +
                        "FPCS: " + String.valueOf(simFPCSPassthrough[0]) + ", " + String.valueOf(simFPCSPassthrough[1]) + ", " + String.valueOf(simFPCSPassthrough[2]) + " | " +
                        "Costs: " + String.valueOf(simCostPassthrough[0]) + ", " + String.valueOf(simCostPassthrough[1]) + ", " + String.valueOf(simCostPassthrough[2]));
                    switch (universality)
                    {
                        case YES:
                            {
                                Blueprint sim = new Blueprint(simPopPassthrough, minSimLoops, maxSimLoops, avgAge, simFPCSPassthrough, simCostPassthrough, true, mutAllowed);
                                Main.simBlueprintArrayList.add(sim);
                                break;
                            }
                        case NO:
                            {
                                Blueprint sim = new Blueprint(simPopPassthrough, minSimLoops, maxSimLoops, avgAge, simFPCSPassthrough, simCostPassthrough, false, mutAllowed);
                                simBlueprintArrayList.add(sim);
                                break;
                            }
                        case BOTH:
                            {
                                Blueprint sima = new Blueprint(simPopPassthrough, minSimLoops, maxSimLoops, avgAge, simFPCSPassthrough, simCostPassthrough, true, mutAllowed);
                                Blueprint simb = new Blueprint(simPopPassthrough, minSimLoops, maxSimLoops, avgAge, simFPCSPassthrough, simCostPassthrough, false, mutAllowed);
                                simBlueprintArrayList.add(sima);
                                simBlueprintArrayList.add(simb);
                                break;
                            }
                    }
                }
            }
        } catch (ParserConfigurationException e){e.printStackTrace();
        } catch (SAXException e) {e.printStackTrace();
        } catch (IOException e) {e.printStackTrace();}
        log.finalize();

        int simsRan = 0;
        Simulation[] runSlots = new Simulation[permitConcSims];

        while (simsRan <= simBlueprintArrayList.size())
        {
            for (int i = 0; i < runSlots.length; i++)
            {
                if (runSlots[i] == null)
                {
                    if (simsRan < simBlueprintArrayList.size())
                    {
                        Blueprint bpt = simBlueprintArrayList.get(simsRan);
                        runSlots[i] = new Simulation(bpt.simPopPassthrough, bpt.minSimLoops, bpt.maxSimLoops, bpt.avgAge, bpt.simFPCSPassthrough, bpt.simCostPassthrough, bpt.universality, bpt.mutAllowed);
                        simsRan++; runSlots[i].start();
                    }
                }
                if (!runSlots[i].isAlive())
                {
                    if (simsRan < simBlueprintArrayList.size())
                    {
                        Blueprint bpt = simBlueprintArrayList.get(simsRan);
                        runSlots[i] = new Simulation(bpt.simPopPassthrough, bpt.minSimLoops, bpt.maxSimLoops, bpt.avgAge, bpt.simFPCSPassthrough, bpt.simCostPassthrough, bpt.universality, bpt.mutAllowed);
                        simsRan++; runSlots[i].start();
                    }
                }
            }

            try
            {
                Thread.sleep(3000);
            } catch (InterruptedException e) {e.printStackTrace();}
        }
    }

}

class Blueprint {
    public final int simPopPassthrough;
    public final int minSimLoops;
    public final int maxSimLoops;
    public final int avgAge;
    public final double[] simFPCSPassthrough;
    public final int[] simCostPassthrough;
    public final boolean universality;
    public final boolean mutAllowed;
    public Blueprint(int i_simPopPassthrough, int i_minSimLoops, int i_maxSimLoops, int i_avgAge, double[] i_FPCSPassthrough, int[] i_CostPassthrough, boolean i_universality, boolean i_mutAllowed)
    {
        this.simPopPassthrough = i_simPopPassthrough;
        this.minSimLoops = i_minSimLoops;
        this.maxSimLoops = i_maxSimLoops;
        this.avgAge = i_avgAge;
        this.simFPCSPassthrough = i_FPCSPassthrough;
        this.simCostPassthrough = i_CostPassthrough;
        this.universality = i_universality;
        this.mutAllowed = i_mutAllowed;
    }
}
