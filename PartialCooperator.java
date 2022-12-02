class Cooperator extends Organism {
    /**
     * @return The type of organism
    **/
    public String getType() { return "Cooperator"; } 
    /**
     * Called by update when the organism can reproduce
     * @return an offspring organism
     **/
    public Organism reproduce() { 
        energy = 0;
        return new Cooperator();
    }
    /**
     * @return the cooperation probability of the organism
    **/
    public double getCooperationProbability() { return 1.0; }
    /**
     * @return whether or not the organism cooperates
    */
    public boolean cooperates() { return true; }
}
