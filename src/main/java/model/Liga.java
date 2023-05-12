package model;

import java.util.*;

public class Liga {

    public static final int PUNTOS_PARTIDO_GANADO = 3;
    public static final int PUNTOS_PARTIDO_EMPATADO = 1;
    private Set<Partido> partidos;

    private Set<Equipo> equipos;

    public Liga() {
        this.partidos = new HashSet<>();
        this.equipos = new HashSet<>();
    }

    public Set<Equipo> getEquipos() {
        return this.equipos;
    }

    public boolean agregarPartido(Partido partido) {
        return partidos.add(partido);
    }

    public boolean agregarPartidos(Set<Partido> partidos) {
        return this.partidos.addAll(partidos);
    }

    public String obtenerClasificacion() {
        calcularPuntuaciones();
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        Collections.sort(equiposOrdenados);

        StringBuilder tabla = new StringBuilder();
        tabla.append(formatearBorde("a-----b----------------------b------b----b----c\n"));
        tabla.append(formatearFila("| Pos | Equipo               | Pts  | GF | GC |\n"));
        tabla.append(formatearBorde("d-----e----------------------e------e----e----f\n"));

        int posicion = 1;
        for (Equipo equipo : equiposOrdenados) {
            tabla.append(formatearFila(String.format("| %-3d | %-20s | %-4d | %-2d | %-2d |\n",
                    posicion++, equipo.getNombre(), equipo.getPuntos(), equipo.getGolesAFavor(), equipo.getGolesEnContra())));
        }

        tabla.append(formatearBorde("g-----h----------------------h------h----h----i"));
        return tabla.toString();
    }

    public static String formatearFila(String str) {
        return str.replace('|', '\u2502');
    }

    public static String formatearBorde(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }

    public void calcularPuntuaciones() {
        if (equipos.size() == 0) {
            for (Partido partido : partidos) {
                Equipo local = buscarEquipo(partido.getLocal());
                Equipo visitante = buscarEquipo(partido.getVisitante());
                asignarPuntosYGoles(partido, local, visitante);
            }
        }
    }

    private Equipo buscarEquipo(Equipo equipo) {
        Equipo buscado = equipo;
        if (!equipos.add(equipo)) {
            for (Equipo e : equipos) {
                if (e.equals(equipo)) {
                    buscado = e;
                    break;
                }
            }
        }
        return buscado;
    }

    private void asignarPuntosYGoles(Partido partido, Equipo local, Equipo visitante) {
        asignarPuntos(partido, local, visitante);
        sumarGoles(partido, local, visitante);
    }

    private void sumarGoles(Partido partido, Equipo local, Equipo visitante) {
        local.sumarGolesAFavor(partido.getGolesLocal());
        local.sumarGolesEnContra(partido.getGolesVisitante());
        visitante.sumarGolesAFavor(partido.getGolesVisitante());
        visitante.sumarGolesEnContra(partido.getGolesLocal());
    }

    private void asignarPuntos(Partido partido, Equipo local, Equipo visitante) {
        if (partido.getGolesLocal() > partido.getGolesVisitante()) {
            local.sumarPuntos(PUNTOS_PARTIDO_GANADO);
        } else if (partido.getGolesLocal() == partido.getGolesVisitante()) {
            local.sumarPuntos(PUNTOS_PARTIDO_EMPATADO);
            visitante.sumarPuntos(PUNTOS_PARTIDO_EMPATADO);
        } else {
            visitante.sumarPuntos(PUNTOS_PARTIDO_GANADO);
        }
    }
}
