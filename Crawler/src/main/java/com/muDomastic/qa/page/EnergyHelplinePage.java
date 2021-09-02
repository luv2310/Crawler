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
import org.testng.TestListenerAdapter;
import org.testng.collections.Lists;
import org.yaml.snakeyaml.Yaml;

import com.beust.testng.TestNG;
import com.muDomastic.qa.base.TestBase;
import com.muDomastic.qa.util.TestUtil;

public class EnergyHelplinePage  {

	TestBase tb = new TestBase();

	WebDriver driver = tb.initialization("energyhelpline");
	TestUtil action = new TestUtil();

	// accept cookies
	@FindBy(xpath = "//button[@class=\"cookie-consent__cta cookie-consent__cta--dsc btn-accept-all\"]")
	WebElement acceptcoockies;

	// Page objects

	@FindBy(id = "postcode")
	WebElement postcode;

	@FindBy(id="submit")
	WebElement getStarted;

	@FindBy(xpath="//select[@class=\"ctl-multi-select\"]")
	WebElement selectaddress;

	@FindBy(xpath="//span[contains(text(),'Gas & Electricity')]")
	WebElement dualenergy;

	@FindBy(xpath="//span[contains(text(),'Gas Only')]")
	WebElement gasenergy;


	@FindBy(xpath="//span[contains(text(),'Electricity Only')]")
	WebElement electricityenergy;

	@FindBy(xpath = "//div[@data-testid=\"dualSameSupplierQuestion|true\"]")
	WebElement dualtariffyes;

	@FindBy(xpath = "//div[@data-testid=\"dualSameSupplierQuestion|false\"]")
	WebElement dualtariffno;

	@FindBy(xpath="//div[contains(text(),'Select your current supplier...')]")
	WebElement selectenergysupplier;

	@FindBy(xpath="//div[contains(text(),'Select your current supplier...')]")
	WebElement selectelectricitysupplier;

	@FindBy(xpath="//div[contains(text(),'Select your current supplier...')]")
	WebElement selectgassupplier;

	@FindBy(xpath="//div[contains(text(),'Select your current tariff...')]")
	WebElement energytariffvalue;

	@FindBy(xpath="//div[contains(text(),'Select your current tariff...')]")
	WebElement electricitytariffvalue;

	@FindBy(xpath="//div[contains(text(),'Select your current tariff...')]")
	WebElement gastariffvalue; 

	@FindBy(xpath="//span[contains(text(),'I know how much I use (kwh)')]")
	WebElement knowenergytrue;

	@FindBy(id="currentUsage.gasUsageAsKwh")
	WebElement currentGasUsageKWH;


	@FindBy(id="currentUsage.elecDayUsageAsKwh")
	WebElement currentElectricityUsageKWH;


	@FindBy(id="currentUsage.elecNightUsageAsKwh")
	WebElement currentElectricityUsageKWHNight;

	@FindBy(xpath="//input[@data-testid=\"email-input\"]")
	WebElement enterEmail;

	@FindBy(id = "submit")
	WebElement seeresult;

	@FindBy(xpath="//span[contains(text(),'Load more')]")
	WebElement loadmore;

	@FindBy(xpath="//span[contains(text(),'Filter your results')]")
	WebElement resultpage;

	@FindBy(xpath="//li[@data-testid=\"estimated-annual-cost\"]")
	WebElement estimatedannualcost;

	@FindBy(xpath="//div[@class=\"header\"]//h2")
	WebElement headingNname;

	@FindBy(xpath="//span[@class=\"ant-modal-close-x\"]")
	WebElement closepopup;






	// initialize the Page objects
	public EnergyHelplinePage() {
		PageFactory.initElements(driver, this);

	}

	public void energyHelplineJourney(
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
			String electricitySuppliers,
			String gasSupplier,
			boolean hasGas,
			boolean isDualFuel,
			boolean isEconomy7,
			boolean hasElectricity)
	{
		System.out.println("requestId: "+requestId+" psotcode: "+postCode+", partial adress: "+partialAddress+
				", supplier name: "+supplierName+", payment method: "+paymentMethod+", has gas: "+hasGas+
				",is dual: "+isDualFuel+". is Economy:"+isEconomy7);

		firstPageValidation(postCode, partialAddress, supplierName, isDualFuel, hasGas, paymentMethod, plan);

		enrgyUsage(hasGas, isEconomy7, hasElectricity, gasusage, eleusage, nightPercentage);

		// see result
		action.clickWithjavascriptattempt(seeresult);

		// load more results
		seeMoreResultPage();
	}

	public void firstPageValidation	(String postCode, String partialAddress, String supplierName, boolean isDualFuel,
			boolean hasGas,String paymentMethod,String plan)	
	{		
		try {

			insertAddress(postCode,partialAddress);
			action.wait(1);

			compareWhat(isDualFuel, hasGas);

			selectSupplierName(supplierName, isDualFuel, hasGas);
			action.wait(2);


			selectTariffValue(plan, isDualFuel, hasGas);
			action.wait(1);

			WebElement monthlydebitvalue = driver.findElement(By.xpath("//span[contains(text(),'"+paymentMethod+"')]"));
			action.clickWithjavascriptattempt(monthlydebitvalue);
			action.wait(1);


		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void insertAddress(String postCode,String partialAddress)
	{		
		try {

			System.out.println("Enter Address");
			action.verifyElementPresent(postcode);
			action.sendText(postcode,postCode.replaceAll("\\s+",""));
			action.clickWithjavascriptattempt(getStarted);
			action.wait(2);

			String output = action.selectDropDownByVisibleText(selectaddress,partialAddress);
			if(output.contains("F"))
			{
				Select Dropdown = new Select(selectaddress);
				List<WebElement> dropdownItems = Dropdown.getOptions();
				for(WebElement dropdownItem : dropdownItems)
				{
					String first = dropdownItem.getText(), Second = partialAddress;
					first=first.replaceAll(",", " ");
					first=first.replace(".", " ");
					first=first.replaceAll(" ", "");
					Second=Second.replaceAll(",", " ");
					Second=Second.replaceAll(" ", "");				
					if(Second.contains(first))
					{
						action.selectDropDownByVisibleText(selectaddress,dropdownItem.getText());
						break ;
					}
				}
			}	

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void compareWhat(boolean isDual,boolean hasGas)
	{		
		try {
			if(isDual){
				action.clickWithjavascriptattempt(dualenergy);
				action.clickWithjavascriptattempt(dualtariffyes);
			}
			else if (!hasGas){
				action.clickWithjavascriptattempt(electricityenergy);
			}

			else if(hasGas){
				action.clickWithjavascriptattempt(gasenergy);
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void selectSupplierName(String supplierName,boolean isDual,boolean hasGas)
	{		
		try {			
			if(supplierName.equalsIgnoreCase("Utility Warehouse"))
			{
				if(isDual) {
					action.clickWithjavascriptattempt(selectenergysupplier);

				}
				else if (!hasGas){
					action.clickWithjavascriptattempt(selectelectricitysupplier);
				}

				else if(hasGas){
					action.clickWithjavascriptattempt(selectgassupplier);
				}	

				WebElement suppliernamelement = driver.findElement(By.xpath("//li[contains(text(),'"+supplierName+"')]"));
				action.clickWithjavascriptattempt(suppliernamelement);
			}
			else 
			{
				System.out.println("!!!!!!!!!  supplier name value different !!!!!!!!!!!!!!!!");
			}		
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void selectTariffValue(String plan,boolean isDual,boolean hasGas)
	{		
		try {			
			if(isDual) {
				action.clickWithjavascriptattempt(energytariffvalue);
			}
			else if (!hasGas){
				action.clickWithjavascriptattempt(electricitytariffvalue);
			}

			else if(hasGas){
				action.clickWithjavascriptattempt(gastariffvalue);
			}					

			WebElement planElement = driver.findElement(By.xpath("//li[contains(text(),'"+plan+"')]"));
			action.clickWithjavascriptattempt(planElement);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void enrgyUsage(boolean hasGas, boolean isEconomy7,boolean hasElectricity,String gasusage, String eleusage, String nightPercentage)
	{		
		try {
			System.out.println("enter energy values");
			action.clickWithjavascriptattempt(knowenergytrue);

			if(hasGas)
			{
				action.sendText(currentGasUsageKWH, gasusage);
			}
			if(hasElectricity) 
			{		
				if(isEconomy7)
				{
					int overallElectricity = Integer.parseInt(eleusage);
					int percentRequire =(int) (Float.parseFloat(nightPercentage)*100);	
					int nightUsage =  (overallElectricity*percentRequire)/100;	
					action.sendText(currentElectricityUsageKWH, String.valueOf(overallElectricity-nightUsage));
					action.sendText(currentElectricityUsageKWHNight, String.valueOf(nightUsage));
				}else {
					action.sendText(currentElectricityUsageKWH, eleusage);
				}
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public String seeMoreResultPage()
	{		
		try {
			action.verifyElementPresent(resultpage);
			while(action.verifyElementPresent(loadmore))
			{		
				System.out.println("click on see more result page to load all tariff's");
				action.clickWithjavascriptattempt(loadmore);
				action.wait(2);
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
			return "Fail";
		}
		return "Pass";
	}

	public HashMap<String, Map> storedataNew(boolean hasGas,String paymentMethod) 
	{
		HashMap<String, Map> super_getallthedetails = new HashMap<String, Map>();
		try {
			action.wait(2);
			String resultlistxpaths = "//li[@class=\"ant-list-item\"]";


			// the below line code is to make driver wait till the time it finds the result page
			driver.findElement(By.xpath(resultlistxpaths));
			int rankcount = 1;

			List<WebElement> resultlist = driver.findElements(By.xpath(resultlistxpaths));

			for(WebElement result : resultlist )
			{


				HashMap<Object, Object> rankValue = new HashMap<>() ;

				String supplierName, contractType, personalProjection, isGreen ="None", tariffName, contractTerm,
						savePerYear,rank, paymentMethodvalue, monthlyDirectDebit,earlyExitFee,offers,none="None";

				boolean isSaving=true;
				//not getting anyvalue just saving as none to maintain the json structure
				rankValue.put("comparisonSiteExclusive", "False");
				rankValue.put("Extras", none);

				rank = String.valueOf(rankcount);
				rankValue.put("rank", rank);

				String resultlistxpath = resultlistxpaths.concat("["+rank+"]");
				//tariif name
				String tariffNameXpath = resultlistxpath+"//span[@data-testid=\"tariff-name\"]";
				tariffName = action.findelementsText(tariffNameXpath);		
				rankValue.put("tariffName", tariffName);





				String featuresList [] =  result.getText().split("\\r?\\n");
				rankValue.put("earlyExitFee", featuresList[1]);

				//contract type and contract term

				rankValue.put("contractType", featuresList[2]);
				rankValue.put("contractTerm", featuresList[2]);

				//check if the tariff is green or not
				if(featuresList.toString().toLowerCase().contains("green")){
					isGreen = "True";
				} 		
				rankValue.put("isGreen", isGreen);

				//monthly debit
				String monthlyDirectDebitXpath =resultlistxpath+"//span[@class=\"price\"]";
				monthlyDirectDebit = action.findelementsText(monthlyDirectDebitXpath);
				rankValue.put("monthlyDirectDebit", monthlyDirectDebit);

				//payment type
				rankValue.put("paymentMethod", paymentMethod);

				//savePerYear
				String savePerYearXpath = resultlistxpath+"//span[@class=\"sub\"]";
				savePerYear = action.findelementsText(savePerYearXpath);
				if(savePerYear.contains("increase"))
				{
					isSaving=false;	
					savePerYear = "-".concat(savePerYear);
				}
				rankValue.put("savePerYear", savePerYear);
				rankValue.put("isSaving", isSaving);



				String planDetailXpath =resultlistxpath+"//button[@data-testid='open-plan-details-button']";
				WebElement planDetailsButton = driver.findElement(By.xpath(planDetailXpath));
				action.clickWithjavascriptattempt(planDetailsButton);
				action.wait(2);

				//supplier name 

				supplierName = headingNname.getText().split("-")[0];
				rankValue.put("supplierName", supplierName);

				//personal projection
				personalProjection = estimatedannualcost.getText();
				rankValue.put("personalProjection", personalProjection);

				action.clickWithjavascriptattempt(closepopup);
				action.wait(1);

				//put in main hashmap
				super_getallthedetails.put("Rank_"+rank,rankValue);		
				System.out.println("completed for rank :: "+rank);
				rankcount++;
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}

		return super_getallthedetails;

	}
}
