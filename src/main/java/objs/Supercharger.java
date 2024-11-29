package objs;

import fileio.ReadFile;
import objs.zones.City;
import objs.zones.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Supercharger implements Comparable<Supercharger> {

    private Country country;

    private City city;
    private String name, streetAddress, state, zip, status,countryName;
    private int stalls, kilowatts, elevm;
    private CoordinatePoint coordinatePoint;

    public Supercharger(String name, String streetAddress, City city, String state,
                        String zip, Country country, int stalls, int kilowatts,
                        CoordinatePoint coordinatePoint, int elevm, String status) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.stalls = stalls;
        this.kilowatts = kilowatts;
        this.coordinatePoint = coordinatePoint;
        this.elevm = elevm;
        this.status = status;
    }

    public static List<Supercharger> getSuperchargersByState(String state) {
        List<Supercharger> superchargers = ReadFile.readSuperChargers();
        List<Supercharger> superchargerByState = new ArrayList<>();
        for (Supercharger supercharger : superchargers) {
            if (supercharger.getState().equalsIgnoreCase(state)) {
                superchargerByState.add(supercharger);
            }
        }
        return superchargerByState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStalls() {
        return stalls;
    }

    public void setStalls(int stalls) {
        this.stalls = stalls;
    }

    public int getKilowatts() {
        return kilowatts;
    }

    public void setKilowatts(int kilowatts) {
        this.kilowatts = kilowatts;
    }



    public int getElevm() {
        return elevm;
    }

    public void setElevm(int elevm) {
        this.elevm = elevm;
    }

    public CoordinatePoint getCoordinatePoint() {
        return coordinatePoint;
    }

    public void setCoordinatePoint(CoordinatePoint coordinatePoint) {
        this.coordinatePoint = coordinatePoint;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supercharger that = (Supercharger) o;
        return stalls == that.stalls && kilowatts == that.kilowatts && elevm == that.elevm &&
                name.equals(that.name) && streetAddress.equals(that.streetAddress) && city.equals(that.city) &&
                state.equals(that.state) && zip.equals(that.zip) && country.equals(that.country) &&
                status.equals(that.status) && coordinatePoint.equals(that.coordinatePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, streetAddress, city, state, zip, country, stalls, kilowatts, coordinatePoint, elevm, status);
    }

    @Override
    public String toString() {
        return "Supercharger{" +
                "name='" + name + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", status='" + status + '\'' +
                ", stalls=" + stalls +
                ", kilowatts=" + kilowatts +
                ", elevm=" + elevm +
                ", coordinatePoint=" + coordinatePoint +
                '}';
    }

    @Override
    public int compareTo(Supercharger otherSupercharger) {
        return country.compareTo(otherSupercharger.country);
    }
}
