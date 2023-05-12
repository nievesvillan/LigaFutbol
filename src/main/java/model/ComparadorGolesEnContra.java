package model;

import java.util.Comparator;

public interface ComparadorGolesEnContra {
    Comparator<Equipo> golesEnContraComparator = new Comparator<Equipo>() {
        @Override
        public int compare(Equipo equipo1, Equipo equipo2) {
            return equipo2.getGolesEnContra() - equipo1.getGolesEnContra();
        }
    };
}
