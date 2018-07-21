public class DistanceMeasure {
    
    /**
     * @author sridhar
     * This class has two function i.e. distance measures based on user's input either euclidean or manhattan distance is selected.
     */
    public static double Euclidean(double[] a, double[] b) {
        double sum = 0.;
        
        for (int i = 0; i < a.length; i++) {
            double diff = Math.abs(a[i] - b[i]);
            sum += (diff * diff);
        }
        
        return Math.sqrt(sum);
    }
    
    public static double Manhattan(double[] a, double[] b)
    {
    	double sum =0;
    	for(int i =0 ; i< a.length ; i++)
    	{
    		double diff = Math.abs(a[i] - b[i]);
    		sum += diff;
    	}
		return sum;
    }
}