/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Vista.InicioExamen;
import Modelo.Listar;

/**
 *
 * @author alejo
 */
public class ControlI {
    private InicioExamen vista;
    
    public ControlI(){
        this.vista = vista;
        
        elegirExamen();
    }
    
        public void elegirExamen(){
            vista.CBexamenes.getAction();
            System.out.println(vista.CBexamenes.getAction());
            
        }
        
        
    }

    

