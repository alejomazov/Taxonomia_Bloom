/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    Connection cn;
    String user="root";
    String password="1234";
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/mydb";
    
    public Connection Conex() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = (Connection)DriverManager.getConnection(url, user, password);
            System.out.println("¡Conexión exitosa!");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return cn;
    }
    public void Cerrar(){
        try{
          cn.close();
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
