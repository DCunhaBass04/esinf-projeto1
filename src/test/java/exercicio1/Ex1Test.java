package exercicio1;


import objs.zones.City;
import objs.zones.Country;
import objs.zones.Zone;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;


public class Ex1Test {
    private static final Ex1 ex1 = new Ex1();
    private static final TreeSet<Country> countries = ex1.deliverStructuredData();
    @Test void assertCountriesAreInAlphabeticalOrder(){
        if(!listIsInAlphabeticalOrder(countries))
            fail();
    }
    @Test void assertCitiesAreInAlphabeticalOrder(){ //This also shows that each country has its own cities
        for(Country country : countries)
            if(!listIsInAlphabeticalOrder(country.getCities()))
                fail();
    }
    @Test void assertHamburgHasCorrectNumberOfChargers(){
        String countryName = "Germany", cityName = "Hamburg";
        int value = 5; //calculated value using =CONTAR.SE.S(F:F;"Germany";C:C;"Hamburg") in carregadores_europa.xlsx
        Country country = returnZoneByName(countryName, countries);
        assert country != null;
        City city = returnZoneByName(cityName, country.getCities());
        assert city != null;
        assertEquals(city.getNumOfSuperchargers(), value);
    }
    @Test void assertLondonHasCorrectNumberOfChargers(){
        String countryName = "United Kingdom", cityName = "London";
        int value = 10; //calculated value using =CONTAR.SE.S(F:F;"United Kingdom";C:C;"London") in carregadores_europa.xlsx
        Country country = returnZoneByName(countryName, countries);
        assert country != null;
        City city = returnZoneByName(cityName, country.getCities());
        assert city != null;
        assertEquals(city.getNumOfSuperchargers(), value);
    }
    @Test void assertSaintesHasCorrectNumberOfChargers(){
        String countryName = "France", cityName = "Saintes";
        int value = 2; //calculated value using =CONTAR.SE.S(F:F;"France";C:C;"Saintes") in carregadores_europa.xlsx
        Country country = returnZoneByName(countryName, countries);
        assert country != null;
        City city = returnZoneByName(cityName, country.getCities());
        assert city != null;
        assertEquals(city.getNumOfSuperchargers(), value);
    }
    @Test void assertVierumäkiHasCorrectNumberOfChargers(){
        String countryName = "Finland", cityName = "Vierumäki";
        int value = 1; //calculated value using =CONTAR.SE.S(F:F;"Finland";C:C;"Vierumäki") in carregadores_europa.xlsx
        Country country = returnZoneByName(countryName, countries);
        assert country != null;
        City city = returnZoneByName(cityName, country.getCities());
        assert city != null;
        assertEquals(city.getNumOfSuperchargers(), value);
    }
    private <E extends Zone> boolean listIsInAlphabeticalOrder(TreeSet<E> zones){
        String name = "0000000";
        for(E zone : zones){
            String newName = zone.getName();
            if(newName.compareToIgnoreCase(name) < 0){
                return false;
            }
            name = newName;
        }
        return true;
    }
    private <E extends Zone> E returnZoneByName(String name, TreeSet<E> zones){
        for(E zone : zones) if(zone.getName().equalsIgnoreCase(name)) return zone;
        return null;
    }
}
