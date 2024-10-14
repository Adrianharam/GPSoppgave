package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

	    double latitude1 = toRadians(gpspoint1.getLatitude());
	    double longitude1 = toRadians(gpspoint1.getLongitude());
	    double latitude2 = toRadians(gpspoint2.getLatitude());
	    double longitude2 = toRadians(gpspoint2.getLongitude());

	    double dlat = latitude2 - latitude1;
	    double dlon = longitude2 - longitude1;

	    double a = compute_a(latitude1, latitude2, dlat, dlon);

	    double c = compute_c(a);

	    double R = 6371000;
	    double d = R * c; 

	    return d;
	}

	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		return sin(deltaphi / 2) * sin(deltaphi / 2) +
		           cos(phi1) * cos(phi2) *
		           sin(deltadelta / 2) * sin(deltadelta / 2);

	}

	private static double compute_c(double a) {

		
		return 2 * atan2(sqrt(a), sqrt(1-a));

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

	    int secs = gpspoint2.getTime() - gpspoint1.getTime();
	    if (secs == 0) {
	        return 0;
	    }

	    double distance = distance(gpspoint1, gpspoint2);
	    double speed = distance / secs;

	    return speed;
	}


	public static String formatTime(int secs) {
	    int hours = secs / 3600;
	    int minutes = (secs % 3600) / 60;
	    int seconds = secs % 60;

	    String timestr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
	    return String.format("%10s", timestr);
	}


	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
	    double rounded = Math.round(d * 100.0) / 100.0;

	    String str = Double.toString(rounded);

	    while (str.length() < TEXTWIDTH) {
	        str = " " + str;
	    }

	    return str;
	}
}


