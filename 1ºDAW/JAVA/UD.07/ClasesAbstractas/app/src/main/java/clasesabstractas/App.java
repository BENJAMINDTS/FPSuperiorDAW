package clasesabstractas;

/**
 * Clase principal de ejecución que sirve para testear la jerarquía de animales.
 * Se encarga de instanciar los diferentes objetos de las clases concretas y
 * mostrar sus atributos específicos por consola.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

    /**
     * Método principal (punto de entrada) de la aplicación.
     * Crea instancias de Perro, Gato, Lobo y Leon, imprimiendo la información
     * de cada uno mediante la llamada a sus métodos polimórficos.
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Instanciación de objetos de las clases concretas
        Perro perro = new Perro();
        Gato gato = new Gato();
        Lobo lobo = new Lobo();
        Leon leon = new Leon();

        // Visualización de datos del Perro
        System.out.println("Perro: ");
        System.out.println("Sonido: " + perro.getSonido());
        System.out.println("Alimentos: " + perro.getAlimentos());
        System.out.println("Habitat: " + perro.getHabitat());
        System.out.println("Nombre Cientifico: " + perro.getNombreCientifico());
        System.out.println("---------------------------------------------------");

        // Visualización de datos del Gato
        System.out.println("Gato: ");
        System.out.println("Sonido: " + gato.getSonido());
        System.out.println("Alimentos: " + gato.getAlimentos());
        System.out.println("Habitat: " + gato.getHabitat());
        System.out.println("Nombre Cientifico: " + gato.getNombreCientifico());
        System.out.println("---------------------------------------------------");

        // Visualización de datos del Lobo
        System.out.println("Lobo: ");
        System.out.println("Sonido: " + lobo.getSonido());
        System.out.println("Alimentos: " + lobo.getAlimentos());
        System.out.println("Habitat: " + lobo.getHabitat());
        System.out.println("Nombre Cientifico: " + lobo.getNombreCientifico());
        System.out.println("---------------------------------------------------");

        // Visualización de datos del Leon
        System.out.println("Leon: ");
        System.out.println("Sonido: " + leon.getSonido());
        System.out.println("Alimentos: " + leon.getAlimentos());
        System.out.println("Habitat: " + leon.getHabitat());
        System.out.println("Nombre Cientifico: " + leon.getNombreCientifico());
    }
}