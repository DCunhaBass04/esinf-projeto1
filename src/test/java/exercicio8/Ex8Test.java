package exercicio8;

import objs.StatesAndCharges;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class Ex8Test {
     @Test void assertIfValuesAreCorrectForInput3Portugal(){
         StatesAndCharges statesAndCharges = Ex8.startProcess(3, new ArrayList<String>(Collections.singleton("Portugal")));
         assertEquals(16000, statesAndCharges.getTotalCharge());
         assertTrue(statesAndCharges.getStateNames().containsAll(new ArrayList<>(Arrays.asList("Centro", "Norte", "Algarve"))));
         assertTrue(statesAndCharges.getCityNames().containsAll(new ArrayList<>(Arrays.asList("Alcantarilha", "Almancil", "Castelo Branco", "Fátima", "Guarda",
                 "Matosinhos", "Mealhada", "Ribeira de Pena"))));
     }
     @Test void assertIfValuesAreCorrectForInput1AlentejoCentroNorte(){
         StatesAndCharges statesAndCharges = Ex8.startProcess(1, new ArrayList<>(Arrays.asList("Alentejo", "Centro", "Norte")));
         assertEquals(8300, statesAndCharges.getTotalCharge());
         assertTrue(statesAndCharges.getStateNames().contains("Centro"));
         assertTrue(statesAndCharges.getCityNames().containsAll(new ArrayList<>(Arrays.asList("Castelo Branco", "Fátima", "Guarda", "Mealhada"))));
     }
}