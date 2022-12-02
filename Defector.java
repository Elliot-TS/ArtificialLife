class Defector extends Organism {
    /**
     * @return The type of organism
    **/
    public String getType() { return "Defector"; } 
    /**
     * Called by update when the organism can reproduce
     * @return an offspring organism
     **/
    public Organism reproduce() { 
        energy = 0;
        return new Defector();
    }
    /**
     * @return the cooperation probability of the organism
    **/
    public double getCooperationProbability() { return 0; }
    /**
     * @return whether or not the organism cooperates
    */
    public boolean cooperates() { return false; }
}
