package automationtesting;

public class Utilidades {

    public static void esperar(long cantidadTiempo){
        cantidadTiempo = cantidadTiempo * 1000;
        try{
            Thread.sleep(cantidadTiempo);
        }catch (InterruptedException interruptedException){
            Thread.currentThread().interrupt();
        }

    }

}
