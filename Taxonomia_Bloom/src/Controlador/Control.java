/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Evaluacion;
import Vista.Resultado;
import Modelo.Pregunta;
import Modelo.Listar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Control {
    private Evaluacion vista;
    public Resultado vistaR;
    private ControlR controlR;
    public List<Pregunta> preguntas;
    private int indiceActual;
    int tiempo = 0;
    public ArrayList<Integer> listRespuesta = new ArrayList<>();
    
        
    
    
    public Control(Evaluacion vista) {
        this.vista = vista;
        Listar listar = new Listar();
        preguntas = listar.preguntaBD(); 
        indiceActual = 0;
        
        for(int i=0; i<preguntas.size();i++){
            
            
            listRespuesta.add(0);
            
            
        }
        
        

        inicioExamen();
        
        
        // Boton iniciar 
        vista.btnIniciarP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            mostrarPregunta(indiceActual);

            }
  
    });
  
        // boton siguiente
        
        vista.btnSig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listRespuesta.set(indiceActual,getRespuestaSeleccionada());
                
                vista.buttonGroup.clearSelection();
                
                
                if (indiceActual < preguntas.size() -1) {
                    indiceActual++;
                    setRespuesta(indiceActual);
                    mostrarPregunta(indiceActual);
                    System.out.println(listRespuesta);
                    if(indiceActual==preguntas.size()-1){
                        vista.btnSig.setText("Enviar");
                    }
                  
                } else {
                    Resultado vistaR = new Resultado();
                    ControlR controlR = new ControlR(vistaR,listRespuesta,preguntas);
                    vistaR.setVisible(true);
                    vista.setVisible(false);
   
                }
                
            }
        });
        
        // boton atras
        vista.btnAnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listRespuesta.set(indiceActual,getRespuestaSeleccionada());
                vista.buttonGroup.clearSelection();
                if (indiceActual >  0  ) {
                    indiceActual--;
                    mostrarPregunta(indiceActual);
                    System.out.println(listRespuesta+"atras");
                    setRespuesta(indiceActual);
                    
                    if (vista.btnSig.getText()=="Enviar"){
                        vista.btnSig.setText("Siguiente");
                    }
                } else {
                    vista.btnAnt.setEnabled(false);
                }
                
            }
        });
        
    }    
        private void mostrarPregunta(int i) {
        Pregunta p = preguntas.get(i);
        vista.Pregunta.setText(p.getPregunta());
        vista.TextA.setText(p.getRespuesta_1());
        vista.TextB.setText(p.getRespuesta_2());
        vista.TextC.setText(p.getRespuesta_3());
        vista.TextD.setText(p.getRespuesta_4());
        vista.btnAnt.setEnabled(true);
        vista.btnSig.setEnabled(true);
        vista.btnIniciarP.setEnabled(false);
        vista.nPreguntas.setText(""+(i+1));
        vista.rbtnA.setEnabled(true);
        vista.rbtnB.setEnabled(true);
        vista.rbtnC.setEnabled(true);
        vista.rbtnD.setEnabled(true);
        if ("Verdadero Falso".equals(p.getTipo())){
            vista.rbtnC.setEnabled(false);
            vista.rbtnD.setEnabled(false);
        }else{
            vista.rbtnC.setEnabled(true);
            vista.rbtnD.setEnabled(true);
        }
        
        
    }
        
        
        
        private void inicioExamen() {
        vista.Pregunta.setText("""
                               Preparados para el examen mas salvaje y estupido que van a  tener en sus malditas vidas.
                               
                               Presiona Iniciar Prueba""");
        
        vista.btnAnt.setEnabled(false);
        vista.btnSig.setEnabled(false);
        for (int i =0;i<preguntas.size();i++){
            Pregunta p = preguntas.get(i);
            tiempo += p.getTiempo();
        }
        vista.TextTiempo.setText(tiempo+" Min");
        vista.nPreguntas.setText(""+preguntas.size());
        vista.rbtnA.setEnabled(false);
        vista.rbtnB.setEnabled(false);
        vista.rbtnC.setEnabled(false);
        vista.rbtnD.setEnabled(false);
    }
        
        public int getRespuestaSeleccionada() {
        if (vista.rbtnA.isSelected()) return 1;
        else if (vista.rbtnB.isSelected()) return 2;
        else if (vista.rbtnC.isSelected()) return 3;
        else if (vista.rbtnD.isSelected()) return 4;
        
        return 0;
        
        }
        
        
        
        public void setRespuesta(int i){
            if (listRespuesta.get(i)!=0)
                switch (listRespuesta.get(i)) {
            case 1:
                vista.rbtnA.setSelected(true);
                
                break;
            case 2:
                vista.rbtnB.setSelected(true);
                
                break;
            case 3:
                vista.rbtnC.setSelected(true);
               
                break;
            case 4:
                vista.rbtnD.setSelected(true);
                
                break;
            default:
                break;
        }
            
        }
        
        public List<Integer> getListRespuesta(){
            return listRespuesta;
        }
           
}


