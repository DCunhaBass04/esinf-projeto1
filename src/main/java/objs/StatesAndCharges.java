package objs;

import objs.zones.City;
import objs.zones.State;

import java.util.TreeSet;

public class StatesAndCharges {
    private TreeSet<String> stateNames, cityNames;
    private double totalCharge;

    /**
     * Constructor for this class
     * @param states the list of states
     */
    public StatesAndCharges(TreeSet<State> states){
        stateNames = sumAllStateNames(states);
        totalCharge = sumAllCharges(states);
        cityNames = sumAllCityNames(states);
    }
    private TreeSet<String> sumAllStateNames(TreeSet<State> states){
        TreeSet<String> stateNames = new TreeSet<>();
        for(State state : states)
            stateNames.add(state.getName());
        return stateNames;
    }
    private double sumAllCharges(TreeSet<State> states){
        double charge = 0;
        for(State state : states)
            charge += state.getTotalCharge();
        return charge;
    }
    private TreeSet<String> sumAllCityNames(TreeSet<State> states){
        TreeSet<String> cityNames = new TreeSet<>();
        for(State state : states)
            for(City city : state.getCities())
                cityNames.add(city.getName());
        return cityNames;
    }

    public TreeSet<String> getStateNames() {return stateNames;}
    public double getTotalCharge() {return totalCharge;}
    public TreeSet<String> getCityNames() {return cityNames;}
}
