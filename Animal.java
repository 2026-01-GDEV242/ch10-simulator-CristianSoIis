import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author Cristian Solis
 * @version 2026.04.20
 */
public abstract class Animal
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // Age of the animal
    private int age;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Sets the animal's age to a new age using int newAge.
     * @param newAge The animal's new age.
     */
    protected void setAge(int newAge)
    {
        age = newAge;
    }
    
    /**
     * Return the animal's age.
     * @return The animal's age
     */
    protected int getAge()
    {
        return age;
    }
    
    /**
     * Checks to see if the animal's current age is greater or equal to the animal's Breeding age.
     * @return true if animal's age is greater or equal to animal's Breeding age and false otherwise.
     */
    protected boolean canBreed()
    {
        return getAge() >= getBreedingAge();
    }
    
    /**
     * @return The age at which a animal starts to bread.
     */
    abstract protected int getBreedingAge();
    
    /**
     * Sets the animal's age by one by adding 1 to the animal's current age
     * Checks if the animal's age is greater then the animal's max age and if so sets the animal to dead.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > getMaxAge()) {
            setDead();
        }
    }
    
    /**
     * @return The animal's maxAge it can live for.
     */
    abstract protected int getMaxAge();
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && getRandom().nextDouble() <= getBREEDING_PROBABILITY()) {
            births = getRandom().nextInt(getMAX_LITTER_SIZE()) + 1;
        }
        return births;
    }
    
     /**
     * @return The animal's Breeding probaility.
     */
    abstract protected double getBREEDING_PROBABILITY();
    
    /**
     * @return The animal's max number of births it can give.
     */
    abstract protected int getMAX_LITTER_SIZE();
    
    /**
     * @return The animal's random value.
     */
    abstract protected Random getRandom();
}
