package no.hvl.dat100ptc.oppgave2;
import static java.lang.Integer.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		String timePart = timestr.split("T")[1].split("\\.")[0];
		
		String timeParts[] = timePart.split(":");
		
		
		int hr = parseInt(timeParts[0]);
		int min = parseInt(timeParts[1]);
		int sec = parseInt(timeParts[2]);
		
		int secs = (hr/3600) + (min/60) + sec;
		
		return secs;
		
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
}
