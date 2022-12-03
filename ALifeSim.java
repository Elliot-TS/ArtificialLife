import java.util.HashMap;
import java.util.Map;

import java.util.NoSuchElementException;

/**
* 
* Online/written sources used: Oracle Java Docs, https://stackoverflow.com/questions/45275402/how-can-i-create-a-list-with-n-objects, GeeksForGeeks
*/
class ALifeSim {
    public static void main(String[] args) {
        try {
            int numIterations = Integer.parseInt(args[0]);
            int numCooperators = Integer.parseInt(args[1]);
            int numDefectors = Integer.parseInt(args[2]);
            int numPartials = Integer.parseInt(args[3]);

            Map<String, Integer> populationSize = new HashMap<>();
            populationSize.put(Population.COOPERATOR, numCooperators);
            populationSize.put(Population.DEFECTOR, numDefectors);
            populationSize.put(Population.PARTIAL_COOPERATOR, numPartials);

            Population pop = new Population(populationSize);

            // Run the simulation
            for (int i = 0; i < numIterations; i++) {
                pop.update();
            }

            // Display the output
            populationSize = pop.getPopulationCounts();
            System.out.println("After " + numIterations + " ticks:");
            System.out.println("Cooperators\t= " + populationSize.get(Population.COOPERATOR));
            System.out.println("Defectors\t= " + populationSize.get(Population.DEFECTOR));
            System.out.println("Partial\t= " + populationSize.get(Population.PARTIAL_COOPERATOR));
            System.out.println("\n\nMean Cooperation Probability = " + pop.calculateCooperationMean());
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
}
