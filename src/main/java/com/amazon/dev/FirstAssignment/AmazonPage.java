package com.amazon.dev.FirstAssignment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonPage extends BasePage {

	public By arrowBtn = By.xpath("//span[@id='nav-search-label-id']");
	public By selectSearchBtn = By.xpath("//select[@id='searchDropdownBox']");
	public By enterValue = By.xpath("//input[@id='twotabsearchtextbox']");
	public By submitBtnLocator = By.xpath("//input[contains(@value,'Go')]");
	public By sortBtn = By.xpath("//select[contains(@name,'s')]");
	public By sortByFeature = By.xpath("//select[contains(@id,'s-result-so')]//option");
	public By lowToHighPrice = By.xpath("//a[contains(text(),'Price: L')]");
	public By addtoCardBtn = By.xpath("//input[@name='submit.add-to-cart']");
	public By ListOfMobile = By.xpath("//a//span[contains(@class,'a-price-w')and contains(text(),'1,')]");
	public By cartcount = By.xpath("//div[@id='nav-cart-text-container']//span[contains(text(),'Cart')]");

	public AmazonPage() {

		PageFactory.initElements(driver, this);
	}

	public void switchToParentWindow(String parentWindowId) {

		driver.switchTo().window(parentWindowId);
		driver.findElement(cartcount).click();
	}

	public ArrayList<String> getMobileList(String parentWindowId) {

		ArrayList<String> mobilePriceList = new ArrayList<String>();
		List<WebElement> mobilePrice = driver.findElements(ListOfMobile);
		
		for (int list = 0; list < mobilePrice.size() - 1; list++) {
			if (mobilePrice.get(list).isDisplayed()) {
				mobilePriceList.add(mobilePrice.get(list).getText());
				mobilePrice.get(list).click();

			}
			driver.switchTo().window(parentWindowId);
		}
		return mobilePriceList;
	}

	public void switchToWindowAndAddtoCard(ArrayList<String> hList,String parentWindowId) {
		
		for (String e : hList)
			driver.switchTo().window(e);
		driver.findElement(addtoCardBtn).click();
	}

	public void closeAllChildWindows(ArrayList<String> hList, String parentWindowID) {
		
		for (String e : hList) {
			if (!e.equalsIgnoreCase(parentWindowID)) {
				driver.close();
			}
		}
	}

	public void selectOptionByVisibleText(WebElement element, String visibleText) {

		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Select dropdown = new Select(element);

		dropdown.selectByVisibleText(visibleText);
	}

	public ArrayList<Integer> StringListToIntegerList(ArrayList<String> mobilePriceList) {

		ArrayList<Integer> mobilePrice = new ArrayList<Integer>();

		for (String e : mobilePriceList) {
			String integerNum = e.replace(",", "");
			Integer listedPrice = Integer.valueOf(integerNum);
			mobilePrice.add(listedPrice);
		}
		return mobilePrice;
	}
}
