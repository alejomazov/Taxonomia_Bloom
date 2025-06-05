/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Controlador.Control;
import Vista.InicioExamen;
import Vista.Evaluacion;
import Modelo.Listar;
import Modelo.Pregunta;
import Vista.Resultado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author alejo
 */
public class ControlI {
    private InicioExamen vista;
    private Control control;
    public Evaluacion vista2;
    public List<Pregunta> preguntas;
    
    public ControlI(InicioExamen vista){
        this.vista = vista;
        
        
        
        
        vista.CBexamenes.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                
                elegirExamen();
            }
    
    });
        
        vista.btnInicioExa.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                Evaluacion vista2 = new Evaluacion();
                Control control = new Control(vista2, preguntas);
                vista2.setVisible(true);
                vista.setVisible(false);
                
                
            }
        });
        
      
    }
        public void elegirExamen(){
            Listar listar = new Listar();
            preguntas = listar.preguntaBD(vista.CBexamenes.getSelectedIndex()+1);
            System.out.println(preguntas.size());
            Pregunta p = preguntas.get(1);
            
            System.out.println(p.getPregunta());
            
        }
        
        
    }

    

