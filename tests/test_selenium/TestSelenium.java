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
		boolean error = false;
		Logs logs;
		LogEntries logEntries;
		String referer;
		WebDriverWait wait;
		WebElement element;
		WebElement clickElement;

		action = new Actions(driver);
		wait = new WebDriverWait(driver, 10);

		// Test case 1 ::  Access to main page
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 1 :: Access to main page");
		referer = driver.getCurrentUrl();
		driver.get(baseUrl + "/en/home");
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		
		// Test case 2 :: Access to "Programme & Call > Overview" section and check li with "Prototyping and design" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 2 :: Access to 'Programme & Call > Overview' section and check li with 'Prototyping and design' value");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@class,'container')]/ul[contains(@class,'right')]/div[contains(@id,'p_p_id_71_INSTANCE_UPPER_MENU_')]/div[contains(@class,'portlet-borderless-container')]/div[contains(@class,'portlet-body')]/li/a[contains(.,'Programme & Call')]")).click();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/programme?program_id=22450')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'columnsOverview')]/div[contains(@class,'secondColumn')]/div[contains(@class,'listSecondColumn')]/ul[contains(@class,'secondList')]/li[contains(.,'Prototyping and design')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Prototyping and design' not found in 'Programme & Call > Overview' section");
			error = true;
		}

		// Test case 3 :: Access to "Programme & Call >  Call for projects" section and check for image "Call-Requirement-2"
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 3 :: Access to 'Programme & Call > Call for projects' section and check li with 'Call-Requirement-2' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/programme?program_id=22450&program_section=CALL']")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'container')]/div[contains(@class,'row requerimentsCall')]/div[contains(@class,'imgRequeriments')]/img[contains(@src,'Call-Requirement-2')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'Call-Requirement-2' not found in 'Programme & Call > Call for projects' section");
			error = true;
		}

		// Test case 4 :: Access to "Programme & Call >  Selected projects" section and check h3 with "CerviScan" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 4 :: Access to 'Programme & Call > Selected projects' section and check h3 with 'CerviScan' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'&program_section=PROJECTS')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'row blockThreeBoxIcon')]/div[contains(@class, 'span4 singleBlockThreeBoxIcon')]/a/h3[contains(., 'CerviScan')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'CerviScan' not found in 'Programme & Call > Selected projects' section");
			error = true;
		}
			
		// Test case 5 :: Access to "Projects" section and check h3 with "Novel leukemia therapy" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 5 :: Referer :: " + driver.getCurrentUrl() + " :: Access to 'Projects' section and check h3 with 'Novel leukemia therapy' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/projects')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'row blockThreeBoxIcon')]/div[contains(@class,'span4 singleBlockThreeBoxIcon')]/a/h3[contains(.,'Novel leukemia therapy')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Novel leukemia therapy' not found in 'Projects' section");
			error = true;
		}

		// Test case 6 :: Access to "Novel leukemia therapy" project detail and check for image "logo+centro.png"
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 6 :: Access to 'Novel leukemia therapy' project detail and check for image 'logo+centro.png'");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'span4 singleBlockThreeBoxIcon')]/a[contains(@href,'/49064')]")));
		// Can't click the element due to an overlapped div
		driver.get(element.getAttribute("href"));
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'blockThreeColumn')]/div[contains(@class,'container')]/div[contains(@class,'span3 xs-span12 leftBlock')]/ul/li[contains(@class,'imgLiUlFixa')]/a/img[contains(@src,'logo+centro.png')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'logo+centro.png' not found in 'Novel leukemia therapy' project detail");
			error = true;
		}

		// Test case 7 :: Access to "Community > Participants" section and check for an element with "Eduardo Rocon" value and access to "Community > Mentors" section and check for an element with "Roger Gomis" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 7 :: Access to 'Community > Participants' section and check for an element with 'Eduardo Rocon' value and access to 'Community > Mentors' section and check for an element with 'Roger Gomis' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/community')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'blockHecagon')]/div[contains(@class, 'hexagon')]/div[contains(@class, 'overflow1Hexagon')]/div[contains(@class, 'overflow2Hexagon')]/div[contains(@class, 'overflow3Hexagon')]/div[contains(@class, 'genericHexagon')]/div[contains(@class, 'txtImageHexagonal')]/p[contains(., 'Eduardo Rocon')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Eduardo Rocon' not found in 'Community > Participants' section");
			error = true;
		}
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'container')]/ul[contains(@class, 'nav nav-tabs')]/li/a[contains(@href, '#mentors')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'blockHecagon')]/div[contains(@class, 'hexagon')]/div[contains(@class, 'overflow1Hexagon')]/div[contains(@class, 'overflow2Hexagon')]/div[contains(@class, 'overflow3Hexagon')]/div[contains(@class, 'genericHexagon')]/div[contains(@class, 'txtImageHexagonal')]/p[contains(., 'Roger Gomis')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Roger Gomis' not found in 'Community > Mentors' section");
			error = true;
		}

		// Test case 8 :: Access to "News & events" section and check spans with "The AEEC organises the 4th National Congress of Scientific Entrepreneurs" or "The 2016 CaixaImpulse Training Programme starts!" values
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 8 :: Access to 'News & events' section and check spans with 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' or 'The 2016 CaixaImpulse Training Programme starts!' values");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/news-events')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'span4 xs-span12 blockNews events')]/div[contains(@class, 'datsNews')]/a/p/span[contains(., 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' not found in 'News & events' section");
			error = true;
		}
		if(!isElementPresent(By.xpath("//div[contains(@class, 'span4 xs-span12 blockNews news')]/div[contains(@class, 'datsNews')]/a/p/span[contains(., 'The 2016 CaixaImpulse Training Programme starts!')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'The 2016 CaixaImpulse Training Programme starts!' not found in 'News & events' section");
			error = true;
		}

		// Test case 9 :: Access to "The AEEC organises the 4th National Congress of Scientific Entrepreneurs" event detail and check for an element with "CosmoCaixa Barcelona, Barcelona" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 9 :: Access to 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' event detail and check for an element with 'CosmoCaixa Barcelona, Barcelona' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'span4 xs-span12 blockNews events')]/div[contains(@class, 'datsNews')]/a[contains(@href, '/event/55611')]")));
		// Can't click the element due to an overlapped div
		driver.get(element.getAttribute("href"));
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class, 'blockThreeColumn')]/div[contains(@class, 'container')]/div[contains(@class, 'span3 xs-span12 leftBlock')]/ul/li[contains(., 'CosmoCaixa Barcelona, Barcelona')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'CosmoCaixa Barcelona, Barcelona' not found in 'The AEEC organises the 4th National Congress of Scientific Entrepreneurs' event detail");
			error = true;
		}

		// Test case 10 :: Access to "The 2016 CaixaImpulse Training Programme starts!" news detail and check for an element with "January 05, 2017" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 10 :: Access to 'The 2016 CaixaImpulse Training Programme starts!' news detail and check for an element with 'January 05, 2017' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'news-events')]")));
		moveToElementAndClickIt(element);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'span4 xs-span12 blockNews news')]/div[contains(@class, 'datsNews')]/a[contains(@href, '/news/55552')]")));
		// Can't click the element due to an overlapped div
		driver.get(element.getAttribute("href"));
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class, 'blockThreeColumn')]/div[contains(@class, 'container')]/div[contains(@class, 'span3 xs-span12 leftBlock')]/ul/li[contains(., 'January 05, 2017')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'January 05, 2017' not found in 'The 2016 CaixaImpulse Training Programme starts!' news detail");
			error = true;
		}

		// Test case 11 :: Access to "Apply" section
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 11 :: Access to 'Apply' section");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[contains(@href,'/Convocatorias/login')]")).click();	
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		//extractLogs();
		
		// Validate if there are some errors
		Assert.assertFalse(error);
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