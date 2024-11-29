package exercicio1;

import fileio.ReadFile;
import objs.zones.City;
import objs.zones.Country;
import objs.Supercharger;
import objs.zones.Zone;

import java.util.List;
import java.util.TreeSet;

public final class Ex1 {
    /**
     * This method will add new Countries and new Cities to a new List
     * @return a TreeSet of countries, each country with its list of cities, and each city with their number of chargers
     */
    public TreeSet<Country> deliverStructuredData(){
        List<Supercharger> superchargers = ReadFile.readSuperChargers();
        TreeSet<Country> countries = new TreeSet<>();
        TreeSet<City> firstCountrysCities = new TreeSet<>();
        firstCountrysCities.add(new City(superchargers.get(0).getCity().getName(), 1));
        countries.add(new Country(superchargers.get(0).getCountry().getName(), firstCountrysCities));
        for(Supercharger charger : superchargers){
            Country country = newZone(countries, charger.getCountry().getName());
            if(country != null){
                City city = newZone(country.getCities(), charger.getCity().getName());
                if(city != null){
                    city.incrementNumOfChargers();
                } else {
                    country.addCity(new City(charger.getCity().getName(), 1));
                }
            } else {
                TreeSet<City> newCountrysCities = new TreeSet<>();
                newCountrysCities.add(new City(charger.getCity().getName(), 1));
                countries.add(new Country(charger.getCountry().getName(), newCountrysCities));
            }
        }
        return countries;
    }

    /**
     *
     * @param zones list of countries/cities
     * @param item name of the country/city we'll be searched
     * @return the Country/City object if it's not new, null if it is
     * @param <T> Country/City
     */
    private <T extends Zone> T newZone(TreeSet<T> zones, String item){
        for(T zone : zones){
            if(zone.getName().equals(item)) return zone;
        }
        return null;
    }
}
