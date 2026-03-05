package tarea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la gestión de entidades Departamento.
 * Utiliza una conexión directa JDBC hacia una base de datos MariaDB para
 * realizar operaciones CRUD.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class DAO {

    /**
     * Objeto de conexión activa a la base de datos MariaDB.
     */
    private Connection connection;

    /**
     * Constructor del DAO. Inicializa el controlador JDBC y establece la
     * conexión con la base de datos local usando las credenciales predefinidas.
     *
     * * @throws RuntimeException Si el driver no es encontrado o falla la
     * conexión.
     */
    public DAO() {
        try {
            // 1. Cargar el driver JDBC de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Establecer la conexión
            String url = "jdbc:mariadb://localhost:3306/20889654L?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "bensangon";
            String password = "foc";

            this.connection = DriverManager.getConnection(url, user, password);

            if (!connection.isValid(5)) {
                throw new SQLException("Conexión no válida");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver de MariaDB no encontrado", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }
    }

    /**
     * Realiza una búsqueda parcial de departamentos utilizando coincidencias en
     * el nombre.
     *
     * * @param nombre Cadena de texto a buscar dentro de los nombres de los
     * departamentos.
     * @return Una lista con los departamentos que coinciden con el criterio de
     * búsqueda.
     * @throws RuntimeException Si ocurre un error de SQL durante la consulta.
     */
    public List<Departamento> findByNombre(String nombre) {
        String sql = "SELECT * FROM departamentos WHERE nombre LIKE ?";
        List<Departamento> resultados = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nombre + "%");  // Búsqueda parcial

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    resultados.add(new Departamento(
                            resultSet.getInt("codigo"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("ID_Localizacion"),
                            resultSet.getInt("ID_Manager")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar por nombre", e);
        }

        return resultados;
    }

    /**
     * Inserta un nuevo registro de departamento en la base de datos.
     *
     * * @param departamento El objeto Departamento con los datos a persistir.
     * @throws RuntimeException Si ocurre un error en la ejecución de la
     * sentencia SQL.
     */
    public void save(Departamento departamento) {
        String sql = "INSERT INTO departamentos (codigo, nombre, ID_Localizacion, ID_Manager) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departamento.getCodigo());
            statement.setString(2, departamento.getNombre());
            statement.setInt(3, departamento.getIdLocalizacion());
            statement.setInt(4, departamento.getIdManager());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar departamento", e);
        }
    }

    /**
     * Busca y recupera un departamento específico utilizando su código
     * identificador.
     *
     * * @param codigoTexto El código numérico único (Primary Key) del
     * departamento a buscar.
     * @return El objeto Departamento si se encuentra, de lo contrario null.
     * @throws RuntimeException Si ocurre un error en la consulta SQL.
     */
    public Departamento findById(int codigoTexto) {
        String sql = "SELECT * FROM departamentos WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoTexto);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Departamento(
                            resultSet.getInt("codigo"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("ID_Localizacion"),
                            resultSet.getInt("ID_Manager")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar departamento", e);
        }
        return null;
    }

    /**
     * Actualiza la información de un departamento existente en la base de
     * datos.
     *
     * * @param departamento El objeto Departamento con los datos modificados.
     * @throws RuntimeException Si ocurre un error al ejecutar la sentencia de
     * actualización.
     */
    public void update(Departamento departamento) {
        String sql = "UPDATE departamentos SET nombre = ?, ID_Localizacion = ?, ID_Manager = ? WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, departamento.getNombre());
            statement.setInt(2, departamento.getIdLocalizacion());
            statement.setInt(3, departamento.getIdManager());
            statement.setInt(4, departamento.getCodigo());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar departamento", e);
        }
    }

    /**
     * Elimina permanentemente un departamento de la base de datos mediante su
     * código.
     *
     * * @param id El código único del departamento a eliminar.
     * @return true si el departamento fue eliminado exitosamente, false en caso
     * contrario.
     * @throws RuntimeException Si ocurre un error al ejecutar la sentencia de
     * borrado.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM departamentos WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar departamento", e);
        }
    }

    /**
     * Recupera todos los departamentos registrados en la base de datos.
     *
     * * @return Una lista (List) que contiene todos los objetos Departamento.
     * @throws RuntimeException Si ocurre un error en la consulta general SQL.
     */
    public List<Departamento> mostrarDepartamentos() {
        String sql = "SELECT * FROM departamentos";
        List<Departamento> departamentos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                departamentos.add(new Departamento(
                        resultSet.getInt("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("ID_Localizacion"),
                        resultSet.getInt("ID_Manager")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener departamentos", e);
        }

        return departamentos;
    }

    /**
     * Cierra de manera segura la conexión activa con la base de datos. Este
     * método debe llamarse al finalizar las operaciones para liberar recursos.
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
