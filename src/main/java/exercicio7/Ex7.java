package exercicio7;

import fileio.ReadFile;
import objs.Powertrain;
import objs.Supercharger;
import objs.zones.Country;

import java.io.FileNotFoundException;
import java.util.*;

public final class Ex7 {

    private Ex7() {

    }

    /**
     * Calculates the quotas for each country in the file for a given year if the country doesn't have a quota in the year specified
     * it is ignored. The quota is calculated dividing the total number of stalls of a given country by the total number of powertrain
     * sales of the same country and then multiply the result by a thousand.
     *
     * @param year The year to calculate quotas
     * @return A sorted map which maps the country with the quota.
     * @throws FileNotFoundException
     */

    public static SortedMap<Country, Double> calculateQuotas(int year) throws FileNotFoundException {
        SortedMap<Country, Double> quotas = new TreeMap<>();
        List<Supercharger> superchargers = ReadFile.readSuperChargers();
        SortedSet<Country> countries = ReadFile.getData();
        for (Country country : countries) {
            double quota = 0;
            List<Powertrain> powertrains = country.getPowertrains();
            for (Powertrain powertrain : powertrains) {
                if (powertrain.getYearOfSale() == year) {
                    quota += (double) country.getStalls(superchargers) / powertrain.getNumberOfSales();
                }
            }
            if (quota != 0) {
                quotas.put(country, quota * 1000);
            }
        }
        return quotas;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SortedMap<Country, Double> quotas = Ex7.calculateQuotas(2022);
        quotas.keySet().forEach(country -> System.out.printf("%-18s %.2f %%\n", country.getName(), quotas.get(country)));
    }


}
