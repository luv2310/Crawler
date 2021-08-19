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

public class CompareTheMarketPage  {

	TestBase tb = new TestBase();

	WebDriver driver = tb.initialization("comparethemarket");
	TestUtil action = new TestUtil();

	// accept cookies
	@FindBy(xpath = "//button[@class=\"cookie-consent__cta cookie-consent__cta--dsc btn-accept-all\"]")
	WebElement acceptcoockies;

	// Page objects

	@FindBy(id = "EnergyComparison_YourSupplier_YourCurrentSupplier_Postcode")
	WebElement postcode;

	@FindBy(id = "YourCurrentSupplier_FindMyAddress")
	WebElement postcodefindaddress;

	@FindBy(id = "EnergyComparison_YourSupplier_YourCurrentSupplier_SelectAddress")
	WebElement selectaddress;


	@FindBy(id="EnergyComparison_YourSupplier_YourCurrentSupplier_CurrentEnergySupplier_Other")
	WebElement selectenergysupplier;

	@FindBy(id = "EnergyComparison_YourSupplier_YourCurrentSupplier_CurrentElectricitySupplier_Other")
	WebElement selectelectricitysupplier;

	@FindBy(id = "EnergyComparison_YourSupplier_YourCurrentSupplier_CurrentGasSupplier_Other")
	WebElement selectgassupplier;

	@FindBy(id = "EnergyComparison_YourSupplier_Next")
	WebElement firstPageNext;

	@FindBy(id="EnergyComparison_YourSupplier_YourCurrentSupplier_DoYouHaveYourEnergyBill_NoIDontHaveMyBill")
	WebElement knowenergyfalse;


	@FindBy(xpath="//label//input[@name='DoYouHaveYourEnergyBill'][@value='true']")
	WebElement knowenergytrue;

	@FindBy(id="EnergyComparison_YourSupplier_YourCurrentSupplier_WhatWouldYouLikeToCompare_Gas")
	WebElement gasenergy;

	@FindBy(id="EnergyComparison_YourSupplier_YourCurrentSupplier_WhatWouldYouLikeToCompare_GasElectricity")
	WebElement dualenergy;

	@FindBy(id="EnergyComparison_YourSupplier_YourCurrentSupplier_WhatWouldYouLikeToCompare_Electricity")
	WebElement electricityenergy;

	@FindBy(id="EnergyComparison_YourSupplier_YourCurrentSupplier_IsYourGasAndElectricityFromSameSupplier_yes")
	WebElement sameenergysupplieryes;

	@FindBy(id="EnergyComparison_YourSupplier_YourCurrentSupplier_IsYourGasAndElectricityFromSameSupplier_no")
	WebElement sameenergysupplierno;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourElectricity_WhatElectricityTariffAreYouOn_1785")
	WebElement electricitytariffvalue;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourGas_WhatGasTariffAreYouOn_1785")
	WebElement gastariffvalue; 

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourElectricity_DoYouHaveAnEconomy7Meter_yes")
	WebElement economy7yes;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourElectricity_DoYouHaveAnEconomy7Meter_no")
	WebElement economy7no;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourElectricity_WhatIsYourCurrentElectricityUsage_Unit_Kwh")
	WebElement currentElectricityUsageKWH;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourGas_WhatIsYourCurrentGasUsage_Unit_Kwh")
	WebElement currentGasUsageKWH;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourElectricity_WhatIsYourCurrentElectricityUsage_YourCurrentUsageQuestionKwh")
	WebElement currentElectricityUsageday_1;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourElectricity_DayTimeElectricityUsed_YourCurrentUsageQuestionKwh")
	WebElement currentElectricityUsageday;

	@FindBy(id=	"EnergyComparison_HaveBill_YourEnergy_YourGas_WhatIsYourCurrentGasUsage_YourCurrentUsageQuestionKwh")
	WebElement currentgasUsage;

	@FindBy(id="EnergyComparison_HaveBill_YourEnergy_YourElectricity_NightTimeElectricityUsed_YourCurrentUsageQuestionKwh")
	WebElement currentElectricityUsagenight;

	@FindBy(id=	"EnergyComparison_HaveBill_YourEnergy_YourElectricity_DayTimeElectricityUsed_Kwh_Period")
	WebElement currentElectricityUsageperiod;

	@FindBy(id=	"EnergyComparison_HaveBill_YourEnergy_YourGas_WhatIsYourCurrentGasUsage_Kwh_Period")
	WebElement currentGasUsageperiod; 

	@FindBy(id=	"EnergyComparison_HaveBill_YourEnergy_YourElectricity_WhatIsYourCurrentElectricityUsage_Kwh_Period")
	WebElement currentElectricityUsageperiod_1;

	@FindBy(xpath = "//span[@class=\"Button__icon Button__icon--right\"]")
	WebElement nextButtonCommon;

	@FindBy(id="EnergyComparison_YourDetails_YourPreferences_WhatTariffAreYouInterestedIn_allTariffs")
	WebElement allTariifs;


	@FindBy(id="EnergyComparison_YourDetails_YourContactDetails_LetUsKeepYouUpToDate_doNotContact")
	WebElement doNotContact;


	@FindBy(id="EnergyComparison_YourDetails_YourContactDetails_WhatIsYourEmailAddress")
	WebElement enterEmail;



	// initialize the Page objects
	public CompareTheMarketPage() {
		PageFactory.initElements(driver, this);

	}

	public void CompareTheMarketJourney(
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
			boolean isEconomy7)
	{
		System.out.println("requestId: "+requestId+" psotcode: "+postCode+", partial adress: "+partialAddress+
				", supplier name: "+supplierName+", payment method: "+paymentMethod+", has gas: "+hasGas+
				",is dual: "+isDualFuel+". is Economy: "+isEconomy7);

		//First page 
		firstPageValidation(postCode, partialAddress, supplierName, isDualFuel, hasGas);

		//Second page
		secondPageValidation(hasGas, isEconomy7, electricitySpendFrequency, paymentMethod, gasusage, eleusage, nightPercentage);

		//Third Page
		if(hasGas)
		{
			thirdPage(gasSpendFrequency, gasusage);
		}
		//fourth Page
		fourthPage(paymentMethod);


	}

	public void firstPageValidation	(String postCode, String partialAddress, String supplierName, boolean isDualFuel,
			boolean hasGas)	
	{		
		try {
			acceptCookieBanner();
			action.wait(1);

			insertAddress(postCode,partialAddress);
			action.wait(1);
			knowenergybill();
			action.wait(1);

			compareWhat(isDualFuel,hasGas);
			action.wait(1);

			selectSupplierName(supplierName, isDualFuel, hasGas);

			action.wait(2);
			action.clickWithjavascriptattempt(firstPageNext);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void secondPageValidation(boolean hasGas, boolean isEconomy7,String electricitySpendFrequency, String paymentMethod, String gasusage, String eleusage, String nightPercentage)
	{		
		try {

			action.clickWithjavascriptattempt(electricitytariffvalue);
			if(isEconomy7)
			{
				action.clickWithjavascriptattempt(economy7yes);
			}
			else
			{
				action.clickWithjavascriptattempt(economy7no);	
			}		

			WebElement selectPaymentMethod =driver.findElement(By.xpath("//span[@class=\"ToggleContent\"][contains(text(),'"+paymentMethod+"')]"));			
			action.clickWithjavascriptattempt(selectPaymentMethod);

			action.clickWithjavascriptattempt(currentElectricityUsageKWH);

			//enter daytime electricity usage
			enterEnergyValuePage(isEconomy7, electricitySpendFrequency, eleusage, nightPercentage);
			action.wait(2);
			action.clickWithAttemptActions(nextButtonCommon);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void thirdPage(String gasSpendFrequency,String gasusage)
	{		
		try {
			action.clickWithjavascriptattempt(gastariffvalue);



			//			WebElement selectPaymentMethod =driver.findElement(By.xpath("//span[@class=\"ToggleContent\"][contains(text(),'"+paymentMethod+"')]"));			
			//			action.clickWithjavascriptattempt(selectPaymentMethod);

			action.clickWithjavascriptattempt(currentGasUsageKWH);

			String periodValue = gasSpendFrequency.replaceAll(" ", "").toLowerCase();
			switch (periodValue) 		
			{
			case "year":
				periodValue ="Annually" ;
				break;
			case "month":
				periodValue ="Monthly" ;
				break;
			case "quarter":
				periodValue ="Quarterly" ;
				break;											
			default:
				System.out.println("error occured setting default value to annually");
				periodValue ="Annually" ;
			}

			//plan price detail page	
			System.out.println("gas spent details page");		

			action.sendText(currentgasUsage,gasusage);
			action.selectDropDownByVisibleText(currentGasUsageperiod, periodValue);


			action.wait(2);
			action.clickWithAttemptActions(nextButtonCommon);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void fourthPage(String paymentMethod)
	{		
		try {
			//click on intrested in all tariff 
			action.clickWithjavascriptattempt(allTariifs);

			//select payment method
			WebElement selectPaymentMethod =driver.findElement(By.xpath("//span[@class=\"ToggleContent\"][contains(text(),'"+paymentMethod+"')]"));			
			action.clickWithjavascriptattempt(selectPaymentMethod);

			//enter email id
			String Email = action.randomEmailGenerator();
			action.sendText(enterEmail,Email);
			action.wait(1);

			action.clickWithjavascriptattempt(doNotContact);	
			action.clickWithAttemptActions(nextButtonCommon);


		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void howToPay()
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

	public void enterEnergyValuePage(boolean isEconomy7, String electricitySpendFrequency, 
			String eleusage, String nightPercentage )
	{		
		try {

			String periodValue = electricitySpendFrequency.replaceAll(" ", "").toLowerCase();

			switch (periodValue) 		
			{
			case "year":
				periodValue ="Annually" ;
				break;
			case "month":
				periodValue ="Monthly" ;
				break;
			case "quarter":
				periodValue ="Quarterly" ;
				break;											
			default:
				System.out.println("error occured setting default value to annually");
				periodValue ="Annually" ;
			}

			//plan price detail page	
			System.out.println("energy spent details page");				
			if(isEconomy7)					
			{	
				int overallElectricity = Integer.parseInt(eleusage);
				int percentRequire =(int)	(Float.parseFloat(nightPercentage)*100);	
				int nightUsage =  (overallElectricity*percentRequire)/100;
				action.sendText(currentElectricityUsageday, String.valueOf(overallElectricity-nightUsage));
				action.selectDropDownByVisibleText(currentElectricityUsageperiod, periodValue);
				action.sendText(currentElectricityUsagenight,String.valueOf(nightUsage));
			}
			else 					
			{			
				int overallElectricity = Integer.parseInt(eleusage);
				int percentRequire =(int) (Float.parseFloat(nightPercentage)*100);	
				int nightUsage =  (overallElectricity*percentRequire)/100;	
				action.sendText(currentElectricityUsageday_1, String.valueOf(overallElectricity-nightUsage));
				action.selectDropDownByVisibleText(currentElectricityUsageperiod_1, periodValue);
			}			 
			action.wait(2);

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void acceptCookieBanner()
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
			System.out.println("Enter Address");
			action.sendText(postcode,postCode);
			action.clickVerifiedElement(postcodefindaddress);
			action.wait(1);

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
					if(first.equalsIgnoreCase(Second))
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

	public void knowenergybill()
	{		
		try {

			action.clickWithjavascriptattempt(knowenergytrue);
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
				action.clickWithjavascriptattempt(sameenergysupplieryes);
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
					action.selectDropDownByVisibleText(selectenergysupplier,supplierName);
				}
				else if (!hasGas){
					action.selectDropDownByVisibleText(selectelectricitysupplier,supplierName);
				}

				else if(hasGas){
					action.selectDropDownByVisibleText(selectgassupplier,supplierName);
				}				
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
