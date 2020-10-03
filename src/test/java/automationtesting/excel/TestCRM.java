package automationtesting.excel;

import automationtesting.Utilidades;
import junit.framework.Assert;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static automationtesting.Constantes.*;
import static automationtesting.Utilidades.esperar;
import static org.junit.Assert.assertEquals;
import org.junit.Assert.*;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TestCRM {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void registrarse() throws IOException {
        String rutaArchivo = "src/test/resources/datadriven/datosRegistro.xlsx";
        FileInputStream archivo = new FileInputStream(rutaArchivo);
        XSSFWorkbook libro = new XSSFWorkbook(archivo); //Toma el libro del archivo
        XSSFSheet hoja = libro.getSheetAt(0); //Toma la primera hoja teniendo en cuenta que es a nivel de vector (0 -> hoja1)
        Row fila = hoja.getRow(0); //Parte desde la primera fila en el archivo de excel (0)

        driver.manage().window().maximize();
        driver.get(URL_REGISTRO_CRM);
        Utilidades.esperar(30, driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.findElement(By.id("namefield")).click();
        driver.findElement(By.id("namefield")).sendKeys(String.valueOf(fila.getCell(0)));
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys(""+fila.getCell(1));
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys(""+fila.getCell(2));

        List<WebElement> contenedorCodigoPais = driver.findElements(By.xpath("//select[@id='country_code']/option"));

        for(int i=0; i<contenedorCodigoPais.size(); i++){
            if(contenedorCodigoPais.get(i).getText().contains(String.valueOf(fila.getCell(3)))){
                contenedorCodigoPais.get(i).click();
                break;
            }
        }

        //System.out.println("Numero de celular " + String.valueOf(fila.getCell(4)));
        driver.findElement(By.id("mobile")).click();
        driver.findElement(By.id("mobile")).sendKeys(String.valueOf(fila.getCell(4)));

        List<WebElement> contenedorCiudad = driver.findElements(By.xpath("//select[@name='country_state']/option"));

        for(int i=0; i<contenedorCiudad.size(); i++){
            if(contenedorCiudad.get(i).getText().equals(String.valueOf(fila.getCell(5)))){
                contenedorCiudad.get(i).click();
                break;
            }
        }

        driver.findElement(By.xpath("//label[@for='tos']/span")).click();

    //    driver.findElement(By.id("signupbtn")).click();

        wait.until(visibilityOfElementLocated(By.xpath("//div[@class='crm-font-regular headerCol mT20 mB30 headerFS']")));

        String cadena = driver.findElement(By.xpath("//div[@class='crm-font-regular headerCol mT20 mB30 headerFS']")).getText();
        assertTrue("Welcomes you".contains(cadena));
        assertEquals(String.valueOf(fila.getCell(0)), driver.findElement(By.id("headerUName")).getText());

        Utilidades.esperar(5);

        archivo.close();
    }


    @Test
    public void leerHobbiesExcel() throws IOException {
        String rutaArchivo = "src/test/resources/datadriven/datos.xlsx";
        FileInputStream archivo = new FileInputStream(rutaArchivo);
        XSSFWorkbook libro = new XSSFWorkbook(archivo); //Toma el libro del archivo
        XSSFSheet hoja = libro.getSheetAt(0); //Toma la primera hoja teniendo en cuenta que es a nivel de vector (0 -> hoja1)
        Row fila = hoja.getRow(1); //Parte desde la primera fila en el archivo de excel (0)

        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_REGISTRO);

        List<WebElement> contenedorCheckbox = driver.findElements(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/div"));
        String opciones = String.valueOf(fila.getCell(0));

        List<String> listaOpciones = new ArrayList<>();
        listaOpciones.addAll(Arrays.asList(opciones.split(",")));
        for(int i=0; i<contenedorCheckbox.size(); i++){
            for(int j=0; j<listaOpciones.size(); j++) {
                if (contenedorCheckbox.get(i).getText().equals(listaOpciones.get(j))) {
                    driver.findElement(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/div[" + (i + 1) + "]/input")).click();
                    break;
                }
            }
        }

        esperar(5);
        archivo.close();
    }

    @After
    public void close(){
        driver.quit();
    }

}
