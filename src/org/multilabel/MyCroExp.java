package org.multilabel;

import java.util.logging.Level;
import java.util.logging.Logger;
import mulan.classifier.lazy.MLkNN;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.neural.BPMLL;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.InvalidDataFormatException;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluator;
import mulan.evaluation.MultipleEvaluation;
import mulan.examples.CrossValidationExperiment;
import weka.classifiers.trees.J48;
import weka.core.Utils;

/**
 * Class demonstrating a simple cross-validation experiment
 *
 * @author Grigorios Tsoumakas
 * @version 2010.12.15
 */
public class MyCroExp {
    /**
     * Executes this example
     *
     * @param args command-line arguments -arff and -xml
     */
    public static void main(String[] args) {

        try {
            // e.g. -arff emotions.arff
            String arffFilename = Utils.getOption("arff", args); 
            // e.g. -xml emotions.xml
            String xmlFilename = Utils.getOption("xml", args);

            System.out.println("Loading the dataset...");
            MultiLabelInstances dataset = new MultiLabelInstances(arffFilename, xmlFilename);

//            RAkEL learner1 = new RAkEL(new LabelPowerset(new J48()));
//            MLkNN learner2 = new MLkNN();
//            BPMLL learner3 = new BPMLL();
            BPxMLL learner4 = new BPxMLL();

            Evaluator eval = new Evaluator();
            MultipleEvaluation results;

            int numFolds = 10;
//            results = eval.crossValidate(learner1, dataset, numFolds);
//            System.out.println(results);
//            results = eval.crossValidate(learner2, dataset, numFolds);
//            System.out.println(results);
//            results = eval.crossValidate(learner3, dataset, numFolds);
//            System.out.println(results);
            results = eval.crossValidate(learner4, dataset, numFolds);
            System.out.println(results);
        } catch (InvalidDataFormatException ex) {
            Logger.getLogger(CrossValidationExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CrossValidationExperiment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
