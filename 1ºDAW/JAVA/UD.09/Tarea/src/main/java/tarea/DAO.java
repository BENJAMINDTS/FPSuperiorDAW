package tarea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection connection;

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

    // Método para buscar por nombre (nuevo)
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

    public boolean delete(int id) {
        String sql = "DELETE FROM departamentos WHERE codigo = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar departamento", e);
        }
    }

    public List<Departamento> mostrarDepartamentos() {
        String sql = "SELECT * FROM departamentos";
        List<Departamento> departamentos = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            
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