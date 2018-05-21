package robot;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Capitulos extends Inicio {

	public void RecuperarCapitulos(String Page, WebDriver driver)
			throws IOException, AWTException, InterruptedException {

		ArrayList<String> newList = new ArrayList<>();

		driver.findElement(By.xpath("//a[contains(text(), 'Tabla de Contenidos')]")).click();

		List<WebElement> lstRegistros = driver.findElements(By.className("story-parts"));
		for (WebElement registro : lstRegistros) {
			List<WebElement> lstCampos = registro.findElements(By.tagName("li"));
			for (WebElement campo : lstCampos) {
				List<WebElement> obtenerHref = campo.findElements(By.tagName("a"));
				for (WebElement atributo : obtenerHref) {
					newList.add(atributo.getAttribute("href").toString());
				}
			}
		}

		for (String Lista : newList) {

			String pageOriginal = Lista;
			driver.get(pageOriginal);
			ScrollearPagina(pageOriginal, driver);
			RecuperarTexto(driver.getCurrentUrl(), driver);

		}

	}
}
