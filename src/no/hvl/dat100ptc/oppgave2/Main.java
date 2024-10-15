package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

    public static void main(String[] args) {
        
        // Step 1: Create two GPSPoint objects
        GPSPoint point1 = new GPSPoint(1, 60.385390, 5.332200, 50.0); // Time = 1, latitude, longitude, elevation
        GPSPoint point2 = new GPSPoint(2, 60.385490, 5.332300, 60.0); // Time = 2, different lat, long, elev
        
        // Step 2: Create a GPSData object that can hold 2 GPSPoint objects
        GPSData gpsData = new GPSData(2);
        
        // Step 3: Insert the two GPSPoint objects into the GPSData object using insertGPS method
        gpsData.insertGPS(point1);
        gpsData.insertGPS(point2);
        
        // Step 4: Print the information of the GPSPoint objects using the print method
        gpsData.print();  // This will print the data of the GPSPoints
    }
}
