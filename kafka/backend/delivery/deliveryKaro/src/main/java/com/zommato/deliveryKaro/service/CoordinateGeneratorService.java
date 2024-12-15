package com.zommato.deliveryKaro.service;

import java.util.ArrayList;
import java.util.List;

import com.zommato.deliveryKaro.entity.Coordinate;

public class CoordinateGeneratorService {
	
	public List<Coordinate> coordinateGenerator() {
		
		double strtLat = 37.77749;
		double startLon = -122.4194;
		int numberOfCoordinates = 10;
		
		double latStep = 0.01;
		double lonStep = 0.01;
		
		List<Coordinate> coordinates = generateCoordinate(strtLat, startLon, numberOfCoordinates, latStep, lonStep);
		return coordinates;
	}
	
	public List<Coordinate> generateCoordinate(double startLat, double startLon, int numberOfCoordinates, double latStep, double lonStep) {
		List<Coordinate> coordinates = new ArrayList<>();
		
		for (int i = 0; i < numberOfCoordinates; i++) {
			double newLat = startLat + (i * latStep);
			double newLon = startLon + (i * lonStep);
			coordinates.add(new Coordinate(newLat, newLon));
		}
		
		return coordinates;
		
	}

}
