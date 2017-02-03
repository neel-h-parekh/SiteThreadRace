package defaultpac;

import java.sql.Date;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
	
class MultiLink extends Thread 
{
	private Thread t;
	private String threadName;
	private String threadurl;
	
	WebDriver driver;
	WebDriver fdriver;

	MultiLink(String tname, String turl)
	{
		threadName = tname;
		threadurl = turl;
		System.out.println("Creating " +  threadName + threadurl);
	}
	
	public void run() 
	{
		System.out.println("Running " +  threadName + threadurl);
		
		try 
		{
			System.setProperty("webdriver.chrome.driver","C:\\Sts\\SiteCore\\STCorp_au\\Sitemap\\chromedriver_new.exe");
			System.setProperty("webdriver.gecko.driver","C:\\eclipse\\geckodriver-v0.14.0-win64\\geckodriver.exe");
			fdriver = new FirefoxDriver();
			driver = new ChromeDriver();
			Thread.sleep(100);
			driver.get(threadurl);
			fdriver.get(threadurl);
		}
		catch (Exception e) 
		{
			System.out.println("Thread " +  threadName + threadurl + " interrupted.");
			e.printStackTrace();
		}
		System.out.println("Thread " +  threadName + threadurl + " exiting.");
	}
	
	public void start () 
	{
		System.out.println("Starting " +  threadName + threadurl);
		if (t == null)
		{
			t = new Thread (this, threadName);
			t.start ();
		}
	}
}

public class TestThreadRace 
{
	public static void main(String args[]) 
	{
		MultiLink T1 = new MultiLink("Thread-1","http://dev1-sterlingbackcheck-us/");
		T1.start();
		
		MultiLink T2 = new MultiLink("Thread-2","http://dev1-sterlingbackcheck-uk/");
		T2.start();
		
		MultiLink T3 = new MultiLink("Thread-3","http://dev1-sterlingbackcheck-ca/");
		T3.start();
		
		MultiLink T4 = new MultiLink("Thread-4","http://dev1-sterlingbackcheck-ca/ca-fr");
		T4.start();
	}
}