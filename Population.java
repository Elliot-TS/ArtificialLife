import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Population {
    // Contains a map with each type of organism as the key
    // and a list of all the organisms of that type as the value
    private Map<String, List<Organism>> population;

    private Random randGen = new Random();

    public static final String COOPERATOR = "Cooperator";
    public static final String DEFECTOR = "Defector";
    public static final String PARTIAL_COOPERATOR = "PartialCooperator";
    public static final String[] ORGANISM_TYPES = { COOPERATOR, DEFECTOR, PARTIAL_COOPERATOR };
    
    private static final int REPRODUCTION_ENERGY = 10; // the energy needed to reproduce

    /**
     * Constructs a population of organisms dictated by the given set of pairs that
     * associate names of organisms to counts of that type of organism.  
     * Organisms not mentioned in the mappiing do not appear in the population.
     *
     * @param counts Map describing the population.  The key is the type of organism and the value is how many organisms of that type exist in the population.
     * @throws IllegalArgumentException if the mapping mentions organism types that don't exist
    **/
    public Population(Map<String, Integer> counts) throws IllegalArgumentException {
        // Get the keys from the counts map
        Set<String> organismTypes = counts.keySet();
        Iterator<String> it = organismTypes.iterator();
        while (it.hasNext()) {
            String type = it.next();
            int numOrgs = counts.get(type);
            List<Organism> orgs; 

            // Collect a list of numOrgs new Organism objects
            switch (type) {
                case COOPERATOR:
                    orgs = Stream.generate(Cooperator::new).limit(numOrgs).collect(Collectors.toList());
                    break;
                case DEFECTOR:
                    orgs = Stream.generate(Defector::new).limit(numOrgs).collect(Collectors.toList());
                    break;
                case PARTIAL_COOPERATOR:
                    orgs = Stream.generate(PartialCooperator::new).limit(numOrgs).collect(Collectors.toList());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid organism type: " + type);
            }

            population.put(type, orgs);
        }
    }

    /**
     * Loops through all the organisms in the population and (1) updates them by 
     * calling their update method, (2) checks to see if they cooperate or defect,
     * (3) or checks to see if they reproduce.
    */
    public void update () {
        // Loop through each type of organism
        Set<String> organismTypes = population.keySet();
        Iterator<String> types = organismTypes.iterator();
        while (types.hasNext()) {
            String type = types.next();
            List<Organism> orgs = population.get(type);

            // List through the organisms of each type
            Iterator<Organism> orgsIt = orgs.iterator(); 
            while (orgsIt.hasNext()) {
                Organism org = orgsIt.next();

                // Update the organisms
                org.update();

                // Check to see if they cooperate or defect
                if (org.cooperates()) {
                    // Pick a random organism
                    Organism beneficiary = getRandomOrganism();
                    org.decrementEnergy();
                    beneficiary.incrementEnergy();
                }

                // Check to see if the organism can reproduce
                if (org.getEnergy() > REPRODUCTION_ENERGY) {
                    // Get the new organism
                    Organism newOrg = org.reproduce();
                    // Replace the last organism of a random type
                    List<Organism> deathRow = population.get(getRandomType());
                    deathRow.remove(randGen.nextInt(deathRow.size()));
                    // Add the new organism
                    population.get(newOrg.getType()).add(newOrg);
                }
            }
        }
    }

    /**
    * Select a random organism type
    * @return A random organism type
    */
    public String getRandomType() {
        return ORGANISM_TYPES[randGen.nextInt(ORGANISM_TYPES.length)];
    }

    /**
    * Select a random organism from the population
    * @return A random organism from the population
    */
    public Organism getRandomOrganism () {
        List<Organism> orgs = population.get(getRandomType());
        return orgs.get(randGen.nextInt(orgs.size()));
    }

    /**
     * Calculates the mean cooperation probability of all the organisms in the population
     * Assumes all organisms of each type have the same cooperation probability (which is
     * true in this case)
     * @return the mean cooperation probability of all the organisms
    **/
    public double calculateCooperationMean() {
        double sum = 0;
        int numOrgs = 0;

        Set<String> types = population.keySet();
        Iterator<String> typeIt = types.iterator();

        while (typeIt.hasNext()) {
            List<Organism> orgs = population.get(typeIt.next());

            // If there are no organisms of this type, continue
            if (orgs.size() == 0) { continue; }

            // Otherwise, update the mean cooperation according to the cooperation of the first element
            sum += orgs.get(0).getCooperationProbability() * orgs.size();
            numOrgs += orgs.size();
        }
        return sum / numOrgs;
    }

    /**
     * @return the number of each type of organism in the population a map with organism types as the key and the count as the value
    **/
    public Map<String, Integer> getPopulationCounts() {
        Map<String, Integer> popCounts = new HashMap<>();
        Set<String> types = population.keySet();
        Iterator<String> typeIt = types.iterator();

        while (typeIt.hasNext()) {
            String type = typeIt.next();
            popCounts.put(type, population.get(type).size());
        }
        return popCounts;
    }

}
