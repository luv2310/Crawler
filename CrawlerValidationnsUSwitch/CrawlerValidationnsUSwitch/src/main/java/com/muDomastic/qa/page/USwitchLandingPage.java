package com.muDomastic.qa.page;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.muDomastic.qa.base.TestBase;
import com.muDomastic.qa.util.TestUtil;

public class USwitchLandingPage  {

	TestBase testbase =  new TestBase();
	public static WebDriver driver = TestBase.driver;
	
	// accept cookies
	@FindBy(id = "cookie_banner_accept")
	WebElement acceptcoockies;

	// Page objects
	@FindBy(xpath = "//input[@class='css-5ae4fi' and @name='postcode']")
	WebElement postcode;

	@FindBy(xpath = "//button[@class='css-1vmb0dx']")
	WebElement CompareAndSave;

	@FindBy(id = "address.id")
	WebElement selectaddress;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement continuebtn;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement gascontinuebtn;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement dualfuelcontinuebtn;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement suppliercontinuebtn;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement paycontinuebtn;

	@FindBy(id = "endDate.day")
	WebElement dayendDate;

	@FindBy(id = "endDate.month")
	WebElement MonthendDate;

	@FindBy(id = "endDate.year")
	WebElement YearendDate;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement plancontinuebtn;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement spendingcontinuebtn;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement sharecontinuebtn;

	@FindBy(id = "gas")
	WebElement gasamount;

	@FindBy(id = "electricity")
	WebElement eleamount;

	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement Amountcontinuebtn;

	@FindBy(id = "email-address-input")
	WebElement emailaaddress;

	@FindBy(xpath = "//button[(contains(text(),'Find cheaper deals'))]")
	WebElement findcheapbtn;

	// validate
	@FindBy(xpath = "//h6[@class='css-2usss1' and (contains(text(),'100% Green Variable'))]")
	WebElement headerprin;

	// get the list count
	// div[@class='css-1juarq1']/ol/li
	

	List<WebElement> listoftable = driver.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li"));

//	int listoftable= len(driver.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li"));

	@FindBy(xpath = "//div[@class='css-1juarq1']/ol/li")
	WebElement totalLI;

	// initialize the Page objects
	public USwitchLandingPage() {
		PageFactory.initElements(driver, this);
 
	}

	public void providePostcode() {

		acceptcoockies.click();
		postcode.sendKeys("LU1 1jj");
		CompareAndSave.click();

		Select addressdrpDown = new Select(selectaddress);
		addressdrpDown.selectByVisibleText("Flat 3, Brantwood Court, Brantwood Road, Luton, Bedfordshire, LU1 1JJ");

		continuebtn.click();
		gascontinuebtn.click();
		dualfuelcontinuebtn.click();
		suppliercontinuebtn.click();
		paycontinuebtn.click();

		Select daydp = new Select(dayendDate);
		daydp.selectByValue("25");

		Select mondaydp = new Select(MonthendDate);
		mondaydp.selectByValue("12");
		Select yeardp = new Select(YearendDate);

		yeardp.selectByValue("2023");

		plancontinuebtn.click();

		spendingcontinuebtn.click();

		sharecontinuebtn.click();

		gasamount.sendKeys("28");

		eleamount.sendKeys("30");

		Amountcontinuebtn.click();

		emailaaddress.sendKeys("sddfds@gmail.com");

		try {
			
						Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (findcheapbtn.isEnabled()) {
			findcheapbtn.click();
		} else {
			System.out.println("not enabled");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void storedata() {
		// total number of tables (listed items present in the screen)
		List<WebElement> listoftable = driver.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li"));
		int xpathlistoftablecount = listoftable.size();
		System.out.println(xpathlistoftablecount);
		// fetch the value for each row

		// Store

		for (int listnum = 1; listnum <= xpathlistoftablecount; listnum++) {
			List<WebElement> findContactlist = driver
					.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div"));
			int ContactFindcount = findContactlist.size();

			if (ContactFindcount > 1) {

//	 int listnum=1;
				// eg Pure Planet
				WebElement provider = driver
						.findElement(By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div/div/div/a/img"));
				// eg:100% Green Variable
				WebElement providerdesc = driver
						.findElement(By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div/div/div[3]/h6"));
				// eg Fixed rate contract
				WebElement contract = driver.findElement(
						By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div/div[3]/table/tbody/tr/td/p"));
				// eg 24 Months
				WebElement contractvalue = driver.findElement(By
						.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div/div[3]/table/tbody/tr/td[2]/p"));
				// eg £30.00 per fuel
				WebElement earlyExitFee = driver.findElement(By.xpath(
						"//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div/div[3]/table/tbody/tr[2]/td[2]/p"));

// find the offers and get the data 

				List<WebElement> offerliststatement = driver.findElements(
						By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div/div[3]/div[2]/div"));
				int CountOfferliststatement = offerliststatement.size();

				Map<String, String> getallthedetails = new HashMap<String, String>();
				for (int cos = 1; cos <= CountOfferliststatement; cos++) {
					WebElement OfferStatement = driver.findElement(By.xpath(
							"//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div/div[3]/div[2]/div[" + cos + "]"));

					String mapkeyval = "OfferStatemet" + cos;
					String OfferStatementVal = OfferStatement.getText();

					getallthedetails.put(mapkeyval, OfferStatementVal);
				}

				if (ContactFindcount > 5) {
//rates capture
					WebElement AdditionalAnualCost = driver.findElement(
							By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div[4]/div/div/p[2]/span[2]"));
					WebElement MonthlyPayCost = driver.findElement(
							By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div[4]/div/div/p[3]"));

					String AdditionalAnualCostval = AdditionalAnualCost.getText();
					String MonthlyPayCostval = MonthlyPayCost.getText();

					getallthedetails.put("AdditionalAnualCostval", AdditionalAnualCostval);
					getallthedetails.put("MonthlyPayCostval", MonthlyPayCostval);

				} else {
					WebElement AdditionalAnualCost = driver.findElement(
							By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div[2]/div/div/p[2]/span[2]"));
					WebElement MonthlyPayCost = driver.findElement(
							By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div[2]/div/div/p[3]"));
					String AdditionalAnualCostval1 = AdditionalAnualCost.getText();
					String MonthlyPayCostval1 = MonthlyPayCost.getText();

					getallthedetails.put("AdditionalAnualCostval", AdditionalAnualCostval1);
					getallthedetails.put("MonthlyPayCostval", MonthlyPayCostval1);
				}

				String providername = provider.getAttribute("alt");
				String providerdescval = providerdesc.getText();
				String contractval = contract.getText();
				String contractvalueval = contractvalue.getText();
				String earlyExitFeeval = earlyExitFee.getText();

//first 2 tables name and contract
				getallthedetails.put("providername", providername);
				getallthedetails.put("providerdesc", providerdescval);
				getallthedetails.put("contractval", contractval);
				getallthedetails.put("contractvalueval", contractvalueval);
				getallthedetails.put("earlyExitFeeval", earlyExitFeeval);
//				getallthedetails.put("AdditionalAnualCostval", AdditionalAnualCostval);
//				getallthedetails.put("MonthlyPayCostval", MonthlyPayCostval);
				//

				// print the values
				Iterator iter = getallthedetails.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					System.out.println("[Key] : " + entry.getKey() + " [Value] : " + entry.getValue());
				}

				System.out.println("End of first data-----------------------------------------------");
			}
		}

	}

}
