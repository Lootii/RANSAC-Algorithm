Project Overview
This project implements the RANSAC (Random Sample Consensus) algorithm in Java to detect planes within a 3D point cloud. The algorithm is robust in the presence of outliers, making it ideal for tasks where noisy data is common. The project includes the core RANSAC algorithm, the mathematical plane computation, and utilities for reading and writing point cloud data.

Objectives
Plane Detection: Identify planes in a 3D point cloud using the RANSAC algorithm.
Robustness: Handle noisy data by iteratively refining the plane model, discarding points that deviate significantly.
Flexibility: Allow for adjustments in parameters like the number of iterations and distance thresholds to customize the algorithm's sensitivity and accuracy.

Features
3D Point Cloud Handling: The project includes a PointCloud class that reads, stores, and processes 3D points from a file.
Plane Equation Calculation: The Plane3D class computes the equation of a plane given three points.
RANSAC Algorithm: The PlaneRANSAC class implements the RANSAC algorithm, iterating through possible plane models and selecting the one with the most inliers.
Support Point Identification: Efficiently identifies the set of points that support the best-fit plane.

Project Structure
Point3D.java: Represents a point in 3D space.
Plane3D.java: Defines a plane in 3D space and includes methods to calculate the distance from a point to the plane.
PointCloud.java: Manages a collection of 3D points, including methods to read from a file, save to a file, and sample random points.
PlaneRANSAC.java: Implements the RANSAC algorithm to detect planes within the point cloud.

Customization
Iterations: Adjust the number of iterations in PlaneRANSAC.java to balance between accuracy and performance.
Epsilon (eps): Modify the eps parameter to control the distance threshold for considering a point as an inlier. 

Example
Given a point cloud file PointCloud3.xyz, the RANSAC algorithm detects three dominant planes and saves the results as PointCloud3_p1.xyz, PointCloud3_p2.xyz, etc. with PointCloud3_p0.xyz being the original without planes points 