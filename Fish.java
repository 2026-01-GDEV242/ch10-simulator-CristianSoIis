import java.util.List;
import java.util.Random;

/**
 * A simple model of a fish.
 * Fishs's age, move, breed, and die.
 * 
 * @author Cristian Solis
 * @version 2026.04.20
 */
public class Fish extends Animal
{
    // Characteristics shared by all fish's (class variables).

    // The age at which a fish can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a fish can live.
    private static final int MAX_AGE = 20;
    // The likelihood of a fish breeding.
    private static final double BREEDING_PROBABILITY = 0.08;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 8;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    //private int age;

    /**
     * Create a new fish. A fish may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the fish will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fish(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        //age = 0;
        setAge(0);
        if(randomAge) {
            // age = rand.nextInt(MAX_AGE);
            setAge(rand.nextInt(MAX_AGE));
        }
    }
    
    /**
     * This is what the fish does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newFishs A list to return newly born fishs.
     */
    public void act(List<Animal> newFishs)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newFishs);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the fish's death.
     */
    //private void incrementAge()
    //{
        /*
         * age++;
        if(age > MAX_AGE) 
         */
        //setAge(getAge() + 1);
        //if(getAge() > MAX_AGE) {
            //setDead();
        //}
    //}
    
    /**
     * Check whether or not this fish is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newFishs A list to return newly born fishs.
     */
    private void giveBirth(List<Animal> newFishs)
    {
        // New fishs are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fish young = new Fish(false, field, loc);
            newFishs.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    /*private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    } */

    /**
     * A fish can breed if it has reached the breeding age.
     * @return true if the fish can breed, false otherwise.
     */
    /*private boolean canBreed()
    {
        //return age >= BREEDING_AGE;
        return getAge() >= BREEDING_AGE;

    } */
    
    /**
     * Returns the Fish's breedingAge.
     * @return BREEDING_AGE
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }
    
    /**
     * Returns the Fish's maxAge.
     * @return MAX_AGE
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }
    
    /**
     * Returns the Fish's Breeding probability.
     * @return BREEDING_PROBABILITY
     */
    public double getBREEDING_PROBABILITY()
    {
        return BREEDING_PROBABILITY;
    }
    
    /**
     * Returns the Fish's max amount of births.
     * @return MAX_LITTER_SIZE
     */
    public int getMAX_LITTER_SIZE()
    {
        return MAX_LITTER_SIZE;
    }
    
    /**
     * Returns the Fish's random number for control breeding.
     * @return rand
     */
    public Random getRandom()
    {
        return rand;
    }
}