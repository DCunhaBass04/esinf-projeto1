package exercicio2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ex2Test {

    @Test
    void getCountriesYearsVehicles() {
        String filePath = "ev_sales.csv";

        List<String[]> countriesYearsVehicles = Ex2.getCountriesYearsVehicles(filePath);

        assertEquals(834, countriesYearsVehicles.size());
    }

    @Test
    void getFilterByYears() {
        int startYear = 2011;
        int finalYear = 2012;

        List<String[]> testData = List.of(
                new String[]{"USA", "2010", "1200"},
                new String[]{"USA", "2011", "9800"},
                new String[]{"USA", "2012", "39000"},
                new String[]{"United Kingdom", "2010", "21"},
                new String[]{"United Kingdom", "2011", "7"},
                new String[]{"United Kingdom", "2012", "1600"}
        );

        List<String[]> expectedFilteredYears = List.of(
                new String[]{"USA", "2011", "9800"},
                new String[]{"USA", "2012", "39000"},
                new String[]{"United Kingdom", "2011", "7"},
                new String[]{"United Kingdom", "2012", "1600"}
        );
        List<String[]> filteredYears = Ex2.getFilterByYears(testData, startYear, finalYear);

        assertEquals(expectedFilteredYears.size(), filteredYears.size());
        for (int i = 0; i < expectedFilteredYears.size(); i++) {
            assertArrayEquals(expectedFilteredYears.get(i), filteredYears.get(i));
        }
    }

    @Test
    void getSumVehiclesByCountryAndYear() {
        List<String[]> testData = List.of(
                new String[]{"USA", "2011", "9800"},
                new String[]{"USA", "2011", "8000"},
                new String[]{"USA", "2012", "39000"},
                new String[]{"USA", "2012", "15000"},
                new String[]{"United Kingdom", "2011", "7"},
                new String[]{"United Kingdom", "2011", "1200"},
                new String[]{"United Kingdom", "2012", "1600"},
                new String[]{"United Kingdom", "2012", "970"}
        );

        List<String[]> expectedSumVehiclesByCountryAndYear = List.of(
                new String[]{"USA", "2011", "17800"},
                new String[]{"USA", "2012", "54000"},
                new String[]{"United Kingdom", "2011", "1207"},
                new String[]{"United Kingdom", "2012", "2570"}
        );
        List<String[]> sumVehiclesByCountryAndYear = Ex2.getSumVehiclesByCountryAndYear(testData);

        assertEquals(expectedSumVehiclesByCountryAndYear.size(), sumVehiclesByCountryAndYear.size());
        for (int i = 0; i < expectedSumVehiclesByCountryAndYear.size(); i++) {
            assertArrayEquals(expectedSumVehiclesByCountryAndYear.get(i), sumVehiclesByCountryAndYear.get(i));
        }
    }

    @Test
    void getGrowthRate() {
        List<String[]> testData = List.of(
                new String[]{"USA", "2011", "17800"},
                new String[]{"USA", "2012", "54000"},
                new String[]{"United Kingdom", "2011", "1207"},
                new String[]{"United Kingdom", "2012", "2570"}
        );

        List<String[]> expectedGrowthRate = List.of(
                new String[]{"USA", "2.03"},
                new String[]{"United Kingdom", "1.13"}
        );
        List<String[]> growthRate = Ex2.getGrowthRate(testData);

        assertEquals(expectedGrowthRate.size(), growthRate.size());
        for (int i = 0; i < expectedGrowthRate.size(); i++) {
            assertArrayEquals(expectedGrowthRate.get(i), growthRate.get(i));
        }
    }
}