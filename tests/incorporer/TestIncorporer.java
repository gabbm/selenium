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

public class TestIncorporer{
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
		baseUrl = "http://www.incorporer.ma";
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testIncorporer() throws Exception {
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

		// Test case 1 ::  Accés a la home
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 1 :: Accés a la home");
		referer = driver.getCurrentUrl();
		driver.get(baseUrl);
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		
		// Test case 2 :: Accés al apartat "QUI NOUS SOMMES" comprovar si existeix h1 amb "la Caixa"
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 2 :: Accés al apartat 'QUI NOUS SOMMES' comprovar si existeix h1 amb 'la Caixa'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=2&lang=fr')][contains(.,'Qui nous sommes')]")).click();		
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/h1[contains(.,'« la Caixa »')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'la Caixa' no es troba a 'QUI NOUS SOMMES' ");
			error = true;
		}
		
		// Test case 3 :: Accés al apartat "Le Programme Incorporer" i comprovar si existeix el text 'professionnelle de personnes'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 3 :: Accés al apartat 'Le Programme Incorporer' comprovar si existeix el text 'professionnelle de personnes' ");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=3&lang=fr')][contains(.,'Le Programme Incorporer')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/p[contains(.,'professionnelle de personnes')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'professionnelle de personnes' no es troba a 'Le Programme Incorporer' ");
			error = true;
		}
		
		
		//Test case 4 :: Accés al apartat "Les services Incorporer" i comprovar si existeix el text 'A travers ses 11 techniciens'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 4 ::Accés al apartat 'Les services Incorporer' i comprovar si existeix el text 'A travers ses 11 techniciens'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=4&lang=fr')][contains(.,'Les services Incorporer')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/p[contains(.,'A travers ses 11 techniciens')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'A travers ses 11 techniciens' no es troba a 'Le services Incorporer' ");
			error = true;
		}
		
		//Test case 5 :: Accés al apartat 'Avantages pour l'entreprise' i comprovar si existeix el text 'La collaboration avec le Programme'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 5 ::Accés al apartat 'Avantages pour l'entreprise' i comprovar si existeix el text 'La collaboration avec le Programme'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=5&lang=fr')][contains(.,'Avantages pour')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/p[contains(.,'La collaboration avec le Programme')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'La collaboration avec le Programme' no es troba a 'Avantages pour l'entreprise' ");
			error = true;
		}
		
		//Test case 6 :: Accés al apartat 'Chiffres et témoignages' i comprovar si existeix el text dintre de <li> '1500 personnes en risque'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 6 :: Accés al apartat 'Chiffres et témoignages' i comprovar si existeix el text dintre de <li> '1500 personnes en risque'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=6&lang=fr')][contains(.,'Chiffres et témoignages')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/ul/li[contains(.,'1500 personnes en risque')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element '1500 personnes en risque' no es troba a 'Chiffres et témoignages' ");
			error = true;
		}
		
		//Test case 7 :: Accés al apartat 'Bénéficiaires et profils professionnels' i comprovar si existeix el text dintre de <p> 'sociales membres'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 7 :: Accés al apartat 'Bénéficiaires et profils professionnels' i comprovar si existeix el text dintre de <p> 'sociales membres'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=7&lang=fr')][contains(.,'Bénéficiaires et profils professionnels')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/p[contains(.,'sociales membres')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'sociales membres' no es troba a 'Bénéficiaires et profils professionnels' ");
			error = true;
		}
		
		//Test case 8 :: Accés al apartat 'Entités collaboratrices' i comprovar si existeix el text dintre de <p> 'Le Programme Incorporer'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 8 :: Accés al apartat 'Entités collaboratrices' i comprovar si existeix el text dintre de <p> 'Le Programme Incorporer'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=8&lang=fr')][contains(.,'Entités collaboratrices')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/p[contains(.,'Le Programme Incorporer')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'Le Programme Incorporer' no es troba a 'Entités collaboratrices' ");
			error = true;
		}
		
		//Test case 9 :: Accés al apartat 'Nouvelles et publications' i comprovar si existeix el text dintre de <p> 'M. Jaime LANASPA'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 9 :: Accés al apartat 'Nouvelles et publications' i comprovar si existeix el text dintre de <p> 'M. Jaime LANASPA'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=9&lang=fr')][contains(.,'Nouvelles et publications')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/div[contains(@id,'rightSidebar')]/p/span/span[contains(.,'M. Jaime LANASPA')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'M. Jaime LANASPA' no es troba a 'Nouvelles et publications' ");
			error = true;
		}
		
		//Test case 10 :: Accés al apartat 'Contact' i comprovar si existeix el text dintre de <p> 'Pour nous joindre'
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] INFO :: Test case 10 :: Accés al apartat 'Contact' i comprovar si existeix el text dintre de <p> 'Pour nous joindre'");
		referer = driver.getCurrentUrl();
		driver.findElement(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'mainMenu')]/table[contains(@id,'navlist')]/tbody/tr/td/a[contains(@href,'?pid=10&lang=fr')][contains(.,'Contact')]")).click();
		System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] INFO :: Referer :: " + referer + " :: Current URL :: " + driver.getCurrentUrl());
		if(!isElementPresent(By.xpath("//div[contains(@id,'container')]/div[contains(@id,'allContent')]/p[contains(.,'Pour nous joindre')]"))){
			System.out.println("-- [" + new Date(System.currentTimeMillis()) + "] ERROR :: L'element 'Pour nous joindre' no es troba a 'Contact' ");
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