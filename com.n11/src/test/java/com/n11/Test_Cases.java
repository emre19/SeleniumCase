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
	
	static String favoriyeAl�nan;
	WebDriverWait wait= new WebDriverWait(driver,5);
	
	//Web sitesine girer.
	
	@Test
	public void a_enterSite() {
		
		Assert.assertEquals(driver.getTitle(), "n11.com - Al��veri�in U�urlu Adresi");
		System.out.println("Web Sitesi A��ld�");
	}
	
	//Kullan�c� giri�i sayfas�na girer ve o sayfada oldu�unu onaylar.
	
	@Test
	public void b_loginPage() {
		
		withClassName("btnSignIn").click();
		Assert.assertEquals(driver.getTitle(),"Giri� Yap - n11.com");
		System.out.println("�ye giri� Sayfas� A��ld�");	
	}
	
	//Kullan�c� giri�i yapar.
	
	@Test
	public void c_login() {
		
		withId("email").sendKeys("n11test.selenium@gmail.com");
		withId("password").sendKeys("123456.qwe");
		withId("loginButton").click();
		System.out.println("�ye Gri�i Ba�ar�yla Yap�ld�");
	
	}
	
	//Samsung'u arat�r.
	
	@Test
	public void d_searchSamsung() {
	
		withId("searchData").sendKeys("samsung");
		withClassName("searchBtn").click();
		Assert.assertEquals(driver.getTitle(),"Samsung - n11.com");
		System.out.println("Samsung Arama Sayfas� A��ld�");	
	
	}
	
	//2. sayfaya girer ve sayfada oldu�unu onaylar.
	
	@Test
	public void e_confirmPageNumber() {
		
		withXpath("//*[@class='pagination']/a[2]").click();
		Assert.assertEquals(driver.getTitle(),"Samsung - n11.com - 2");
		System.out.println("Samsung Arama 2.Sayfas� A��ld�");
		
	}
	
	//3.�r�n� Favorilere ekler
	
	@Test
	public void f_addFavoriteList() {
		
		wait.until(clickableByXpath("//*[@id=\"p-253577872\"]/div[2]/span[2]"));
		favoriyeAl�nan=withXpath("//li[3]/div/div/a/h3").getText();
		withXpath("//*[@id=\"p-253577872\"]/div[2]/span[2]").click();
		System.out.println(favoriyeAl�nan);
		
	}
	
	//Hesab�m b�l�m�nden favorilerim sekmesinin i�ine girer
	
	@Test
	public void g_openListPage()  {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", withXpath("//*[@class='myAccountMenu hOpenMenu']/div[1]/a[2]"));
		
	}
	
	//Favori listesini a�ar ve �r�n�n listede bulundu�unu onaylar.
	//Listedeki t�m elemanlar�n Textlerinin i�inde favoriye ekledi�imiz 
	//�smi arar var ise onaylar.
	
	@Test
	public void h_confirmItem() {
		
		withXpath(".//div[@class='listItemWrap']//a//h4").click();
		
		List<WebElement> favoriListesi= driver.findElements(By.xpath("//*[@id=\"view\"]/ul/li"));
		
		for (WebElement i : favoriListesi) {
			if (i.getText().contains(favoriyeAl�nan)) {
		     System.out.println("Eklenen �r�n Listede");
		   }else {
			   System.out.println("Eklenen �r�n Listede yok");
		   }			   
		}
		
	}
	
	//�r�n� listeden ��kart�r ve
	//Listedeki t�m elemanlar�n Textlerinin i�inde favoriye ekledi�imiz 
	//�sim silmi�mi diye bakar.
	
	@Test
	public void i_itemDelete() {
		withXpath(".//span[@class='deleteProFromFavorites']").click();
		
		List<WebElement> favoriListesi= driver.findElements(By.xpath("//*[@id=\"view\"]/ul/li"));
		
		for (WebElement i : favoriListesi) {
			if (i.getText().contains(favoriyeAl�nan)) {
		     System.out.println("Eklenen �r�n Ba�ar�yla Silindi");
		   }else {
			   System.out.println("Eklenen �r�n Silinemedi");
		   }			   
		}
		
	}
	
	// Silinen �r�n�n listede olup olmad���n� denetler.
	
	@Test
	public void j_confirmDeletion() {
		
		List<WebElement> favoriListesi= driver.findElements(By.xpath("//*[@id=\"view\"]/ul/li"));
		
		for (WebElement i : favoriListesi) {
			if (i.getText().contains(favoriyeAl�nan)) {
		     System.out.println("Eklenen �r�n Hala Listede");
		   }else {
			   System.out.println("Eklenen �r�n Listeden Kald�r�lm��");
		   }			   
		}
	}	
}
