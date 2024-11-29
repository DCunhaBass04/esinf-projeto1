package exercicio8;

import fileio.ReadFile;
import objs.StatesAndCharges;
import objs.Supercharger;
import objs.zones.City;
import objs.zones.State;

import java.util.*;

public final class Ex8 {
    private static List<Supercharger> superchargers = ReadFile.readSuperChargers();

    private Ex8() {

    }

    /**
     * This method will start the whole process required by Ex08
     * @param n number of top states
     * @param names names of country or state names
     * @return object that stores state names, total charge and city names
     */
    public static StatesAndCharges startProcess(int n, List<String> names){
        if(isItACountry(names)){
            names = getAllStatesFromCountryList(names);
        }
        return new StatesAndCharges(topStates(n, names));
    }

    /**
     * Checks if the inserted names are countries or states
     * @param names list of names
     * @return true if it's a country name, false if it's a state name
     */
    private static boolean isItACountry(List<String> names){
        for(Supercharger charger : superchargers)
            for(String name : names)
                if (charger.getCountry().getName().equals(name))
                    return true;
        return false;
    }

    /**
     * Goes through a list of countries and gets all of their states
     * @param names list of country names
     * @return list of state names
     */
    private static List<String> getAllStatesFromCountryList(List<String> names){
        List<String> statesNames = new ArrayList<>();
        for(Supercharger charger : superchargers) {
            for(String name : names)
                if (charger.getCountry().getName().equals(name) && !statesNames.contains(charger.getState()))
                    statesNames.add(charger.getState());
        }
        return statesNames;
    }

    /**
     * This method gets all the states' charges and only returns the top n ones
     * @param n the number of states on the top states list
     * @param stateNames all state names
     * @return top n states, organized by descending value of charge
     */
    private static TreeSet<State> topStates(int n, List<String> stateNames){
        TreeSet<State> topNStates = new TreeSet<>();
        List<State> allStates = new ArrayList<>();
        for(String name : stateNames)
            allStates.add(deliverAState(name));
        Collections.sort(allStates);
        for(int i = 0; i < n; i++)
            topNStates.add(allStates.get(i));
        return topNStates;
    }

    /**
     * This method goes through the charger list and returns the total information regarding one state
     * @param name one state's name
     * @return the new state object
     */
    private static State deliverAState(String name){
        TreeSet<City> cities = new TreeSet<>();
        double totalValue = 0;
        for(Supercharger charger : superchargers){
            if(charger.getState().equals(name) && charger.getStatus().equals("Open")){
                City city = newCity(cities, charger.getCity().getName());
                if(city != null)
                    city.incrementNumOfChargers();
                else
                    cities.add(new City(charger.getCity().getName(), 1));
                totalValue += charger.getKilowatts() * charger.getStalls();
            }
        }
        return new State(name, totalValue, cities);
    }

    /**
     * Checks if a city is already in the city list
     * @param cities list of cities
     * @param item new city's name
     * @return the city object if it's on the list, null if not
     */
    private static City newCity(TreeSet<City> cities, String item){
        for(City city : cities){
            if(city.getName().equals(item)) return city;
        }
        return null;
    }

}



