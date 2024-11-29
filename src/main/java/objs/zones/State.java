package objs.zones;

import java.util.TreeSet;

public class State extends Zone implements Comparable<State> {
    private Double totalCharge;
    private TreeSet<City> cities;
    public State(String name, Double totalCharge, TreeSet<City> cities){
        super(name);
        this.totalCharge = totalCharge;
        this.cities = cities;
    }
    public Double getTotalCharge() {return totalCharge;}
    public TreeSet<City> getCities(){return cities;}
    @Override
    public int compareTo(State o) {
        return o.totalCharge.compareTo(this.totalCharge);
    }
}
