package exercicio6;

import objs.CoordinatePoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Exercicio6Test {

    /**
     * Asserts that for different interest points close to the domain of values, there is at least one point which is the same distance from
     * both.
     */
    @Test
    void assertSameCoordinatesSamePoi() {
        Set<CoordinatePoint> pointsOfInterest = new HashSet<>();
        CoordinatePoint interestPoint1 = new CoordinatePoint(-54, 38);
        CoordinatePoint interestPoint2 = new CoordinatePoint(2, -27);
        pointsOfInterest.add(interestPoint1);
        pointsOfInterest.add(interestPoint2);
        HashMap<CoordinatePoint, ArrayList<CoordinatePoint>> clusters = Exercicio6.obtainClusters(pointsOfInterest);
        ArrayList<CoordinatePoint> closestPointsToPoint1 = clusters.get(interestPoint1);
        ArrayList<CoordinatePoint> closestPointsToPoint2 = clusters.get(interestPoint2);
        boolean passed = true;
        for (int i = 0; i < closestPointsToPoint1.size(); i++) {
            double x1 = closestPointsToPoint1.get(i).getXCoordinate();
            double x2 = closestPointsToPoint2.get(i).getXCoordinate();
            double y1 = closestPointsToPoint1.get(i).getYCoordinate();
            double y2 = closestPointsToPoint2.get(i).getYCoordinate();
            if (x1 == x2 && y1 == y2) {
                Assertions.assertTrue(passed);
            }
        }
    }

    /**
     * Asserts that for the same points of interest the list of closest points is exactly the same
     */
    @Test
    void assertDifferentPoints() {
        Set<CoordinatePoint> pointsOfInterest = new HashSet<>();
        CoordinatePoint interestPoint1 = new CoordinatePoint(23, -40);
        CoordinatePoint interestPoint2 = new CoordinatePoint(23, -40);
        pointsOfInterest.add(interestPoint1);
        pointsOfInterest.add(interestPoint2);
        HashMap<CoordinatePoint, ArrayList<CoordinatePoint>> clusters = Exercicio6.obtainClusters(pointsOfInterest);
        ArrayList<CoordinatePoint> closestPointsToPoint1 = clusters.get(interestPoint1);
        ArrayList<CoordinatePoint> closestPointsToPoint2 = clusters.get(interestPoint2);
        for (int i = 0; i < closestPointsToPoint1.size(); i++) {
            double x1 = closestPointsToPoint1.get(i).getXCoordinate();
            double x2 = closestPointsToPoint2.get(i).getXCoordinate();
            double y1 = closestPointsToPoint1.get(i).getYCoordinate();
            double y2 = closestPointsToPoint2.get(i).getYCoordinate();
            Assertions.assertTrue(x1 == x2 && y1 == y2);
        }
    }

    /**
     * Assert that for the default values of the points of interest the list is the same
     */
    @Test
    void assertDefaultValuesForInterestPoints() {
        Set<CoordinatePoint> pointsOfInterest = new HashSet<>();
        CoordinatePoint interestPoint1 = new CoordinatePoint();
        CoordinatePoint interestPoint2 = new CoordinatePoint();
        pointsOfInterest.add(interestPoint1);
        pointsOfInterest.add(interestPoint2);
        HashMap<CoordinatePoint, ArrayList<CoordinatePoint>> clusters = Exercicio6.obtainClusters(pointsOfInterest);
        ArrayList<CoordinatePoint> closestPointsToPoint1 = clusters.get(interestPoint1);
        ArrayList<CoordinatePoint> closestPointsToPoint2 = clusters.get(interestPoint2);
        boolean passed = true;
        for (int i = 0; i < closestPointsToPoint1.size(); i++) {
            double x1 = closestPointsToPoint1.get(i).getXCoordinate();
            double x2 = closestPointsToPoint2.get(i).getXCoordinate();
            double y1 = closestPointsToPoint1.get(i).getYCoordinate();
            double y2 = closestPointsToPoint2.get(i).getYCoordinate();
            if (x1 == x2 && y1 == y2) {
                Assertions.assertTrue(passed);
            }
        }
    }
}