import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

public class TestSelenium {
	private boolean acceptNextAlert = true;
	private DesiredCapabilities desiredCapabilities;
	private LoggingPreferences logs;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		logs = new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.ALL);
		logs.enable(LogType.CLIENT, Level.ALL);
		logs.enable(LogType.DRIVER, Level.ALL);
		logs.enable(LogType.PERFORMANCE, Level.ALL);
		logs.enable(LogType.PROFILER, Level.ALL);
		logs.enable(LogType.SERVER, Level.ALL);
	  
		desiredCapabilities = DesiredCapabilities.firefox();
		desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
	
		driver = new FirefoxDriver(desiredCapabilities);
		baseUrl = "https://www.caixaimpulse.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitled() throws Exception {
		Actions action;
		WebDriverWait wait;
		WebElement element;

		action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/en/home");
		driver.findElement(By.xpath("//a[contains(@href,'projects')]")).click();

		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'span4 singleBlockThreeBoxIcon')]/a[contains(@href, '50161')]")));
		action.moveToElement(element).click();

		driver.findElement(By.xpath("//a[contains(@href,'community')]")).click();
		driver.findElement(By.xpath("//a[contains(@href,'news-events')]")).click();
    
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'datsNews')]/a[contains(@href,'55003')]")));
		action.moveToElement(element).click();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
		driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
  
	private void ExtractLogs() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}
}