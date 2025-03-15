package modeloBancario;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Cliente {
    // Declaración de variables
    private String dni; // DNI del cliente
    private String contrasena; // Contraseña del cliente
    private String nombre;
    private String direccion;
    private String telefono;
    private List<Cuenta> cuentas; // Lista de cuentas del cliente

    // Expresión regular para validar la contraseña
    private static final String CONTRASENA_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    // Constructor con parámetros
    public Cliente(String dni, String contrasena, String nombre, String direccion, String telefono) {
        this.dni = dni;
        setContrasena(contrasena); // Validar y establecer la contraseña
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuentas = new ArrayList<>();
    }

    // Constructor sin parámetros
    public Cliente() {
        this.dni = "";
        this.contrasena = "";
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.cuentas = new ArrayList<>();
    }

    // Métodos get y set
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (validarContrasena(contrasena)) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad.");
        }
    }

    // Método para validar la contraseña
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

    // Métodos para gestionar cuentas
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // Método para buscar una cuenta por su número
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
}