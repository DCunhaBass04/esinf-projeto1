package exercicio3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import objs.Powertrain;



final class Ex3 {
    final static File csvFile = new File("ev_sales.csv");


    /**
     * Empty constructor built to block the instantiation of the class , since it only contains implementation, and is in fact not an object
     */

    private Ex3() {
    }


    /**
     /**
     * This method resolves exercise 3. It reads information from the "ev_sales.csv" file and identifies countries where there were no increase in sales from one year to another.
     * When such happens, this method also calculates and returns the difference in sales for each powertrain type (either BEV or PHEV) between the two years.
     *
     * @return A sorted two-dimensional String array containing the countries that did not experience an increase in sales from one year to another.
     * The countries are not sorted alphabetically by name, but each entry includes the respective years. The array has four columns:
     * 1. Country Name
     * 2. Year Pair
     * 3. BEV Sales Difference in those two years
     * 4. PHEV Sales Difference in those two years.
     * Each line in the array represents an entry where no increase in sales was observed. In this case, the count indicates 53, indicating that there were 53 instances of no sales increase in the same country in the file.
     *In this case count indicates = 53, which means that there were no increase of sales in the same country on the file 53 times.
     *
     * @throws FileNotFoundException if the "ev_sales.csv" file is not found or cannot be accessed.
     */

    public static String[][] sortCountriesWithNoCoVehicleDifference() throws FileNotFoundException, ClassCastException {
        ArrayList<Powertrain> data = readData();
        //mapping countries -> years -> array with the sales of each type (check type_to_num)
        HashMap<String, HashMap<Integer, Integer[]>> map = new HashMap<>();
        for (Powertrain element : data) {
            if (!map.containsKey(element.getCountryName())) {
                HashMap<Integer, Integer[]> minimap = new HashMap<>();
                Integer[] temp = new Integer[2];                            // sales for bev and sales for phev
                temp[type_to_num(element.getType())] = element.getNumberOfSales();
                temp[((type_to_num(element.getType())) + 1) % 2] = 0;
                minimap.put(element.getYearOfSale(), temp);
                map.put(element.getCountryName(), minimap);
            } else {
                HashMap<Integer, Integer[]> minimap = map.get(element.getCountryName());
                if (!minimap.containsKey(element.getYearOfSale())) {
                    Integer[] temp = new Integer[2];
                    temp[type_to_num(element.getType())] = element.getNumberOfSales();
                    temp[((type_to_num(element.getType())) + 1) % 2] = 0;
                    minimap.put(element.getYearOfSale(), temp);
                } else {
                    minimap.get(element.getYearOfSale())[type_to_num(element.getType())] = element.getNumberOfSales();
                }
            }
        }
        int count = 0;                                                              //number of countries with no sales increase
        String[][] declines = new String[data.size()][4];
        for (String key : map.keySet()) {                       // key = country
            HashMap<Integer, Integer[]> minimap = map.get(key);  // equals minimap from map to this minimap
            for (Integer year : minimap.keySet()) {
                int lastBev;
                int lastPhev;
                int lastyear = year - 1;
                if (lastyear == -1) {
                    lastyear = year;
                    continue;
                }
                if (minimap.containsKey(lastyear)) {
                    lastBev = minimap.get(lastyear)[0];
                    lastPhev = minimap.get(lastyear)[1];
                } else {
                    lastBev = 0;
                    lastPhev = 0;
                }
                if ((lastBev + lastPhev) >= (minimap.get(year)[0] + minimap.get(year)[1])) {
                    declines[count][0] = key;
                    declines[count][1] = lastyear + "/" + year;
                    declines[count][2] = "BEV: " + (minimap.get(year)[0] - lastBev);
                    declines[count][3] = "PHEV: " + (minimap.get(year)[1] - lastPhev);
                    count += 1;
                }
                lastyear = year;
            }
        }
        String[][] final_declines = new String[count][4];
        System.arraycopy(declines, 0, final_declines, 0, count);
        Arrays.deepToString(final_declines)
                .replace("], ", "],\n")
                .replace("[[", "[")
                .replace("]]", "]");

        return final_declines;
    }

    /**
     * Method created to distinguish bev powertrian from phev .
     * Bev has the identifier number 0 and phev 1
     *
     * @param type the type
     * @return 0 if is phev on if is phev
     */

    public static int type_to_num(String type) {
        if (type.equals("BEV")) {
            return 0;
        }

        return 1;
    }


    /**
     * This method "readData" reads data from the csv file and stores it in a list of powertrains,
     * @return an Arraylist of Powertrains , each parameter of powertrain is a column of the file.
     * Each line is saved as a powertrain instance
     * @throws FileNotFoundException in case the file is not on the correct place, or we are not reading the file properly with the Scanner
     */

    public static ArrayList<Powertrain> readData() throws FileNotFoundException {
        ArrayList<Powertrain> powertrains = new ArrayList<>();
        Scanner csvScanner = new Scanner(csvFile);
        csvScanner.nextLine(); // Ignore header line
        while (csvScanner.hasNext() && !csvScanner.nextLine().equals(" ")) { //using iterator method hasnext

            String currentLine = csvScanner.nextLine();

            String[] currentLinePartitioned = currentLine.split(",");
            Powertrain savedPowerTrain = new Powertrain(currentLinePartitioned[0], currentLinePartitioned[1],
                    Integer.parseInt(currentLinePartitioned[2]), Integer.parseInt(currentLinePartitioned[3]));
            powertrains.add(savedPowerTrain);
        }
        return powertrains;
    }

}








