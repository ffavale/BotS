package bots;

import java.net.StandardSocketOptions;
import java.math.*;
import bots.simulation.*;

public class Main{

    public static void SplashScreen(){
        System.out.println("#####################################################################################");
        System.out.println("/ // // // // // // // // // // // // // // // // // // // // // // // // // // // //");
        System.out.println("#####################################################################################");
        System.out.println("");
        System.out.println("          ________            ____        __  __  __              ____  ");
        System.out.println("         /_  __/ /_  ___     / __ )____ _/ /_/ /_/ /__     ____  / __/ ");
        System.out.println("          / / / __ \\/ _ \\   / __  / __ `/ __/ __/ / _ \\   / __ \\/ /_");
        System.out.println("         / / / / / /  __/  / /_/ / /_/ / /_/ /_/ /  __/  / /_/ / __/");
        System.out.println("        /_/ /_/ /_/\\___/  /_____/\\__,_/\\__/\\__/_/\\___/   \\____/_/");
        System.out.println("                __  __            _____");
        System.out.println("               / /_/ /_  ___     / ___/___  _  _____  _____");
        System.out.println("              / __/ __ \\/ _ \\    \\__ \\/ _ \\| |/_/ _ \\/ ___/");
        System.out.println("             / /_/ / / /  __/   ___/ /  __/>  </  __(__  )");
        System.out.println("             \\__/_/ /_/\\___/   /____/\\___/_/|_|\\___/____/");
        System.out.println("");
        System.out.println("");
        System.out.println("#####################################################################################");
        System.out.println("// // // // // // // // // // // // // // // // // // // // // // // // // // // // /");
        System.out.println("#####################################################################################");
        System.out.println("");


    }


    /* Program starts here */
    public static void main(String[] args){
        SplashScreen();
        double[] ratio = {1, 0, 0, 0};
        int[] costs = {10, 15, 3};
        Simulation a = new Simulation(100, ratio, costs);
        a.info();


    }
}
