package robot;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Inicio {

	static WebDriver driver;
	static WebDriverWait wait;
	static ArrayList<String> obtenerId = new ArrayList<>();
	File file = new File("C:\\Users\\ntedesco\\Music\\FFic.txt");
	FileWriter fw;
	BufferedWriter bw;
	PrintWriter out;
	FileReader fr;
	BufferedReader br;
	String line = null;
	List<String> lines = new ArrayList<String>();

	@Before
	public void InPagina(String Pagina) {

		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");

		driver = new ChromeDriver();

	}

	public void ScrollearPagina(String Page, WebDriver robot) throws AWTException, InterruptedException, IOException {

		wait = new WebDriverWait(robot, 500);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		WebElement barraProgreso = driver.findElement(By.id("progressbar"));

		double progreso = Double.parseDouble(barraProgreso.getAttribute("style").substring(7, 10));

		while (progreso < 90) {
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			Thread.sleep(2000);
			barraProgreso = driver.findElement(By.id("progressbar"));
			progreso = Double.parseDouble(barraProgreso.getAttribute("style").substring(7, 10));

		}

	}

	public void RecuperarTexto(String PageFinal, WebDriver driver) throws IOException {
		List<WebElement> pagmedio = driver.findElements(By.xpath("//div[contains(@class, 'page highlighter')]"));
		for (WebElement num : pagmedio) {
			List<WebElement> textoLectura = num.findElements(By.xpath("//div[1]/pre/p"));
			for (WebElement attr : textoLectura) {
				String paginas = attr.getText();
				if (paginas.length() > 0) {
					writingFile(paginas);
				}
			}
			break;
		}

	}

	public void writingFile(String texto) {

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			out.println(texto);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cerrarPagina(WebDriver driver) {
		driver.close();
		driver.quit();
	}

}
