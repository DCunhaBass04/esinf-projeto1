package objs;

/**
 * This class corresponds to the GPS coordinates in the excel file
 */

public final class CoordinatePoint implements Comparable<CoordinatePoint> {

    private double xCoordinate;
    private double yCoordinate;

    public CoordinatePoint() {
        xCoordinate = 0;
        yCoordinate = 0;
    }

    public CoordinatePoint(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }


    public double getYCoordinate() {
        return yCoordinate;
    }

    public double getDistanceBetweenPoint(CoordinatePoint otherPoint) {
        return Math.sqrt(Math.pow(xCoordinate - otherPoint.xCoordinate, 2) + Math.pow(yCoordinate - otherPoint.yCoordinate, 2));
    }

    public static double getDistanceBetweenPoints(CoordinatePoint a, CoordinatePoint b) {
        return Math.sqrt(Math.pow(a.xCoordinate - b.xCoordinate, 2) + Math.pow(a.yCoordinate - b.yCoordinate, 2));
    }

    @Override
    public String toString() {
        return "(" + xCoordinate + " | " + yCoordinate + ")";
    }

    @Override
    public int compareTo(CoordinatePoint otherPoint) {
        double distanceThis = Math.sqrt(Math.pow(xCoordinate, 2) + Math.pow(yCoordinate, 2));
        double distanceOther = Math.sqrt(Math.pow(otherPoint.xCoordinate, 2) + Math.pow(otherPoint.yCoordinate, 2));
        return (int) (distanceThis - distanceOther);
    }

}
