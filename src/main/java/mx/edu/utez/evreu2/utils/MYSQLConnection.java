package mx.edu.utez.evreu2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {
    final String DBNAME = "evreu2",
    user = "root",
    password = "root",
    timezone = "America/Mexico_City",
    useSSL = "false",
    publickey = "true",
    ddlauto = "true",
    host = "Localhost";

    public Connection connect() {
        String dataSource = String.format(
                "jdbc:mysql://%s:3306/%s?user=%s&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s&createDatabaseIfNotExist=%s",
                host, DBNAME, user, password, timezone, useSSL, publickey, ddlauto
        );
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(dataSource);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try{
            Connection conn = new MYSQLConnection().connect();
            if(conn != null) {
                System.out.println("Conexion realizada");
                conn.close();
            }else{
                System.out.println("Conexion fallida");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
