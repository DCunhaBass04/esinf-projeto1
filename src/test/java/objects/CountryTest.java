package objects;

import fileio.ReadFile;
import objs.Supercharger;
import objs.zones.Country;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.*;

public class CountryTest {
    @Test void assertNewChargerWithLowerValueIsAddedToCorrectList(){
        final int VALUE = 150;
        int chargerValue = 140;
        ArrayList<Integer> kwsForFirstCountry = new ArrayList<>();
        kwsForFirstCountry.add(chargerValue);
        Country country = new Country("Portugal", kwsForFirstCountry, VALUE);
        assertTrue(country.getKwsBelowOrEqualX().contains(chargerValue));
    }
    @Test void assertNewChargerWithEqualValueIsAddedToCorrectList(){
        final int VALUE = 150;
        int chargerValue = 150;
        ArrayList<Integer> kwsForFirstCountry = new ArrayList<>();
        kwsForFirstCountry.add(chargerValue);
        Country country = new Country("Portugal", kwsForFirstCountry, VALUE);
        assertTrue(country.getKwsBelowOrEqualX().contains(chargerValue));
    }
    @Test void assertNewChargerWithAboveValueIsAddedToCorrectList(){
        final int VALUE = 150;
        int chargerValue = 160;
        ArrayList<Integer> kwsForFirstCountry = new ArrayList<>();
        kwsForFirstCountry.add(chargerValue);
        Country country = new Country("Portugal", kwsForFirstCountry, VALUE);
        assertTrue(country.getKwsAboveX().contains(chargerValue));
    }
    @Test void assertGetStallsGivesUsTheCorrectNumberOfStallsInSweden(){
        List<Supercharger> chargers = ReadFile.readSuperChargers();
        int supposedNoOfStalls = 953; //calculated value using =SOMA.SE(F:F;"Sweden";G:G) in carregadores_europa.xlsx
        Country newCountry = new Country("Sweden");
        assertEquals(newCountry.getStalls(chargers), supposedNoOfStalls);
    }
    @Test void assertGetStallsGivesUsTheCorrectNumberOfStallsInFrance(){
        List<Supercharger> chargers = ReadFile.readSuperChargers();
        int supposedNoOfStalls = 1982; //calculated value using =SOMA.SE(F:F;"France";G:G) in carregadores_europa.xlsx
        Country newCountry = new Country("France");
        assertEquals(newCountry.getStalls(chargers), supposedNoOfStalls);
    }
    @Test void assertGetYearSalesGivesUsTheCorrectValueWithAustraliaIn2014() throws FileNotFoundException {
        int chosenYear = 2014;
        int supposedNoOfSales = 1320;  //calculated value using =SOMA.SE.S(D:D;A:A;"Australia";C:C;"2014") in ev_sales.csv (after splitting it into different cells)
        SortedSet<Country> countries = ReadFile.getData();
        Country country = findCountryInList(countries, "Australia");
        if(country != null) {
            assertEquals(country.getYearSales(chosenYear), supposedNoOfSales);
        } else fail();
    }
    @Test void assertGetYearSalesGivesUsTheCorrectValueWithChileIn2017() throws FileNotFoundException {
        int chosenYear = 2017;
        int supposedNoOfSales = 135;  //calculated value using =SOMA.SE.S(D:D;A:A;"Chile";C:C;"2017") in ev_sales.csv (after splitting it into different cells)
        SortedSet<Country> countries = ReadFile.getData();
        Country country = findCountryInList(countries, "Chile");
        if(country != null) {
            assertEquals(country.getYearSales(chosenYear), supposedNoOfSales);
        } else fail();
    }
    private Country findCountryInList(SortedSet<Country> countries, String countryName){
        for(Country country : countries){
            if(country.getName().equals(countryName)) return country;
        }
        return null;
    }
}
