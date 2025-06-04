
package Modelo;


public class Pregunta {
    
    // Atributos 
    private int id;
    private String pregunta;
    private String respuesta_1;
    private String respuesta_2;
    private String respuesta_3;
    private String respuesta_4;
    private int respuestaCorrecta;
    private int tiempo;
    private String tipo;
    private String nivelTaxonomia;
    
    
    //Costructor
    
    public Pregunta(int id, String pregunta, String respuesta_1,String respuesta_2,String respuesta_3,String respuesta_4,int respuestaCorrecta,int tiempo, String tipo, String nivelTaxonomia) {
    this.id = id;
    this.pregunta = pregunta;
    this.respuesta_1=respuesta_1;
    this.respuesta_2=respuesta_2;
    this.respuesta_3=respuesta_3;
    this.respuesta_4=respuesta_4;
    this.respuestaCorrecta=respuestaCorrecta;
    this.tiempo = tiempo;
    this.tipo = tipo;
    this.nivelTaxonomia = nivelTaxonomia;
    }


    //Setters y getters 
    public int getId() {
        return id;
}

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    public String getRespuesta_1() {
        return respuesta_1;
    }

    public void setRespuesta_1(String respuesta_1) {
        this.respuesta_1 = respuesta_1;
    }
    public String getRespuesta_2() {
        return respuesta_2;
    }

    public void setRespuesta2(String respuesta_2) {
        this.respuesta_2 = respuesta_2;
    }
    public String getRespuesta_3() {
        return respuesta_3;
    }

    public void setRespuesta3(String respuesta_3) {
        this.respuesta_3 = respuesta_3;
    }
    public String getRespuesta_4() {
        return respuesta_4;
    }

    public void setRespuesta4(String respuesta_4) {
        this.respuesta_4 = respuesta_4;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivelTaxonomia() {
        return nivelTaxonomia;
    }

    public void setNivelTaxonomia(String nivelTaxonomia) {
        this.nivelTaxonomia = nivelTaxonomia;
    }

    
}
