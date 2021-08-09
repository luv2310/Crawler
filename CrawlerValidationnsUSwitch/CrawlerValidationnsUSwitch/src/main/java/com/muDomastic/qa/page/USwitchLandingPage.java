package com.muDomastic.qa.page;

import java.awt.Desktop.Action;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Driver;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.swing.ActionMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(xpath = "//button[contains(text(),'Where can you find your gas and electricity usage?')] | //button[contains(text(),'Where can you find your electricity usage?')]")
	WebElement energySpent;

	@FindBy(xpath = "//label[contains(text(),'What is your plan name?')] | //label[contains(text(),'What is your electricity plan name?')]")
	WebElement planLabel;

	@FindBy(xpath = "//label[contains(text(),'Which supplier are you with?')] | //label[contains(text(),'Which electricity supplier are you with?')]")	
	WebElement supplierLabel;

	@FindBy(xpath = "//span[@class='css-1o8wgdi'] | //span[(contains(text(),'Other'))]")
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

	@FindBy(xpath="//button[@class=\"css-7litt4\"]")
	WebElement buttonCancel;

	@FindBy(xpath="//a[contains(text(),\"View standing charges and rates\")]")
	WebElement standingCharges;

	@FindBy(xpath = "//h1[contains(text(),'Energy bills are set to rise')]")
	WebElement alertHeading;

	@FindBy(xpath = "//h3[@class=\"css-g5tok7\"][1]")
	WebElement aboutThisPlantable; 

	@FindBy(xpath = "//button[@class=\"css-rvpwdk\"]")
	WebElement alertCancelButton;

	@FindBy(xpath = "//a[contains(text(),'No, use current rates')]")
	WebElement alertCancelButton_2;

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

			action.verifyElementPresent(supplierLabel);
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
		action.verifyElementPresent(planLabel);
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
		action.verifyElementPresent(knowEnergy);
		if(action.verifyElementPresent(knowEnergy))
		{
			System.out.println(" do you know how much you spend page ");
			action.clickVerifiedElement(spendingcontinuebtn);
			action.verifyElementPresent(energyParam);
			if(action.verifyElementPresent(energyParam))
			{
				action.clickVerifiedElement(selectkwh);
				action.clickVerifiedElement(spendingcontinuebtn);
			}
		}


		//plan price detail page
		action.verifyElementPresent(energySpent);
		if (action.verifyElementPresent(energySpent))
		{
			System.out.println("energy spent details page");
			if(hasGas)
			{
				if(action.verifyElementPresent(gasamount))
				{
					action.sendText(gasamount, gasusage);

				}
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

		if(action.verifyElementPresent(alertHeading))
		{
			action.clickVerifiedElement(alertCancelButton);
		}	

		if(action.verifyElementPresent(alertCancelButton_2))
		{
			action.clickVerifiedElement(alertCancelButton_2);
		}

		action.verifyElementPresent(collOnUKPage);
		try {
			while(action.verifyElementPresent(seeMoreResult))
			{		
				System.out.println("click on see more result page to load all tariff's");
				action.clickWithAttempt(seeMoreResult);
				action.wait(3);

			}
		}
		catch (Exception e) 
		{
			System.out.println("Alert !!! Exception occurred while clicking on see more result of final page.. ");
		}

	}


	public HashMap<String, Map> storedataNew(boolean hasGas,String paymentMethod) 
	{
		Actions actions = new Actions(driver);
		HashMap<String, Map> super_getallthedetails = new HashMap<String, Map>();
		String planFeatureXpath="//div[@class=\"css-17o3d8d\"]";
		List<WebElement> listoftable = driver.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li"));
		int listSize = listoftable.size();
		for (int i=1; i<=listSize; i++)
		{					
			HashMap<Object, Object> rankValue = new HashMap<>() ;
			HashMap<String, String> electricityComparisondetails = new HashMap<String, String>();
			HashMap<String, String> gasComparisondetails = new HashMap<String, String>();
			String isGreen="None",Extras="None",comparisonSiteExclusive="None",savePerYear="None";
			boolean isSaving = true;
			boolean flagContractLength = false,flagcomparison=false; 

			String planFeatureListXpath ="//li["+i+"]//div[@class=\"css-1bd1op\"]//a[@class=\"css-v38h9u\"]";
			WebElement ele = null;
			try {
				ele = driver.findElement(By.xpath(planFeatureListXpath)); // planinfo clickable button
			}catch (Exception e) {
				System.out.println("element not found but still continue");
			}

			if(ele!=null)
			{   
				int tablecount = 0;
				action.clickWithjavascriptattempt(ele);
				action.wait(2);
				action.verifyElementPresent(buttonCancel);
				List<WebElement> tableLength =  driver.findElements(By.xpath("//tr[@class=\"css-1owsb5q\"]//p[@class=\"css-1i44vdy\"]"));
				for (WebElement tableElement : tableLength)
				{
					tablecount++;
					String variable_name=driver.findElement(By.xpath("//tr["+tablecount+"]//p[@class=\"css-1i44vdy\"]/../..//p[@class=\"css-zkjwo2\"]")).getText().toLowerCase();
					switch (variable_name) 		
					{
					case "supplier":
						variable_name ="supplierName" ;
						break;
					case "plan name":
						variable_name ="tariffName" ;
						break;
					case "early exit fee":
						variable_name ="earlyExitFee" ;
						break;
					case "estimated annual cost":
						variable_name ="personalProjection" ;
						break;
					case "monthly direct debit":
						variable_name ="monthlyDirectDebit" ;
						break;
					case "contract ends":
						flagContractLength=true;
						variable_name ="contractEnd";
						break;	
					case "contract length":
						variable_name ="contractTerm";
						break;												
					default:
						System.out.println("error occured");
						variable_name ="not set" ;
					}
					String	variable_value=driver.findElement(By.xpath("//tr["+tablecount+"]//p[@class=\"css-1i44vdy\"]")).getText().toLowerCase();
					rankValue.put(variable_name,variable_value);
				}

				if(tablecount<=5)
				{
					rankValue.put("ContractTerm","variable");
				}

				List<WebElement> planFeaturesXpath = driver.findElements(By.xpath(planFeatureXpath));
				for (WebElement element : planFeaturesXpath)
				{
					String elementValue = element.getText();

					if(elementValue.toLowerCase().contains("green tariff"))
					{
						isGreen = "True";
					}
					if(elementValue.toLowerCase().contains("extras"))
					{
						Extras = "True";
					}
					if(elementValue.toLowerCase().contains("comparison"))
					{
						comparisonSiteExclusive = "True";
						flagcomparison=true;
					}
				}
				rankValue.put("isGreen",isGreen);
				rankValue.put("comparisonSiteExclusive",comparisonSiteExclusive);
				rankValue.put("Extras",Extras);
				rankValue.put("rank",String.valueOf(i));
				rankValue.put("paymentMethod",paymentMethod);			

				if(flagcomparison)
				{
					System.out.println("flag comparison is true");
					action.wait(2);
					System.out.println("Standing Charges validation");
					action.clickWithAttempt(standingCharges);
					//actions.moveToElement(standingCharges).click().perform();
					if(!action.verifyElementPresent(aboutThisPlantable))
					{
						System.out.println("Standing Charges validation click attempt number 2");
						action.clickWithAttempt(standingCharges);
					}
					List<WebElement> comparisonlist = driver.findElements(By.xpath("//h3[@class=\"css-g5tok7\"]/..//table[1]//p"));
					String variable_name = null;
					String variable_value = null;
					String temp = null;
					String gas_temp = null;
					boolean flagvariablvalueupdated = true;
					boolean gas_flagvariablvalueupdated = true;

					for (WebElement comparisonelementfromlist : comparisonlist)
					{
						boolean update = false;
						String comparisonElement = comparisonelementfromlist.getText().toLowerCase();
						switch (comparisonElement) 		
						{
						case "supplier":
							variable_name ="supplierName" ;
							flagvariablvalueupdated= false;
							break;
						case "tariff name":
							variable_name ="tariffName" ;
							flagvariablvalueupdated= false;
							break;
						case "tariff type":
							variable_name ="tariffType" ;
							flagvariablvalueupdated= false;
							break;
						case "payment method":
							variable_name ="paymentMethod" ;
							flagvariablvalueupdated= false;
							break;
						case "unit rates":
							variable_name ="unitRate" ;
							flagvariablvalueupdated= false;
							break;
						case "standing charge":
							variable_name ="standingCharge";
							flagvariablvalueupdated= false;
							break;	
						case "tariff ends on":
							variable_name ="tariffEndson";
							flagvariablvalueupdated= false;
							break;		
						case "price guaranteed":
							variable_name ="priceGuaranteedUntil" ;
							flagvariablvalueupdated= false;
							break;
						case "exit fees":
							variable_name ="exitfees" ;
							flagvariablvalueupdated= false;
							break;
						case "discounts":
							variable_name ="discounts";
							flagvariablvalueupdated= false;
							break;	
						case "additional":
							variable_name ="additionalproductsorservices";
							flagvariablvalueupdated= false;
							break;
						default:
							variable_value=comparisonElement;

							if(flagvariablvalueupdated)
							{
								variable_value = temp+" && "+variable_value;
								update=true;
							}
							temp = variable_value ;
							flagvariablvalueupdated= true;		
						}

						if(update)
						{	
							electricityComparisondetails.replace(variable_name, variable_value);
							continue ;
						}

						else if(flagvariablvalueupdated)

						{
							electricityComparisondetails.put(variable_name, variable_value);	
						}
					}
					rankValue.put("electricity", electricityComparisondetails);
					if(hasGas)
					{
						List<WebElement> gas_comparisonlist = driver.findElements(By.xpath("//h3[@class=\"css-g5tok7\"]/..//table[2]//p"));
						String gas_variable_name = null;
						String gas_variable_value = null;

						for (WebElement comparisonelementfromlist : comparisonlist)
						{
							boolean gas_update = false;
							String gas_comparisonElement = comparisonelementfromlist.getText().toLowerCase();
							switch (gas_comparisonElement) 		
							{
							case "supplier":
								gas_variable_name ="supplierName" ;
								gas_flagvariablvalueupdated= false;
								break;
							case "tariff name":
								gas_variable_name ="tariffName" ;
								gas_flagvariablvalueupdated= false;
								break;
							case "tariff type":
								gas_variable_name ="tariffType" ;
								gas_flagvariablvalueupdated= false;
								break;
							case "payment method":
								gas_variable_name ="paymentMethod" ;
								gas_flagvariablvalueupdated= false;
								break;
							case "unit rates":
								gas_variable_name ="unitRate" ;
								gas_flagvariablvalueupdated= false;
								break;
							case "standing charge":
								gas_variable_name ="standingCharge";
								gas_flagvariablvalueupdated= false;
								break;	
							case "tariff ends on":
								gas_variable_name ="tariffEndson";
								gas_flagvariablvalueupdated= false;
								break;		
							case "price guaranteed":
								gas_variable_name ="priceGuaranteedUntil" ;
								gas_flagvariablvalueupdated= false;
								break;
							case "exit fees":
								gas_variable_name ="exitfees" ;
								gas_flagvariablvalueupdated= false;
								break;
							case "discounts":
								gas_variable_name ="discounts";
								gas_flagvariablvalueupdated= false;
								break;	
							case "additional":
								gas_variable_name ="additionalproductsorservices";
								gas_flagvariablvalueupdated= false;
								break;
							default:
								gas_variable_value=gas_comparisonElement;

								if(gas_flagvariablvalueupdated)
								{
									gas_variable_value = gas_temp+" && "+gas_variable_value;
									gas_update=true;
								}
								gas_temp = gas_variable_value ;
								gas_flagvariablvalueupdated= true;		
							}

							if(gas_update)
							{	
								gasComparisondetails.replace(gas_variable_name, gas_variable_value);
								continue ;
							}

							else if(gas_flagvariablvalueupdated)
							{
								gasComparisondetails.put(gas_variable_name,gas_variable_value);	
							}
						}	
						rankValue.put("gas",gasComparisondetails);
					}
					actions.moveToElement(buttonCancel).click().perform();
					action.wait(2);
					actions.moveToElement(buttonCancel).click().perform();
				}

				if(flagContractLength)
				{
					if(!flagcomparison) 
					{
						action.wait(3);
						actions.moveToElement(buttonCancel).click().perform();
					}
					String variable_value = null;
					WebElement savingElement = driver.findElement(By.xpath("//li["+i+"]//div[@class=\"css-ppu0ks\"]//p[@class=\"css-1x40lsu\"]"));
					variable_value	= savingElement.getText();
					rankValue.put("contractTerm", variable_value);
				}	

				if(!flagContractLength && !flagcomparison)
				{
					action.wait(2);
					actions.moveToElement(buttonCancel).click().perform();
				}
				try
				{
					String iconElement = driver.findElement(By.xpath("//li["+i+"]//span[@class=\"css-1a48qmz\"]")).getText();
					WebElement savingElement = driver.findElement(By.xpath("//li["+i+"]//span[@class=\"css-jbkn09\"]"));
					savePerYear=savingElement.getText();
					if(iconElement.contains("+"))
					{
						isSaving = false ;
						savePerYear = "-"+savePerYear;
					}
				}
				catch (Exception e) 
				{
					System.out.println("Exception while getting the additional annual cost");
				}
				rankValue.put("isSaving", isSaving);
				rankValue.put("savePerYear",savePerYear);					
				System.out.println("completed for rank +++ "+i);

				super_getallthedetails.put("Rank_"+i,rankValue);
				if(i==21)
				{
					System.out.println();
				}
			}
		}
		return super_getallthedetails;
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
