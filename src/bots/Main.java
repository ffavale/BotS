package bots;

import bots.simulation.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;
import logg.*;

public class Main
{
    public static void splashScreen(Logg i_log)
    {
        i_log.logMessage("\n#####################################################################################\n/ // // // // // // // // // // // // // // // // // // // // // // // // // // // //\n#####################################################################################\n\n          ________            ____        __  __  __              ____  \n         /_  __/ /_  ___     / __ )____ _/ /_/ /_/ /__     ____  / __/ \n          / / / __ \\/ _ \\   / __  / __ `/ __/ __/ / _ \\   / __ \\/ /_\n         / / / / / /  __/  / /_/ / /_/ / /_/ /_/ /  __/  / /_/ / __/\n        /_/ /_/ /_/\\___/  /_____/\\__,_/\\__/\\__/_/\\___/   \\____/_/\n                __  __            _____\n               / /_/ /_  ___     / ___/___  _  _____  _____\n              / __/ __ \\/ _ \\    \\__ \\/ _ \\| |/_/ _ \\/ ___/\n             / /_/ / / /  __/   ___/ /  __/>  </  __(__  )\n             \\__/_/ /_/\\___/   /____/\\___/_/|_|\\___/____/\n\n\n#####################################################################################\n// // // // // // // // // // // // // // // // // // // // // // // // // // // // /\n#####################################################################################\n");
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
            Document document = builder.parse("settings.xml");
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
                    double[] simFPCSPassthrough = new double[3];
                    int[] simCostPassthrough = new int[3];

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
                            }
                        }
                    }
                    log.logMessage("Starting simulation with -> " +
                        "population: " + String.valueOf(simPopPassthrough) + " | " +
                        "FPCS: " + String.valueOf(simFPCSPassthrough[0]) + ", " + String.valueOf(simFPCSPassthrough[1]) + ", " + String.valueOf(simFPCSPassthrough[2]) + " | " +
                        "Costs: " + String.valueOf(simCostPassthrough[0]) + ", " + String.valueOf(simCostPassthrough[1]) + ", " + String.valueOf(simCostPassthrough[2]));
                    Simulation sim = new Simulation(simPopPassthrough, 1000, simFPCSPassthrough, simCostPassthrough);
                    sim.start();
                }
            }

        } catch (ParserConfigurationException e){e.printStackTrace();
        } catch (SAXException e) {e.printStackTrace();
        } catch (IOException e) {e.printStackTrace();}
    }
}
