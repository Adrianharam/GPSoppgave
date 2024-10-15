package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {
		int x1, y1, x2, y2; 
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			x1 = MARGIN + (int) ((gpspoints[i].getLongitude()- minlon) * xstep);
			y1 = ybase - (int)((gpspoints[i].getLatitude() - minlat) * ystep);
			
			x2 = MARGIN + (int)((gpspoints[i + 1].getLongitude() - minlon) * xstep);
	        y2 = ybase - (int)((gpspoints[i + 1].getLatitude() - minlat) * ystep);
	        
	        setColor(0, 0, 255);
	        drawLine(x1, y1, x2, y2);
			
			
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int y = 50;

		setColor(0,0,0);
		setFont("Courier",12);
		
		double totalDistance = gpscomputer.totalDistance();
	    double totalElevation = gpscomputer.totalElevation();
	    double maxSpeed = gpscomputer.maxSpeed();
	    double averageSpeed = gpscomputer.averageSpeed();
	    int totalTime = gpscomputer.totalTime();

	    // Vis statistikken i vinduet
	    drawString("Total Distance: " + String.format("%.2f", totalDistance) + " km", TEXTDISTANCE, y);
	    drawString("Total Elevation: " + String.format("%.2f", totalElevation) + " m", TEXTDISTANCE, y + 20);
	    drawString("Max Speed: " + String.format("%.2f", maxSpeed) + " km/h", TEXTDISTANCE, y + 40);
	    drawString("Average Speed: " + String.format("%.2f", averageSpeed) + " km/h", TEXTDISTANCE, y + 60);
	    drawString("Total Time: " + totalTime + " s", TEXTDISTANCE, y + 80);
	    
	}

	public void replayRoute(int ybase) {

		int x, y;
		
		for (int i = 0; i < gpspoints.length; i++) {
			x = MARGIN + (int)((gpspoints[i].getLongitude() - minlon) * xstep);
	        y = ybase - (int)((gpspoints[i].getLatitude() - minlat) * ystep);

	        setColor(255, 0, 0);
	        fillCircle(x, y, 5);
	        pause(100);
	        
	        setColor(255, 255, 255);
	        fillCircle(x, y, 5);
		}
	}

}
