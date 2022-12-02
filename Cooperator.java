class PartialCooperator extends Organism {
    /**
     * @return The type of organism
    **/
    public String getType() { return "PartialCooperator"; } 
    /**
     * Called by update when the organism can reproduce
     * @return an offspring organism
     **/
    public Organism reproduce() { 
        energy = 0;
        return new PartialCooperator();
    }
    /**
     * @return the cooperation probability of the organism
    **/
    public double getCooperationProbability() { return 0.5; }
    /**
     * @return whether or not the organism cooperates
    */
    public boolean cooperates() { return Math.random() > getCooperationProbability(); }
}

