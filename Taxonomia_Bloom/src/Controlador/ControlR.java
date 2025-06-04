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
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author alejo
 */
public final class ControlR {
    private Resultado vista;
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
    
    
    
    
    public ControlR(Resultado vista, List<Integer> respuestas, List<Pregunta> preguntas) {
        this.vista = vista;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        
        
        
        mostrarResultados();
    }
        
    private void mostrarResultados() {
        DecimalFormat fd = new DecimalFormat("#.00");
        for (int i = 0; i < respuestas.size(); i++) {
            Pregunta p = preguntas.get(i);
            if (respuestas.get(i)==p.getRespuestaCorrecta()){
                contador++;
                if ("Conocimiento".equals(p.getNivelTaxonomia())){
                    contador1++;     
                }
                else if("Compresión".equals(p.getNivelTaxonomia())){
                    contador2++;
                }
                
                else if("Aplicación".equals(p.getNivelTaxonomia())){
                    contador3++;
                }
                
                else if("Análisis".equals(p.getNivelTaxonomia())){
                    contador4++;
                }
                
                else if("Síntesis".equals(p.getNivelTaxonomia())){
                    contador5++;
                }
                
                else if("Evaluación".equals(p.getNivelTaxonomia())){
                    contador6++;
                }
                
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
                                    Respuestas correctas 
                                    Total = %s %% 
                                    - Taxonomia conocimiento: %s %%
                                    - Taxonomia conocimiento: %s %%
                                    - Taxonomia conocimiento: %s %%
                                    - Taxonomia conocimiento: %s %%
                                    - Taxonomia conocimiento: %s %%
                                    - Taxonomia conocimiento: %s %%
                                    
                                   """.formatted(fd.format(pResCorrectas),
                                           fd.format(pResCorrectas1),
                                           fd.format(pResCorrectas2),
                                           fd.format(pResCorrectas3),
                                           fd.format(pResCorrectas4),
                                           fd.format(pResCorrectas5),
                                           fd.format(pResCorrectas6)));
    }
        

}


