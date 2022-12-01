class Population {
    // Contains a map with each type of organism as the key
    // and a list of all the organisms of that type as the value
    private Map<String, List<Organism>> population;

    /**
     * Constructs a population of organisms dictated by the given set of pairs that
     * associate names of organisms to counts of that type of organism.  
     * Organisms not mentioned in the mappiing do not appear in the population.
     *
     * @param counts Map describing the population.  The key is the type of organism and the value is how many organisms of that type exist in the population.
     * @throws IllegalArgumentException if the mapping mentions organism types that don't exist
    **/
    public Population(Map<String, Integer> counts) throws IllegalArgumentException {}

    /**
     * Loops through all the organisms in the population and (1) updates them by 
     * calling their update method, (2) checks to see if they cooperate or defect,
     * (3) or checks to see if they reproduce.
    *//
    public void update () {}

    /**
     * Calculates the mean cooperation probability of all the organisms in the population
     * @return the mean cooperation probability of all the organisms
    **/
    public double calculateCooperationMean() {}

    /**
     * @return the number of each type of organism in the population a map with organism types as the key and the count as the value
    **/
    public Map<String, Integer> getPopulationCounts() {}

}
