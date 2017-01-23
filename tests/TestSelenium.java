import java.lang.Thread;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.logging.Level;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.Keys;

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
		baseUrl = "https://www.caixaimpulse.com";
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitled() throws Exception {
		Actions action;
		Logs logs;
		LogEntries logEntries;
		WebDriverWait wait;
		WebElement element;
		WebElement clickElement;

		action = new Actions(driver);
		wait = new WebDriverWait(driver, 10);

		// Test case 1 ::  Access to main page
		System.out.println("Test case 1 :: Referer :: " + driver.getCurrentUrl() + " :: Access to main page");
		driver.get(baseUrl + "/en/home");
		
		// Test case 2 :: Access to "Programme & Call > Overview" section and check li with "Prototyping and design" value
		System.out.println("Test case 2 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Programme & Call > Overview' section and check li with 'Prototyping and design' value");
		driver.findElement(By.xpath("//div[contains(@class,'container')]/ul[contains(@class,'right')]/div[contains(@id,'p_p_id_71_INSTANCE_UPPER_MENU_')]/div[contains(@class,'portlet-borderless-container')]/div[contains(@class,'portlet-body')]/li/a[contains(.,'Programme & Call')]")).click();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/programme?program_id=22450')]")));
		moveToElementAndClickIt(element);
		if(isElementPresent(By.xpath("//section[contains(@class,'columnsOverview')]/div[contains(@class,'secondColumn')]/div[contains(@class,'listSecondColumn')]/ul[contains(@class,'secondList')]/li[contains(.,'Prototyping and design')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Prototyping and design' not found in 'Programme & Call > Overview' section");
		}

		// Test case 3 :: Access to "Programme & Call >  Call for projects" section and check for image "Call-Requirement-2"
		System.out.println("Test case 3 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Programme & Call > Call for projects' section and check li with 'Call-Requirement-2' value");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/programme?program_id=22450&program_section=CALL']")));
		moveToElementAndClickIt(element);
		if(isElementPresent(By.xpath("//div(@class, 'container')]/div[contains(@class,'row requerimentsCall')]/div[contains(@class,'row requerimentsCall')]/img[contains(@src,'Call-Requirement-2')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'Call-Requirement-2' not found in 'Programme & Call > Call for projects' section");
		}

		// Test case 4 :: Access to "Programme & Call >  Selected projects" section and check h3 with "CerviScan" value
		System.out.println("Test case 4 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Programme & Call > Selected projects' section and check h3 with 'CerviScan' value");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'&program_section=PROJECTS')]")));
		moveToElementAndClickIt(element);
		if(isElementPresent(By.xpath("//div(@class, 'row blockThreeBoxIcon')]/div[contains(@class, 'span4 singleBlockThreeBoxIcon')]/a/h3[contains(., 'CerviScan')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'CerviScan' not found in 'Programme & Call > Selected projects' section");
		}
			
		// Test case 5 :: Access to "Projects" section and check h3 with "Novel leukemia therapy" value
		System.out.println("Test case 5 :: Referer :: " + driver.getCurrentUrl() + " :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Projects' section and check h3 with 'Novel leukemia therapy' value");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/projects')]")));
		moveToElementAndClickIt(element);
		if(isElementPresent(By.xpath("//div(@class, 'row blockThreeBoxIcon')]/div[contains(@class,'span4 singleBlockThreeBoxIcon')]/a/h3[contains(.,'Novel leukemia therapy')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Novel leukemia therapy' not found in 'Projects' section");
		}

		// Test case 6 :: Access to "Novel leukemia therapy" project detail and check for image "logo+centro.png"
		System.out.println("Test case 6 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Novel leukemia therapy' project detail and check for image 'logo+centro.png'");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'span4 singleBlockThreeBoxIcon')]/a[contains(@href,'/49064')]")));
		// Can't click the element due to an overlapped div
		driver.get(element.getAttribute("href"));
		if(isElementPresent(By.xpath("//section(@class,'blockThreeColumn')]/div[contains(@class,'container')]/div[contains(@class,'span3 xs-span12 leftBlock')]/ul/li[contains(@class,'imgLiUlFixa')]/a/img[contains(@src,'logo+centro.png')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'logo+centro.png' not found in 'Novel leukemia therapy' project detail");
		}

		// Test case 7 :: Access to "Community > Participants" section and check for an element with "Eduardo Rocon" value and access to "Community > Mentors" section and check for an element with "Roger Gomis" value
		System.out.println("Test case 7 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Community > Participants' section and check for an element with 'Eduardo Rocon' value and access to 'Community > Mentors' section and check for an element with 'Roger Gomis' value");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/community')]")));
		moveToElementAndClickIt(element);
		if(isElementPresent(By.xpath("//div(@class, 'nav nav-tabs')]/div[contains(@class, 'hexagon')]/div[contains(@class, 'overflow1Hexagon')]/div[contains(@class, 'overflow2Hexagon')]/div[contains(@class, 'overflow3Hexagon')]/div[contains(@class, 'genericHexagon')]/div[contains(@class, 'txtImageHexagonal')]/p[contains(., 'Eduardo Rocon')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Eduardo Rocon' not found in 'Community > Participants' section");
		}
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'container')]/ul[contains(@class, 'nav nav-tabs')]/li/a[contains(@href, '#mentors')]")));
		moveToElementAndClickIt(element);
		if(isElementPresent(By.xpath("//div(@class, 'nav nav-tabs')]/div[contains(@class, 'hexagon')]/div[contains(@class, 'overflow1Hexagon')]/div[contains(@class, 'overflow2Hexagon')]/div[contains(@class, 'overflow3Hexagon')]/div[contains(@class, 'genericHexagon')]/div[contains(@class, 'txtImageHexagonal')]/p[contains(., 'Roger Gomis')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Roger Gomis' not found in 'Community > Mentors' section");
		}

		// Test case 8 :: Access to "News & events" section and check spans with "The AEEC organises the 4th National Congress of Scientific Entrepreneurs" or "The 2016 CaixaImpulse Training Programme starts!" values
		System.out.println("Test case 8 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'News & events' section and check spans with 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' or 'The 2016 CaixaImpulse Training Programme starts!' values");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/news-events')]")));
		moveToElementAndClickIt(element);
		if(isElementPresent(By.xpath("//div(@class, 'span4 xs-span12 blockNews events')]/div[contains(@class, 'datsNews')]/a/p/span[contains(., 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' not found in 'News & events' section");
		}
		if(isElementPresent(By.xpath("//div(@class, 'span4 xs-span12 blockNews news')]/div[contains(@class, 'datsNews')]/a/p/span[contains(., 'The 2016 CaixaImpulse Training Programme starts!')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'The 2016 CaixaImpulse Training Programme starts!' not found in 'News & events' section");
		}

		// Test case 9 :: Access to "The AEEC organises the 4th National Congress of Scientific Entrepreneurs" event detail and check for an element with "CosmoCaixa Barcelona, Barcelona" value
		System.out.println("Test case 9 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' event detail and check for an element with 'CosmoCaixa Barcelona, Barcelona' value");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'span4 xs-span12 blockNews events')]/div[contains(@class, 'datsNews')]/a[contains(@href, '/event/55611')]")));
		// Can't click the element due to an overlapped div
		driver.get(element.getAttribute("href"));
		if(isElementPresent(By.xpath("//section(@class, 'blockThreeColumn')]/div[contains(@class, 'container')]/div[contains(@class, 'span3 xs-span12 leftBlock')]/ul/li[contains(., 'CosmoCaixa Barcelona, Barcelona')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'CosmoCaixa Barcelona, Barcelona' not found in 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' event detail");
		}

		// Test case 10 :: Access to "The 2016 CaixaImpulse Training Programme starts!" news detail and check for an element with "January 05, 2017" value
		System.out.println("Test case 10 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'The 2016 CaixaImpulse Training Programme starts!' news detail and check for an element with 'January 05, 2017' value");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'news-events')]")));
		moveToElementAndClickIt(element);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'span4 xs-span12 blockNews news')]/div[contains(@class, 'datsNews')]/a[contains(@href, '/news/55552')]")));
		// Can't click the element due to an overlapped div
		driver.get(element.getAttribute("href"));
		if(isElementPresent(By.xpath("//section(@class, 'blockThreeColumn')]/div[contains(@class, 'container')]/div[contains(@class, 'span3 xs-span12 leftBlock')]/ul/li[contains(., 'January 05, 2017')]"))){
			System.out.println("[" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'January 05, 2017' not found in 'The 2016 CaixaImpulse Training Programme starts!' news detail");
		}

		// Test case 11 :: Access to "Apply" section
		System.out.println("Test case 11 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Apply' section");
		driver.findElement(By.xpath("//a[contains(@href,'/Convocatorias/login')]")).click();	

		//extractLogs();
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
	
	private void moveToElementAndClickIt(WebElement element) {
		Actions action;

		action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
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
  
	private void extractLogs() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println("[" + new Date(entry.getTimestamp()) + "] " + entry.getLevel() + " :: " + entry.getMessage());
		}
	}
}