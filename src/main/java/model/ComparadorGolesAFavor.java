package model;

import java.util.Comparator;

public interface ComparadorGolesAFavor {
    Comparator<Equipo> golesAFavorComparator = new Comparator<Equipo>() {
        @Override
        public int compare(Equipo equipo1, Equipo equipo2) {
            return equipo2.getGolesAFavor() - equipo1.getGolesAFavor();
        }
    };
}
