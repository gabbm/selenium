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

public class TestBecaris {
	private boolean acceptNextAlert = true;
	private DesiredCapabilities desiredCapabilities;
	private LoggingPreferences logs;
	private String baseUrl;
	private String baseUrlES;
	private String baseUrlEN;
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
	
		System.setProperty("webdriver.firefox.bin", "/opt/firefox46/firefox-bin");
		driver = new FirefoxDriver(desiredCapabilities);
		baseUrl = "https://becarislacaixa.net";
		baseUrlES = "https://becarioslacaixa.net";
		baseUrlEN = "https://lacaixafellows.net";
		
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
		driver.get(baseUrlES);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		
		// Test case 2 ::  Access to main page (EN)
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 2 :: Access to main page (EN)");
		referer = driver.getCurrentUrl();
		driver.get(baseUrlEN);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		
		// Test case 3 ::  Access to main page (CA) and check for gmaps fellows map, facebook wall widget and tweet timeline widget
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 3 :: Access to main page (CA) and check for gmaps fellow map, facebook wall widget and tweet timeline widget");
		referer = driver.getCurrentUrl();
		driver.get(baseUrl + "/ca/home");
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@id, 'map')]/div/div[contains(@class,'gm-style')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Gmaps fellows map not found in 'Home' page");
			error = true;
		}
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@id, 'social-tab')]/div[contains(@class,'aticle-twitter')]/article[contains(@class,'article-tweets-lacaixa')]/div[contains(@class,'box-tweets')]/a[contains(@href,'facebook')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Facebook wall widget not found in 'Home' page");
			error = true;
		}
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@id, 'social-tab')]/div[contains(@class,'aticle-twitter')]/article[contains(@class,'article-tweets')]/div[contains(@class,'header-tweets')]/iframe[contains(@id,'twitter-widget-0')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Twitter timeline widget not found in 'Home' page");
			error = true;
		}
		
		// Test case 4 :: Access to "Becaris" section and check h1 with "UNIVERSITAT" value
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 4 ::  Access to 'Becaris' section and check h1 with 'UNIVERSITAT' value");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'main-nav')]/ul[contains(@class,'nav')]/li[contains(@class,'btn_becari')]/a[contains(.,'Becaris')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'blc_internship_06')]/div[contains(@class,'container-fluid')]/div[contains(@class,'row-fluid')]/div[contains(@class,'span12')]/div[contains(@class,'row-fluid')]/div[contains(@class,'span10')]/div[contains(@class,'box-filter-intership')]/h1[contains(.,'UNIVERSITAT')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Element 'UNIVERSITAT' not found in 'Becaris' section");
			error = true;
		}
		
		// Test case 5 :: Access to "Activitats" section and check for javascript calendar visible
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 5 ::  Access to 'Activitats' section and check for javascript calendar visible");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'main-nav')]/ul[contains(@class,'nav')]/li[contains(@class,'btn_activitat')]/a[contains(.,'Activitats')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'blc_activitats')]/div[contains(@class,'span4')]/div/div/div/div[contains(@class,'activities')]/div[contains(@class,'becaris-calendar')]/div[contains(@class,'clndr')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Javascript calendar not found in 'Activitats' section");
			error = true;
		}

		// Test case 6 :: Access to "Noticies" section and check for noticia icon
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 6 ::  Access to 'Noticies' section and check for noticia icon");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'main-nav')]/ul[contains(@class,'nav')]/li[contains(@class,'btn_noticies')]/a[contains(.,'Notícies')]")));
		moveToElementAndClickIt(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@class,'container-news')]/div[contains(@class,'row-newsmobile')]/article[contains(@class,'article-news-img')]/div[contains(@class,'icon_noticia_small')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Noticia icon not found in 'Noticies' section");
			error = true;
		}
			
		// Test case 7 :: Access to "Advanced Search", filter by "Achucarro Basque Center for Neuroscience" and check for "Ricardo Marticorena Alvarez" a value 
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 7 :: Access to 'Advanced Search', filter by 'Achucarro Basque Center for Neuroscience and check for 'Ricardo Marticorena Alvarez' value");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@class,'bgFunctions')]/ul[contains(@class,'nav-functions')]/li[contains(@id,'originalSearch')]/span[contains(@class,'hidden-phone')]")).click();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'searchToggle')]/div[contains(@class, 'row-fluid')]/div[contains(@class,'span10')]/form[contains(@class, 'search-form')]/button[contains(@class, 'button hidden-phone submit-search avanzada')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'filtre-destUni')]/div[contains(@id,'mCSB_2')]/div[contains(@id,'mCSB_2_container')]/div[contains(@class, 'mensajes')]/div[contains(@class, 'texto')]/label[contains(@for,'destUni2118')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//section[contains(@class,'blc_becaris_result')]/article[contains(@class,'becari')]/div[contains(@class,'content_box')]/div[contains(@class,'content')]/h3/a[contains(.,'Ricardo Marticorena Alvarez')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: 'Ricardo Marticorena Alvarez' a value not found in 'Advanced Search' section");
			error = true;
		}
		
		// Test case 8 :: Access to "Ricardo Marticorena Alvarez" profile and check for "B004236" image visible
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 8 ::  Access to 'Ricardo Marticorena Alvarez' profile and check for 'B004236' image visible");
		referer = driver.getCurrentUrl();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[contains(@class,'blc_becaris_result')]/article[contains(@class,'becari')]/div[contains(@class,'content_box')]/div[contains(@class,'content')]/h3/a[contains(.,'Ricardo Marticorena Alvarez')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@class,'content-profile')]/div[contains(@class,'row-fluid')]/div[contains(@class,'span12')]/div[contains(@class,'span4')]/article[contains(@class,'content-avatar')]/img[contains(@src,'B004236')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Image 'B004236' not vibile in 'Ricardo Marticorena Alvarez' profile");
			error = true;
		}
		
		// Test case 10 ::  Access to main page (CA) and check visibily of login form
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 10 :: Access to main page (CA) and check visibilitye of login form");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@class,'menu-nav')]/div[contains(@class,'logo_blc')]/a")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'bgHeader')]/div[contains(@class, 'bgFunctions')]/ul[contains(@class,'nav-functions')]/li[contains(@class, 'no-logged-user')]/a[contains(@class, 'avatarBecari hidden-phone')]")));
		// We are using JavaScript executor due to "Element is not clickable at point" error
		clickElementThroughJavascript(element);
		if(!isElementPresentAndVisible(By.xpath("//div[contains(@class, 'login-modals')]/div[contains(@id,'userModal')]/div/div/div[contains(.,'Entra al teu perfil')]"), wait)){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: Login form not visible in 'Home' page");
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
