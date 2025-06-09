/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


public class Listar {
    private Pregunta p;
    
   
    Conexion con = new Conexion();
    Connection cn = con.Conex();
    
    public List<Pregunta> preguntaBD(int examen){
        List<Pregunta> preguntas = new ArrayList<>();
        
        String consultasql ="SELECT id_pregunta, enunciado,respuesta_1,respuesta_2,respuesta_3,respuesta_4,"
                + "respuestaCorrecta, tiempo, tipo, nivel "
                + "FROM preguntas "
                + "WHERE id_examen = ?";
        System.out.println(examen);
        
        
        try {
            
            PreparedStatement st = cn.prepareStatement(consultasql);
            st.setInt(1, examen);
            ResultSet rs = st.executeQuery();
           
            while (rs.next()){
                p = new Pregunta(
                rs.getInt("id_pregunta"),
                rs.getString("enunciado"),
                rs.getString("respuesta_1"),
                rs.getString("respuesta_2"),
                rs.getString("respuesta_3"),
                rs.getString("respuesta_4"),
                rs.getInt("respuestaCorrecta"),
                rs.getInt("tiempo"),
                rs.getString("tipo"),
                rs.getString("nivel")   
            );
              
            preguntas.add(p);
            } 
            con.Cerrar();
        }catch(Exception e){
            System.out.println("Error en listar: "+e);
        }
        return preguntas; 
    }
    

    
}