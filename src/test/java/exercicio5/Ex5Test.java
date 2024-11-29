package exercicio5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class Ex5Test {

    @Test
    public void testGetCountriesGPS() {
        String filePath = "carregadores_europa.xlsx";

        List<String[]> countriesGPS = Ex5.getCountriesGPS(filePath);

        assertEquals(1109, countriesGPS.size());
    }

    @Test
    public void testGetPairs() {
        List<String[]> testData = List.of(
                new String[]{"Germany", "48.145652, 11.741631"},
                new String[]{"Netherlands", "52.31061, 4.936187"},
                new String[]{"Germany", "43.666884, 7.124728"},
                new String[]{"Netherlands", "52.30858, 5.141127"},
                new String[]{"Germany", "56.165403, 15.585989"}
        );

        List<String[]> expectedPairs = List.of(
                new String[]{"Germany", "48.145652", "11.741631", "43.666884", "7.124728", "612.63"},
                new String[]{"Germany", "48.145652", "11.741631", "56.165403", "15.585989", "929.15"},
                new String[]{"Netherlands", "52.31061", "4.936187", "52.30858", "5.141127", "13.93"},
                new String[]{"Germany", "43.666884", "7.124728", "56.165403", "15.585989", "1513.42"}
        );

        List<String[]> pairs = Ex5.getPairs(testData);

        assertEquals(expectedPairs.size(), pairs.size());
        for (int i = 0; i < expectedPairs.size(); i++) {
            assertArrayEquals(expectedPairs.get(i), pairs.get(i));
        }
    }

    @Test
    public void testHaversineDistance() {
        double lat1 = 52.31061;
        double lon1 = 4.936187;
        double lat2 = 52.30858;
        double lon2 = 5.141127;

        double distance = Ex5.haversineDistance(lat1, lon1, lat2, lon2);

        double expectedDistance = 13.93;
        assertEquals(expectedDistance, distance, 0.01);
    }

    @Test
    public void testGetSorted() {
        List<String[]> testData = new ArrayList<>();
        testData.add(new String[]{"Germany", "52.394056", "13.542307", "53.668642", "9.995189", "276.27"});
        testData.add(new String[]{"Germany", "50.3352", "6.949047", "53.668642", "9.995189", "425.2"});
        testData.add(new String[]{"France", "50.3352", "6.949047", "48.145652", "11.741631", "425.2"});
        testData.add(new String[]{"Germany", "53.668642", "9.995189", "48.145652", "11.741631", "626.17"});
        testData.add(new String[]{"Netherlands", "52.31061", "4.936187", "52.30858", "5.141127", "13.93"});

        List<String[]> expectedSortedData = List.of(
                new String[]{"Germany", "53.668642", "9.995189", "48.145652", "11.741631", "626.17"},
                new String[]{"France", "50.3352", "6.949047", "48.145652", "11.741631", "425.2"},
                new String[]{"Germany", "50.3352", "6.949047", "53.668642", "9.995189", "425.2"},
                new String[]{"Germany", "52.394056", "13.542307", "53.668642", "9.995189", "276.27"},
                new String[]{"Netherlands", "52.31061", "4.936187", "52.30858", "5.141127", "13.93"}
        );

        List<String[]> sortedData = Ex5.getSorted(testData);

        assertEquals(expectedSortedData.size(), sortedData.size());
        for (int i = 0; i < expectedSortedData.size(); i++) {
            assertArrayEquals(expectedSortedData.get(i), sortedData.get(i));
        }
    }
}
