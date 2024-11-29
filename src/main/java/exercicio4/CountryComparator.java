package exercicio4;

import objs.zones.Country;

import java.util.Comparator;

public class CountryComparator implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        int comparison = Integer.compare(o2.getKws().size(), o1.getKws().size());
        if(comparison == 0) return o1.getName().compareTo(o2.getName());
        else return comparison;
    }
}
