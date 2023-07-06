package mx.edu.utez.evreu2.models.user;

import mx.edu.utez.evreu2.models.crud.DAOrepository;
import mx.edu.utez.evreu2.utils.MYSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Daouser implements DAOrepository<User> {

    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try{
            conn = new MYSQLConnection().connect(); //Establecer la conexion
            String query = "SELECT * FROM people;"; //Preparamos la sentencia
            pstm = conn.prepareStatement(query);   //Ejecutamos la sentencia en la base de datos
            rs = pstm.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLastname(rs.getString("Lastname"));
                user.setBirthday(rs.getString("birthday"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        }catch(SQLException e){
            Logger.getLogger(Daouser.class.getName())
                    .log(Level.SEVERE, "Error findAll"
                    + e.getMessage());
        }finally{
            close();
        }

        return users;
    }

    @Override
    public User findOne(Long id) {
        try{
            conn = new MYSQLConnection().connect(); //Establecer la conexion
            String query = "SELECT * FROM people WHERE id = ?;"; //Preparamos la sentencia
            pstm = conn.prepareStatement(query);   //Ejecutamos la sentencia en la base de datos
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            User user = new User();
            if (rs.next()){
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLastname(rs.getString("Lastname"));
                user.setBirthday(rs.getString("birthday"));
                user.setEmail(rs.getString("email"));
                //users.add(User);
            }
            return user;
        }catch(SQLException e){
            Logger.getLogger(Daouser.class.getName())
                    .log(Level.SEVERE, "Error findAll"
                            + e.getMessage());
        }finally{
            close();
        }
        return null;
    }

    @Override
    public boolean save(User object) {
        try{
            conn = new MYSQLConnection().connect();
            String query = "INSERT INTO people (name, surname, lastname, birthday, email) VALUES (?, ?, ?, ?, ?)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, object.getName());
            pstm.setString(2, object.getSurname());
            pstm.setString(3, object.getLastname());
            pstm.setString(4, object.getBirthday());
            pstm.setString(5, object.getEmail());
            return pstm.executeUpdate() > 0; // ==1
        }catch(SQLException e){
            Logger.getLogger(Daouser.class.getName())
                    .log(Level.SEVERE, "Error findAll"
                            + e.getMessage());
        }finally{
            close();
        }
        return false;
    }

    @Override
    public boolean update(User object) {
        try{
            conn = new MYSQLConnection().connect();
            String query = "UPDATE people SET name = ?, surname = ?, lastname = ?, birthday = ?, email = ? WHERE id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, object.getName());
            pstm.setString(2, object.getSurname());
            pstm.setString(3, object.getLastname());
            pstm.setString(4, object.getBirthday());
            pstm.setString(5, object.getEmail());
            return pstm.executeUpdate() > 0; // ==1
        }catch(SQLException e){
            Logger.getLogger(Daouser.class.getName())
                    .log(Level.SEVERE, "Error findAll"
                            + e.getMessage());
        }finally{
            close();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try{
            conn = new MYSQLConnection().connect();
            String query = "DELETE from poeple WHERE id = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            return pstm.executeUpdate() == 1;
        }catch (SQLException e){
            Logger.getLogger(Daouser.class.getName())
                    .log(Level.SEVERE, "Error findAll"
                            + e.getMessage());
        }finally {
            close();
        }
        return false;
    }
    //Intermediario entre la aplicación y la base de datos

    public void close(){
        try{
            if(conn != null) conn.close();
            if(pstm != null) pstm.close();
            if(rs != null) pstm.close();
        }catch(SQLException e){

        }
    }

}
