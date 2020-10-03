package automationtesting.tablas;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static automationtesting.Constantes.*;
import static org.junit.Assert.assertEquals;

public class TestTablas {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void validarCantidadFilasColumnasTabla(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_TABLAS);

        List columnas = driver.findElements(By.xpath("//table/thead/tr/th"));
        System.out.println("Cantidad de columnas " + columnas.size());

        List filas = driver.findElements(By.xpath("//table/tbody/tr"));
        System.out.println("Cantidad de filas " + filas.size());

        //assertEquals(VALIDAR_TEXTO_LOGIN, driver.findElement(By.xpath("//*[@id=\"page-36\"]/div/div[1]/div/p[1]")).getText());
    }

    @Test
    public void consultarValorTabla(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_TABLAS);

        WebElement tabla = driver.findElement(By.tagName("table"));

        WebElement fila = tabla.findElement(By.xpath("//tbody/tr[1]"));
        WebElement celdaNecesitada = tabla.findElement(By.xpath("//tbody/tr[2]/td[4]"));
        List contenedorEmpresas = tabla.findElements(By.xpath("//tbody/tr/td[1]"));
        for(int i=0; i<contenedorEmpresas.size(); i++){
            System.out.println("Empresa #"+(i+1) + " - " + tabla.findElement(By.xpath("//table/tbody/tr["+(i+1)+"]/td[1]")).getText());
        }

        System.out.println("Cantidad de empresas " + contenedorEmpresas.size());
        System.out.println("Datos de la fila 1 " + fila.getText());
        System.out.println("Celda necesitada " + celdaNecesitada.getText());

        Utilidades.esperar(2);
        //assertEquals(VALIDAR_TEXTO_LOGIN, driver.findElement(By.xpath("//*[@id=\"page-36\"]/div/div[1]/div/p[1]")).getText());
    }

    @After
    public void close(){
        driver.quit();
    }

}
