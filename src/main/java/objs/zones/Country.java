package objs.zones;

import objs.Powertrain;
import objs.Supercharger;

import java.util.*;

/**
 * This is a class made to represent each country and their attributes, the cities (for Ex1.java) and the list of the KWs (for Ex4.java)
 */
public class Country extends Zone implements Comparable<Country> {
    private TreeSet<City> cities;
    private TreeSet<State> states;
    private final List<Powertrain> powertrains = new ArrayList<>();
    private final TreeSet<City> DEFAULTCITIES = new TreeSet<>();
    private ArrayList<Integer> kwsBelowOrEqualX = new ArrayList<>(), kwsAboveX = new ArrayList<>(), kws;
    private final ArrayList<Integer> DEFAULTKWS = new ArrayList<>();


    /**
     * This is a constructor with just the name
     *
     * @param name the country's name
     */
    public Country(String name) {
        super(name);
        cities = DEFAULTCITIES;
        kws = DEFAULTKWS;
        kwsAboveX = DEFAULTKWS;
        kwsBelowOrEqualX = DEFAULTKWS;
    }

    public List<Powertrain> getPowertrains() {
        return powertrains;
    }

    /**
     * This is the constructor with the country's name and cities
     * This constructor will help with Ex1
     *
     * @param name   the country's name
     * @param cities the list of the country's cities
     */
    public Country(String name, TreeSet<City> cities) {
        super(name);
        this.cities = cities;
        kws = DEFAULTKWS;
        kwsAboveX = DEFAULTKWS;
        kwsBelowOrEqualX = DEFAULTKWS;
    }

    /**
     * This is the constructor with the country's name, states and cities
     * This constructor will help with Ex8
     *
     * @param name   the country's name
     * @param states the list of the country's states
     * @param cities the list of the country's cities
     */
    public Country(String name, TreeSet<State> states, TreeSet<City> cities) {
        super(name);
        this.states = states;
        this.cities = cities;
        kws = DEFAULTKWS;
        kwsAboveX = DEFAULTKWS;
        kwsBelowOrEqualX = DEFAULTKWS;
    }

    /**
     * This is the constructor with the country's name and the initialization of the 3 separate KWs lists
     * This constructor will help with Ex4
     * It will also send you to the method addItemsToCertainList()
     *
     * @param name      the country's names
     * @param kws       the initialized version of the overall KW list
     * @param THRESHOLD the threshold value that will separate the belowXKW and aboveXKW lists
     */
    public Country(String name, ArrayList<Integer> kws, final int THRESHOLD) {
        super(name);
        this.kws = kws;
        cities = DEFAULTCITIES;
        addItemsToCertainList(THRESHOLD);
    }

    /**
     * This method will distribute the first and only (for now) charger to the correct list according to the THRESHOLD
     *
     * @param THRESHOLD the threshold value that will decide if the charger goes to kwsAboveX or kwsBelowOrEqualX
     */
    private void addItemsToCertainList(final int THRESHOLD) {
        int value = kws.get(0);
        if (value > THRESHOLD) kwsAboveX.add(value);
        else kwsBelowOrEqualX.add(value);
    }

    /**
     * This method will be used repeatedly to add the new charger to the charger list and then add it to the correct sublist
     *
     * @param value     the KW value of the new charger
     * @param THRESHOLD the threshold value that will decide if the charger goes to kwsAboveX or kwsBelowOrEqualX
     */
    public void addNewCharger(int value, final int THRESHOLD) {
        kws.add(value);
        if (value > THRESHOLD) kwsAboveX.add(value);
        else kwsBelowOrEqualX.add(value);
    }

    /**
     * This method determines if a Country object is equal to another object
     *
     * @param o the other object
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country other = (Country) o;
        return this.getName().equals(other.getName()) && cities.equals(other.cities) && kws.equals(other.kws)
                && kwsAboveX.equals(other.kwsAboveX) && kwsBelowOrEqualX.equals(other.kwsBelowOrEqualX);
    }

    /**
     * Returns the total number of stalls that this country has.
     *
     * @param superchargers The list of superchargers of this country, which is read from the Excel file.
     * @return Total number of stalls.
     */

    public int getStalls(List<Supercharger> superchargers) {
        int stalls = 0;
        for (Supercharger supercharger : superchargers) {
            if (supercharger.getCountry().getName().equalsIgnoreCase(this.getName())) {
                stalls += supercharger.getStalls();
            }
        }
        return stalls;
    }

    /**
     * Gets the total number of sales for a given year of this country's powertrains.
     *
     * @param year The year to calculate total of sales.
     * @return The total number of sales.
     */
    public int getYearSales(int year) {
        int totalSales = 0;
        for (Powertrain powertrain : powertrains) {
            if (powertrain.getYearOfSale() == year) {
                totalSales += powertrain.getNumberOfSales();
            }
        }
        return totalSales;
    }

    public List<Powertrain> getPowertrainsByYear(int year) {
        List<Powertrain> powertrainsYear = new ArrayList<>();
        for (Powertrain powertrain : powertrains) {
            if (powertrain.getYearOfSale() == year) {
                powertrainsYear.add(powertrain);
            }
        }
        return powertrainsYear;
    }

    public TreeSet<Integer> getCountryYears() {
        TreeSet<Integer> years = new TreeSet<>();
        for (Powertrain powertrain : powertrains) {
            years.add(powertrain.getYearOfSale());
        }
        return years;
    }


    public void addPowertrains(List<Powertrain> powertrainsToAdd) {
        powertrains.addAll(powertrainsToAdd);
    }


    @Override
    public int hashCode() {
        return Objects.hash(cities);
    }

    public String getName() {
        return super.getName();
    }

    public ArrayList<Integer> getKws() {
        return kws;
    }

    public ArrayList<Integer> getKwsAboveX() {
        return kwsAboveX;
    }

    public ArrayList<Integer> getKwsBelowOrEqualX() {
        return kwsBelowOrEqualX;
    }

    public TreeSet<City> getCities() {
        return cities;
    }

    public TreeSet<State> getStates() {return states;}

    public void addCity(City city) {
        cities.add(city);
    }

    public String toString() {
        if (!cities.equals(DEFAULTCITIES)) {
            StringBuilder toReturn = new StringBuilder(String.format("%s:%n", super.getName()));
            for (City city : cities) {
                toReturn.append(city.toString());
            }
            return toReturn.toString();
        } else {
            return String.format("%15s %10d %10d %10d", super.getName(), kwsAboveX.size(), kwsBelowOrEqualX.size(), kws.size());
        }
    }

    @Override
    public int compareTo(Country otherCountry) {
        if (getName().equalsIgnoreCase("usa") && otherCountry.getName().equalsIgnoreCase("united kingdom")) {
            return 1;
        }
        return super.getName().compareTo(otherCountry.getName());
    }
}
