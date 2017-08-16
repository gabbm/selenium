import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestBecarisRunner {
	public static void main(String[] args) {
		boolean successful = true;
		
		try{
			System.out.println("#########################");
			System.out.println("# Starting Test Becaris #");
			System.out.println("#########################");
			
	  		Result result = JUnitCore.runClasses(TestBecaris.class);
			
			// Obtain the JUnit test errors
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
			
			System.out.println("#############################");
			System.out.println("# Test executed in " + result.getRunTime() + " ms #");
			System.out.println("#  Test successful ? " + result.wasSuccessful() + "  #");
			System.out.println("#############################");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR :: " + e.getMessage());
			
			System.out.println("###########################");
			System.out.println("# Test successful ? false #");
			System.out.println("###########################");
		}

	}
}
