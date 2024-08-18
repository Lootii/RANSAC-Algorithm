/*
 *
 * Name: Lootii Kiri
 *
*/ 


public class Plane3D {
	
	// Three points on the plane
	public Point3D point1;
	public Point3D point2;
	public Point3D point3;
	// Coefficients for the plane equation: ax + by + cz + d = 0
	public double a;
	public double b;
	public double c;
	public double d;
	
	// Constructor initializing the plane with three points
	public Plane3D(Point3D p1, Point3D p2, Point3D p3) {
		this.point1 = p1;
		this.point2 = p2;
		this.point3 = p3;
	}
	
	// Constructor initializing the plane with equation coefficients
	public Plane3D(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	

	
	
	
	// Method to calculate the distance from a point to the plane
	public double getDistance(Point3D pt) {
		
		
		return (Math.abs((a*pt.getX()) + (b*pt.getY()) + (c*pt.getZ()) + d)/(Math.sqrt((Math.pow(a, 2)) + (Math.pow(b, 2))+ (Math.pow(c, 2)))));
		
	}
	
	// Main method to test the Plane3D class
	public static void main(String[] args) {
		Plane3D plane = new Plane3D(3, 2, 6, -5);// Create a plane using equation coefficients

		Point3D pt = new Point3D(1,-2,4); // Create a point in 3D space
		
		System.out.println(plane.getDistance(pt)); // Print the distance from the point to the plane
	
	}

}
