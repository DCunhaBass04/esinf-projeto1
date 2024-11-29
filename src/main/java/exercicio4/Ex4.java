package exercicio4;

import fileio.ReadFile;
import objs.Supercharger;
import objs.zones.Country;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Ex4 {
    public static final int DEFINED_THRESHOLD = 150;

    /**
     * This method will return a list of countries sorted by descending number of chargers
     * @return list of countries
     */
    public List<Country> deliverCountriesByNumChargers() {
        List<Supercharger> superchargers = ReadFile.readSuperChargers();
        List<Country> countries = new ArrayList<>();
        ArrayList<Integer> kwsForFirstCountry = new ArrayList<>();
        kwsForFirstCountry.add(superchargers.get(0).getKilowatts());
        countries.add(new Country(superchargers.get(0).getCountry().getName(), kwsForFirstCountry, DEFINED_THRESHOLD));
        for (Supercharger charger : superchargers) {
            Country country = newCountry(countries, charger.getCountry().getName());
            if (country != null) {
                country.addNewCharger(charger.getKilowatts(), DEFINED_THRESHOLD);
            } else {
                ArrayList<Integer> kwsForFollowingCountries = new ArrayList<>();
                kwsForFollowingCountries.add(charger.getKilowatts());
                countries.add(new Country(charger.getCountry().getName(), kwsForFollowingCountries, DEFINED_THRESHOLD));
            }
        }
        Comparator<Country> comparator = new CountryComparator();
        countries.sort(comparator);
        return countries;
    }

    /**
     * This method is used to see if a Country is already on the given list
     * @param countries list of countries
     * @param item name of new country
     * @return the country if it's not new, null if it is
     */
    private Country newCountry(List<Country> countries, String item){
        for(Country country : countries){
            if(country.getName().equals(item)) return country;
        }
        return null;
    }
}
