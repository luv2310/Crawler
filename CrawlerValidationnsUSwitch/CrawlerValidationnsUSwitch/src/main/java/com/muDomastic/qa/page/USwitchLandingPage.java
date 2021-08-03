package com.muDomastic.qa.page;

import java.awt.Desktop.Action;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.ActionMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.yaml.snakeyaml.Yaml;

import com.muDomastic.qa.base.TestBase;
import com.muDomastic.qa.util.TestUtil;

public class USwitchLandingPage  {

	TestBase tb = new TestBase();

	WebDriver driver = tb.initialization();

	TestUtil action = new TestUtil();

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

	@FindBy(xpath = "//*[@id=\"electricity\"] | //*[@id=\"electricityDay\"]")
	WebElement eleamount;

	@FindBy(id = "electricityNight")
	WebElement elenight;


	@FindBy(xpath = "//button[(contains(text(),'Continue'))]")
	WebElement Amountcontinuebtn;

	@FindBy(id = "email-address-input")
	WebElement emailaaddress;

	@FindBy(xpath = "//button[(contains(text(),'Find cheaper deals'))]")
	WebElement findcheapbtn;


	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	WebElement Enabled;

	@FindBy(xpath = "//span[contains(text(),'No')]")
	WebElement NotEnabled;

	@FindBy(xpath = "//h6[@class='css-2usss1' and (contains(text(),'100% Green Variable'))]")
	WebElement headerprin;

	// get the list count
	List<WebElement> listoftable = driver.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li"));
	@FindBy(xpath = "//div[@class='css-1juarq1']/ol/li")
	WebElement totalLI;

	@FindBy(xpath = "//label[contains(text(),'We think you have an Economy 7 meter. Is this correct?')]")
	WebElement Economy7;

	@FindBy(xpath = "//label[contains(text(),'Do you know how much you use or spend on your energy?')]")
	WebElement knowEnergy;

	@FindBy(xpath = "//label[contains(text(),'Would you like to share your usage in £ or kWh?')]")
	WebElement energyParam;

	@FindBy(xpath = "//button[contains(text(),'Where can you find your gas and electricity usage?')]")
	WebElement energySpent;

	@FindBy(xpath = "//label[contains(text(),'What is your plan name?')]")
	WebElement planLabel;

	@FindBy(xpath = "//label[contains(text(),'Which supplier are you with?')]")
	WebElement supplierLabel;

	@FindBy(xpath = "//span[@class='css-1o8wgdi'] |\r\n"
			+ "//span[(contains(text(),'Other'))]")
	WebElement Other;

	@FindBy(xpath = "//Select[@class=\"css-1mesbj6\"]")
	WebElement energyDropdown;

	@FindBy(xpath = "//span[@class=\"css-1o8wgdi\" ] [(contains(text(),'Other'))]")
	WebElement planOther;

	@FindBy(xpath = "//Select[@id=\"gas.frequency\"]")
	WebElement gasfreq;

	@FindBy(xpath = "//Select[@id=\"electricity.frequency\"]")
	WebElement elecfreq;

	@FindBy(xpath = "//Select[@class=\"css-1mesbj6\"]")
	WebElement planSelect;

	@FindBy(xpath = "//Select[@id=\"spendNightPercentage\"]")
	WebElement nightPercent;

	@FindBy(xpath = "//span[@class='css-1o8wgdi'][(contains(text(),'Share in kWh'))]")
	WebElement selectkwh;

	@FindBy(xpath = "//h1[contains(text(),'Make switching faster and easier')]")
	WebElement emailPageHeading;

	@FindBy(xpath = "//button[@class=\"css-17rbu5b\" and contains(text(),'Create account')]")
	WebElement createAccountPopup;

	@FindBy(id = "email-address")
	WebElement  createAccountemailaaddress;

	@FindBy(xpath = "//label[contains(text(),'No')]")
	WebElement createAccount2ndpopup;

	@FindBy(xpath = "//button[@class=\"css-17za0yq\"]")
	WebElement seeMoreResult;

	@FindBy(id = "us-nav-btn-account-menu")
	WebElement  accountTab;

	@FindBy(xpath="//a[@class=\"us-nav-contact-number\"]")
	WebElement collOnUKPage;

	@FindBy(xpath="//div[@class=\"css-1juarq1\"]")
	WebElement elementList;
	
	// initialize the Page objects
	public USwitchLandingPage() {
		PageFactory.initElements(driver, this);

	}

	public void uSwitchJourney(
			String postCode,
			String partialAddress,
			String supplierName,
			String paymentMethod,
			String plan,
			String gasusage,
			String eleusage,
			String gasSpendFrequency,
			String electricitySpendFrequency,
			String nightPercentage,
			String requestId,
			boolean hasGas,
			boolean isDualFuel,
			boolean isEconomy7)
	{
		System.out.println("psotcode: "+postCode+", partial adress: "+partialAddress+
				", supplier name: "+supplierName+", payment method: "+paymentMethod+", has gas: "+hasGas+
				",is dual: "+isDualFuel+". is Economy: "+isEconomy7);

		//accept cookies banner handling
		System.out.println("accept cookie button");
		action.clickVerifiedElement(acceptcoockies);

		//postcode page
		System.out.println("enter post code");
		action.sendText(postcode,postCode);
		action.clickVerifiedElement(CompareAndSave);
		action.wait(2);

		//address page
		System.out.println("select address");
		String output = action.selectDropDownByVisibleText(selectaddress,partialAddress);
		if(output.contains("F"))
		{
			Select Dropdown = new Select(selectaddress);
			List<WebElement> items = Dropdown.getOptions();
			for(WebElement e: items)
			{
				if(!e.getText().contains("address"))
				{
					action.selectDropDownByVisibleText(selectaddress,e.getText());
					break ;
				}
			}
			
		}
		action.clickVerifiedElement(continuebtn);
		action.wait(2);

		//economy page
		if(isEconomy7)
		{
			System.out.println("economy 7 approval page");
			action.clickVerifiedElement(Enabled);
			action.clickVerifiedElement(continuebtn);
			action.wait(2);
		}

		//gas page
		if(hasGas)
		{
			System.out.println("gas enabled page");
			action.clickVerifiedElement(Enabled);
			action.clickVerifiedElement(gascontinuebtn); 
			action.wait(2);

			//if gas is enabled than only we can select dual fuel option dual fuel page	
			if(isDualFuel)
			{
				System.out.println("dual gas enabled page");
				action.wait(2);
				action.clickVerifiedElement(Enabled);
				action.clickVerifiedElement(dualfuelcontinuebtn); 
				action.wait(2);
			}
			else
			{			
				System.out.println("dual gas disabled page");
				action.clickVerifiedElement(NotEnabled);
				action.clickVerifiedElement(gascontinuebtn); 
				action.wait(2);
			}

		}
		else
		{
			System.out.println("gas disabled page");
			action.clickVerifiedElement(NotEnabled);
			action.clickVerifiedElement(gascontinuebtn); 
		}

		//supplier name page
		try {

			action.wait(3);
			if(action.verifyElementPresent(supplierLabel))
				System.out.println("supplier name page");
			if(supplierName.equalsIgnoreCase("Utility Warehouse"))
			{
				action.clickVerifiedElement(Other);
				action.selectDropDownByVisibleText(energyDropdown,supplierName);
				action.clickVerifiedElement(suppliercontinuebtn);
			}
			else 
			{
				System.out.println("supplier name value different !!!!!!!!!!!!!!!!");
			}			
		}
		catch(Exception e)
		{
			System.out.println("exception occured while entering supplier name" + e);
		}

		//how do you pay energy page
		try {	
			System.out.println("how to pay energy page");
			WebElement payment = driver.findElement(By.xpath("//span[@class=\"css-1o8wgdi\" and  (contains(text(),'"+paymentMethod+"'))]"));
			action.clickVerifiedElement(payment);		
			action.clickVerifiedElement(paycontinuebtn);		
		}
		catch (Exception e) {
			System.out.println("exception occured while clicking on payment method page" + e);

		}

		//plan value select page
		System.out.println("plan label page");
		if(action.verifyElementPresent(planLabel))
		{
			try {
				WebElement planvalue = driver.findElement(By.xpath("//span[@class=\"css-1o8wgdi\" and  (contains(text(),'"+plan+"'))]"));
				action.clickVerifiedElement(planvalue);
				action.wait(1);
				action.clickVerifiedElement(plancontinuebtn);
			}
			catch(Exception e)
			{
				try {
					action.clickVerifiedElement(planOther);
					action.selectDropDownByVisibleText(planSelect,plan);
					action.wait(1);
					action.clickVerifiedElement(plancontinuebtn);
				}catch (Exception c) {
					System.out.println("error ocuured while selecting the plan");
				}
			}
		}

		// do you know how much you spend page 
		if(action.verifyElementPresent(knowEnergy))
		{
			System.out.println(" do you know how much you spend page ");
			action.clickVerifiedElement(spendingcontinuebtn);
			if(action.verifyElementPresent(energyParam))
			{
				action.clickVerifiedElement(selectkwh);
				action.clickVerifiedElement(spendingcontinuebtn);
			}
		}


		//plan price detail page
		if (action.verifyElementPresent(energySpent))
		{
			System.out.println("energy spent details page");
			if(action.verifyElementPresent(gasamount))
			{
				action.sendText(gasamount, gasusage);

			}
			if(action.verifyElementPresent(eleamount))
			{			
				action.sendText(eleamount, eleusage);
			}
			if(action.verifyElementPresent(elenight))
			{	
				if(Float.parseFloat(nightPercentage) < (float) 0.05)
				{
					action.sendText(elenight, "5");
				}
				else 
				{
					action.sendText(elenight, nightPercentage);

				}
			}
			action.wait(2);
			if(action.verifyElementPresent(Amountcontinuebtn))
			{
				action.clickVerifiedElement(Amountcontinuebtn);
				//email page
				if(action.verifyElementPresent(emailPageHeading))
				{
					String Email = action.randomEmailGenerator();
					action.sendText(emailaaddress,Email);
					action.wait(1);
					action.clickVerifiedElement(findcheapbtn);
				}
			}
			else if (action.verifyElementPresent(findcheapbtn)) 
			{
				action.clickVerifiedElement(findcheapbtn);
				if(action.verifyElementPresent(createAccountPopup))
				{							
					String Email = action.randomEmailGenerator();
					System.out.println("handling create account popup email id is : "+ Email);
					action.wait(2);
					action.sendText(createAccountemailaaddress, Email);
					action.clickVerifiedElement(createAccountPopup);
					if(action.verifyElementPresent(createAccount2ndpopup))
					{
						action.clickVerifiedElement(createAccount2ndpopup);
						action.clickVerifiedElement(continuebtn);
					}
				}
			}
		}

		action.wait(5);
		action.verifyElementPresent(collOnUKPage);
		while(action.verifyElementPresent(seeMoreResult))
		{
			System.out.println("click on see more result page to load all tariff's");
			action.clickVerifiedElement(seeMoreResult);
			action.wait(3);
		}
		//div[@class=\"css-1juarq1\"]
		List<WebElement> items = driver.findElements(By.xpath("//div[@class=\"css-1juarq1\"]/ol/li"));
		for(WebElement e: items)
		{
		    System.out.print("element text ::: :: :"+e.getText());
		}
	}

	public HashMap<String, Map> storedata() throws IOException {
		// total number of element in a list (listed items present in the screen)
		List<WebElement> listoftable = driver.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li"));
		int xpathlistoftablecount = listoftable.size();
		System.out.println(xpathlistoftablecount);
		HashMap<String, Map> super_getallthedetails = new HashMap<String, Map>();

		for (int listnum = 1; listnum <= xpathlistoftablecount; listnum++) 
		{
			Map<String, String> getallthedetails = new HashMap<String, String>();

			List<WebElement> findContactlist = driver
					.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div"));
			int ContactFindcount = findContactlist.size();
			// checking if the elemet from the list has sub elements or not and if so then creating the xpath for same.
			if (ContactFindcount > 1) {

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
							By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div[4]/div/div/p[2]"));

					String AdditionalAnualCostval = AdditionalAnualCost.getText();
					String MonthlyPayCostval = MonthlyPayCost.getText();

					getallthedetails.put("AdditionalAnualCostval", AdditionalAnualCostval);
					getallthedetails.put("MonthlyPayCostval", MonthlyPayCostval);

				} else {
					WebElement AdditionalAnualCost = driver.findElement(
							By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div[2]/div/div/p[2]/span[2]"));
					WebElement MonthlyPayCost = driver.findElement(
							By.xpath("//div[@class='css-1juarq1']/ol/li[" + listnum + "]/div[2]/div/div/p[2]"));
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
				getallthedetails.put("providername", providername);
				getallthedetails.put("providerdesc", providerdescval);
				getallthedetails.put("contractval", contractval);
				getallthedetails.put("contractvalueval", contractvalueval);
				getallthedetails.put("earlyExitFeeval", earlyExitFeeval);
				super_getallthedetails.put("value_"+listnum, getallthedetails);
				System.out.println("---Details  Fetched---");
			}
		}
		return super_getallthedetails;	

	}

}
