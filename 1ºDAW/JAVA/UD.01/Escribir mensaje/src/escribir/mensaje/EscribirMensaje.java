package escribir.mensaje;

public class EscribirMensaje {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //inicio de variables
        int number = 1;
        String message = "Tu numero es " + number;
        //Escribir mensaje
        for (int x = 0; x < 5; x++) {
            System.out.println(message);
            number++;
            message = "Tu numero es " + number;
        }
    }

}
