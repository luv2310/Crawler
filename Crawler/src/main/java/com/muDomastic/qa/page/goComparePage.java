package com.muDomastic.qa.page;

import java.io.File;
//import java.awt.Desktop.Action;
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
import org.openqa.selenium.JavascriptExecutor;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muDomastic.qa.base.TestBase;
import com.muDomastic.qa.util.TestUtil;

public class goComparePage  {

	TestBase tb = new TestBase();

	WebDriver driver = tb.initialization("goCompare");
	TestUtil action = new TestUtil();
	// Page objects


	@FindBy(xpath = "//div[@class=\"g-product-hero__button-container\"]//span[@class=\"g-button__inner\"][contains(text(),'Compare prices')]")
	WebElement comparepricebutton;

	@FindBy(xpath = "//span[contains(text(),'Both gas and electricit')]")
	WebElement bothgasandelectricity;

	@FindBy(xpath = "//span[contains(text(),'Gas only')]")
	WebElement gasonly;

	@FindBy(xpath = "//span[contains(text(),'Online')]")
	WebElement online;

	@FindBy(xpath = "//span[contains(text(),'Electricity only')]")
	WebElement electricityonly;


	@FindBy(id = "AddressLookup_Postcode")
	WebElement postcode;

	@FindBy(xpath = "//span[@class=\"button__text\"][contains(text(),'Find address')]")
	WebElement findaddress;

	@FindBy(xpath = "//ul[@id=\"AddressLookup_AddressList\"]//li")
	WebElement selectaddress;


	@FindBy(xpath = "//label[@for='IsDualFuel_Yes']")
	WebElement samesuppliernameyes;


	@FindBy(xpath = "//span[contains(text(),'Show all suppliers')]")
	WebElement showallsuppliers;


	@FindBy(xpath = "//section//input[@class=\"input__native input__native--dark\"]")
	WebElement entersuppliername;

	@FindBy(xpath = "//div[@class='supplier-list__name']")
	WebElement supplierlist;

	@FindBy(xpath = "//label[@for=\"ElectricitySmartMeter_No\"]")
	WebElement electricitysmartmeterno;


	@FindBy(xpath = "//label[@for=\"GasSmartMeter_No\"]")
	WebElement gassmartmeterno;

	@FindBy(xpath = "//label[@for=\"DualFuelIsEconomy7_Yes\"]")
	WebElement economy7yes;


	@FindBy(xpath = "//label[@for=\"DualFuelIsEconomy7_No\"]")
	WebElement economy7no;


	@FindBy(xpath = "//label[@for=\"UsageTypeId_Yes\"]")
	WebElement knowusageyes;

	@FindBy(id="DualFuelTariff_EnabledDropdown")
	WebElement selecttariffname;

	@FindBy(id="GasUsage_EnergySpendType")
	WebElement iusegas;

	@FindBy(id="ElectricityUsage_EnergySpendType")
	WebElement iuseelectricity;

	@FindBy(id="GasUsage_SpendFrequency")
	WebElement gasfrequancy;

	@FindBy(xpath = "//input[@id='GasUsage_UsagekWh']")
	WebElement entergasusage;

	@FindBy(xpath = "//input[@id='ElectricityUsage_UsageDaykWh']")
	WebElement enterelectricitydayusage;

	@FindBy(xpath = "//input[@id=\"ElectricityUsage_UsagekWh\"]")
	WebElement enterelectricitydayusage_1;

	@FindBy(xpath = "//input[@id='ElectricityUsage_UsageNightkWh']")
	WebElement enterelectricitynightusage;


	@FindBy(xpath = "//span[contains(text(),'Accept and compare tariffs')]")
	WebElement acceptandcompare;


	@FindBy(xpath = "//span[@class=\"radio__text\"][contains(text(),'Yearly')]")
	WebElement yearlyradiobutton;



	@FindBy(xpath = "//div[@class=\"results-notification__text\"]//p")
	WebElement moreinfocontractterm;


	@FindBy(xpath = "//button[@class=\"slideout__close\"]")
	WebElement slideoutbutton;

	@FindBy(xpath = "//span[@class=\"slide-down__link-text\"]")
	WebElement comparisondetailvalue;

	@FindBy(xpath = "//h2[@class='h2'][contains(text(),'About your electricity tariff')]")
	WebElement electricitytariffheading;



	// initialize the Page objects
	public goComparePage() {
		PageFactory.initElements(driver, this);

	}

	public void goCompareJourney(
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
			boolean hasElectricity,
			boolean hasGas,
			boolean isDualFuel,
			boolean isEconomy7)
	{
		System.out.println("requestId: "+requestId+" psotcode: "+postCode+", partial adress: "+partialAddress+
				", supplier name: "+supplierName+", payment method: "+paymentMethod+", has gas: "+hasGas+
				",is dual: "+isDualFuel+". is Economy: "+isEconomy7);

		//First page 
		firstPageValidation(postCode, partialAddress, supplierName, isDualFuel, hasGas, hasElectricity, 
				paymentMethod, isEconomy7, plan, gasSpendFrequency, eleusage, nightPercentage, gasusage);
		//Second page

	}

	public void firstPageValidation	(String postCode, String partialAddress, String supplierName, boolean isDualFuel,
			boolean hasGas, boolean hasElectricity, String paymentMethod, boolean isEconomy7, String plan,
			String gasSpendFrequency,String eleusage,String  nightPercentage, String gasusage)	
	{		
		try {
			//click on compare the price button
			//action.verifyElementPresent(comparepricebutton);
			//action.clickWithAttemptActions(comparepricebutton);
			//
			//			//enter address
			enterAddress(partialAddress, postCode);
			//
			//			//What Energy Do You Use
			whatEnergyYouUse(hasGas, isDualFuel, hasElectricity);

			//enter supplier name
			enterSupplierName(supplierName);

			action.wait(2);
			//How do you pay for your gas and electricity  bills?
			WebElement payment = driver.findElement(By.xpath("//span[contains(text(),'"+paymentMethod+"')]"));
			action.clickWithjavascriptattempt(payment);

			//pay bill online
			action.clickWithjavascriptattempt(online);

			//electricity and gas smart meter
			action.clickWithjavascriptattempt(electricitysmartmeterno);
			action.clickWithjavascriptattempt(gassmartmeterno);

			//economy7 mete
			if(isEconomy7) {
				action.clickWithjavascriptattempt(economy7yes);
			}
			else{
				action.clickWithjavascriptattempt(economy7no);
			}

			//enter tariff name
			action.selectDropDownByVisibleText(selecttariffname, plan);

			//how much energy spend details
			enterEnergyValuePage( isEconomy7,  gasSpendFrequency, eleusage, nightPercentage, gasusage );

			action.verifyElementPresent(acceptandcompare);
			action.clickWithjavascriptattempt(acceptandcompare);


		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitchPage, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void enterAddress(String partialAddress,String postCode)
	{		
		try {
			// enter postcode
			boolean complicatedConditionNotDiscovered = true;

			action.verifyElementPresent(postcode);
			action.sendText(postcode, postCode);
			action.clickWithjavascriptattempt(findaddress);

			if(partialAddress.equalsIgnoreCase("22 Pilleys Lane, Boston, Lincolnshire, PE21 9RB"))
			{
				complicatedConditionNotDiscovered = false ;
			}
			List<WebElement> dropdownItems = driver.findElements(By.xpath("//ul[@id=\"AddressLookup_AddressList\"]//li"));

			if(complicatedConditionNotDiscovered) {
				for(WebElement dropdownItem : dropdownItems)
				{
					String first = dropdownItem.getText(), Second = partialAddress;
					first=first.replaceAll(",", " ");
					first=first.replace(".", " ");
					first=first.replaceAll(" ", "").toLowerCase();
					Second=Second.replaceAll(",", " ");
					Second=Second.replaceAll(" ", "").toLowerCase();				
					if(Second.contains(first))
					{
						action.clickWithjavascriptattempt(dropdownItem);
						break ;
					}
				}
			}else {
				for(WebElement dropdownItem : dropdownItems)
				{
					if(dropdownItem.getText().equalsIgnoreCase("22 Pilleys Lane, Boston, Lincolnshire"))
					{
						action.clickWithjavascriptattempt(dropdownItem);
						break;
					}
				}
			}


		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void whatEnergyYouUse(boolean hasGas, boolean isDualFuel, boolean hasElectricity)
	{		
		try {

			if(isDualFuel)
			{
				action.clickWithjavascriptattempt(bothgasandelectricity);				
				action.clickWithjavascriptattempt(samesuppliernameyes);
			}
			else if(hasGas)
			{
				action.clickWithjavascriptattempt(gasonly);
			}
			else if(hasElectricity)
			{
				action.clickWithjavascriptattempt(electricityonly);
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void enterSupplierName(String supplierName)
	{		
		try {
			//div[@class='supplier-list']//di
			String	supplierlistxpath = "//div[@class=\"supplier-list__name\"]";

			action.clickWithjavascriptattempt(showallsuppliers);
			action.sendText(entersuppliername, supplierName);
			action.clickWithjavascriptattempt(supplierlist);	
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}




	public void enterEnergyValuePage(boolean isEconomy7, String gasSpendFrequency, 
			String eleusage, String nightPercentage, String gasusage )
	{		
		try {

			//plan price detail page	
			System.out.println("energy spent details page");		

			action.clickWithjavascriptattempt(knowusageyes);		

			if(isEconomy7)					
			{	
				//gas usage
				action.selectDropDownByVisibleText(iusegas,"I use");
				action.sendText(entergasusage, gasusage);

				//electricity usage
				int overallElectricity = Integer.parseInt(eleusage);
				int percentRequire =(int)	(Float.parseFloat(nightPercentage)*100);	
				int nightUsage =  (overallElectricity*percentRequire)/100;
				action.selectDropDownByVisibleText(iuseelectricity,"I use");
				action.sendText(enterelectricitydayusage, String.valueOf(overallElectricity-nightUsage));
				action.sendText(enterelectricitynightusage,String.valueOf(nightUsage));
			}
			else 					
			{			
				//gas usage
				action.selectDropDownByVisibleText(iusegas,"I use");
				action.sendText(entergasusage, gasusage);

				//electricity usage
				int overallElectricity = Integer.parseInt(eleusage);
				int percentRequire =(int) (Float.parseFloat(nightPercentage)*100);	
				int nightUsage =  (overallElectricity*percentRequire)/100;	

				action.selectDropDownByVisibleText(iuseelectricity,"I use");
				action.sendText(enterelectricitydayusage_1, String.valueOf(overallElectricity-nightUsage));
			}			 
			action.wait(2);

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public HashMap<String, Map> storedataNew(boolean hasGas,boolean isEconomy7) 
	{

		HashMap<String, Map> super_getallthedetails = new HashMap<String, Map>();


		try {

			//store data
			System.out.println("storing data now");

			//select yearly tariff
			action.verifyElementPresent(yearlyradiobutton);
			action.clickWithjavascriptattempt(yearlyradiobutton);

			String resultlistxpath = "//div[@class=\"gc-results-item__overflow-wrapper\"]/..";
			String earlyExitFess = "//div[@class=\"results-option__fee\"]";
			String contracttype = "//span[@class=\"results-tariff__text\"]";
			String contractterm = "//div[@class=\"gc-results-item__overflow-wrapper\"]";
			String suppliernameXpath = "//div[@class=\"results-tariff__supplier\"]//span";
			String tariffNameXpath = "//div[@class=\"results-tariff__tariff\"]";
			String pricecapxpath = "//div[@class=\"gc-results-item__price\"]";
			String paymentMethodXpath = "//div[@class=\"gc-results-payment-type\"]//p";
			String moreinfoxpath = "//span[@class=\"button__text\"][contains(text(),'More info')]";

			// the below line code is to make driver wait till the time it finds the result page
			driver.findElement(By.xpath(resultlistxpath));


			/*there is no list in the result page so we fetching all the webelements with the same class and 
			inserting them in list*/

			List<WebElement> resultlist = driver.findElements(By.xpath(resultlistxpath));
			List<WebElement> earlyExitFesslist = driver.findElements(By.xpath(earlyExitFess));
			List<WebElement> suppliernamelist = driver.findElements(By.xpath(suppliernameXpath));
			List<WebElement> tariffNamelist = driver.findElements(By.xpath(tariffNameXpath));
			List<WebElement> contracttypelist = driver.findElements(By.xpath(contracttype));
			List<WebElement> contracttermlist = driver.findElements(By.xpath(contractterm));
			List<WebElement> pricecapxpathlist = driver.findElements(By.xpath(pricecapxpath));
			List<WebElement> paymentMethodlist = driver.findElements(By.xpath(paymentMethodXpath));
			List<WebElement> moreinfolist = driver.findElements(By.xpath(moreinfoxpath));

			//			String saa =	driver.getPageSource().toString();
			//			ObjectMapper mapper = new ObjectMapper();
			//			try {  
			//				// Writing to a file   
			//				mapper.writeValue(new File(System.getProperty("user.dir")+"\\sourcepage.txt"), saa);
			//			} catch (IOException e) {  
			//				e.printStackTrace();  
			//			}  
			//System.out.println(saa);

			for(int i =0 ; i<resultlist.size() ; i++ )

			{
				String supplierName, contractType, personalProjection, comparisonSiteExclusive = null,
						isGreen ="None", tariffName, contractTerm, rank, paymentMethodvalue,savePerYear,
						monthlyDirectDebit,earlyExitFee,offers,none="None";
				boolean compariosnflag=false, isSaving = true;

				HashMap<Object, Object> rankValue = new HashMap<>() ;
				WebElement moreinfo = moreinfolist.get(i);			

				rankValue.put("Extras", none); 

				if(resultlist.get(i).getAttribute("class").toString().toLowerCase().contains("exclusive"))
				{
					comparisonSiteExclusive = "true";
					compariosnflag = true;
				}
				else {
					comparisonSiteExclusive = "False";
				}
				rankValue.put("comparisonSiteExclusive",comparisonSiteExclusive);
				earlyExitFee =  earlyExitFesslist.get(i).getText();
				rankValue.put("earlyExitFee", earlyExitFee);

				supplierName = suppliernamelist.get(i).getText();
				rankValue.put("supplierName", supplierName);


				tariffName = tariffNamelist.get(i).getText();			
				rankValue.put("tariffName", tariffName);

				if(tariffName.toLowerCase().contains("green")){
					isGreen = "True";
				} 				
				else {
					isGreen = none;
				}
				rankValue.put("isGreen", isGreen);

				contractType = contracttypelist.get(i).getText();	
				rankValue.put("contractType", contractType);



				String[] pricecaparray =	pricecapxpathlist.get(i).getText().split("\\r?\\n");
				personalProjection = (pricecaparray[0].split(" "))[0];						
				rankValue.put("personalProjection", personalProjection);

				String [] monthlyDirectDebitarray = personalProjection.split(" ");
				monthlyDirectDebit = "£".concat(String.valueOf((Integer.parseInt(monthlyDirectDebitarray[0].replace("£", "0")))/12));

				rankValue.put("monthlyDirectDebit", monthlyDirectDebit);

				//check if the tariff is green or not

				rank = String.valueOf(i+1);
				rankValue.put("rank", rank);

				savePerYear = (pricecaparray[1].split(" "))[0];
				if(savePerYear.contains("+"))
				{
					isSaving = false ; 
					savePerYear = savePerYear.replace("+","-");
				}	
				rankValue.put("savePerYear", savePerYear);
				rankValue.put("isSaving", isSaving);

				paymentMethodvalue = paymentMethodlist.get(i).getText();
				rankValue.put("paymentMethod", paymentMethodvalue);

				if(contractType.toLowerCase().contains("variable")){
					contractTerm = "Variable";	
				}
				else	{
					action.clickWithjavascriptattempt(moreinfo);
					action.wait(3);
					action.verifyElementPresent(moreinfocontractterm);
					contractTerm = (moreinfocontractterm.getText().split("\\r?\\n"))[0];
					if(!compariosnflag)
					{
						action.clickWithjavascriptattempt(slideoutbutton);
						action.verifyElementPresent(yearlyradiobutton);
					}
				}			
				rankValue.put("contractTerm", contractTerm);

				if(compariosnflag)
				{
					action.clickWithjavascriptattempt(comparisondetailvalue);
					action.wait(1);
					comparisonSiteExclusive(rankValue, supplierName, isGreen, contractType, tariffName, contractTerm, paymentMethodvalue, hasGas, isEconomy7);
					action.clickWithjavascriptattempt(slideoutbutton);
					action.verifyElementPresent(yearlyradiobutton);
				}
				System.out.println("completed for rank :: " + rank);
				super_getallthedetails.put("Rank_"+rank,rankValue);						
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return super_getallthedetails;
	}
	public void comparisonSiteExclusive(HashMap<Object, Object> rankValue,
			String supplierName, String isGreen, String contractType,  String tariffName, String contractTerm,
			String paymentMethod,boolean hasGas,boolean isEconomy7)
	{		
		try {

			int setStandingChargeRank = 13, setExitFeeRank = 19, setUnitRateRank = 9, setNightUnitRate = 11,
					setAdditionRate =21;
			int setStandingChargeRank_gas = 11, setExitFeeRank_gas = 17, setUnitRateRank_gas = 9, setAdditionRate_gas =19;
			//verify user is able ti see electricity and gas tariff

		
			action.verifyElementPresent(electricitytariffheading);

			HashMap<String, String> electricityComparisondetails = new HashMap<String, String>();
			if(!isEconomy7)
			{
				setStandingChargeRank = 11; 
				setExitFeeRank = 17;
				setUnitRateRank = 9;
				setAdditionRate =19;
			}

			String 	electricitytablexpath = "//div[@class=\"tariff-table tariff-table--elec\"]//p";
			List<WebElement>  comparisonlistelectricityvalues = driver.findElements(By.xpath(electricitytablexpath));
			electricityComparisondetails.put("supplierName", supplierName);
			electricityComparisondetails.put("isGreen", isGreen);
			electricityComparisondetails.put("tariffType", contractType);
			electricityComparisondetails.put("tariffName",tariffName );
			electricityComparisondetails.put("paymentMethod", paymentMethod);
			electricityComparisondetails.put("tariffEndson", contractTerm);
			electricityComparisondetails.put("priceGuaranteedUntil", contractTerm);
			electricityComparisondetails.put("standingCharge", comparisonlistelectricityvalues.get(setStandingChargeRank).getText());
			electricityComparisondetails.put("exitfees", comparisonlistelectricityvalues.get(setExitFeeRank).getText());
			electricityComparisondetails.put("unitRate", comparisonlistelectricityvalues.get(setUnitRateRank).getText());
			if(isEconomy7) {
				electricityComparisondetails.put("nightUnitRate", comparisonlistelectricityvalues.get(setNightUnitRate).getText());	
			}
			electricityComparisondetails.put("discounts", "not applicable");
			electricityComparisondetails.put("additionalproductsorservices", comparisonlistelectricityvalues.get(setAdditionRate).getText());
			rankValue.put("electricity", electricityComparisondetails);

			if(hasGas) {
				HashMap<String, String> gasComparisondetails = new HashMap<String, String>();
				String gastablexpath = "//div[@class=\"tariff-table tariff-table--gas\"]//p";
				List<WebElement>  comparisonlistgasvalues = driver.findElements(By.xpath(gastablexpath));
				gasComparisondetails.put("supplierName", supplierName);
				gasComparisondetails.put("isGreen", isGreen);
				gasComparisondetails.put("tariffType", contractType);
				gasComparisondetails.put("tariffName",tariffName );
				gasComparisondetails.put("paymentMethod", paymentMethod);
				gasComparisondetails.put("tariffEndson", contractTerm);
				gasComparisondetails.put("priceGuaranteedUntil", contractTerm);
				gasComparisondetails.put("standingCharge",  comparisonlistgasvalues.get(setStandingChargeRank_gas).getText());
				gasComparisondetails.put("exitfees", comparisonlistgasvalues.get(setExitFeeRank_gas).getText());
				gasComparisondetails.put("unitRate", comparisonlistgasvalues.get(setUnitRateRank_gas).getText());
				gasComparisondetails.put("discounts", "not applicable");
				gasComparisondetails.put("additionalproductsorservices",comparisonlistgasvalues.get(setAdditionRate_gas).getText());
				rankValue.put("gas", gasComparisondetails);
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


}
