package exercicio6;

import fileio.ReadFile;
import objs.CoordinatePoint;
import objs.Supercharger;

import java.util.*;

public final class Exercicio6 {

    // Prevent instantiation
    private Exercicio6() {

    }

    /**
     * This is the method that resolves exercise 6. It Takes a set of points of interest and calculates the clusters of all points
     * in the Excel file that are closest to each one. It saves this data in a HashMap which maps each coordinate point to an
     * array list containing the points in the Excel file ordered by the closest to the point of interest itself.
     * @param pointsOfInterest The set of points of interest that the user want's to calculate its clusters.
     * @return A hash map which maps a point of interest to a list containing the points closest to it.
     */

    public static HashMap<CoordinatePoint, ArrayList<CoordinatePoint>> obtainClusters(Set<CoordinatePoint> pointsOfInterest) {
        HashMap<CoordinatePoint, ArrayList<CoordinatePoint>> clusters = new HashMap<>();
        ArrayList<CoordinatePoint> coordinatePointsInFile = new ArrayList<>();
        List<Supercharger> superchargerList = ReadFile.readSuperChargers();
        superchargerList.forEach(supercharger -> coordinatePointsInFile.add(supercharger.getCoordinatePoint()));
        for (CoordinatePoint poi: pointsOfInterest) {
            // Comparing based on the distance between the poi and each point in the Excel file.
            coordinatePointsInFile.sort(Comparator.comparingDouble(poi::getDistanceBetweenPoint));
            ArrayList<CoordinatePoint> coordinatePointsToPoi = new ArrayList<>(coordinatePointsInFile);
            clusters.put(poi, coordinatePointsToPoi);
        }
        return clusters;
    }


}
