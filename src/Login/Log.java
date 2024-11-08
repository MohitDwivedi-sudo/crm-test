package Login;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Log {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://uatcrm.savemax.com/");
		Thread.sleep(3000);
		
		driver.manage().window().maximize();
		
		WebElement box= driver.findElement(By.name("email-phone"));
		box.sendKeys("rohit.pandey@savemax.com");
		
		box= driver.findElement(By.xpath("(//input[contains(@class, 'w-full') and contains(@class, 'rounded-lg') and contains(@class, 'border') and contains(@class, 'py-2') and contains(@class, 'pl-9') and contains(@class, 'placeholder:text-sm') and contains(@class, 'w-full') and contains(@class, 'rounded-xl') and contains(@class, 'border') and contains(@class, 'p-1')])[2]"));
		box.sendKeys("savemax");
		
		
		WebElement button=driver.findElement(By.xpath("//button[contains(@class, 'btn') and contains(@class, 'grid') and contains(@class, 'min-w-full') and contains(@class, 'place-content-center') and contains(@class, 'bg-[color:var(--company-theme-blue)]') and contains(@class, 'text-lg') and contains(@class, 'font-normal') and contains(@class, 'capitalize') and contains(@class, 'text-white')]"));
	    button.click();
	    
	    Thread.sleep(1500);
	    
	    //Lead_Create(driver);
	    availOnBreak(driver);
	    
	    //driver.quit();
	}



public static void Lead_Create(WebDriver driver) throws InterruptedException {
	
	Thread.sleep(2000);
	WebElement addButton = driver.findElement(By.xpath("//img[@alt='Add Button']"));
	addButton.click();
	Thread.sleep(2000);
	WebElement box1=driver.findElement(By.id("firstName"));
	box1.sendKeys("Automated");
	
	box1=driver.findElement(By.id("lastName"));
	box1.sendKeys("1stTime");
	
	box1=driver.findElement(By.id("email"));
	box1.sendKeys("auto123@test.sel");
	
	box1=driver.findElement(By.id("phone"));
	box1.sendKeys("1234561230");
	
	box1= driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[2]/div[2]/div[2]/div[1]/div/div/div/div[1]"));
	box1.click();
	
	dropDown("Renter",driver);
	
	WebElement notes = driver.findElement(By.xpath("//*[@id=\"note\"]"));
	notes.sendKeys("This");
	
	box1=driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[2]/div[3]/div[2]/div[1]/div/div/div/div"));
	box1.click();
	
	dropDown("Self",driver);
	
	box1=driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[2]/div[3]/div[2]/div[2]/div/div/div/div"));
	box1.click();
	
	dropDown("Web",driver);
	
	box1=driver.findElement(By.id("note"));
	box1.sendKeys(" is Automated Note added");
	
	box1=driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[2]/div[4]/div[2]/div[2]/div/div/div/div"));
	box1.click();
	
	dropDown("Yukon",driver);
	
	Thread.sleep(2000);
	box1=driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[2]/div[4]/div[2]/div[3]/div/div/div/div"));
	box1.click();
	
	dropDown("Mayo",driver);
	
	box1=driver.findElement(By.id("pincode"));
	box1.sendKeys("M2E 4T2");
	
	box1=driver.findElement(By.id("addressDetails"));
	box1.sendKeys("C38/23 Automated Address Street 11 Baker Street");
	
	box1=driver.findElement(By.xpath("/html/body/div/div/div[1]/div/form/div[3]/div/button[2]"));
	box1.click();
	
    }

public static void availOnBreak(WebDriver driver) throws InterruptedException {
	
	WebElement box = driver.findElement(By.xpath("/html/body/div/div/header/div[1]/button/img"));
	box.click();
	
	Thread.sleep(2000);
	
	box = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[2]/div/div/div[1]/div[3]/button[2]/div[3]"));
	box.click();
	
	Thread.sleep(2000);
	
	box = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[2]/div/div/div[1]/button"));
	box.click();
	
	Thread.sleep(2000);
	
	box = driver.findElement(By.xpath("/html/body/div/div/header/div[1]/button/img"));
	box.click();
	
	Thread.sleep(2000);
	
	box = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[2]/div/div/div[1]/div[3]/button[1]/div[3]"));
	box.click();
	
	Thread.sleep(2000);
	
	box = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[2]/div/div/div[1]/button"));
	box.click();
	
}

public static void dropDown(String sr, WebDriver driver) {

	String optionTextToSelect = sr;
	String script = "var options = document.querySelectorAll('.react-select__option'); " +
	                "for (var i = 0; i < options.length; i++) { " +
	                "   if (options[i].textContent.includes('" + optionTextToSelect + "')) { " +
	                "       options[i].click(); " +
	                "       break; " +
	                "   } " +
	                "}";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript(script);	
}}





























