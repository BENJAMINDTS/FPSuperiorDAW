package modeloBancario;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representa al usuario titular del sistema bancario.
 * Gestiona la información personal, la seguridad mediante contraseñas robustas
 * y el catálogo de cuentas asociadas al individuo.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Cliente {

    /** Documento Nacional de Identidad del cliente */
    private String dni;

    /** Clave de acceso al sistema (almacenada en texto plano para este modelo) */
    private String contrasena;

    /** Nombre completo del titular */
    private String nombre;

    /** Domicilio de residencia del cliente */
    private String direccion;

    /** Número de contacto telefónico */
    private String telefono;

    /**
     * Relación de agregación: Lista de productos financieros (cuentas) del cliente
     */
    private List<Cuenta> cuentas;

    /**
     * * Patrón Regex para validación de contraseña:
     * - Al menos una minúscula, una mayúscula, un número y un carácter especial.
     * - Longitud mínima de 8 caracteres.
     */
    private static final String CONTRASENA_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    /**
     * Constructor con parámetros. Inicializa al cliente y valida su política de
     * seguridad.
     * * @param dni Identificador legal.
     * 
     * @param contrasena Clave que debe cumplir con los requisitos del patrón.
     * @param nombre     Nombre del titular.
     * @param direccion  Domicilio fiscal.
     * @param telefono   Teléfono de contacto.
     */
    public Cliente(String dni, String contrasena, String nombre, String direccion, String telefono) {
        this.dni = dni;
        setContrasena(contrasena); // Invocación al setter para aplicar validación Regex
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuentas = new ArrayList<>();
    }

    /**
     * Constructor por defecto. Crea una instancia con campos vacíos e inicializa la
     * lista de cuentas.
     */
    public Cliente() {
        this.dni = "";
        this.contrasena = "";
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.cuentas = new ArrayList<>();
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del cliente tras validar su robustez.
     * * @param contrasena Nueva clave a evaluar.
     * 
     * @throws IllegalArgumentException Si la clave no cumple con el patrón de
     *                                  seguridad.
     */
    public void setContrasena(String contrasena) {
        if (validarContrasena(contrasena)) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad.");
        }
    }

    /**
     * Motor de validación interna mediante expresiones regulares.
     * * @param contrasena Cadena a evaluar.
     * 
     * @return true si cumple los requisitos, false en caso contrario.
     */
    private boolean validarContrasena(String contrasena) {
        Pattern pattern = Pattern.compile(CONTRASENA_PATTERN);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // --- Gestión de la Colección de Cuentas ---

    /**
     * Vincula una nueva cuenta bancaria al catálogo del cliente.
     * * @param cuenta Objeto de tipo Cuenta (Ahorro, Corriente, etc.).
     */
    public void agregarCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            cuentas.add(cuenta);
        }
    }

    /**
     * Recupera el listado de todas las cuentas pertenecientes al titular.
     * * @return List de objetos Cuenta.
     */
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * Realiza una búsqueda lineal dentro de las cuentas del cliente.
     * * @param numeroCuenta El IBAN o identificador a buscar.
     * 
     * @return El objeto Cuenta coincidente o null si no se encuentra.
     */
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
}