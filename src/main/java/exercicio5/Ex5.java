package exercicio5;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Ex5 {

    public static final double EARTH_RADIUS_KM = 6371;

    public static List<String[]> getCountriesGPS(String filePath) {
        List<String[]> filteredData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            boolean firstRow = true;

            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                Cell countryCell = row.getCell(5);
                Cell gpsCell = row.getCell(8);

                if (countryCell != null && gpsCell != null) {
                    String country = countryCell.getStringCellValue();
                    String gps = gpsCell.getStringCellValue();
                    filteredData.add(new String[]{country, gps});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filteredData;
    }

    public static List<String[]> getPairs(List<String[]> data) {
        boolean duplicate;
        List<String[]> duplicateTest = new ArrayList<>();
        List<String[]> result = new ArrayList<>();

        for (int i = 0; i < data.size() - 1; i++) {
            String[] entry1 = data.get(i);
            String gps1 = entry1[1];
            String[] parts1 = entry1[1].split(", ");
            String country1 = entry1[0];
            String lat1 = parts1[0];
            String lon1 = parts1[1];

            for (int j = i + 1; j < data.size(); j++) {
                duplicate = true;
                String[] entry2 = data.get(j);
                String gps2 = entry1[1];
                String[] parts2 = entry2[1].split(", ");
                String country2 = entry2[0];
                String lat2 = parts2[0];
                String lon2 = parts2[1];

                for (int k = 0; k < duplicateTest.size() - 1; k++) {
                    String[] duplicateTestEntry = duplicateTest.get(k);
                    if (gps1.equals(duplicateTestEntry[2]) && Objects.equals(gps2, duplicateTestEntry[1]) && country1.equals(country2)) {
                        duplicate = false;
                        break;
                    }
                }
                if (duplicate && country1.equals(country2)) {
                    duplicateTest.add(new String[]{country1, gps1, gps2});
                    double distance = haversineDistance(Double.parseDouble(lat1), Double.parseDouble(lon1), Double.parseDouble(lat2), Double.parseDouble(lon2));
                    result.add(new String[]{country1, lat1, lon1, lat2, lon2, String.valueOf(distance)});
                }
            }
        }

        return result;
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * c;
        String formattedDistance = String.format("%.2f", distance);
        formattedDistance = formattedDistance.replace(",", ".");
        return Double.parseDouble(formattedDistance);
    }

    public static List<String[]> getSorted(List<String[]> data) {
        List<String[]> sortedTestData = new ArrayList<>(data);

        sortedTestData.sort((o1, o2) -> {
            double value1 = Double.parseDouble(o1[o1.length - 1]);
            double value2 = Double.parseDouble(o2[o2.length - 1]);
            int valueComparison = Double.compare(value2, value1);

            if (valueComparison != 0) {
                return valueComparison;
            } else {
                return o1[0].compareTo(o2[0]);
            }
        });

        return sortedTestData;
    }

}
