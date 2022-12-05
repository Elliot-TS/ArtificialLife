import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.NoSuchElementException;

/**
* 
* Online/written sources used: 
    *Oracle Java Docs
    * https://stackoverflow.com/questions/45275402/how-can-i-create-a-list-with-n-objects
    * GeeksForGeeks
    * https://stackoverflow.com/questions/25147094/how-can-i-turn-a-list-of-lists-into-a-list-in-java-8
*/
class ALifeSim {
    public static void main(String[] args) {
        try {
            int numIterations = Integer.parseInt(args[0]);
            int numCooperators = Integer.parseInt(args[1]);
            int numDefectors = Integer.parseInt(args[2]);
            int numPartials = Integer.parseInt(args[3]);

            // This was for collecting the data
            //print10Simulations(numIterations, numCooperators, numDefectors, numPartials);

            // Run 1 simulation and print it according to the format in the assignment
            Map<String, Integer> populationSize = new HashMap<>();
            populationSize.put(Population.COOPERATOR, numCooperators);
            populationSize.put(Population.DEFECTOR, numDefectors);
            populationSize.put(Population.PARTIAL_COOPERATOR, numPartials);

            Population pop = new Population(populationSize);

            // Run the simulation
            for (int i = 0; i < numIterations; i++) {
                pop.update();
            }
            populationSize = pop.getPopulationCounts();


            // Display the output
            System.out.println("After " + numIterations + " ticks:");
            System.out.println("Cooperators\t= " + populationSize.get(Population.COOPERATOR));
            System.out.println("Defectors\t= " + populationSize.get(Population.DEFECTOR));
            System.out.println("Partial\t\t= " + populationSize.get(Population.PARTIAL_COOPERATOR));
            System.out.println("\nMean Cooperation Probability = " + pop.calculateCooperationMean());
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method was used to collect the data for the assignment by running the 
     * simulation 10 times rather than 1 and printing it in a format that was easier
     * to convert to CSV and then into an ASCII table
     * @param numIterations Number of iterations to run the simulation for
     * @param numCooperators Number of cooperators to start the simulation with
     * @param numDefectors Number of defectors to start the simulation with
     * @param numPartials Number of partial cooperators to start the simulation with
     */
    private static void print10Simulations(int numIterations, int numCooperators, int numDefectors, int numPartials) {
        List<Integer> cooperators = new ArrayList<Integer>();
        List<Integer> defectors = new ArrayList<Integer>();
        List<Integer> partials = new ArrayList<Integer>();
        List<Double> meanCooperation = new ArrayList<Double>();

        for (int j = 0; j < 10; j++) {
            Map<String, Integer> populationSize = new HashMap<>();
            populationSize.put(Population.COOPERATOR, numCooperators);
            populationSize.put(Population.DEFECTOR, numDefectors);
            populationSize.put(Population.PARTIAL_COOPERATOR, numPartials);

            Population pop = new Population(populationSize);

            // Run the simulation
            for (int i = 0; i < numIterations; i++) {
                pop.update();
            }
            populationSize = pop.getPopulationCounts();
            cooperators.add(populationSize.get(Population.COOPERATOR));
            defectors.add(populationSize.get(Population.DEFECTOR));
            partials.add(populationSize.get(Population.PARTIAL_COOPERATOR));
            meanCooperation.add(pop.calculateCooperationMean());
        }

        // Display the output
        System.out.println("Field, Run 1, Run 2, Run 3, Run 4, Run 5, Run 6, Run 7, Run 8, Run 9, Run 10");
        System.out.println("Cooperators, " + cooperators);
        System.out.println("Defectors, " + defectors);
        System.out.println("Partial, " + partials);
        System.out.println("Mean Coop Prob, " + meanCooperation);
        System.out.println("Cooperators, " + cooperators.stream().map(x -> x == 0 ? "0%" : "100%").collect(Collectors.toList()));
        System.out.println("Defectors, " + defectors.stream().map(x -> x == 0 ? "0%" : "0%").collect(Collectors.toList()));
        System.out.println("Partial, " + partials.stream().map(x -> x == 0 ? "0%" : "50%").collect(Collectors.toList()));
        
        List<String> aofas = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            double perc = 0;
            if (cooperators.get(i) != 0) {
                perc += 1;
            }
            if (partials.get(i) != 0) {
                perc += 0.5;
            }
            perc *= 100/3;
            String percString = String.format("%.0f", perc);
            aofas.add(percString + "%");
        }
        System.out.println("Avg of Avgs, " + aofas);
    }
}
