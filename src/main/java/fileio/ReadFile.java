package fileio;

import objs.CoordinatePoint;
import objs.Powertrain;
import objs.Supercharger;
import objs.zones.City;
import objs.zones.Country;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * This class was created in order to store the methods that are responsible for reading the
 * necessary .xlsx and .csv files
 */
public final class ReadFile {
    private static final XSSFWorkbook excelFile;
    private static final File csvFile = new File("ev_sales.csv");

    static {
        try {
            excelFile = new XSSFWorkbook(new File("carregadores_europa.xlsx"));
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public ReadFile() {

    }


    /**
     * This method reads the Excel file and creates an ArrayList of Supercharges
     *
     * @return the ArrayList of Supercharges
     */
    public static List<Supercharger> readSuperChargers() {
        XSSFSheet firstSheet = excelFile.getSheetAt(0);
        List<Supercharger> listOfSuperchargers = new ArrayList<>();
        if (firstSheet.getRow(0) != null) {
            firstSheet.removeRow(firstSheet.getRow(0)); // Removes the first row which just corresponds to the headers.
        }
        for (Row row : firstSheet) {
            String zip;
            try {
                zip = row.getCell(4).getStringCellValue();
            } catch (IllegalStateException e) {
                zip = row.getCell(4).getNumericCellValue() + "";
            }
            String[] xyValues = row.getCell(8).getStringCellValue().split(", "); // Returns the x and y values in an array as Strings respectively.
            listOfSuperchargers.add(new Supercharger(row.getCell(0).getStringCellValue() /*name*/,
                    row.getCell(1).getStringCellValue() /*street address*/,
                    new City(row.getCell(2).getStringCellValue()) /*city*/,
                    row.getCell(3).getStringCellValue() /*state (can be empty)*/,
                    zip,
                    new Country(row.getCell(5).getStringCellValue().trim()) /*country*/,
                    (int) row.getCell(6).getNumericCellValue() /*stalls*/,
                    (int) row.getCell(7).getNumericCellValue() /*kilowatts*/,
                    new CoordinatePoint(Double.parseDouble(xyValues[0]), Double.parseDouble(xyValues[1])) /*coordinate points (gps)*/,
                    (int) row.getCell(9).getNumericCellValue() /*elevm*/,
                    row.getCell(10).getStringCellValue() /*status*/));
        }
        return listOfSuperchargers;
    }

  /*  public static List <Country> getStates{
        XSSFSheet firstSheet = excelFile.getSheetAt(0);
        List<Supercharger> listOfSuperchargers = new ArrayList<>();
        if (firstSheet.getRow(0) != null) {
            firstSheet.removeRow(firstSheet.getRow(0)); //skip the first line
        }
        for (Row row : firstSheet) {
            String zip;
            try {
                zip = row.getCell(4).getStringCellValue();
            } catch (IllegalStateException e) {
                zip = row.getCell(4).getNumericCellValue() + "";
            }
            String[] xyValues = row.getCell(8).getStringCellValue().split(", "); // Returns


        }


    }

   */

    /**
     * Gets a sorted set (alphabetically) of countries. It also fills each country's powertrain list with the data
     * in the csv file. That is from within the map you can call the list of powertrains of each country; they will be filled.
     * @return A sorted set of each country present in the file
     * @throws FileNotFoundException
     */
    public static SortedSet<Country> getData() throws FileNotFoundException {
        SortedSet<Country> countries = getCountries();
        fillCountriesData(countries);
        return countries;
    }

    public static SortedSet<Integer> getYears() throws FileNotFoundException {
        SortedSet<Integer> years = new TreeSet<>();
        SortedSet<Country> countries = getData();
        for (Country country : countries) {
            List<Powertrain> powertrains = country.getPowertrains();
            for (Powertrain powertrain : powertrains) {
                int year = powertrain.getYearOfSale();
                years.add(year);
            }
        }
        return years;
    }

    /**
     * Fills each country's list of powertrains. The data is read from the csv file.
     * @param countries The set of countries that is present in the csv file.
     * @throws FileNotFoundException
     */

    private static void fillCountriesData(SortedSet<Country> countries) throws FileNotFoundException {
        Scanner csvScanner = new Scanner(csvFile);
        csvScanner.nextLine(); // Ignore header
        csvScanner.nextLine(); // Skip white line
        String currentLine = csvScanner.nextLine();
        String[] currentLineSplit = currentLine.split(",");
        for (Country country : countries) {
            List<Powertrain> countryPowertrains = new ArrayList<>();
            while (csvScanner.hasNext()) {
                if (currentLineSplit[0].equals(country.getName())) {
                    String powerTrainCountry = currentLineSplit[0];
                    String powerTrainName = currentLineSplit[1];
                    int powerTrainYear = Integer.parseInt(currentLineSplit[2]);
                    int powerTrainSales = Integer.parseInt(currentLineSplit[3]);
                    Powertrain currentPowertrain = new Powertrain(powerTrainCountry, powerTrainName, powerTrainYear, powerTrainSales);
                    countryPowertrains.add(currentPowertrain);
                    currentLine = csvScanner.nextLine();
                    currentLineSplit = currentLine.split(",");
                } else if (currentLineSplit[0].isEmpty()) {
                    currentLine = csvScanner.nextLine();
                    currentLineSplit = currentLine.split(",");
                } else {
                    break;
                }
            }
            country.addPowertrains(countryPowertrains);
        }
    }

    /**
     * Scans the csv file for countries. For each unique country found, it creates a Country object and returns them all in a sorted set.
     * @return Sorted with all countries from the csv file.
     * @throws FileNotFoundException
     */

    private static SortedSet<Country> getCountries() throws FileNotFoundException {
        SortedSet<Country> countries = new TreeSet<>();     //countries with all values associated
        Scanner csvScanner = new Scanner(csvFile);
        csvScanner.nextLine(); // Ignore header line
        while (csvScanner.hasNext() && !csvScanner.nextLine().equals(" ")) {
            String currentLine = csvScanner.nextLine();
            String[] currentLinePartitioned = currentLine.split(",");
            Country countryToAdd = new Country(currentLinePartitioned[0]);
            countries.add(countryToAdd);
        }
        return countries;
    }
    public static List<String[]> getCountriesYearsVehicles(File csvFile) throws FileNotFoundException {
        List<String[]> result = new ArrayList<>();
        Scanner scanner = new Scanner(csvFile);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\t");
            String country = parts[0];
            String year = parts[2];
            String numVehicles = parts[3];
            result.add(new String[]{country, year, numVehicles});
        }
        scanner.close();
        return result;
    }
}

