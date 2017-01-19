import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestSeleniumRunner {
	public static void main(String[] args) {
		try{
	  		Result result = JUnitCore.runClasses(TestSelenium.class);
			System.out.println("Test executed in " + result.getRunTime() + " ms");
			System.out.println("Test successful ? " + result.wasSuccessful());

			for (Failure failure : result.getFailures()) {
	        	        System.out.println(failure.toString());
		        }
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR :: " + e.getMessage());
		}
     	}
}
