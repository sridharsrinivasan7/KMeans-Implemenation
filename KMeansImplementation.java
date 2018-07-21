import java.util.Scanner;

public class KMeansImplementation {
    
    private static int K;
    private static int I;
    private static int N;
    private static int D;
    public static int dtype;
    private static double[][] data;
    private static ClusteringLogic[] clusters;
    
    /**
     * @author sridh
     * This Class does the clustering logic and routes the data to appropriate distance measures 
     */
    public static double[][] kmeans(double[][] dat, int k, int iter, int type, int[] seeds) {
        K = k;
        I = iter;
        dtype = type;
        N = dat.length;
        D = dat[0].length;
        
        //Kmeans object retains a copy of and array of clusters with centroids.
        
        data = dat;
        clusters = new ClusteringLogic[K];
        double[][] centroids = new double[K][D];    
        
        //Initialize K clusters with the K seeds that were pre-chosen.
        for (int i = 0; i < K; i++) {
            clusters[i] = new ClusteringLogic(data[seeds[i]]);
        }
        
       //For each iteration the centriod value is recalculated.
        for (int i = 0; i < I; i++) {
            ClusterPoints();
            System.out.println("Iteration " + (i + 1) + " completed...\n");
        }
        
        //Copy each of the K centroids to an array for output.
        for (int i = 0; i < K; i++) {
            centroids[i] = clusters[i].Centroid();
        }
        
        return centroids;
    }

    private static void ClusterPoints() 
    {
        //Assign points to nearest cluster
        for (int i = 0; i < N; i++) {
            if (data[i] == null) {  //This may be too late for error check, may remove later
                System.out.println("There is no point...");
            }
            
            //Insert point into cluster with nearest centroid
            clusters[findNearestCentroid(data[i])].Insert(data[i], i);
             }
        
        for (int i = 0; i < K; i++) {
        	System.out.println("\nCluster "+i);
            clusters[i].PrintPoints();
        }
        
        //Calculate new centroid for each cluster
        for (int i = 0; i <K; i++) {
            clusters[i].CalcCentroid();
        }
        
        //Total SSE is sum of SSE of all clusters, overall will decrease.
        double totalSSE = 0.;
        
        //Print out SSE's to monitor progress
        for (int i = 0; i < K; i++) {
            double sse = clusters[i].SumSquareError();
            
            totalSSE += sse;
            System.out.println("The SSE for cluster " + (i) + " is " + sse);
        }
        
        System.out.println("The Total SSE for this iteration: " + totalSSE);
        
       
        //Clear the points in all clusters
        for (int i = 0; i < K; i++) {
            clusters[i].ClearPoints();
        }
    }
    
    private static int findNearestCentroid(double[] candidate) {
        int pos = 0;
        double minDist = DistanceMeasure.Euclidean(candidate, clusters[pos].Centroid());
        double Dist = DistanceMeasure.Manhattan(candidate, clusters[pos].Centroid());

        
        if(dtype ==1 )
        {
        	for (int i = 1; i < clusters.length; i++) {
                if (DistanceMeasure.Euclidean(candidate, clusters[i].Centroid()) < minDist) {
                    pos = i;
                    minDist = DistanceMeasure.Euclidean(candidate, clusters[pos].Centroid());
                }
            }   
            return pos;	
        }
        else {
        	for (int i = 1; i < clusters.length; i++) {
                if (DistanceMeasure.Manhattan(candidate, clusters[i].Centroid()) < minDist) {
                    pos = i;
                    minDist = DistanceMeasure.Manhattan(candidate, clusters[pos].Centroid());
               }
        	} 
    return pos;
    }
  }
}