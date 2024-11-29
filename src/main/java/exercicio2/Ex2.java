package exercicio2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Ex2 {
    static final int startYear = 2011;
    static final int finalYear = 2012;

    public static List<String[]> getCountriesYearsVehicles(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String country = parts[0];
                    String year = parts[2];
                    String numVehicles = parts[3];
                    String[] extractedData = {country, year, numVehicles};
                    data.add(extractedData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static List<String[]> getFilterByYears(List<String[]> data, int startYear, int finalYear) {
        List<String[]> filteredData = new ArrayList<>();
        for (String[] entry : data) {
            int entryYear = Integer.parseInt(entry[1]);
            if (entryYear == startYear) {
                filteredData.add(entry);
            }
            if (entryYear == finalYear) {
                filteredData.add(entry);
            }
        }
        return filteredData;
    }

    public static List<String[]> getSumVehiclesByCountryAndYear(List<String[]> data) {
        List<String[]> summedData = new ArrayList<>();
        List<String[]> dataV2 = new ArrayList<>(data);

        for (int i = 0; i < dataV2.size(); i++) {
            String[] currentRow = dataV2.get(i);
            String country = currentRow[0];
            String year = currentRow[1];
            int sum = Integer.parseInt(currentRow[2]);

            for (int j = i + 1; j < dataV2.size(); j++) {
                String[] nextRow = dataV2.get(j);
                if (country.equals(nextRow[0]) && year.equals(nextRow[1])) {
                    sum += Integer.parseInt(nextRow[2]);
                    dataV2.remove(j);
                    j--;
                }
            }

            String[] summedRow = {country, year, String.valueOf(sum)};
            summedData.add(summedRow);
        }

        return Collections.unmodifiableList(summedData);
    }

    public static List<String[]> getGrowthRate(List<String[]> data) {
        List<String[]> transformedData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            String[] currentRow = data.get(i);
            String country = currentRow[0];
            String year = currentRow[1];
            int numVehicles2011 = Integer.parseInt(currentRow[2]);

            if (Integer.parseInt(year) == startYear) {
                for (int j = i + 1; j < data.size(); j++) {
                    String[] nextRow = data.get(j);
                    if (nextRow[0].equals(country) && Integer.parseInt(nextRow[1]) == finalYear) {
                        int numVehicles2012 = Integer.parseInt(nextRow[2]);
                        int difference = numVehicles2012 - numVehicles2011;
                        double division = (double) difference / numVehicles2011;

                        String[] transformedRow = {country, String.format("%.2f", division)};
                        transformedData.add(transformedRow);
                    }
                }
            }
        }

        return transformedData;
    }
}
