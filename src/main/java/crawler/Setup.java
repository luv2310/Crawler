package crawler;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Setup 
{
	public static void main( String[] args ) throws InterruptedException
	{
		System.out.println( "!!!!!!!!!!! Crawler Run!!!!!!!!!!!!!!!!!" );
		Setup setup = new Setup();
		setup.operaload();
	}

	public void operaload() throws InterruptedException 
	{
		DesiredCapabilities capablities=DesiredCapabilities.opera();
		String opera_profile = "C:\\Users\\luv.mendiratta\\AppData\\Roaming\\Opera Software\\Opera Stable";
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--user-data-dir="+opera_profile);
		chromeOptions.setBinary("C:\\Users\\luv.mendiratta\\AppData\\Local\\Programs\\Opera\\77.0.4054.172\\opera.exe");
		System.setProperty("webdriver.opera.driver", "C:\\Users\\luv.mendiratta\\Desktop\\operadriver_win64\\operadriver_win64\\operadriver.exe");
		capablities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		OperaDriver driver = new OperaDriver(capablities);
		driver.get("https://whatismyipaddress.com");
		Thread.sleep(1000);
		driver.quit();
	}
}
