package no.hvl.dat100ptc.oppgave2;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

    
    private GPSPoint[] gpspoints;
    protected int antall = 0;

   
    public GPSData(int n) {
        gpspoints = new GPSPoint[n]; 
        antall = 0;                  
    }

    
    public GPSPoint[] getGPSPoints() {
        return this.gpspoints;
    }

    
    protected boolean insertGPS(GPSPoint gpspoint) {
        
        if (antall < gpspoints.length) {
            gpspoints[antall] = gpspoint; 
            antall++; 
            return true; 
        } else {
            return false; 
        }
    }

   
    public boolean insert(String time, String latitude, String longitude, String elevation) {

        // Bruker DateTimeFormatter for å tolke ISO 8601 format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalTime localTime = LocalTime.parse(time, formatter);

        // Konverterer tidspunktet til sekunder siden midnatt
        int timeInSeconds = localTime.toSecondOfDay();

        double latDouble = Double.parseDouble(latitude);
        double longDouble = Double.parseDouble(longitude);
        double elevDouble = Double.parseDouble(elevation);

        GPSPoint gpspoint = new GPSPoint(timeInSeconds, latDouble, longDouble, elevDouble);
        return insertGPS(gpspoint);
    }

   
    public void print() {
        System.out.println("====== GPS Data - START ======");
        for (int i = 0; i < antall; i++) {
            System.out.print(gpspoints[i].toString());
        }
        System.out.println("====== GPS Data - SLUTT ======");
    }
}


