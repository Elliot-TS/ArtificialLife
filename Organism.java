public abstract class Organism {
    protected int energy;

    /**
     * Construct an organism with an initial energy of 0
    **/
    public Organism () { energy = 0; }

    /**
     * Updates the organism. Increments the energy. 
    **/
    public void update() { incrementEnergy(); }

    /**
     * @return the current energy of the organism
    **/
    public int getEnergy() { return energy; }

    /**
     * Increments the organism's energy by one
    **/
    public void incrementEnergy() { energy++; }

    /**
     * Decrements the organism's energy by one
    **/
    public void decrementEnergy() { energy--; }

    /**
     * @return The type of organism
    **/
    abstract String getType();

    /**
     * Called by update when the organism can reproduce
     * @return an offspring organism
     **/
    abstract Organism reproduce();

    /**
     * @return the cooperation probability of the organism
    **/
    abstract double getCooperationProbability();

    /**
     * @return whether or not the organism cooperates
    *//
    abstract boolean cooperates();
}
