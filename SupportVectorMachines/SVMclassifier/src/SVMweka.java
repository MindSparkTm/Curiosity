import java.io.File;
import java.io.IOException;

import libsvm.LibSVM;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;



public class SVMweka {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		 Dataset data = FileHandler.loadDataset(new File("traindata.csv"),3,",");
		 
	
	        /*
	         * Contruct a LibSVM classifier with default settings.
	         */
	        Classifier svm = new LibSVM();
	        svm.buildClassifier(data);

	        /*
	         * Load a data set, this can be a different one, but we will use the
	         * same one.
	         */
	        Dataset dataForClassification = FileHandler.loadDataset(new File("testdata.csv"),3,",");
	        /* Counters for correct and wrong predictions. */
	        int correct = 0, wrong = 0;
	        /* Classify all instances and check with the correct class values */
	        for (Instance inst : dataForClassification) {
	            Object predictedClassValue = svm.classify(inst);
	            Object realClassValue = inst.classValue();
	            if (predictedClassValue.equals(realClassValue))
	                correct++;
	            else
	                wrong++;
	        }
	        System.out.println("Correct predictions  " + correct);
	        System.out.println("Wrong predictions " + wrong);


	}

}
