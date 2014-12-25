package practica1;

import com.rapidminer.Process;
import com.rapidminer.RapidMiner;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.IOContainer;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.learner.bayes.SimpleDistributionModel;
import com.rapidminer.operator.learner.meta.MultiModelByRegression;
import com.rapidminer.operator.performance.PerformanceVector;
import com.rapidminer.tools.XMLException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // write your code here
        try {
            //obtenemos los articulos de la portada del dia
            //java.lang.Process p = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"/practica_1_testData.py");
            //p.waitFor();

            RapidMiner.setExecutionMode(RapidMiner.ExecutionMode.COMMAND_LINE);
            RapidMiner.init();

            //RepositoryLocation loc = new RepositoryLocation("//Local Repository/data/listin-articulos-ts");
            Process process = new Process(new File(System.getProperty("user.dir")+"/procesos/svm.rmp"));
            //process.setProcessLocation(new RepositoryProcessLocation(loc));

            //Operator op_training = process.getOperator("Read CSV (2)");
            //op_training.setParameter("csv_file",System.getProperty("user.dir")+"/csv/listin-articulos.csv");
            //Operator op_sw = process.getOperator("Filter Stopwords (Dictionary)");
            //op_sw.setParameter("file",System.getProperty("user.dir")+"/stopwords/stop-words-spanish.txt");

            //Operator op_test = process.getOperator("Read CSV");
            //op_test.setParameter("csv_file",System.getProperty("user.dir")+"/csv/listin-articulos-test.csv");
            //Operator op_sw2 = process.getOperator("Filter Stopwords (2)");
            //op_sw2.setParameter("file",System.getProperty("user.dir")+"/stopwords/stop-words-spanish.txt");

            // execute the process and get the resulting objects
            //IOContainer ioInput = new IOContainer(new IOObject[] {myIOObject});
            IOContainer ioInput = new IOContainer();
            IOContainer ioResult = process.run(ioInput);

            // use the result(s) as needed, for example if your process just returns one ExampleSet, use this:
            System.out.println("ELEMENTOS:" + ioResult.size());
            if (ioResult.getElementAt(0) instanceof MultiModelByRegression) {
                MultiModelByRegression resultSet = (MultiModelByRegression)ioResult.getElementAt(0);
                System.out.println("ENTRO:"+resultSet.toString());
            }
            if (ioResult.getElementAt(1) instanceof ExampleSet) {
                ExampleSet resultSet = (ExampleSet)ioResult.getElementAt(1);
                System.out.println("ENTRO:"+resultSet.toString());
            }
            if (ioResult.getElementAt(2) instanceof PerformanceVector) {
                PerformanceVector resultSet = (PerformanceVector)ioResult.getElementAt(2);
                System.out.println("ENTRO:"+resultSet.toString());
            }
        } catch (IOException | XMLException | OperatorException ex) {
            ex.printStackTrace();
        }
    }
}
