
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Resultado;
import Vista.Evaluacion;
import Modelo.Pregunta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.UIManager;

public final class ControlR {
    private Resultado vista;
    public Evaluacion vistaE = new Evaluacion();
    private List<Integer> respuestas;
    private List<Pregunta> preguntas;
    private int indiceActual = 0;

    public ControlR(Resultado vista, List<Integer> respuestas, List<Pregunta> preguntas) {
        this.vista = vista;
        this.preguntas = preguntas;
        this.respuestas = respuestas;

        mostrarResultados();

        vista.btnRexamen.addActionListener(e -> {
            revisarRespuestas();
            vistaE.btnIniciarP.setText("Volver");
            indiceActual = 0;
        });
    }

    private void mostrarResultados() {
        DecimalFormat fd = new DecimalFormat("#.00");
        int totalCorrectas = 0;
        int[] taxonomias = new int[6];
        int vf = 0, mp = 0;

        for (int i = 0; i < respuestas.size(); i++) {
            Pregunta p = preguntas.get(i);
            if (respuestas.get(i) == p.getRespuestaCorrecta()) {
                totalCorrectas++;
                switch (p.getNivelTaxonomia()) {
                    case "Conocimiento" -> taxonomias[0]++;
                    case "Comprensión" -> taxonomias[1]++;
                    case "Aplicación" -> taxonomias[2]++;
                    case "Análisis" -> taxonomias[3]++;
                    case "Síntesis" -> taxonomias[4]++;
                    case "Evaluación" -> taxonomias[5]++;
                }
            }

            switch (p.getTipo()) {
                case "Verdadero Falso" -> vf++;
                case "Multiple" -> mp++;
            }
        }

        double total = ((double) totalCorrectas / preguntas.size()) * 100;

        vista.txtRespuestas.setText(String.format("""
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
        """,
                fd.format(total),
                fd.format((double) taxonomias[0] / preguntas.size() * 100),
                fd.format((double) taxonomias[1] / preguntas.size() * 100),
                fd.format((double) taxonomias[2] / preguntas.size() * 100),
                fd.format((double) taxonomias[3] / preguntas.size() * 100),
                fd.format((double) taxonomias[4] / preguntas.size() * 100),
                fd.format((double) taxonomias[5] / preguntas.size() * 100),
                vf, mp));
    }

    public void revisarRespuestas() {
        // Eliminar listeners anteriores para evitar duplicación
        for (ActionListener al : vistaE.btnSig.getActionListeners()) vistaE.btnSig.removeActionListener(al);
        for (ActionListener al : vistaE.btnAnt.getActionListeners()) vistaE.btnAnt.removeActionListener(al);
        for (ActionListener al : vistaE.btnIniciarP.getActionListeners()) vistaE.btnIniciarP.removeActionListener(al);

        mostrarPregunta(indiceActual);
        setRespuesta(indiceActual);

        vista.setVisible(false);
        vistaE.setVisible(true);
        inhabilitarRespuestas();
        revisarRcorrecta(indiceActual);

        vistaE.btnSig.addActionListener(e -> {
            vistaE.buttonGroup.clearSelection();
            if (indiceActual < preguntas.size() - 1) {
                indiceActual++;
                setRespuesta(indiceActual);
                mostrarPregunta(indiceActual);
                inhabilitarRespuestas();
                revisarRcorrecta(indiceActual);
            }
        });

        vistaE.btnAnt.addActionListener(e -> {
            vistaE.buttonGroup.clearSelection();
            if (indiceActual > 0) {
                indiceActual--;
                mostrarPregunta(indiceActual);
                inhabilitarRespuestas();
                setRespuesta(indiceActual);
                revisarRcorrecta(indiceActual);
            }
        });

        vistaE.btnIniciarP.addActionListener(e -> {
            vistaE.setVisible(false);
            vista.setVisible(true);
            indiceActual = 0;
            mostrarResultados();
        });
    }

    public void setRespuesta(int i) {
        Pregunta p = preguntas.get(i);
        int respuestaUsuario = respuestas.get(i);
        int respuestaCorrecta = p.getRespuestaCorrecta();

        Color colorOriginal = UIManager.getColor("TextField.background");
        vistaE.TextA.setBackground(colorOriginal);
        vistaE.TextB.setBackground(colorOriginal);
        vistaE.TextC.setBackground(colorOriginal);
        vistaE.TextD.setBackground(colorOriginal);

        switch (respuestaUsuario) {
            case 1 -> vistaE.rbtnA.setSelected(true);
            case 2 -> vistaE.rbtnB.setSelected(true);
            case 3 -> vistaE.rbtnC.setSelected(true);
            case 4 -> vistaE.rbtnD.setSelected(true);
        }

        if (respuestaUsuario == respuestaCorrecta) {
            switch (respuestaUsuario) {
                case 1 -> vistaE.TextA.setBackground(Color.green);
                case 2 -> vistaE.TextB.setBackground(Color.green);
                case 3 -> vistaE.TextC.setBackground(Color.green);
                case 4 -> vistaE.TextD.setBackground(Color.green);
            }
        } else {
            switch (respuestaUsuario) {
                case 1 -> vistaE.TextA.setBackground(Color.red);
                case 2 -> vistaE.TextB.setBackground(Color.red);
                case 3 -> vistaE.TextC.setBackground(Color.red);
                case 4 -> vistaE.TextD.setBackground(Color.red);
            }

            switch (respuestaCorrecta) {
                case 1 -> vistaE.TextA.setBackground(Color.green);
                case 2 -> vistaE.TextB.setBackground(Color.green);
                case 3 -> vistaE.TextC.setBackground(Color.green);
                case 4 -> vistaE.TextD.setBackground(Color.green);
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

        vistaE.nPreguntas.setText("" + (i + 1));

        vistaE.btnAnt.setEnabled(i > 0);
        vistaE.btnSig.setEnabled(i < preguntas.size() - 1);

        vistaE.rbtnA.setEnabled(true);
        vistaE.rbtnB.setEnabled(true);

        if ("Verdadero Falso".equals(p.getTipo())) {
            vistaE.rbtnC.setEnabled(false);
            vistaE.rbtnD.setEnabled(false);
        } else {
            vistaE.rbtnC.setEnabled(true);
            vistaE.rbtnD.setEnabled(true);
        }
    }

    public void inhabilitarRespuestas() {
        vistaE.rbtnA.setEnabled(false);
        vistaE.rbtnB.setEnabled(false);
        vistaE.rbtnC.setEnabled(false);
        vistaE.rbtnD.setEnabled(false);
    }

    public void revisarRcorrecta(int i) {
        Pregunta p = preguntas.get(i);
        if (respuestas.get(i) == p.getRespuestaCorrecta()) {
            System.out.println("Respuesta correcta");
        } else {
            System.out.println("Respuesta incorrecta");
        }
    }
}
