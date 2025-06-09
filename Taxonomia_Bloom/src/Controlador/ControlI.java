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
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author alejo
 */
public class ControlI {
    private InicioExamen vista;
    private Control control;
    public Evaluacion vista2;
    public List<Pregunta> preguntas;
    int tiempo;
    String tBloom;
    
    public ControlI(InicioExamen vista){
        this.vista = vista;
        
        
        
        
        
        vista.CBexamenes.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                
                elegirExamen();
            }
    
    });
        
        vista.btnInicioExa.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                elegirExamen();
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
            vista.txtPreguntas.setText(String.valueOf(preguntas.size()));
            vista.txtTiempo.setText(tiempoTotal()+" Min");
            tipoBloom();
            
        }
        
        public int tiempoTotal(){
            tiempo = 0;
            for (int i =0;i<preguntas.size();i++){
            Pregunta p = preguntas.get(i);
            tiempo += p.getTiempo();
        }
            return tiempo;
        }
        
        public int tipoBloom(){
            int taxCto = 0;
            int taxCsion = 0;
            int taxApli = 0;
            int taxAnal = 0;
            int taxSint = 0;
            int taxEva = 0;
            
            StyledDocument doc = vista.txtpBloom.getStyledDocument();
            try{
                String textoActual = doc.getText(0, doc.getLength());
                int i=textoActual.indexOf(":");
                
                if (i!=-1){
                    String texto =textoActual.substring(0,i+1).trim();
                    doc.remove(0, doc.getLength());
                    doc.insertString(0, texto, null);
                }
                
            }catch(BadLocationException e){
            e.printStackTrace();
            }
            
            for(int i= 0; i<preguntas.size();i++){
                Pregunta p = preguntas.get(i);
                System.out.println(p.getNivelTaxonomia());
                if (null != p.getNivelTaxonomia())
                    switch (p.getNivelTaxonomia()) {
                    case "Conocimiento" -> taxCto++;
                    case "Comprensión" -> taxCsion++;
                    case "Aplicación" -> taxApli++;
                    case "Análisis" -> taxAnal++;
                    case "Síntesis" -> taxSint++;
                    case "Evaluación" -> taxEva++;
                    default -> {
                    }
                }
            }
 
            String textoBloom = "\nPreguntas de recordar: "+taxCto+"\n"
                    + "Preguntas de comprender: "+taxCsion+"\n"
                    + "Preguntas de aplicar: "+taxApli+"\n"
                    + "Preguntas de analizar: "+taxAnal+"\n"
                    + "Preguntas de crear: "+taxSint+"\n"
                    + "Preguntas de evaluar: "+taxEva+"\n";

            try{
            doc.insertString(doc.getLength(), textoBloom, null);
            }catch (BadLocationException e){
                e.printStackTrace();
            }
            return 0;
        }
        
        
}

    

