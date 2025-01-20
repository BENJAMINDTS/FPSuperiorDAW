/*
 * This source file was generated by the Gradle 'init' task
 */
package listadodeficheros;

import java.io.File;

public class ListadoDeFicheros {
   

    public static void main(String[] args) {
        String[] NFicheros;
        File[] TFichero;

        File f = new File("C:\\Users\\Usuario\\Desktop\\Hola_Mundo");
        NFicheros= f.list();
        TFichero = f.listFiles();
        for(int i = 0; i<NFicheros.length; i++){
            if (TFichero[i].isDirectory()) {
                System.out.println(NFicheros[i] + " es un directorio");
            } else {
                System.out.println(NFicheros[i] + " es un archivo de tipo " + getFileExtension(TFichero[i]));
            }
        }
                
                        
                        
    }
                
    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "desconocido"; 
        }
        return name.substring(lastIndexOf + 1);
    }               
}
