package automationtesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Ésta clase permite tener funcionalidades genéricas para la automatización
 * @author: Mario Benítez
 * @version: 1.0
 */
public class Utilidades {

    public static void esperar(long cantidadTiempo){
        cantidadTiempo = cantidadTiempo * 1000;
        try{
            Thread.sleep(cantidadTiempo);
        }catch (InterruptedException interruptedException){
            Thread.currentThread().interrupt();
        }
    }

    public static void esperar(long cantidadTiempo, WebDriver driver){
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
    }
    /**
     *
     * @author Mario Benítez
     * @version 1.0
     * Método para seleccionar elementos de SELECT y RADIOS de manera dinámica
     * @param contenedorRadios Contenedor que tiene acceso a los elementos a inspeccionar
     * @param texto Argumento que buscará dentro de los elementos inspeccionados.
     *
     */
    public static void seleccionarElementoDinamicamente(List<WebElement> contenedorRadios, String texto){
        for(int i=0; i<contenedorRadios.size(); i++){
            if(contenedorRadios.get(i).getText().equals(texto)){
                contenedorRadios.get(i).click();
                break;
            }
        }
    }

}
