package automationtesting.excel;

import automationtesting.Utilidades;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static automationtesting.Constantes.URL_PRUEBA_DEMO_LOGIN;
import static automationtesting.Constantes.URL_PRUEBA_DEMO_REGISTRO;
import static automationtesting.Utilidades.esperar;
import static org.junit.Assert.assertEquals;

public class TestLeerDatosExcel {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void leerDatosExcel() throws IOException {
        String rutaArchivo = "src/test/resources/datadriven/datos.xlsx";
        FileInputStream archivo = new FileInputStream(rutaArchivo);
        XSSFWorkbook libro = new XSSFWorkbook(archivo); //Toma el libro del archivo
        XSSFSheet hoja = libro.getSheetAt(0); //Toma la primera hoja teniendo en cuenta que es a nivel de vector (0 -> hoja1)
        Row fila = hoja.getRow(0); //Parte desde la primera fila en el archivo de excel (0)

        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_LOGIN);

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys(""+fila.getCell(0));
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(""+fila.getCell(1));
        driver.findElement(By.name("login")).click();

        String usuario = String.valueOf(fila.getCell(0)).split("@")[0];

        assertEquals("Hello "+usuario+" (not "+usuario+"? Sign out)", driver.findElement(By.xpath("//*[@id='page-36']/div/div[1]/div/p[1]")).getText());

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
