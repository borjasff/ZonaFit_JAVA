package zona_fit.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConection(){
        Connection conexion = null;
        var DataBase = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + DataBase;
        var user = "root";
        var password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
        } catch (Exception e){
            System.out.println("Error to connect to database: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConection();
        if(conexion != null){
            System.out.println("Conexión exitosa: " + conexion);
        } else {
            System.out.println("Conexión erronea: " + conexion);
        }
    }
}
