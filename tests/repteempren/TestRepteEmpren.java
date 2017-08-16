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

public class TestRepteEmpren {
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
		baseUrl = "http://repteempren.educaixa.com";
		
		// Set and maximize window
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testRepteEmpren() throws Exception {
		Actions action;
		boolean error = false;
		Logs logs;
		LogEntries logEntries;
		String referer;
		WebDriverWait wait;
		WebElement element;
		WebElement clickElement;

		action = new Actions(driver);
		wait = new WebDriverWait(driver, 20);

		// Test case 1 ::  Access to main page (ES)
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 1 :: Access to main page (ES)");
		referer = driver.getCurrentUrl();
		driver.get(baseUrl + "/es/home");
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		
		// Test case 2 ::  Access to main page (CA)
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 2 :: Access to main page (CA)");
		referer = driver.getCurrentUrl();
		driver.get(baseUrl + "/ca/home");
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		
		// Test case 3 :: Access to "Programa > Descripcio" section and check li with "KitCaixa Joves Emprenedors" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 3 ::  Access to 'Programa > Descripcio' section and check li with 'KitCaixa Joves Emprenedors' value");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@class,'container')]/ul[contains(@class,'right')]/div[contains(@id,'p_p_id_71_INSTANCE_UPPER_MENU_')]/div[contains(@class,'portlet-borderless-container')]/div[contains(@class,'portlet-body')]/li/a[contains(.,'Programa')]")).click();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/ca/programa?program_id=29239')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'columnsOverview')]/div[contains(@class,'container')]/div[contains(@class,'secondColumn')]/div[contains(@class,'listSecondColumn')]/ul[contains(@class,'firstList')]/li/a[contains(.,'KitCaixa Joves Emprenedors')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'KitCaixa Joves Emprenedors' not found in 'Programa > Descripcio' section");
			error = true;
		}
		
		// Test case 4 :: Access to "Programa > Convocatoria" section and check for image "mano"
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 4 :: Access to 'Programa > Convocatoria' section and check for image 'mano.png'");
		referer = driver.getCurrentUrl();		
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[contains(@class, 'navHeadTabsProgram')]/div[contains(@class, 'container')]/ul[contains(@class, 'nav')]/li/a[contains(@href,'program_section=CALL')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class, 'convocatoriaCall')]/div[contains(@class, 'container')]/div[contains(@class,'iconConvocatoria')]/div[contains(@class,'iconsIndiConvocatoria')]/img[contains(@src,'mano')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'mano' not found in 'Programa > Convocatoria' section");
			error = true;
		}

		// Test case 5 :: Access to "Programa >  Projectes seleccionats" section and check h3 with "YUNEO" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 5 :: Access to 'Programa >  Projectes seleccionats' section and check h3 with 'YUNEO' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[contains(@class, 'navHeadTabsProgram')]/div[contains(@class, 'container')]/ul[contains(@class, 'nav')]/li/a[contains(@href,'program_section=PROJECTS')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class, 'threeBoxIcon')]/div[contains(@class, 'container')]/div[contains(@class, 'container')]/div[contains(@class, 'singleBlockThreeBoxIcon')]/div[contains(@class, 'newFeaturedProjects')]/div[contains(@class, 'projectTitleBox')]/h3[contains(@class, 'projectTitle')]/a[contains(., 'YUNEO')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'YUNEO' not found in 'Programa >  Projectes seleccionats' section");
			error = true;
		}
			
		// Test case 6 :: Access to "YUNEO" project detail and check for "sana" p value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 6 :: Access to 'Novel leukemia therapy' project detail and check for image 'logo+centro.png'");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[contains(@class, 'threeBoxIcon')]/div[contains(@class, 'container')]/div[contains(@class, 'container')]/div[contains(@class, 'singleBlockThreeBoxIcon')]/div[contains(@class, 'newFeaturedProjects')]/div[contains(@class, 'projectTitleBox')]/h3[contains(@class, 'projectTitle')]/a[contains(., 'YUNEO')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'innovacioFixa')]/div[contains(@class,'container')]/p[contains(.,'sana')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: 'sana' p value not found in 'YUNEO' project detail");
			error = true;
		}

		// Test case 7 :: Access to "Incubadora > Descripcio" section and check p with "Lean Startup" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 7 ::  Access to 'Incubadora > Descripcio' section and check li with 'KitCaixa Joves Emprenedors' value");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@class,'container')]/ul[contains(@class,'right')]/div[contains(@id,'p_p_id_71_INSTANCE_UPPER_MENU_')]/div[contains(@class,'portlet-borderless-container')]/div[contains(@class,'portlet-body')]/li/a[contains(.,'Incubadora')]")).click();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/ca/incubadora?program_id=56913')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'columnsOverview')]/div[contains(@class,'container')]/div[contains(@class,'secondColumn')]/div[contains(@class,'listSecondColumn')]/p[contains(.,'Lean Startup')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Lean Startup' not found in 'Incubadora > Descripcio' section");
			error = true;
		}
		
		// Test case 8 :: Access to "Incubadora > Convocatoria" section and check for image "Calendari"
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 8 :: Access to 'Incubadora > Convocatoria' section and check for image 'Calendari'");
		referer = driver.getCurrentUrl();		
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[contains(@class, 'navHeadTabsProgram')]/div[contains(@class, 'container')]/ul[contains(@class, 'nav')]/li/a[contains(@href,'program_section=CALL')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'#calendario')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		if(!isElementPresent(By.xpath("//div[contains(@class, 'sliderCalendario')]/div[contains(@class,'container')]/img[contains(@src,'Calendari')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'Calendari' not found in 'Incubadora > Convocatoria' section");
			error = true;
		}
		
		// Test case 9 :: Access to "Projectes" section and check for javascript projects map
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 9 :: Access to 'Projectes' section and check for javascript projects map");
		referer = driver.getCurrentUrl();		
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/ca/projectes')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresentAndVisible(By.xpath("//section[contains(@class, 'breadcrumbTitleImg')]/iframe[contains(@id,'repteempren-mapa')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Javascript projects map not found in 'Projectes' section");
			error = true;
		}
		
		// Test case 10 :: Access to "Col·laboradors > Mentors" section and check for an element with "Eduardo Elosegui" value and access to "Col·laboradors > Inspiradors" section and check for an element with "Boris Mir" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 10 :: Access to 'Col·laboradors > Mentors' section and check for an element with 'Eduardo Elosegui' value and access to 'Col·laboradors > Inspiradors' section and check for an element with 'Boris Mir' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/ca/collaboradors')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'blockHecagon')]/div[contains(@class, 'hexagon')]/div[contains(@class, 'overflow1Hexagon')]/div[contains(@class, 'genericHexagon')]/div[contains(@class, 'txtImageHexagonal')]/p[contains(., 'Eduardo Elosegui')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Eduardo Elosegui' not found in 'Col·laboradors > Mentors' section");
			error = true;
		}
		element = driver.findElement(By.xpath("//section[contains(@class, 'navHeadTabs')]/div[contains(@class, 'container')]/ul[contains(@class, 'nav nav-tabs')]/li/a[contains(@href, '#inspiradors')]"));
		// We are using JavaScript executor due to "Timed out after 20 seconds waiting for element to be clickable" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'blockHecagon')]/div[contains(@class, 'hexagon')]/div[contains(@class, 'overflow1Hexagon')]/div[contains(@class, 'genericHexagon')]/div[contains(@class, 'txtImageHexagonal')]/p[contains(., 'Boris Mir')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Boris Mir' not found in 'Col·laboradors > Inspiradors' section");
			error = true;
		}

		// Test case 11 :: Access to "Noticies" section and check span with "Coneix els equips finalistes" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 11 :: Access to 'Noticies' section and check span with 'Coneix els equips finalistes' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/ca/noticies')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'news')]/div[contains(@class, 'datsNews')]/a/p/span[contains(., 'Coneix els equips finalistes')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'Coneix els equips finalistes' not found in 'Noticies' section");
			error = true;
		}

		// Test case 12 :: Access to "Coneix els equips finalistes" news detail and check for an element with "BRACELEPSY" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 12 :: Access to 'Coneix els equips finalistes' news detail and check for an element with 'BRACELEPSY' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'news')]/div[contains(@class, 'datsNews')]/a[contains(@href, '/news/59945')]")));
		// Can't click the element due to an overlapped div
		driver.get(element.getAttribute("href"));
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class, 'blockThreeColumn')]/div[contains(@class, 'container')]/div[contains(@class, 'midBlock')]/ul/li/strong[contains(., 'BRACELEPSY')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'BRACELEPSY' not found in 'Coneix els equips finalistes' news detail");
			error = true;
		}

		// Test case 13 :: Access to "Coneix els guanyadors" section
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 13 :: Access to 'Coneix els guanyadors' section");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[contains(@href,'program_id=29239&program_section=PROJECTS')]")).click();	
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
			e.printStackTrace();
			
			return false;
		}
	}
	
	private boolean isElementPresent(By by, WebDriverWait wait) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	private boolean isElementPresentAndVisible(By by, WebDriverWait wait) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	private void moveToElementAndClickIt(WebElement element) {
		Actions action;

		action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}

	private void clickElementThroughJavascript(WebElement element){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}
	
	private boolean isAlertPresent() {
		try {
		driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
			
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