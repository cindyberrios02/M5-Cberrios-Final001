package com.edutecno.modelo;

import java.util.Date;

public class Horoscopo {
    private String animal;
    private Date fecha_inicio;
    private Date fecha_fin;

    public Horoscopo() {
    }

    public Horoscopo(String animal, Date fecha_inicio, Date fecha_fin) {
        this.animal = animal;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public String getAnimal() { return animal; }
    public void setAnimal(String animal) { this.animal = animal; }

    public Date getFecha_inicio() { return fecha_inicio; }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() { return fecha_fin; }
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "Horoscopo{" +
                "animal='" + animal + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                '}';
    }
}