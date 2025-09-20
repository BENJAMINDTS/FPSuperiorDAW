
package clasesabstractas;



public class App {


    public static void main(String[] args) {
        Perro perro = new Perro();
        Gato gato = new Gato();
        Lobo lobo = new Lobo();
        Leon leon = new Leon();

        System.out.println("Perro: ");
        System.out.println("Sonido: " + perro.getSonido());
        System.out.println("Alimentos: " + perro.getAlimentos());
        System.out.println("Habitat: " + perro.getHabitat());
        System.out.println("Nombre Cientifico: " + perro.getNombreCientifico());
        System.out.println("---------------------------------------------------");
        System.out.println("Gato: ");
        System.out.println("Sonido: " + gato.getSonido());
        System.out.println("Alimentos: " + gato.getAlimentos());
        System.out.println("Habitat: " + gato.getHabitat());
        System.out.println("Nombre Cientifico: " + gato.getNombreCientifico());
        System.out.println("---------------------------------------------------");
        System.out.println("Lobo: ");
        System.out.println("Sonido: " + lobo.getSonido());
        System.out.println("Alimentos: " + lobo.getAlimentos());
        System.out.println("Habitat: " + lobo.getHabitat());
        System.out.println("Nombre Cientifico: " + lobo.getNombreCientifico());
        System.out.println("---------------------------------------------------");
        System.out.println("Leon: ");
        System.out.println("Sonido: " + leon.getSonido());
        System.out.println("Alimentos: " + leon.getAlimentos());
        System.out.println("Habitat: " + leon.getHabitat());
        System.out.println("Nombre Cientifico: " + leon.getNombreCientifico());
    }
}
