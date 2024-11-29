package exercicio3;

import objs.Powertrain;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Ex3Test {


    //In file there is only PHEV or BEV so there is no need to test other words ,there is no empty words on the column

    @Test
    void bevStringShouldBeZeroAndPhevOne(){
        assertEquals(0,Ex3.type_to_num("BEV"));
        assertEquals(1,Ex3.type_to_num("PHEV"));

    }

    @Test
        void readDataIsReadCorrectly() {
            File csvFile = new File("ev_sales.csv");

            // Check if the file exists and is accessible before testing
            if (!csvFile.exists() || !csvFile.canRead()) {
                throw new AssertionError("Test cannot be executed because the CSV file is missing or inaccessible.");
            }

            assertDoesNotThrow(() -> Ex3.readData());
        }


    @Test
    void readDataIsStoringCorrectly()throws FileNotFoundException {
        // Call your readData method to read data from the test file
        ArrayList<Powertrain> powertrains = Ex3.readData();


        // wrongfirst value country,powertrain,year,number_of_vehicles
        //first value :Australia,BEV,2011,49
        //mid value : Japan,PHEV,2014,16000
        //last value World,BEV,2022,7300000


        //size = 834 = (1669-3 /2) +1


        //size Check



        assertEquals(834,powertrains.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            Powertrain unExistingValue = powertrains.get(834);
        });


        //ensuring it skips the headed line



        Powertrain wrongFirstValue = powertrains.get(0);
        assertNotEquals("country", wrongFirstValue.getCountryName());
        assertNotEquals("powertrain", wrongFirstValue.getYearOfSale());
        assertNotEquals("year", wrongFirstValue.getType());
        assertNotEquals("number_of_vehicles", wrongFirstValue.getNumberOfSales());


        Powertrain firstValue = powertrains.get(0);
        assertEquals("Australia", firstValue.getCountryName());
        assertEquals("BEV", firstValue.getType());
        assertEquals(2011, firstValue.getYearOfSale());
        assertEquals(49, firstValue.getNumberOfSales());

        Powertrain midValue = powertrains.get(419);
        assertEquals("Japan", midValue.getCountryName());
        assertEquals("PHEV", midValue.getType());
        assertEquals(2014, midValue.getYearOfSale());
        assertEquals(16000, midValue.getNumberOfSales());





      Powertrain lastValue = powertrains.get(833);
        assertEquals("World", lastValue.getCountryName());
        assertEquals("BEV", lastValue.getType());
        assertEquals(2022, lastValue.getYearOfSale());
        assertEquals(7300000, lastValue.getNumberOfSales());

        //ensuring the size is 834




    }


        @Test
        void testSortCountriesWithNoCoVehicleDifferenceFunctionality() {
            try {
                String[][] result = Ex3.sortCountriesWithNoCoVehicleDifference();

                // Check if the result is not null
                assertNotNull(result);

                // Check if the result is a two-dimensional array
                assertTrue(result.length > 0);


                //List of Values that are correct


                Set<String> expectedValues = new HashSet<>
                        (Arrays.asList(
                        "Portugal",
                        "2011/2012",
                        "BEV: -530",
                        "PHEV: -4",
                                "Israel",
                                "2015/2016",
                                "BEV: 2",
                                "PHEV: -2",
                                //only with one powertrain in each year Lines 227,229
                                "Chile",
                                "2011/2012",
                                "BEV: -1",
                                "PHEV: 0",
                                // where the sales where the same
                                "Chile",
                                "2012/2013",
                                "BEV: 0",
                                "PHEV: 0"



                ));

                //Test with values where there was an increase of sales
                Set<String> wrongValues = new HashSet<>
                        (Arrays.asList(

                                "Australia",
                                "2009/2010",
                                "BEV: 121",
                                "PHEV: 80",
                                "World",
                                "2021/2022",
                                "BEV: 2700000",
                                "PHEV: 100000",
                                "Poland",
                                "2010/2011",
                                "BEV: 28",
                                "PHEV: -1"

                        ));
                // values that does not exist
                Set<String> unExistedValues = new HashSet<>
                        (Arrays.asList(
                                "Australia",
                                "2010/2011",
                                "BEV: -121",
                                "PHEV: 0"



                        ));


                // Loop through the result array and remove found values from the set
                for (String[] row : result) {
                    for (String value : row) {
                        if (value != null && expectedValues.contains(value)) {
                            expectedValues.remove(value);
                            wrongValues.remove(value);
                            unExistedValues.remove(value);
                        }
                    }
                }
                assertTrue(expectedValues.isEmpty(), "The expected correct values were in result ");
                assertFalse(wrongValues.isEmpty(), "Wrong values were not in result ");
                assertFalse(unExistedValues.isEmpty());


            } catch (FileNotFoundException e) {
                fail("FileNotFoundException should not be thrown in this test.");
            } catch (Exception e) {
                fail("An unexpected exception occurred: " + e.getMessage());
            }
        }
    }








