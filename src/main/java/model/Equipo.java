package model;

import java.util.Comparator;

public class Equipo implements Comparable<Equipo> {

    private String nombre;

    private int puntos;

    private int golesAFavor;

    private int golesEnContra;

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void sumarGolesAFavor(int goles) {
        this.golesAFavor += goles;
    }

    public void sumarGolesEnContra(int goles) {
        this.golesEnContra += goles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipo equipo = (Equipo) o;

        if (nombre != null ? !nombre.equals(equipo.nombre) : equipo.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @Override
    public int compareTo(Equipo o) {
        int resultado = o.puntos - this.puntos;

        if (resultado == 0) {
            resultado = o.golesAFavor - this.golesAFavor;
            if (resultado == 0) {
                resultado = this.golesEnContra - o.golesEnContra;
            }
        }

        return resultado;
    }

    // Comparator para ordenar equipos por goles a favor
    Comparator<Equipo> golesAFavorComparator = new Comparator<Equipo>() {
        @Override
        public int compare(Equipo equipo1, Equipo equipo2) {
            return equipo2.getGolesAFavor() - equipo1.getGolesAFavor();
        }
    };
}

