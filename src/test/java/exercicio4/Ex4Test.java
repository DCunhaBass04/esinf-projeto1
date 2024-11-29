package exercicio4;

import objs.zones.Country;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Ex4Test {
    Ex4 ex4 = new Ex4();
    List<Country> countries = ex4.deliverCountriesByNumChargers();
    @Test void assertCountriesAreInDescendingNoOfChargersOrder(){
        int value = Integer.MAX_VALUE;
        for(Country country : countries){
            int newValue = country.getKws().size();
            if(newValue > value)
                fail();
            value = newValue;
        }
    }
    @Test void assertSumOfTheTwoSubListsEqualGlobalList(){
        for(Country country : countries)
            if(!(country.getKwsBelowOrEqualX().size() + country.getKwsAboveX().size() == country.getKws().size()))
                fail();
    }
    @Test void assertBelowOrEqualListOnlyHasBelowOrEqualToXValues(){
        for(Country country : countries)
            for(int value : country.getKwsBelowOrEqualX())
                if (!(value <= Ex4.DEFINED_THRESHOLD))
                    fail();
    }
    @Test void assertAboveListOnlyHasAboveToXValues(){
        for(Country country : countries)
            for(int value : country.getKwsAboveX())
                if (!(value > Ex4.DEFINED_THRESHOLD))
                    fail();
    }
    @Test void assertGermanyHasTheCorrectNumberOfChargers(){
        int totalValue = 183, //calculated value using =CONTAR.SE(F:F;"Germany") in carregadores_europa.xlsx
                belowOrEqualValue = 50, //calculated value using =CONTAR.SE.S(F:F;"Germany"; H:H; "<=150") in carregadores_europa.xlsx
                aboveValue = 133; //calculated value using =CONTAR.SE.S(F:F;"Germany"; H:H; ">150")) in carregadores_europa.xlsx
        Country germany = returnCountryByName("Germany"); assert germany != null;
        if(!(germany.getKws().size() == totalValue && germany.getKwsBelowOrEqualX().size() ==
                belowOrEqualValue && germany.getKwsAboveX().size() == aboveValue))
            fail();
    }
    @Test void assertAustriaHasTheCorrectNumberOfChargers(){
        int totalValue = 38, //calculated value using =CONTAR.SE(F:F;"Austria") in carregadores_europa.xlsx
                belowOrEqualValue = 22, //calculated value using =CONTAR.SE.S(F:F;"Austria"; H:H; "<=150") in carregadores_europa.xlsx
                aboveValue = 16; //calculated value using =CONTAR.SE.S(F:F;"Austria"; H:H; ">150")) in carregadores_europa.xlsx
        Country austria = returnCountryByName("Austria"); assert austria != null;
        if(!(austria.getKws().size() == totalValue && austria.getKwsBelowOrEqualX().size() ==
                belowOrEqualValue && austria.getKwsAboveX().size() == aboveValue))
            fail();
    }
    @Test void assertNorwayHasTheCorrectNumberOfChargers(){
        int totalValue = 128, //calculated value using =CONTAR.SE(F:F;"Norway") in carregadores_europa.xlsx
                belowOrEqualValue = 56, //calculated value using =CONTAR.SE.S(F:F;"Norway"; H:H; "<=150") in carregadores_europa.xlsx
                aboveValue = 72; //calculated value using =CONTAR.SE.S(F:F;"Norway"; H:H; ">150")) in carregadores_europa.xlsx
        Country norway  = returnCountryByName("Norway"); assert norway != null;
        if(!(norway.getKws().size() == totalValue && norway.getKwsBelowOrEqualX().size() ==
                belowOrEqualValue && norway.getKwsAboveX().size() == aboveValue))
            fail();
    }
    private Country returnCountryByName(String name){
        for(Country country : countries)
            if(country.getName().equalsIgnoreCase(name))
                return country;
        return null;
    }
}
