package com.n11;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.junit.FixMethodOrder;
import org.openqa.selenium.By;
import junit.framework.Assert;
import org.junit.Test;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Cases extends Web_operation {
	
	static String favoriyeAlýnan;
	WebDriverWait wait= new WebDriverWait(driver,5);
	
	//Web sitesine girer.
	
	@Test
	public void a_enterSite() {
		
		Assert.assertEquals(driver.getTitle(), "n11.com - Alýþveriþin Uðurlu Adresi");
		System.out.println("Web Sitesi Açýldý");
	}
	
	//Kullanýcý giriþi sayfasýna girer ve o sayfada olduðunu onaylar.
	
	@Test
	public void b_loginPage() {
		
		withClassName("btnSignIn").click();
		Assert.assertEquals(driver.getTitle(),"Giriþ Yap - n11.com");
		System.out.println("Üye giriþ Sayfasý Açýldý");	
	}
	
	//Kullanýcý giriþi yapar.
	
	@Test
	public void c_login() {
		
		withId("email").sendKeys("n11test.selenium@gmail.com");
		withId("password").sendKeys("123456.qwe");
		withId("loginButton").click();
		System.out.println("Üye Griþi Baþarýyla Yapýldý");
	
	}
	
	//Samsung'u aratýr.
	
	@Test
	public void d_searchSamsung() {
	
		withId("searchData").sendKeys("samsung");
		withClassName("searchBtn").click();
		Assert.assertEquals(driver.getTitle(),"Samsung - n11.com");
		System.out.println("Samsung Arama Sayfasý Açýldý");	
	
	}
	
	//2. sayfaya girer ve sayfada olduðunu onaylar.
	
	@Test
	public void e_confirmPageNumber() {
		
		withXpath("//*[@class='pagination']/a[2]").click();
		Assert.assertEquals(driver.getTitle(),"Samsung - n11.com - 2");
		System.out.println("Samsung Arama 2.Sayfasý Açýldý");
		
	}
	
	//3.Ürünü Favorilere ekler
	
	@Test
	public void f_addFavoriteList() {
		
		wait.until(clickableByXpath("//*[@id=\"p-253577872\"]/div[2]/span[2]"));
		favoriyeAlýnan=withXpath("//li[3]/div/div/a/h3").getText();
		withXpath("//*[@id=\"p-253577872\"]/div[2]/span[2]").click();
		System.out.println(favoriyeAlýnan);
		
	}
	
	//Hesabým bölümünden favorilerim sekmesinin içine girer
	
	@Test
	public void g_openListPage()  {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", withXpath("//*[@class='myAccountMenu hOpenMenu']/div[1]/a[2]"));
		
	}
	
	//Favori listesini açar ve ürünün listede bulunduðunu onaylar.
	//Listedeki tüm elemanlarýn Textlerinin içinde favoriye eklediðimiz 
	//Ýsmi arar var ise onaylar.
	
	@Test
	public void h_confirmItem() {
		
		withXpath(".//div[@class='listItemWrap']//a//h4").click();
		
		List<WebElement> favoriListesi= driver.findElements(By.xpath("//*[@id=\"view\"]/ul/li"));
		
		for (WebElement i : favoriListesi) {
			if (i.getText().contains(favoriyeAlýnan)) {
		     System.out.println("Eklenen Ürün Listede");
		   }else {
			   System.out.println("Eklenen Ürün Listede yok");
		   }			   
		}
		
	}
	
	//Ürünü listeden çýkartýr ve
	//Listedeki tüm elemanlarýn Textlerinin içinde favoriye eklediðimiz 
	//Ýsim silmiþmi diye bakar.
	
	@Test
	public void i_itemDelete() {
		withXpath(".//span[@class='deleteProFromFavorites']").click();
		
		List<WebElement> favoriListesi= driver.findElements(By.xpath("//*[@id=\"view\"]/ul/li"));
		
		for (WebElement i : favoriListesi) {
			if (i.getText().contains(favoriyeAlýnan)) {
		     System.out.println("Eklenen Ürün Baþarýyla Silindi");
		   }else {
			   System.out.println("Eklenen Ürün Silinemedi");
		   }			   
		}
		
	}
	
	// Silinen ürünün listede olup olmadýðýný denetler.
	
	@Test
	public void j_confirmDeletion() {
		
		List<WebElement> favoriListesi= driver.findElements(By.xpath("//*[@id=\"view\"]/ul/li"));
		
		for (WebElement i : favoriListesi) {
			if (i.getText().contains(favoriyeAlýnan)) {
		     System.out.println("Eklenen Ürün Hala Listede");
		   }else {
			   System.out.println("Eklenen Ürün Listeden Kaldýrýlmýþ");
		   }			   
		}
	}	
}
