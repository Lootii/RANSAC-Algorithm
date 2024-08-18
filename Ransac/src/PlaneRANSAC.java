/*
 *
 * Name: Lootii Kiri
 *
*/ 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;



public class PlaneRANSAC {
	
	// Point cloud to work with
	PointCloud pc;
	double eps; // Distance threshold for determining plane support
	
	// Constructor to initialize with a point cloud
	public PlaneRANSAC(PointCloud pc) {
		this.pc = pc;
	}
	
	// Set the distance threshold
	public void setEps(double eps) {
		this.eps = eps;
	}
	
	// Get the distance threshold
	public double getEps() {
		return eps;
	}
	
	
	//returns number of iterations required to obtain a certain level of confidence to
	// identify a plane made of a certain percentage of points
	public int getNumberOfIterations (double confidence, double percentageOfPointsOnPlane) {
		
		return (int) ((Math.log(1-confidence))/(Math.log(1- Math.pow(percentageOfPointsOnPlane,3))));
		
	}

	// Method to get three random points from the point cloud
	public List<Point3D> threeRandomPoints() {
		List<Point3D> threePoints = new ArrayList<Point3D>();

		while (threePoints.size() < 3 ) {
			threePoints.add(pc.getPoint());
		}
		return threePoints;
	}
	
	// Method to compute the plane equation from three points
	public Plane3D planeEquationFromPoints() {
		
		List<Point3D> points = threeRandomPoints();
		// Extract coordinates from the three points
		Point3D pA = points.get(0);
		double pAx = pA.getX();
		double pAy = pA.getY();
		double pAz = pA.getZ();
		
		Point3D pB = points.get(1);
		double pBx = pB.getX();
		double pBy = pB.getY();
		double pBz = pB.getZ();
		
		Point3D pC = points.get(2);
		double pCx = pC.getX();
		double pCy = pC.getY();
		double pCz = pC.getZ();
		
		// Calculate the plane equation coefficients
		double a = ((pBy-pAy)*(pCz-pAz))-((pCy-pAy)*(pBz-pAz));
		double b = ((pBz-pAz)*(pCx-pAx))-((pCz-pAz)*(pBx-pAx));
		double c = ((pBx-pAx)*(pCy-pAy))-((pCx-pAx)*(pBy-pAy));
		double d = (-1)*((a*pAx)+(b*pAy)+(c*pAz));
		
		// Return the plane defined by these coefficients
		Plane3D plane = new Plane3D(a,b,c,d);
		return plane;
	}
	
	// Method to get the list of points that support the current plane model within the threshold
	public List<Point3D> getSupport(double eps) {
		 Plane3D plane = planeEquationFromPoints();
		 
		 List<Point3D> currentList = new ArrayList<Point3D>();
		 Iterator<Point3D> iterator = pc.iterator();
		 
		 while(iterator.hasNext()) {
			Point3D point = iterator.next();
				if (plane.getDistance(point) < eps) {
					currentList.add(point);
				}
		 }
		 
		 return currentList;
	}
	
	 // Method to run the RANSAC algorithm for a specified number of iterations
	public void run (int numberOfIterations, String filename) {
		int i =0;
		List<Point3D> bestSupport = null;
		while (i<numberOfIterations) {
			i++;
			List<Point3D> currentSupport = getSupport(2.5);
			if (bestSupport == null) {
				bestSupport = currentSupport;
				
				continue;	
			}
			
			if (currentSupport.size() > bestSupport.size()) {
				
				bestSupport = currentSupport;
				
				
			}
			
			
		}
		
		
		// Create a new point cloud for the best support points and save it
		PointCloud planePoints = new PointCloud();
		for(Point3D point: bestSupport) {
			planePoints.addPoint(point);
		}
		
		planePoints.save(filename);
		
		// Remove the best support points from the original point cloud
		Iterator<Point3D> iterator = pc.iterator();
		 while(iterator.hasNext()) {
			Point3D point = iterator.next();
			
			for(Point3D supportPoint: bestSupport) {
				if(point.equals(supportPoint)) {
					iterator.remove();
				}
			}
		 }
	}
	
	
	// Main method to test the PlaneRANSAC class
	public static void main(String[]args) {
		PointCloud pc = new PointCloud("/Users/lootiikiri/Desktop/PointCloud3.xyz"); // Load point cloud
		
		PlaneRANSAC pr = new PlaneRANSAC(pc); // Initialize PlaneRANSAC with the point cloud
		
		pr.run(1, "/Users/lootiikiri/Desktop/PointCloud3_p1.xyz"); // Run RANSAC for 1 iteration and save result
		pr.run(1, "/Users/lootiikiri/Desktop/PointCloud3_p2.xyz");
		pr.run(1, "/Users/lootiikiri/Desktop/PointCloud3_p3.xyz");
		pc.save("/Users/lootiikiri/Desktop/PointCloud3_p0.xyz"); // Save the modified point cloud
		
	}	
}
