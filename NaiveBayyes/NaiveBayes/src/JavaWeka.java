import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.evaluation.*;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.net.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;


public class JavaWeka {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  ConverterUtils.DataSource source1;
			try {
				source1 = new ConverterUtils.DataSource("traindata.arff");
				 Instances train = source1.getDataSet();
			        // setting class attribute if the data format does not provide this information
			        // For example, the XRFF format saves the class attribute information as well
			        if (train.classIndex() == -1)
			            train.setClassIndex(train.numAttributes() - 1);
			        
			        ConverterUtils.DataSource source2 = new ConverterUtils.DataSource("testdata.arff");
			        Instances test = source2.getDataSet();
			        // setting class attribute if the data format does not provide this information
			        // For example, the XRFF format saves the class attribute information as well
			        if (test.classIndex() == -1)
			            test.setClassIndex(train.numAttributes() - 1);
			        
			        NaiveBayes naiveBayes = new NaiveBayes();
			        naiveBayes.buildClassifier(train);
			        

			        Evaluation eval = new Evaluation(train);
			        eval.evaluateModel(naiveBayes,test);
			        
			        System.out.println(eval.toSummaryString("\nResults\n####\n",true));
			        System.out.println(eval.fMeasure(1)+ "" + eval.precision(1)+ "" +eval.recall(1));
			        
			        

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
