package no.hvl.dat100ptc.oppgave2;
import static java.lang.Integer.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		String timePart = timestr.substring(11,19);
		
		
		int hr = parseInt(timePart.substring(0, 2));
		int min = parseInt(timePart.substring(3, 5));
		int sec = parseInt(timePart.substring(6, 8));
		
		int secs = (hr*3600) + (min*60) + sec;
		
		return secs;
		
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	
}
