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

public class USwitchPage  {

	//creating action object
	TestUtil action = new TestUtil();

	//driver initialisation
	WebDriver driver = new TestBase().initialization("uswitch");
	Actions actions = new Actions(driver);

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

	@FindBy(xpath = "//div[@class=\"css-jhrqka\"]//span[@class=\"css-jbkn09\"]")
	WebElement savePerYearValue;

	@FindBy(xpath = "//div[@class=\"css-jhrqka\"]//span[@class=\"css-1a48qmz\"]")
	WebElement isSavingSymbol;


	// initialize the Page objects
	public USwitchPage() {
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
		try {	
			acceptBanner();
			insertAddress(postCode,partialAddress);
			economy7Page(isEconomy7);
			gasNDualPage(hasGas, isDualFuel);
			supplierNamePage(supplierName);
			howToPayPage(paymentMethod);
			planValuePage(plan);
			knowEnergyPage();
			enterEnergyValuePage( hasGas,  isEconomy7,  gasusage,  eleusage,  nightPercentage );
			emailNfindcheapbuttonpage();
			landingPageAlert();
			seeMoreResultPage();
		}
		catch (Exception e) 
		{			
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public HashMap<String, Map> storedataNew(boolean hasGas,String paymentMethod) 
	{				
		HashMap<String, Map> super_getallthedetails = new HashMap<String, Map>();
		try{
			List<WebElement> listoftable = driver.findElements(By.xpath("//div[@class='css-1juarq1']/ol/li"));
			int listSize = listoftable.size();
			for (int i=1; i<=listSize; i++)
			{					
				HashMap<Object, Object> rankValue = new HashMap<>() ;
				String isGreen="None",Extras="None",comparisonSiteExclusive="None",savePerYear="None";
				boolean isSaving = true;
				boolean flagcomparison=false; 

				String planFeatureListXpath ="//ol//li["+i+"]//div[@class=\"css-1bd1op\"]//a[@class=\"css-v38h9u\"]";
				WebElement ele = null;
				try {
					ele = driver.findElement(By.xpath(planFeatureListXpath)); // planinfo clickable button
				}catch (Exception e) {
					System.out.println("element not found but still continue");
				}

				if(ele!=null)
				{   
					action.clickWithjavascriptattempt(ele);
					action.wait(2);
					planInfoGenericTable(rankValue);
					flagcomparison = planInfoGenericFeaturesAvailable(isGreen, Extras, comparisonSiteExclusive, flagcomparison, rankValue);
					rankValue.put("rank",String.valueOf(i));
					rankValue.put("paymentMethod",paymentMethod);	
					planInfoGenericSavePerYear(rankValue);
					if(flagcomparison)
					{
						planInfoComparisonIsTrue(hasGas, rankValue);
					}				
					action.wait(2);
					actions.moveToElement(buttonCancel).click().perform();
					System.out.println("completed for rank +++ "+i);
					super_getallthedetails.put("Rank_"+i,rankValue);
				}
			}
		}
		catch (Exception e)
		{			
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return super_getallthedetails;
	}





	public void acceptBanner()
	{		
		try {
			//accept cookies banner handling
			System.out.println("accept cookie button");
			action.clickVerifiedElement(acceptcoockies);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void insertAddress(String postCode,String partialAddress)
	{		
		try {
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
				List<WebElement> dropdownItems = Dropdown.getOptions();
				for(WebElement dropdownItem : dropdownItems)
				{
					String first = dropdownItem.getText(), Second = partialAddress;
					first=first.replaceAll(",", " ");
					first=first.replaceAll(" ", "");
					Second=Second.replaceAll(",", " ");
					Second=Second.replaceAll(" ", "");				

					if(first.equalsIgnoreCase(Second))
					{
						action.selectDropDownByVisibleText(selectaddress,dropdownItem.getText());
						break ;
					}
				}
			}	
			action.clickVerifiedElement(continuebtn);
			action.wait(2);			
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void economy7Page(boolean isEconomy7)
	{		
		try {
			//economy page
			if(isEconomy7)
			{
				System.out.println("economy 7 approval page");
				action.clickVerifiedElement(Enabled);
				action.clickVerifiedElement(continuebtn);
				action.wait(2);
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void gasNDualPage(boolean hasGas,boolean isDualFuel)
	{		
		try {
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
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void supplierNamePage(String supplierName)
	{		
		try {
			//supplier name page
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
				System.out.println("!!!!!!!!! supplier name value different !!!!!!!!!!!!!!!!");
			}					
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void howToPayPage(String paymentMethod)
	{		
		try {

			//how do you pay energy page//how do you pay energy page
			System.out.println("how to pay energy page");
			WebElement payment = driver.findElement(By.xpath("//span[@class=\"css-1o8wgdi\" and  (contains(text(),'"+paymentMethod+"'))]"));
			action.clickVerifiedElement(payment);		
			action.clickVerifiedElement(paycontinuebtn);		

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void planValuePage(String plan)
	{		
		//plan value select page
		try {
			System.out.println("plan label page");
			action.verifyElementPresent(planLabel);
			if(action.verifyElementPresent(planLabel))
			{
				WebElement planvalue = driver.findElement(By.xpath("//span[@class=\"css-1o8wgdi\" and  (contains(text(),'"+plan+"'))]"));
				action.clickVerifiedElement(planvalue);
				action.wait(1);
				action.clickVerifiedElement(plancontinuebtn);
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void knowEnergyPage()
	{		
		try {
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
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void enterEnergyValuePage(boolean hasGas, boolean isEconomy7, String gasusage, String eleusage, String nightPercentage )
	{		
		try {
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
					int overallElectricity = Integer.parseInt(eleusage);
					int percentRequire =(int) (Float.parseFloat(nightPercentage)*100);	
					int nightUsage =  (overallElectricity*percentRequire)/100;	
					action.sendText(eleamount, String.valueOf(overallElectricity-nightUsage));
				}

				if(isEconomy7)
				{	
					int overallElectricity = Integer.parseInt(eleusage);
					int percentRequire =(int)	(Float.parseFloat(nightPercentage)*100);	
					int nightUsage =  (overallElectricity*percentRequire)/100;
					action.sendText(elenight,String.valueOf(nightUsage));
				}
				action.wait(2);
			}			
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void emailNfindcheapbuttonpage()
	{		
		try {
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
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void landingPageAlert()
	{		
		try {
			if(action.verifyElementPresent(alertHeading))
			{
				action.clickVerifiedElement(alertCancelButton);
			}	
			if(action.verifyElementPresent(alertCancelButton_2))
			{
				action.clickVerifiedElement(alertCancelButton_2);
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}
	public void seeMoreResultPage()
	{		
		try {
			action.verifyElementPresent(collOnUKPage);
			while(action.verifyElementPresent(seeMoreResult))
			{		
				System.out.println("click on see more result page to load all tariff's");
				action.clickWithAttempt(seeMoreResult);
				action.wait(3);
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}




	public void planInfoGenericTable(HashMap<Object, Object> rankValue) 
	{	
		try {
			action.verifyElementPresent(buttonCancel);
			List<WebElement> planInfoGenericTable =  driver.findElements(By.xpath("//tr[@class=\"css-1owsb5q\"]//p[@class=\"css-1i44vdy\"]"));			
			int tablecount = 0;
			for (WebElement tableElement : planInfoGenericTable)
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
					variable_name ="contractTerm";
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

			// this is in case there is no contract term and end date in the form 
			if(tablecount<=5)
			{
				rankValue.put("contractTerm","variable");
			}

			//saving contract type value on the basis of contract term field
			if(!rankValue.get("contractTerm").toString().equalsIgnoreCase("variable"))
			{
				rankValue.put("contractType","Fixed rate contract");
			}
			else {
				rankValue.put("contractType","No Contract");
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public boolean planInfoGenericFeaturesAvailable(
			String isGreen,
			String Extras,
			String comparisonSiteExclusive,
			boolean flagcomparison,
			HashMap<Object, Object> rankValue
			) 
	{	
		try {
			String planFeatureXpath="//div[@class=\"css-17o3d8d\"]";
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
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return flagcomparison;
	}

	public void planInfoGenericSavePerYear(HashMap<Object, Object> rankValue)
	{
		try				
		{
			boolean isSaving = true;
			String iconElement = isSavingSymbol.getText();
			String 	savePerYear=savePerYearValue.getText();
			if(iconElement.contains("+"))
			{
				isSaving = false ;
				savePerYear = "-"+savePerYear;
			}	
			rankValue.put("isSaving", isSaving);
			rankValue.put("savePerYear",savePerYear);		
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}		
	}

	public void planInfoComparisonIsTrue(
			boolean hasGas,
			HashMap<Object, Object> rankValue
			)
	{		
		HashMap<String, String> electricityComparisondetails = new HashMap<String, String>();
		HashMap<String, String> gasComparisondetails = new HashMap<String, String>();
		try				
		{

			System.out.println("flag comparison is true");
			action.wait(2);
			System.out.println("Standing Charges validation");
			action.clickWithAttempt(standingCharges);
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
				case "unit rate":
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

				for (WebElement comparisonelementfromlist : gas_comparisonlist)
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
					case "unit rate":
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





		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}		
	}


}
