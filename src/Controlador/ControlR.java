/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Resultado;
import Vista.Evaluacion;
import Controlador.Control;
import Modelo.Listar;
import Modelo.Pregunta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author alejo
 */
public final class ControlR {
    private Resultado vista;
    public Evaluacion vistaE= new Evaluacion();
    private Control controlE;
    private List<Integer> respuestas;
    private List<Pregunta> preguntas;
    private double pResCorrectas;
    private double pResCorrectas1;
    private double pResCorrectas2;
    private double pResCorrectas3;
    private double pResCorrectas4;
    private double pResCorrectas5;
    private double pResCorrectas6;
    private double contador=0;
    private double contador1=0;
    private double contador2=0;
    private double contador3=0;
    private double contador4=0;
    private double contador5=0;
    private double contador6=0;
    private int vf=0;
    private int mp=0;
    private int indiceActual = 0;
    
    
    
    
    
    
    
    public ControlR(Resultado vista, List<Integer> respuestas, List<Pregunta> preguntas) {
        this.vista = vista;
        
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        
        
        
        
        
        
        
        mostrarResultados();
        
        
        // Inicio botones resultado
        //Boton revisar examen
        vista.btnRexamen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revisarRespuestas();
                
            }
        });
        //Final botones resultado


        
        
        
        
    }

        
    private void mostrarResultados() {
        DecimalFormat fd = new DecimalFormat("#.00");
        for (int i = 0; i < respuestas.size(); i++) {
            Pregunta p = preguntas.get(i);
            
            /*
            
            */
            if (respuestas.get(i)==p.getRespuestaCorrecta()){
                contador++;
                System.out.println("Prueba");
                if (null != p.getNivelTaxonomia())switch (p.getNivelTaxonomia()) {
                    case "Conocimiento" -> contador1++;
                    case "Compresión" -> contador2++;
                    case "Aplicación" -> contador3++;
                    case "Análisis" -> contador4++;
                    case "Síntesis" -> contador5++;
                    case "Evaluación" -> contador6++;
                    default -> {
                    }
                }
                
            }
            if (null != p.getTipo())switch(p.getTipo()){
                case "Verdadero Falso"-> vf++;
                case "Multiple"-> mp++;
                
            }
        }
        pResCorrectas =((contador/preguntas.size())*100);
        pResCorrectas1 =((contador1/preguntas.size())*100);
        pResCorrectas2 =((contador2/preguntas.size())*100);
        pResCorrectas3 =((contador3/preguntas.size())*100);
        pResCorrectas4 =((contador4/preguntas.size())*100);
        pResCorrectas5 =((contador5/preguntas.size())*100);
        pResCorrectas6 =((contador6/preguntas.size())*100);
        vista.txtRespuestas.setText("""
                                    Respuestas correctas:
                                    
                                    Total = %s %% 
                                    -- Taxonomia Recordar: %s %%
                                    -- Taxonomia Comprender: %s %%
                                    -- Taxonomia Aplicar: %s %%
                                    -- Taxonomia Analizar: %s %%
                                    -- Taxonomia Crear: %s %%
                                    -- Taxonomia Evaluar: %s %%
                                    
                                    Segun tipo de pregunta:
                                    
                                    -- Verdadero o falso: %s
                                    -- Multiple: %s
                                   """.formatted(fd.format(pResCorrectas),
                                           fd.format(pResCorrectas1),
                                           fd.format(pResCorrectas2),
                                           fd.format(pResCorrectas3),
                                           fd.format(pResCorrectas4),
                                           fd.format(pResCorrectas5),
                                           fd.format(pResCorrectas6),
                                           vf,
                                           mp));
    }
    
    public void revisarRespuestas(){
        mostrarPregunta(indiceActual);
        setRespuesta(indiceActual);
        
        vista.setVisible(false);
        vistaE.setVisible(true);
        vistaE.btnAnt.setEnabled(false);
        inhabilitarRespuestas();
        revisarRcorrecta(indiceActual);
        
        //Control control = new Control(vistaE,preguntas);
        
        
        
        
                //Inicio botones evaluacion
         
        vistaE.btnSig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                
                vistaE.buttonGroup.clearSelection();
                if (indiceActual < preguntas.size() -1) {
                    indiceActual++;
                    setRespuesta(indiceActual);
                    mostrarPregunta(indiceActual);
                    inhabilitarRespuestas();
                    revisarRcorrecta(indiceActual);
                    if(indiceActual==preguntas.size()-1){
                        vistaE.btnSig.setEnabled(false);
                        
                    }
                }
            }
         });
        
                // boton atras
        vistaE.btnAnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                vistaE.buttonGroup.clearSelection();
                if (indiceActual >  0  ) {
                    indiceActual--;
                    mostrarPregunta(indiceActual);
                    inhabilitarRespuestas();
                    setRespuesta(indiceActual);
                    revisarRcorrecta(indiceActual);
                    if (0 == indiceActual){
                        vistaE.btnAnt.setEnabled(false);
                    }
                } else {
                    
                    
                }
                
            }
        });
        
        
        //Final botones evaluacion
        
        
        
    }  
 public void setRespuesta(int i){
     
            Pregunta p = preguntas.get(i);
            if (respuestas.get(i)!=0)
                switch (respuestas.get(i)) {
            case 1 -> {
                vistaE.rbtnA.setSelected(true);
                if(respuestas.get(i)==p.getRespuestaCorrecta()){
                vistaE.TextA.setBackground(Color.green);
                }else {
                    
                    
                    vistaE.TextA.setBackground(Color.red);
                    
                    
                }
                System.out.println("Respuesta correcta");
            }
            case 2 -> {
                vistaE.rbtnB.setSelected(true);
                if(respuestas.get(i)==p.getRespuestaCorrecta()){
                vistaE.TextB.setBackground(Color.green);
                }else {
                    vistaE.TextB.setBackground(Color.red);
            }
            }
            case 3 -> {
                vistaE.rbtnC.setSelected(true);
                if(respuestas.get(i)==p.getRespuestaCorrecta()){
                vistaE.TextC.setBackground(Color.green);
                }else {
                    vistaE.TextC.setBackground(Color.red);
            }
            }
            case 4 -> {
                vistaE.rbtnD.setSelected(true);
                if(respuestas.get(i)==p.getRespuestaCorrecta()){
                vistaE.TextD.setBackground(Color.green);
                }else {
                    vistaE.TextD.setBackground(Color.red);
            }
            }
            default -> {
                }
        }
            
        }
 
        public void mostrarPregunta(int i) {
        Pregunta p = preguntas.get(i);
        vistaE.Pregunta.setText(p.getPregunta());
        vistaE.TextA.setText(p.getRespuesta_1());
        vistaE.TextB.setText(p.getRespuesta_2());
        vistaE.TextC.setText(p.getRespuesta_3());
        vistaE.TextD.setText(p.getRespuesta_4());
        vistaE.btnAnt.setEnabled(true);
        vistaE.btnSig.setEnabled(true);
        vistaE.btnIniciarP.setEnabled(false);
        vistaE.nPreguntas.setText(""+(i+1));
        vistaE.rbtnA.setEnabled(true);
        vistaE.rbtnB.setEnabled(true);
        vistaE.rbtnC.setEnabled(true);
        vistaE.rbtnD.setEnabled(true);
        if ("Verdadero Falso".equals(p.getTipo())){
            vistaE.rbtnC.setEnabled(false);
            vistaE.rbtnD.setEnabled(false);
        }else{
            vistaE.rbtnC.setEnabled(true);
            vistaE.rbtnD.setEnabled(true);
        }
        
        
    }
        
        public void inhabilitarRespuestas(){
            vistaE.rbtnA.setEnabled(false);
            vistaE.rbtnB.setEnabled(false);
            vistaE.rbtnC.setEnabled(false);
            vistaE.rbtnD.setEnabled(false);
        }
        public void revisarRcorrecta(int i){
           Pregunta p = preguntas.get(i);
           if(respuestas.get(i)==p.getRespuestaCorrecta())
                System.out.println("Respuestacorrecta");
           else{
               System.out.println("Respuesta incorrecta");
           }
                
            
        
        }
}


