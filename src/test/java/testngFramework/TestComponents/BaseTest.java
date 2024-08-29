package testngFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import testngProjectFramwork.pageobjects.LandingPage;

public class BaseTest {

 public WebDriver driver;
 public LandingPage landingpage;
	
	public WebDriver InitilizeDriver() throws IOException, InterruptedException
	{
		
		Properties pro = new Properties();
		// convert file into  inputstream
		FileInputStream Gfile= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//testngProjectFramwork//resourses//GlobalData.proporties");
		
		
		pro.load(Gfile); //to load the path of a global file or any file
	
		String browsername=	System.getProperty("browser")!=null ?System.getProperty("browser"):pro.getProperty("browser");
		//the expression is called java ternary operator
		//String browsername=	pro.getProperty("browser");
	
	
		if(browsername.equalsIgnoreCase("chrome"))
		{
		// driver = new ChromeDriver();
			
		ChromeOptions options = new ChromeOptions();
	       // WebDriverManager.chromedriver().setup();
	         //Set default font size to achieve similar effect as setting zoom level
	       options.addArguments("--force-device-scale-factor=0.9");
	        
	        // Launch Chrome browser with modified options
	    WebDriver driver = new ChromeDriver(options);
	        
		 driver.manage().window().maximize();	
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
		 return driver;
		

	}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			//Webdriver driver = new EdgeDriver();
		}
	return driver;
	
	
}
	
	
public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
	TakesScreenshot ts	=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	
	File file=new File(System.getProperty("user.dir")+ "//reports//" +testCaseName+".png");
	FileUtils.copyFile(source, file);
		
	return System.getProperty("user.dir")+ "//reports//" +testCaseName+".png"; 	
		
	}
	
	


	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException, InterruptedException
	{
		
		driver =InitilizeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goToURL();
		return landingpage;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string 
	String jsonContent=	FileUtils.readFileToString(new File(filePath)
			, StandardCharsets.UTF_8);		
		
	//String to HashMap --Jackson DataBind
	ObjectMapper Mapper = new ObjectMapper();
	List<HashMap<String,String>>data=Mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
			{
		
			});
	return data;
	}
	
	
	
	

	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	}