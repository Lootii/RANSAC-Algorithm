/*
 *
 * Name: Lootii Kiri
 *
*/ 


public class Point3D {
	
	  private double x; // x-coordinate
	  private double y; // y-coordinate
	  private double z; // z-coordinate

	  
	  

	  // Constructor to create a point with given coordinates
	  public Point3D(double x, double y, double z) {
	    this.x= x;
	    this.y= y;
	    this.z= z;
	  }
	  
	  // Getter for x-coordinate
	  public double getX() {
		  return x;
	  }

	  // Getter for y-coordinate
	  public double getY() {
		  return y;
	  }	

	  // Getter for z-coordinate
	  public double getZ() {
		  return z;
	  }
	  
	  // Override toString method to print the point in a readable format
	  public String toString() { 
		  return "("+x+","+y+","+z+")";
	  }

}
