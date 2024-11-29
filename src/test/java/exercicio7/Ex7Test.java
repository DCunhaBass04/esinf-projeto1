package exercicio7;

import fileio.ReadFile;
import objs.zones.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;

class Ex7Test {

    @Test
    void assertEmptyListForYearNonExistent() throws FileNotFoundException {
        SortedMap<Country, Double> quotasA = Ex7.calculateQuotas(1850);
        Assertions.assertTrue(quotasA.isEmpty());
    }

    @Test
    void assertSameYearSameList() throws FileNotFoundException {
        SortedMap<Country, Double> quotasB = Ex7.calculateQuotas(2022);
        SortedMap<Country, Double> quotasA = Ex7.calculateQuotas(2022);
        boolean passed = true;
        if (quotasA.keySet().size() != quotasB.keySet().size()) {
            passed = false;
        } else {
            for (Country countryA : quotasA.keySet()) {
                Double quotaA = quotasA.get(countryA);
                Double quotaB = quotasB.get(countryA);
                if (!Objects.equals(quotaA, quotaB)) {
                    passed = false;
                }
            }
        }
        Assertions.assertTrue(passed);
    }

    @Test
    void assertDifferentQuotasForDifferentYears() throws FileNotFoundException {
        boolean passed = true;
        SortedMap<Country, Double> quotasA = Ex7.calculateQuotas(2022);
        SortedMap<Country, Double> quotasB = Ex7.calculateQuotas(2021);
        if (quotasA.size() == quotasB.size()) {
            for (Country countryA : quotasA.keySet()) {
                for (Country countryB : quotasB.keySet()) {
                    if (!Objects.equals(quotasA.get(countryA), quotasB.get(countryB))) {
                        break;
                    }
                }
            }
        }
        Assertions.assertTrue(passed);
    }

    @Test
    void assertNoNegativeQuotaForAllYears() throws FileNotFoundException {
        boolean passed = true;
        SortedSet<Integer> years = ReadFile.getYears();
        for (Integer year : years) {
            SortedMap<Country, Double> quotas = Ex7.calculateQuotas(year);
            for (Country country : quotas.keySet()) {
                if (quotas.get(country) < 0) {
                    passed = false;
                    break;
                }
            }
        }
        Assertions.assertTrue(passed);
    }

}