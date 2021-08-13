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
		System.out.println("psotcode: "+postCode+", partial adress: "+partialAddress+
				", supplier name: "+supplierName+", payment method: "+paymentMethod+", has gas: "+hasGas+
				",is dual: "+isDualFuel+". is Economy: "+isEconomy7);

		//first page 
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
		action.clickVerifiedElement(firstPageNext);


		//second page




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

			action.clickVerifiedElement(knowenergytrue);
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
				action.clickVerifiedElement(dualenergy);
				action.clickVerifiedElement(sameenergysupplieryes);
			}

			else if (!hasGas){
				action.clickVerifiedElement(electricityenergy);
			}

			else if(hasGas){
				action.clickVerifiedElement(gasenergy);
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


	public void name()
	{		
		try {

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class USwitch Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public HashMap<String, Map> storedataNew(boolean hasGas,String paymentMethod) 
	{
		return null;

	}
}
