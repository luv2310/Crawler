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

public class EnergyLinxPage  {

	TestBase tb = new TestBase();

	WebDriver driver = tb.initialization("energylinx");
	TestUtil action = new TestUtil();

	// accept cookies
	@FindBy(xpath = "//span[@class=\"cookieBar-button\"]")
	WebElement acceptcoockies;
	
	// Page objects

	@FindBy(name = "values[postcode]")
	WebElement postcode;

	@FindBy(xpath="//span[@class=\"label-text\"][contains(text(),'Gas')]")
	WebElement gasenergy;

	@FindBy(xpath="//span[@class=\"label-text\"][contains(text(),'Gas and electricity')]")
	WebElement dualenergy;

	@FindBy(xpath="//span[@class=\"label-text\"][contains(text(),'Electricity')]")
	WebElement electricityenergy;

	@FindBy(xpath="//form[@class=\"form-domestic\"]//span[contains(text(),'Compare now')]")
	WebElement firstpagenext;


	@FindBy(id="supplyAddress")
	WebElement selectaddress;

	@FindBy(id = "supplierSingle")
	WebElement dualtariffyes;

	@FindBy(id = "supplierSeparate")
	WebElement dualtariffno;

	@FindBy(id="currentDualSupplier")
	WebElement selectenergysupplier;

	@FindBy(id = "currentElectricitySupplier")
	WebElement selectelectricitysupplier;

	@FindBy(id = "currentGasSupplier")
	WebElement selectgassupplier;

	@FindBy(id="currentDualTariff")
	WebElement energytariffvalue;

	@FindBy(id="currentElectricityTariff")
	WebElement electricitytariffvalue;

	@FindBy(id="currentGasTariff")
	WebElement gastariffvalue; 

	@FindBy(xpath = "//input[@id='CurrentPayElectricityMonthlyNew'] | //input[@id='CurrentPayElectricityMonthlySingle']")
	WebElement monthlydebitpaymentmethodelec;

	@FindBy(xpath="//input[@id='electricityEconomy_7_meterYes'] | //input[@id='electricityEconomy_7_meterYesSingle']")
	WebElement economy7yes;

	@FindBy(xpath="//input[@id='electricityEconomy_7_meterNo'] | //input[@id='electricityEconomy_7_meterNoSingle']")
	WebElement economy7no;

	@FindBy(xpath="//div[@class=\"slider slider-horizontal\"]")
	WebElement economy7slider;


	@FindBy(xpath="//input[@id='SpendElecPoundsSingle'] | //input[@id='SpendElecPounds']")
	WebElement knowenergytrueelec;

	@FindBy(id="SpendElecDontKnowSingle")
	WebElement knowenergyfalseelec;

	@FindBy(xpath="//input[@class='form-control kwhsValueElec'] | //input[@class='form-control kwhsValue']")
	WebElement currentElectricityUsageKWH;

	@FindBy(xpath="//input[@id='CurrentPayGasMonthlySingle'] | //input[@id='CurrentPayGasMonthly']")
	WebElement monthlydebitpaymentmethodgas;

	@FindBy(xpath="//input[@id='SpendGasPounds'] | //input[@id='SpendGasPoundsSingle']")
	WebElement knowenergytruegas;

	@FindBy(xpath="//input[@id='SpendGasDontKnow'] | //input[@id='SpendGasDontKnowSingle']")
	WebElement knowenergyfalsegas;


	@FindBy(xpath="//input[@class='form-control kwhsValueGas'] | //input[@class='form-control kwhsValue']")
	WebElement currentGasUsageKWH;

	@FindBy(id="emailAddress")
	WebElement enterEmail;

	@FindBy(id = "btn-submit")
	WebElement seeresult;


	// initialize the Page objects
	public EnergyLinxPage() {
		PageFactory.initElements(driver, this);

	}

	public void energyLinxJourney(
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

		action.clickWithjavascriptattempt(acceptcoockies);
		//First page 
		firstPageValidation(postCode, partialAddress, supplierName, isDualFuel, hasGas, plan);
		System.out.println("");
		//Second page
		secondPageValidation(hasGas, isEconomy7, electricitySpendFrequency, paymentMethod, gasusage, eleusage, nightPercentage);

		//Third Page
		if(hasGas)
		{
			thirdPage(gasusage);
		}
		//fourth Page
		fourthPage(paymentMethod);


	}

	public void firstPageValidation	(String postCode, String partialAddress, String supplierName, boolean isDualFuel,
			boolean hasGas,String plan)	
	{		
		try {

			compareWhat(isDualFuel,hasGas);
			action.clickWithjavascriptattempt(firstpagenext);
			action.wait(1);

			insertAddress(postCode,partialAddress);
			action.wait(1);

			if(isDualFuel){
				action.clickWithjavascriptattempt(dualtariffyes);
			}
			else{
				action.clickWithjavascriptattempt(dualtariffno);
			}

			selectSupplierName(supplierName, isDualFuel, hasGas);
			action.wait(1);

			selectTariffValue(plan, isDualFuel, hasGas);


		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void secondPageValidation(boolean hasGas, boolean isEconomy7,String electricitySpendFrequency,
			String paymentMethod, String gasusage, String eleusage, String nightPercentage)
	{		
		try {


			if(isEconomy7){
				action.clickWithjavascriptattempt(economy7yes);
				int percentrequired = (100-((int) (Float.parseFloat(nightPercentage)*100)));
				action.moveSlider(economy7slider, percentrequired);		
				System.out.println();
			}
			else{
				action.clickWithjavascriptattempt(economy7no);	
			}		

			action.clickWithjavascriptattempt(monthlydebitpaymentmethodelec);

			action.clickWithjavascriptattempt(knowenergytrueelec);

			action.sendText(currentElectricityUsageKWH, eleusage);

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void thirdPage(String gasusage)
	{		
		try {

			action.clickWithjavascriptattempt(monthlydebitpaymentmethodgas);

			action.clickWithjavascriptattempt(knowenergytruegas);

			action.sendText(currentGasUsageKWH, gasusage);

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void fourthPage(String paymentMethod)
	{		
		try {
			//enter email id
			String Email = action.randomEmailGenerator();
			action.sendText(enterEmail,Email);
			action.wait(1);
			action.clickWithjavascriptattempt(seeresult);


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

	public void selectTariffValue(String plan,boolean isDual,boolean hasGas)
	{		
		try {			
			if(isDual) {
				action.selectDropDownByVisibleText(energytariffvalue,plan);
			}
			else if (!hasGas){
				action.selectDropDownByVisibleText(electricitytariffvalue,plan);
			}

			else if(hasGas){
				action.selectDropDownByVisibleText(gastariffvalue,plan);
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
			String resultlistxpath = "//div[@id=\"accordion\"]//div[@data-supplier-name]";


			// the below line code is to make driver wait till the time it finds the result page
			driver.findElement(By.xpath(resultlistxpath));

			List<WebElement> resultlist = driver.findElements(By.xpath(resultlistxpath));
			int rankcount = 1;
			for(int i =0 ; i<resultlist.size(); i++)
			{

				HashMap<Object, Object> rankValue = new HashMap<>() ;

				String supplierName, contractType, personalProjection, isGreen ="None", tariffName, contractTerm,
						savePerYear,rank, paymentMethodvalue, monthlyDirectDebit,earlyExitFee,offers,none="None";

				boolean isSaving=true;
				int listvalue= i+1;
				// not getting anyvalue just saving as none to maintain the json structure
				String suppliernameXpath = "//div[@id='accordion']//div[@data-supplier-name]["+listvalue+"]";

				String className = driver.findElement(By.xpath(suppliernameXpath)).getAttribute("class");

				if(className.contains("switch-to-true"))
				{
					rankValue.put("comparisonSiteExclusive", "False");
					rankValue.put("Extras", none);
					rankValue.put("rank", 	rankcount);


					//supplier name 
					supplierName =driver.findElement(By.xpath(suppliernameXpath)).getAttribute("data-supplier-name");
					rankValue.put("supplierName", supplierName);

					String personalprojectionrXpath=suppliernameXpath+"//div[@class='projectioncolumn hidden-320']";
					String personalProjectionvalues [] = action.findelementsText(personalprojectionrXpath).split("\\r?\\n");
					rankValue.put("personalProjection", personalProjectionvalues[0]);


					//	monthly debit and contract type
					String monthlyDirectDebitXpath = suppliernameXpath+"//div[@class=\"newcostcolumn\"]";
					monthlyDirectDebit = action.findelementsText(monthlyDirectDebitXpath);
					if(monthlyDirectDebit.toLowerCase().contains("fixed"))
					{
						String monthlyDirectDebitvalues[] = monthlyDirectDebit.split("\\r?\\n");
						rankValue.put("monthlyDirectDebit", monthlyDirectDebitvalues[0]);
						rankValue.put("contractType", monthlyDirectDebitvalues[1]);
						rankValue.put("contractTerm", monthlyDirectDebitvalues[1]);

					}else
					{
						rankValue.put("monthlyDirectDebit", "variable");
						rankValue.put("contractType", monthlyDirectDebit);
						rankValue.put("contractTerm", monthlyDirectDebit);
					}


					String savePerYearXpath = suppliernameXpath+"//div[@class=\"savingscolumn hidden-320\"]";
					savePerYear = action.findelementsText(savePerYearXpath);
					String savePerYearvalues[] = savePerYear.split("\\r?\\n");
					if(savePerYear.contains("extra")){
						isSaving=false;	
						savePerYear="-".concat(savePerYearvalues[1]);
					}else {
						savePerYear=savePerYearvalues[1];
					}
					rankValue.put("savePerYear", savePerYear);
					rankValue.put("isSaving", isSaving);

					//earlyexit fee
					String earlyExitfeeXpath = suppliernameXpath+"//div[@class=\"earlyterm\"]";
					earlyExitFee =  action.findelementsText(earlyExitfeeXpath);
					rankValue.put("earlyExitFee", earlyExitFee);					

					//tariif name
					String tariffNameXpath = suppliernameXpath+"//div[@class=\"tariff-name\"]";
					tariffName = action.findelementsText(tariffNameXpath);		
					rankValue.put("tariffName", tariffName);

					//check if the tariff is green or not
					if(tariffName.toLowerCase().contains("green"))
					{
						isGreen = "True";
					} 				
					rankValue.put("isGreen", isGreen);

					rankValue.put("paymentMethod", paymentMethod);

					//put in main hashmap
					super_getallthedetails.put("Rank_"+rankcount,rankValue);	
					System.out.println("completed for rank :: "+rankcount);
					rankcount++;
				}
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return super_getallthedetails;
	}
}
