package robot;

import org.testng.annotations.Test;
import java.util.Scanner;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import java.awt.AWTException;
import java.io.IOException;

public class Pagina extends Capitulos {

	String Page;

	public String getPage() {
		return Page;
	}

	public void setPage(String page) {
		Page = page;
	}

	@BeforeTest
	public void f() {

		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a page ");
		String n = reader.nextLine();
		setPage(n);
		reader.close();
	}

	@Test(priority = 1)
	public void Enter() throws IOException, InterruptedException, AWTException {

		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");

		Capitulos prueba = new Capitulos();
		driver = new ChromeDriver();
		driver.get(Page);
		Thread.sleep(6000);
		prueba.RecuperarCapitulos(Page, driver);
		prueba.cerrarPagina(driver);

	}

}
