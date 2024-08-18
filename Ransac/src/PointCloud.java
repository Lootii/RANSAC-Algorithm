/*
 *
 * Name: Lootii Kiri
 *
*/ 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PointCloud {
	
	//private String filename;
	public List<Point3D> points; // List of points in the point cloud
	
	// Constructor to read points from a file
	PointCloud(String filename){
		points = read(filename);
	}
	
	// Default constructor to initialize an empty point cloud
	PointCloud(){
		points = new ArrayList<Point3D>(); 	
	}
	
	// Method to add a point to the point cloud
	public void addPoint (Point3D pt) {
		points.add(pt);
	}
	
	// Method to get a random point from the point cloud
	public Point3D getPoint() {	
		int random = new Random().nextInt(points.size());
		return points.get(random);
	}
	
	// Method to save the point cloud to a file
	public void save(String filename) {
		File f = new File(filename);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter writer = null; 
		
		try {
			writer = new PrintWriter(f);
			writer.println("x,y,z");
			for (Point3D point : points) {
				writer.println(
						point.getX() + "," + point.getY() + "," + point.getZ());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			writer.close();
		}
	}
	
	// Method to read points from a file and return a list of Point3D objects
	public static List<Point3D> read(String filename) {

		List<Point3D> point3DList = new ArrayList<Point3D>();
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine(); // Skip header line
			line = reader.readLine(); // Read first data line

			while (line != null) {

				String[] parts = line.split("\\s");

				double x = Double.parseDouble(parts[0]);
				double y = Double.parseDouble(parts[1]);
				double z = Double.parseDouble(parts[2]);
				Point3D point = new Point3D(x, y, z);
				point3DList.add(point);

				line = reader.readLine();

			}
			reader.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return point3DList;

	}
	
	
	 // Iterator method for iterating over the list of points
	Iterator<Point3D> iterator(){
		return new MyIterator<Point3D>();
	}
	
	// Custom iterator class for iterating over the points
	@SuppressWarnings("hiding")
	class MyIterator<Point3D> implements Iterator<Point3D> {
		
		private int index;
		
		public MyIterator(){
			index = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index < points.size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Point3D next() {
			// TODO Auto-generated method stub
			return (Point3D) points.get(index++);
		}
		
		public void remove() {
			points.remove(index-1);
		}
		
	}

	
	public static void main(String[] args) throws Exception {
		//test class here with examples
	}
}
