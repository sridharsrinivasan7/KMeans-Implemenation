import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class ClusteringLogic {
    private final int dimensions;
    
    private ArrayList<double[]> points;
    private double[] centroid;
    
    public ArrayList<Integer> index = new ArrayList<Integer>();
    

    public ClusteringLogic(double[] c) {
        dimensions = c.length;
        centroid = c;
        points = new ArrayList<>();
    }
    
	@Override
	public String toString() {
		return ""+ points;
	}
    
   //Returing the average of the centroid.
	
    public double[] Centroid() {
        return centroid;
    }
    
    public ArrayList<double[]> getpoints()
    {
        return points;
    }  
    

    public void SetCentroid(double[] c) {
        centroid = c;
    }
    
    //Insert given point into cluster.

    public void Insert(double[] point, int i) {
        points.add(point);
        index.add(i);
    }
    
    public void PrintPoints(){
     		System.out.print(index);
       	
     }
   
    //Evaluating the SunSquareError 
    public double SumSquareError() {
        double sse = 0.0;
        
        for (double[] point : points) {
            double dist = DistanceMeasure.Euclidean(point, centroid);
            sse += dist * dist;
        }     
        return sse;
    }
    

    public void ClearPoints() {
        points = new ArrayList();
    }
    
   //Calculating the new centriod values upon the new cluster created
    public void CalcCentroid() {
        centroid = new double[dimensions];
        double[] sum = new double[dimensions];
        int n = 0;  
        
        for (double[] point : points) {
            for (int d = 0; d < dimensions; d++) {
                sum[d] += point[d];
            }
            
            n++;
        }
        
        for (int d = 0; d < dimensions; d++) {
            centroid[d] = sum[d] / (double) n;
        }
    }
}