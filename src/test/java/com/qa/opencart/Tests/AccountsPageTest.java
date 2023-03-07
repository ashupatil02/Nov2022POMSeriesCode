package com.qa.opencart.Tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest  extends BaseTest {
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void accPageHeadersTest() {
		List<String> actualAccPageHeadersList = accPage.getAccountsPageHeadersList();
		System.out.println("Acc Page Headers List: " + actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	@Test
	public void accPageHeadersValueTest() {
		List<String> actualAccPageHeadersList = accPage.getAccountsPageHeadersList();
		System.out.println("Actual acc Page Headers List: " + actualAccPageHeadersList);
		System.out.println("Expected acc Page Headers List: " +  AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	//	Assert.assertEquals(actualAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
		Assert.assertEquals(actualAccPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchProductCountTest() {
		searchPage = accPage.performSearch("Macbook");
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);
	}
	@Test
	public void searchProductTest() {
		searchPage = accPage.performSearch("Macbook");
		if((searchPage.getSearchProductsCount()>0)) {
			searchPage.selectProduct("MacBook Pro");
			productInfoPage = searchPage.selectProduct("MacBook Pro");
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader,"MacBook Pro");
		}
		}
		
		@DataProvider
		public Object[][] getProductData() {
			return new Object[][] {
				{"Macbook"},
				{"iMac"},
				{"Apple"},
				{"Samsung"},
				{"Naveen"}
			};
		}
		
		@Test(dataProvider = "getProductData")
		public void searchProductCountTest(String searchKey) {
			searchPage = accPage.performSearch(searchKey);
			Assert.assertTrue(searchPage.getSearchProductsCount()>0);
		}
		
		public Object[][] getProductTestData() {
			return new Object[][] {
				{"Macbook", "MacBook Pro"},
				{"Macbook", "MacBook Air"},
				{"iMac", "iMac"},
				{"Apple", "Apple Cinema 30\""},
				{"Samsung", "Samsung SyncMaster 941BW"},
				{"Samsung", "Samsung Galaxy Tab 10.1"},
			};
			
			
			
			
		
	}
}
