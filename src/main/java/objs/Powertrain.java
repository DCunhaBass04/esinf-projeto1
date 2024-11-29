package objs;

import java.util.List;
import java.util.Objects;

public class Powertrain implements Comparable<Powertrain> {

    private final String countryName;
    private final String type; // 2nd column of the csv
    private final int yearOfSale; // 3rd column of the csv
    private final int numberOfSales; // 4th column of the csv

    public Powertrain(String countryName, String type, int yearOfSale, int numberOfSales) {
        this.countryName = countryName;
        this.type = type;
        this.yearOfSale = yearOfSale;
        this.numberOfSales = numberOfSales;
    }


    public String getCountryName() {
        return countryName;
    }

    public String getType() {
        return type;
    }

    public int getYearOfSale() {
        return yearOfSale;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    @Override
    public boolean equals(Object otherPowertrain) {
        if (this == otherPowertrain) return true;
        if (otherPowertrain == null || getClass() != otherPowertrain.getClass()) return false;
        Powertrain that = (Powertrain) otherPowertrain;
        return yearOfSale == that.yearOfSale && numberOfSales == that.numberOfSales && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, yearOfSale, numberOfSales);
    }

    // Default criteria for comparing: number of sales
    @Override
    public int compareTo(Powertrain otherPowertrain) {
        return this.getNumberOfSales() - otherPowertrain.getNumberOfSales();
    }

    @Override
    public String toString() {
        String format = "Country name: " + countryName + "\n" + "Type: " + type + "\n" + "Year: " + yearOfSale + "\n" + "Sales: " + numberOfSales +"\n";
        return format;
    }
}
