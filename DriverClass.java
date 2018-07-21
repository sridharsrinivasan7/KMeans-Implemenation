import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DriverClass {
    
    private static int[] seeds;

    public static void main(String[] args) {

    	int n = 4, d = 150; 

    	Scanner scan = new Scanner(System.in);
    	
    	//Requesting user to input the number clusters, Iterations and Distance Measure.
		System.out.println("Enter the Number of Clusters you want");
		int k = scan.nextInt();
		
		System.out.println("Enter the Number of Iteration you want");
		int itr = scan.nextInt();
		
		System.out.println("Enter the distance measure 1: Euclidean Else: Manhattan");
		int dtype = scan.nextInt();	
		scan.close();	
			
		//Reading the Iris.txt dataset and parsing via it.
        List<Iris> Traindataset = ReadDataset("Iris.txt"); 
        double[][] data = new double[d][n] ;
        	
        	for(int i=0; i < Traindataset.size();i++){
        			data[i][0] = Traindataset.get(i).sepalLength;
        			data[i][1] = Traindataset.get(i).sepalWidth;
        			data[i][2] = Traindataset.get(i).petalLength;
        			data[i][3] = Traindataset.get(i).petalWidth;
        	}
        	System.out.println("Processing the text file...");
        	for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    System.out.print(data[i][j] + " ");
                }
                System.out.println();
        	}
        	System.out.println("Text file is of length " +data.length);
    
        	//Function initally generates Random centriods 
        randomSeeds(k, n);  
        
        double[][]centroids = KMeansImplementation.kmeans(data, k, itr, dtype, seeds); //Clustering executed in this line
        
        System.out.println(" Final centroids:\n");
        System.out.println("===================================================================================");
        
        for (int j = 0; j < centroids.length; j++) {
            StringBuilder line = new StringBuilder();
            line.append("Centroid ");
            line.append(j);
            line.append(": ");
            
            for (int m = 0; m < centroids[0].length; m++) {
                line.append("\t");
                line.append(centroids[j][m]);
            }
            
            System.out.println(line.toString());
        }
    } 

    //Reading the File
	public static List<Iris> ReadDataset(String txtFile) {
		List<Iris> dataset = new ArrayList<Iris>();
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(txtFile));
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					String[] cell = line.split(",");
					dataset.add(new Iris(Double.parseDouble(cell[0]), Double.parseDouble(cell[1]), Double.parseDouble(cell[2]), Double.parseDouble(cell[3]),
							cell[4]));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return dataset;
	}
	
	static class Iris {
		public Double sepalLength;
		public Double sepalWidth;
		public Double petalLength;
		public Double petalWidth;
		public String Label;

		public Iris(Double sepalLength, Double sepalWidth, Double petalLength, Double petalWidth, String Label) {
			this.sepalLength = sepalLength;
			this.sepalWidth = sepalWidth;
			this.petalLength = petalLength;
			this.petalWidth = petalWidth;
			this.Label = Label;
		}
	}	

    private static void randomSeeds(int k, int n) {
        seeds = new int[k];
        int count = 0;

        while (count < k) {
        	
            boolean uniqueIndex = true;
            Random rand = new Random();
            int index = rand.nextInt(k);
            
            //Check for uniqueness
            for (int i = 0; i < count; i++) {
                if (index == seeds[i]) {
                    uniqueIndex = false;
                }
            }
            
            //If unique, add index and continue
            if(uniqueIndex) {
                seeds[count] = index;
                count++;
            }
        }
    }
}
	
    