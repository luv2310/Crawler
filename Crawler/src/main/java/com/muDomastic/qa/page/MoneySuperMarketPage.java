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
import org.openqa.selenium.Point;
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

public class MoneySuperMarketPage  {

	TestBase tb = new TestBase();

	WebDriver driver = tb.initialization("moneysupermarket");
	TestUtil action = new TestUtil();


	// Page objects


	@FindBy(xpath = "//a[contains(text(),'Cloudflare')]")
	WebElement vpnpopup;


	@FindBy(id = "banner-accept")
	WebElement banneraccept;

	@FindBy(id = "postcode")
	WebElement postcode;

	@FindBy(xpath = "//span[contains(text(),'Find address')]")
	WebElement postcodefindaddress;

	@FindBy(id = "current-property")
	WebElement selectaddress;

	@FindBy(xpath="//span[@class=\"form-input-radio-btn__label-title\"][contains(text(),'Gas & Electricity')]")
	WebElement dualenergy; 

	@FindBy(xpath = "//span[@class=\"form-input-radio-btn__label-title\"][contains(text(),'Just Electricity')]")
	WebElement electricityenergy;

	@FindBy(xpath = "//span[@class=\"form-input-radio-btn__label-title\"][contains(text(),'Just Gas')]")
	WebElement gasenergy;

	@FindBy(xpath = "//a[contains(text(),'Not the right supplier?')]")
	WebElement nottherightsupplier;

	@FindBy(xpath = "//label[@class=\"form-input-radio-btn supplier-select__other\"]//span[contains(text(),'Other')]")
	WebElement othersupplier;

	@FindBy(id="gas-suppliers")
	WebElement selectgassupplier;

	@FindBy(id="dual-suppliers")
	WebElement selectenergysupplier;

	@FindBy(id="electricity-suppliers")
	WebElement selectelectricitysupplier;

	@FindBy(xpath="//div[@data-dtl-id=\"payment-type-radio-group-container\"]//a[@class=\"form-edit-input__edit__link\"]")
	WebElement edittariffpaymentmethod;

	@FindBy(xpath="//div[@data-dtl-id=\"supplier-tariff-select-container\"]//select[@class=\"form-input-select__field checked custom-focus\"]")
	WebElement tariifvalue;

	@FindBy(xpath="//div[@class=\"usage-form\"]//span[contains(text(),'Yes')]")
	WebElement knowenergytrue;

	@FindBy(xpath="//div[@class=\"usage-form\"]//span[contains(text(),'No')]")
	WebElement knowenergyfalse;

	@FindBy(xpath="//div[@data-dtl-id=\"same-supplier-btn\"]//span[contains(text(),'Yes')]")
	WebElement sameenergysupplieryes;

	@FindBy(xpath="//div[@data-dtl-id=\"same-supplier-btn\"]//span[contains(text(),'No')]")
	WebElement sameenergysupplierno;

	@FindBy(xpath="//div[@data-dtl-id=\"usage-electricity-readonly\"]//a")
	WebElement editelectricityusage;

	@FindBy(id=	"electricity-consumption-period")
	WebElement currentElectricityUsageperiod;

	@FindBy(id="electricity-consumption-value")
	WebElement currentElectricityUsageKWH;

	@FindBy(xpath="//label[@class=\"form-input-range-slider economy-seven-slider__input-container\"]")
	WebElement economy7Slider; 

	@FindBy(xpath="//div[@data-dtl-id=\"usage-gas-readonly\"]//a")
	WebElement editgasusage;

	@FindBy(id="gas-consumption-value")
	WebElement currentGasUsageKWH;

	@FindBy(id=	"gas-consumption-period")
	WebElement currentGasUsageperiod; 


	@FindBy(xpath="//input[@data-dtl-id='email']")
	WebElement enterEmail;

	@FindBy(xpath="//input[@data-dtl-id='email-address-confirmation']")
	WebElement enterEmailconfirm;

	@FindBy(xpath="//label[@class=\"form-input-checkbox-btn email-capture__email-permission\"]")
	WebElement checkboxemail;

	@FindBy(xpath = "//span[@class=\"btn__text\"]")
	WebElement seeresults;

	@FindBy(xpath = "//ul//li[@class='results__item'][2]//div[@class='deal__basic-info']"
			+ "//div[@class=\"deal__title-and-rating\"]//div[@class=\"deal__rate-type\"]")
	WebElement contracttermload;


	// initialize the Page objects
	public MoneySuperMarketPage() {
		PageFactory.initElements(driver, this);

	}

	public void MoneySuperMarketJourney(
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
		secondPageValidation(hasGas, electricitySpendFrequency, paymentMethod, gasusage, eleusage, plan, isEconomy7,nightPercentage);

	}

	public void firstPageValidation	(String postCode, String partialAddress, String supplierName, boolean isDualFuel,
			boolean hasGas)	
	{		
		try {
			insertAddress(postCode,partialAddress);
			action.wait(2);
			compareWhat(isDualFuel,hasGas);
			action.wait(2);

			selectSupplierName(supplierName, isDualFuel, hasGas);
			action.wait(1);


		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void secondPageValidation(boolean hasGas,String electricitySpendFrequency, String paymentMethod, String gasusage, 
			String eleusage,String plan, boolean isEconomy7, String nightPercentage){		
		try {	
			//edit tariff payment method
			action.clickWithjavascriptattempt(edittariffpaymentmethod);
			WebElement selectPaymentMethod =driver.findElement(By.xpath("//span[@class=\"form-input-radio-btn__label-title\"][contains(text(),'"+paymentMethod+"')]"));			
			action.clickWithjavascriptattempt(selectPaymentMethod);
			action.wait(1);

			//	tariifvalue
			action.selectDropDownByVisibleText(	tariifvalue, plan);

			//enter daytime electricity usage
			enterEnergyValuePage(electricitySpendFrequency, eleusage, gasusage, hasGas, isEconomy7, nightPercentage);
			action.wait(1);

			enterEmailNConfirm();
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void enterEmailNConfirm()
	{		
		try {
			//click on intrested in all tariff 

			//enter email id
			String Email = action.randomEmailGenerator();
			action.sendText(enterEmail,Email);
			action.sendText(enterEmailconfirm,Email);
			action.wait(1);

			action.clickWithjavascriptattempt(checkboxemail);	
			action.wait(1);

			action.clickWithjavascriptattempt(seeresults);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public void enterEnergyValuePage(String electricitySpendFrequency, 
			String eleusage, String gasusage, boolean hasGas, boolean isEconomy7, String nightPercentage )
	{		
		try {

			action.clickWithjavascriptattempt(knowenergytrue);

			String periodValue = electricitySpendFrequency.replaceAll(" ", "").toLowerCase();

			switch (periodValue) 		
			{
			case "year":
				periodValue ="Year" ;
				break;
			case "month":
				periodValue ="Monthly" ;
				break;
			case "quarter":
				periodValue ="Quarterly" ;
				break;											
			default:
				System.out.println("error occured setting default value to annually");
				periodValue ="Year" ;
			}

			//plan price detail paGE
			System.out.println("energy spent details page");				

			action.clickWithjavascriptattempt(editelectricityusage);
			action.clearSendText(currentElectricityUsageKWH,eleusage);
			action.selectDropDownByVisibleText(currentElectricityUsageperiod, periodValue);

			if(isEconomy7)
			{
				int percentrequired = (100-((int) (Float.parseFloat(nightPercentage)*100)));
				action.moveSlider(economy7Slider, percentrequired);				
			}

			if(hasGas) {
				action.clickWithjavascriptattempt(editgasusage);

				action.clearSendText(currentGasUsageKWH,gasusage);
				action.selectDropDownByVisibleText(currentGasUsageperiod, periodValue);
			}

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
			action.sendText(postcode,postCode);
			action.clickVerifiedElement(postcodefindaddress);
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

			action.clickWithjavascriptattempt(sameenergysupplieryes);
			action.clickWithjavascriptattempt(nottherightsupplier);
			action.clickWithjavascriptattempt(othersupplier);

			if(supplierName.equalsIgnoreCase("Utility Warehouse"))
			{
				//update supplier name as the utility warehouse
				supplierName = "The Utility Warehouse"; 
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
			action.wait(2);

			int attemptleft = 0;
			int  totalattempt =20;

			String resultlistxpath = "//ul//li[@class=\"results__item\"]";
			String contractxpath = "//div[@class=\"deal__info-cell deal__info-cell--rate-type\"]//div[@class=\"deal__rate-type\"]";
	

			// the below line code is to make driver wait till the time it finds the result page
			driver.findElement(By.xpath(resultlistxpath));

			List<WebElement> resultlist = driver.findElements(By.xpath(resultlistxpath));
			List<WebElement> contractlist = driver.findElements(By.xpath(contractxpath));

			for(int i =0 ; i<resultlist.size() ; i++ )
			{
				HashMap<Object, Object> rankValue = new HashMap<>() ;

				String supplierName, contractType, personalProjection, isGreen ="None", tariffName, contractTerm,
						savePerYear,rank, paymentMethodvalue, monthlyDirectDebit,earlyExitFee,offers,none="None";

				boolean isSaving=true;
				//not getting anyvalue just saving as none to maintain the json structure
				rankValue.put("comparisonSiteExclusive", "False");
				rankValue.put("Extras", none);

				rank = String.valueOf(i+1);
				rankValue.put("rank", rank);

				//supplier name 
				String suppliernameXpath = "//ul//li[@class='results__item']["+rank+"]"
						+ "//div[@class='deal__provider-logo']//img";
				supplierName =driver.findElement(By.xpath(suppliernameXpath)).getAttribute("alt");
				rankValue.put("supplierName", supplierName);

				//earlyexit fee
				String earlyExitfeeXpath = "//ul//li[@class='results__item']["+rank+"]//div[@class='deal__info-cell deal__info-cell--exit-fee']"
						+ "//span[@class='deal__breakdown-value']";
				earlyExitFee =  action.findelementsText(earlyExitfeeXpath);
				rankValue.put("earlyExitFee", earlyExitFee);

				String savePerYearXpath = "//ul//li[@class='results__item']["+rank+"]//div[@class='deal__info-cell deal__info-cell--saving']"
						+ "//span[@class='deal__breakdown-value']";

				//savePerYear
				savePerYear = action.findelementsText(savePerYearXpath);
				if(savePerYear.contains("-"))
				{
					isSaving=false;	
				}
				rankValue.put("savePerYear", savePerYear);
				rankValue.put("isSaving", isSaving);

				//personal projection
				String personalprojectionrXpath = "//ul//li[@class='results__item']["+rank+"]//div[@class='deal__info-cell deal__info-cell--estimated-cost']"
						+ "//div[@class='deal__breakdown-item deal__breakdown-item--small']//span[@class='deal__breakdown-value']";
				personalProjection = action.findelementsText(personalprojectionrXpath);
				rankValue.put("personalProjection", personalProjection);

				//monthly debit
				String monthlyDirectDebitXpath = "//ul//li[@class='results__item']["+rank+"]//div[@class='deal__info-cell deal__info-cell--estimated-cost']"
						+ "//div[@class='deal__breakdown-item deal__breakdown-item--large']//span[@class='deal__breakdown-value']";
				monthlyDirectDebit = action.findelementsText(monthlyDirectDebitXpath);
				rankValue.put("monthlyDirectDebit", monthlyDirectDebit);

				//tariif name
				String tariffNameXpath = "//ul//li[@class='results__item']["+rank+"]//div[@class='deal__basic-info']"
						+ "//div[@class='deal__title']";
				tariffName = action.findelementsText(tariffNameXpath);		
				rankValue.put("tariffName", tariffName);

				//check if the tariff is green or not
				if(tariffName.toLowerCase().contains("green"))
				{
					isGreen = "True";
				} 				
				rankValue.put("isGreen", isGreen);

				//contract type and contract term
				String contractTypeXpath = "//ul//li[@class='results__item']["+rank+"]//div[@class='deal__basic-info']"
						+ "//div[@class=\"deal__title-and-rating\"]";
				
				contractType =contractlist.get(i).getText();
				
				rankValue.put("contractType", contractType);
				rankValue.put("contractTerm", contractType);

				//payment ype
				rankValue.put("paymentMethod", paymentMethod);

				//put in main hashmap
				super_getallthedetails.put("Rank_"+rank,rankValue);		
				System.out.println("completed for rank :: "+rank);
			}

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}

		return super_getallthedetails;

	}
}
