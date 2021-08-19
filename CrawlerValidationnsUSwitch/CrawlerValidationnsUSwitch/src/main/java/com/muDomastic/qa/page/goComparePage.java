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







	//ul[@id="AddressLookup_AddressList"]//li
	//span[@class="button__text"][contains(text(),'Find address')]



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
			action.verifyElementPresent(comparepricebutton);
			action.clickWithAttemptActions(comparepricebutton);
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

			//economy7 meter
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

	public void thirdPage(String gasSpendFrequency,String gasusage)
	{		
		try {

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void fourthPage(String paymentMethod)
	{		
		try {

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void enterAddress(String partialAddress,String postCode)
	{		
		try {
			// enter postcode
			action.verifyElementPresent(postcode);
			action.sendText(postcode, postCode);
			action.clickWithjavascriptattempt(findaddress);

			List<WebElement> dropdownItems = driver.findElements(By.xpath("//ul[@id=\"AddressLookup_AddressList\"]//li"));
			for(WebElement dropdownItem : dropdownItems)
			{
				String first = dropdownItem.getText(), Second = partialAddress;
				first=first.replaceAll(",", " ").toLowerCase();
				first=first.replaceAll(" ", "");
				Second=Second.replaceAll(",", " ").toLowerCase();
				Second=Second.replaceAll(" ", "");				
				if(Second.contains(first))
				{
					action.clickWithjavascriptattempt(dropdownItem);
					break ;
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
				action.clickVerifiedElement(bothgasandelectricity);				
				action.clickVerifiedElement(samesuppliernameyes);
			}
			else if(hasGas)
			{
				action.clickVerifiedElement(gasonly);
			}
			else if(hasElectricity)
			{
				action.clickVerifiedElement(electricityonly);
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


	public void howToPay()
	{		
		try {

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

	public HashMap<String, Map> storedataNew(boolean hasGas,String paymentMethod) 
	{

		HashMap<String, Map> super_getallthedetails = new HashMap<String, Map>();


		try {

			String resultlistxpath = "//div[@class=\"Table_Table__2DxTv\"]//div[@class=\"TariffProperties_TariffPropertiesWrapper__3SF5G\"]";
			String suppliernameXpath = "//div//img[@class=\"BrandLogo__image\"]";
			String tariffNameXpath = "//p[@class=\"TariffSummary_TariffName__1gGda\"]";
			String ExtrasXpath = "//div[@class=\"TableRow_Wrapper__2Zact\"]";

			// the below line code is to make driver wait till the time it finds the result page
			driver.findElement(By.xpath(resultlistxpath));

			/*there is no list in the result page so we fetching all the webelements with the same class and 
			inserting them in list*/

			List<WebElement> resultlist = driver.findElements(By.xpath(resultlistxpath));
			List<WebElement> susuppliernameList = driver.findElements(By.xpath(suppliernameXpath));
			List<WebElement> tariffNameList = driver.findElements(By.xpath(tariffNameXpath));
			List<WebElement> ExtrasList = driver.findElements(By.xpath(ExtrasXpath));
			for(int i =0 ; i<resultlist.size() ; i++ )
			{
				HashMap<Object, Object> rankValue = new HashMap<>() ;

				String supplierName, contractType, personalProjection, isGreen ="None", tariffName, contractTerm, rank, paymentMethodvalue, monthlyDirectDebit,earlyExitFee,offers,none="None";

				//not getting anyvalue just saving as none to maintain the json structure
				rankValue.put("comparisonSiteExclusive", none);
				rankValue.put("Extras", none);
				rankValue.put("savePerYear", none);
				rankValue.put("isSaving", false);




				//supplier name 
				supplierName = susuppliernameList.get(i).getAttribute("alt");	
				rankValue.put("supplierName", supplierName);

				//tariif name
				tariffName = tariffNameList.get(i).getText();			
				rankValue.put("tariffName", tariffName);

				//check if the tariff is green or not
				offers = ExtrasList.get(i).getText();		
				if(offers.toLowerCase().contains("100% renewable electricity"))
				{
					isGreen = "True";
				} 				
				rankValue.put("isGreen", isGreen);

				//inout upper header main data.
				String[] resultarray=	resultlist.get(i).getText().split("\\r?\\n");

				contractType = resultarray[7];
				rankValue.put("contractType", contractType);

				personalProjection = resultarray[4];
				rankValue.put("personalProjection", personalProjection);

				contractTerm = resultarray[7];
				rankValue.put("contractTerm", contractTerm);

				rank = String.valueOf(i+1);
				rankValue.put("rank", rank);

				paymentMethodvalue = resultarray[8]; 
				rankValue.put("paymentMethod", paymentMethodvalue);

				monthlyDirectDebit = resultarray[2]; 
				rankValue.put("monthlyDirectDebit", monthlyDirectDebit);

				earlyExitFee =  resultarray[6]; 
				rankValue.put("earlyExitFee", earlyExitFee);



				//put in main hashmap
				super_getallthedetails.put("Rank_"+rank,rankValue);						
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}

		return super_getallthedetails;

	}
}
