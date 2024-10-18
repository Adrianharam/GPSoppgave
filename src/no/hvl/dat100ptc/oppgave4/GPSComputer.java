package no.hvl.dat100ptc.oppgave4;


import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
 
import no.hvl.dat100ptc.TODO;


import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	public double totalDistance() {

		double distance = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;
// TODO

	}

	public double totalElevation() {

		double elevation = 0;

		for (int i = 0; i < gpspoints.length; i++) {
			double elevdiff = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			if (elevdiff > 0) {
				elevation += elevdiff;
			}

		}

		return elevation;

	}

	public int totalTime() {

		int start = gpspoints[0].getTime();
		int end = gpspoints[gpspoints.length - 1].getTime();

		return end - start;
	}

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length - 1];

// TODO
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double distance = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			int time = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			speeds[i] += distance / time;
		}
		return speeds;
	}

	public double maxSpeed() {
		double[] speeds = speeds();
		return GPSUtils.findMax(speeds);
	}

	public double averageSpeed() {
		double totalDistance = totalDistance();
		int totalTime = totalTime();

		return totalDistance / totalTime;
	}

// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double met = 0;
		double speedmph = speed * MS;

		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph < 12) {
			met = 6.0;
		} else if (speedmph < 14) {
			met = 8.0;
		} else if (speedmph < 16) {
			met = 10.0;
		} else if (speedmph < 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}

		double timer = secs / 3600;
		return weight * met * timer;

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		double[] speeds = speeds();

		for (int i = 0; i < speeds.length; i++) {
			int tidsforskjell = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			totalkcal += kcal(weight, tidsforskjell, speeds[i]);
		}

		return totalkcal;
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		int totaltime = totalTime();
		double totaldistance = totalDistance() / 1000;
		double totalelevation = totalElevation();
		double maxspeed = maxSpeed() * 3.6;
		double averagespeed = averageSpeed() * 3.6;
		double energy = totalKcal(WEIGHT);

		System.out.println("==============================================");
		System.out.println("Total Time     :" + totaltime);
		System.out.println("Total Distance :" + totaldistance + " km");
		System.out.println("Total Elevation:" + totalelevation + " m");
		System.out.println("Max speed      :" + maxspeed + " km/t");
		System.out.println("Average speed  :" + averagespeed + " km/t");
		System.out.println("Energy         :" + energy + " kcal");
		System.out.println("==============================================");
	}

}
