import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Performs simple logistic regression.
 * User: tpeng
 * Date: 6/22/12
 * Time: 11:01 PM
 * 
 * @author tpeng
 * @author Matthieu Labas
 */
public class SLR {

	/** the learning rate */
	private double rate;

	/** the weight to learn */
	private double[] weights;

	/** the number of iterations */
	private int ITERATIONS = 3000;

	public SLR(int n) {
		this.rate = 0.0001;
		weights = new double[n];
	}

	private static double sigmoid(double z) {
		return 1.0 / (1.0 + Math.exp(-z));
	}

	public void train(List<Instance> instances) {
		for (int n=0; n<ITERATIONS; n++) {
			double lik = 0.0;
			for (int i=0; i<instances.size(); i++) {
				double[] x = instances.get(i).x;
				double predicted = classify(x);
				int label = instances.get(i).label;
				for (int j=0; j<weights.length; j++) {
					weights[j] = weights[j] + rate * (label - predicted) * x[j];
				}
				// not necessary for learning
				lik += label * Math.log(classify(x)) + (1-label) * Math.log(1- classify(x));
			}
			System.out.println("iteration: " + n + " " + Arrays.toString(weights) + " mle: " + lik);
		}
	}

	private double classify(double[] x) {
		double logit = .0;
		for (int i=0; i<weights.length;i++)  {
			logit += weights[i] * x[i];
		}
		return sigmoid(logit);
	}

	public static class Instance {
		public int label;
		public double[] x;

		public Instance(int label, double[] x) {
			this.label = label;
			this.x = x;
		}
	}

	public static List<Instance> readDataSet(String file) throws FileNotFoundException {
		List<Instance> dataset = new ArrayList<Instance>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
			
				String[] columns = line.split("\\s+");

				// skip first column and last column is the label
				int i = 1;
				double[] data = new double[columns.length-2];
				for (i=1; i<columns.length-2; i++) {
					data[i-1] = Double.parseDouble(columns[i]);
				}
				int label = Integer.parseInt(columns[i]);
				Instance instance = new Instance(label, data);
				dataset.add(instance);
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}
		return dataset;
	}


	public static void main(String... args) throws FileNotFoundException {
		List<Instance> instances = readDataSet("editedata.txt");
		SLR logistic = new SLR(3);
		logistic.train(instances);
		String month,day,time,origin,destination;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the month of travel");
		month = input.nextLine();
		
		System.out.println("Enter the day of travel");
		day = input.nextLine();
		
		System.out.println("Enter the time of travel");
		time = input.nextLine();
		
		
		
		System.out.println("Enter the origin airport code of travel");
		origin = input.nextLine();
		
		System.out.println("Enter the destination airport code of travel");
		destination = input.nextLine();
		
		String csvFile = "fdata.txt";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";


		try {
            Scanner x = new Scanner(new File(csvFile));
            
			//br = new BufferedReader(new FileReader(csvFile));
			
			while(x.hasNext())
			{
				String month1,day1,time1,timegroup,airportid,temperature,dewpoint,humidity,windspeed,destinationairportid;
				month1 = x.next();
				day1 = x.next();
				time1 = x.next();
				timegroup = x.next();
		        airportid = x.next();
			    temperature = x.next();
			    dewpoint = x.next();
			    humidity = x.next();
			    windspeed = x.next();
			    destinationairportid = x.next();
			    
               if(month.compareTo(month1)==0)
               {
            	   if(day.compareTo(day1)==0)
            	   {
            		   if(time.compareTo(time1)==0)
            		   {
            			   
            		   
            		   if(origin.compareTo(airportid)==0)
            		   {
            			   if(destination.compareTo(destinationairportid)==0)
            			   {
            				   System.out.println(month1 + "\t" + day1 +"\t"+ time1 + "\t"+ timegroup+"\t"+ airportid+"\t"+temperature +"\t"+dewpoint+"\t"+humidity+
            					    	"\t"+windspeed+"\t"+destinationairportid);
            				   
            				   int t1 = Integer.parseInt(timegroup);
            				   int m1 = Integer.parseInt(month1);
            				   Double tmp = Double.parseDouble(temperature);
            				   
            				   System.out.println(t1 + "" + m1 + " " + tmp);
            				   
            				   double[] z = {m1,tmp,t1};
            					
            					System.out.println("Done");
            					System.out.println("prob(1|x) = " + logistic.classify(z));
            					
            				    double res = logistic.classify(z);
            				    
            				    if(res>0.5)
            				    {
            				    	System.out.println("Flight might delay");
            				    	
            				    }
            				    
            				    else
            				    	System.out.println("Flight is on time");
            				   
            				   
            					    			
            			   }
            			   
            		   }
            		   
            		   }
            		   
            	   }
            	   
               }
			   
			    
			}
			
			x.close();
		}
				
				
				
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			System.out.println("Done");
				
			
}

}