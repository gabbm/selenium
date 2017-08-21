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

public class TestIncorpora {
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
		baseUrl = "https://www.incorpora.org";
		
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
		driver.get(baseUrl + "/es");
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		
		// Test case 2 ::  Access to main page (CA), check for 'icono-reincorpora' image and brightcove player
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 2 ::  Access to main page (CA), check for 'icono-reincorpora' image and brightcove player");
		referer = driver.getCurrentUrl();
		driver.get(baseUrl + "/ca");
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@class, 'modul-programa')]/div[contains(@class,'image1')]/a[contains(@class,'banner-home-a')]/div[contains(@class,'info-programa')]/p/img[contains(@src,'icono-reincorpora')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'icono-reincorpora' not found in 'Home' page");
			error = true;
		}
		driver.findElement(By.xpath("//section[contains(@class,'slider')]/div[contains(@id,'carouselSlider')]/div[contains(@class,'carousel-inner')]/div[contains(@class,'item')]/div[contains(@class,'brightcove-modal')]/div[contains(@class,'contenidor-bg-1')]/div[contains(@class,'container')]/div[contains(@class,'video-icon-hover')]")).click();
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@id, 'brightcoveDiv')]/div[contains(@class,'playvideo')]/div[contains(@class,'video-js')]/video[contains(@id,'brightcove')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Brightcove player not found in 'Home' page");
			error = true;
		}
		
		// Test case 3 :: Access to "Per a EMPRESES" section and check for 'r-social' image
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 3 ::  Access to 'Per a EMPRESES' section and check for 'r-social' image");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'nav-collapse')]/ul[contains(@class,'nav')]/li/a[contains(@href,'per-a-empreses')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class,'contenidor-pagina')]/div[contains(@class,'paraempresa')]/div[contains(@class,'span4')]/div[contains(@class,'info')]/img[contains(@src,'r-social')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: 'r-social' image not found in 'Per a EMPRESES' section");
			error = true;
		}
		
		// Test case 4 :: Access to "Per a PERSONES" section and check for 'Insercion-empresa' image
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 4 ::  Access to 'Per a PERSONES' section and check for 'Insercion-empresa' image");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'nav-collapse')]/ul[contains(@class,'nav')]/li/a[contains(@href,'per-a-persones')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class,'tab-content')]/div[contains(@class,'tab-pane')]/img[contains(@src,'Insercion-empresa')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: 'Insercion-empresa' image not found in 'Per a PERSONES' section");
			error = true;
		}
		
		// Test case 5 :: Access to "Per a PROFESSIONALS D'INSERCIO" section and check for 'Millora la com' a value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 5 ::  Access to 'Per a PROFESSIONALS D'INSERCIO' section and check for 'Millora la com' a value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'nav-collapse')]/ul[contains(@class,'nav')]/li/a[contains(@href,'per-a-professionals-insercio')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class,'cursos')]/div[contains(@class,'cursos-text')]/ul/li/a[contains(.,'Millora la com')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: a value 'Millora la com' not found in 'Per a PROFESSIONALS D'INSERCIO' section");
			error = true;
		}
		
		// Test case 6 :: Access to "Xarxa Incorpora" section and check for 'Entitats socials' div value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 6 ::  Access to 'Xarxa Incorpora' section and check for 'Entitats socials' div value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'nav-collapse')]/ul[contains(@class,'nav')]/li/a[contains(@href,'xarxa-incorpora')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class,'span12')]/div[contains(@class,'mapa')]/div[contains(@class,'img-mapa')]/div[contains(.,'Entitats socials')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: div value 'Entitats socials' not found in 'Xarxa Incorpora' section");
			error = true;
		}
		
		// Test case 7 :: Access to "Metode Incorpora" section and check for Brightcove player
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 7 ::  Access to 'Metode Incorpora' section and check for Brightcove player");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'nav-collapse')]/ul[contains(@class,'nav')]/li/a[contains(@href,'metodeincorpora')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@class, 'modulplayvideo')]/div[contains(@class,'modul-video')]/div[contains(@class,'playvideo')]/div[contains(@class,'video-js')]/video[contains(@id,'vjs_video')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Brightcove player not found in 'Metode Incorpora' section");
			error = true;
		}
		
		// Test case 8 :: Access to "Actualitat" section and check for 'Coneix les darreres' p value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 8 ::  Access to 'Actualitat' section and check for 'Coneix les darreres' p value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'nav-collapse')]/ul[contains(@class,'nav')]/li/a[contains(@href,'actualitat')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'titol-incorpora')]/div[contains(@class,'contenidor-bg-1')]/div[contains(@class,'container')]/div[contains(@class,'row-fluid')]/div[contains(@class,'span4')]/div[contains(@class,'formacio_css')]/p[contains(.,'Coneix les darreres')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: p value 'Coneix les darreres' not found in 'Actualitat' section");
			error = true;
		}
		
		// Test case 9 :: Access to "Entrevista mutua navarra" detail and check for 'Navarra-entrevista-Mutua-Navarra' image
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 9 ::  Access to 'Entrevista mutua navarra' detail and check for 'Navarra-entrevista-Mutua-Navarra' image");
		referer = driver.getCurrentUrl();
		driver.get(baseUrl + "/ca/detall-actualitat/entrevista-mutua-navarra");
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class, 'contenidor-pagina')]/div[contains(@class,'modul-detall')]/div[contains(@class,'span12')]/div[contains(@class,'detall_actualitat')]/img[contains(@src,'Navarra-entrevista-Mutua-Navarra')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'Navarra-entrevista-Mutua-Navarra' not found in 'Entrevista mutua navarra' detail");
			error = true;
		}
		
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