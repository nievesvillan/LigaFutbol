import model.Equipo;
import model.Liga;
import model.Partido;

import java.io.*;
import java.util.*;

import static model.ComparadorGolesAFavor.golesAFavorComparator;
import static model.ComparadorGolesEnContra.golesEnContraComparator;

public class App {

    public static final String DELIMITADOR = "::";

    public static void main(String[] args) {
        Liga kingLeague = new Liga();
        kingLeague.agregarPartidos(leerFichero());
        kingLeague.calcularPuntuaciones();
        Set<Equipo> equipos = kingLeague.getEquipos();
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        Collections.sort(equiposOrdenados);
        escribirFichero(equiposOrdenados);//genera el archivo html con los equipos ordenados por puntos en liga

        // Ordenar equipos por goles a favor
        Collections.sort(equiposOrdenados, golesAFavorComparator);
        escribirFicheroGolesAFavor(equiposOrdenados); //genera el archivo html con los equipos ordenados por goles a favor

        // Ordenar equipos por goles en contra
        Collections.sort(equiposOrdenados, golesEnContraComparator);
        escribirFicheroGolesEnContra(equiposOrdenados); //genera el archivo html con los equipos ordenados por goles en contra

    }

    private static Set<Partido> leerFichero() {
        Set<Partido> partidos = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/ut5-tarea13-partidos.txt"));
            String linea = reader.readLine();
            while (linea != null) {
                String[] datos = linea.split(DELIMITADOR);
                Partido partido = new Partido(
                        new Equipo(datos[0].trim()),
                        new Equipo(datos[1].trim()),
                        Integer.parseInt(datos[2]),
                        Integer.parseInt((datos[3])));
                partidos.add(partido);
                linea = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }

    /**
     * Crea un fichero HTML donde imprima la tabla clasificatoria
     */
    private static void escribirFichero(List<Equipo> clasificacion) {
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new BufferedWriter(new FileWriter("ClasificacionEquipos.html")));
            salida.println(
                    "<html> " +
                            "<head> " +
                            "<title>Clasificación liga</title> " +
                            "<link rel=\"stylesheet\" href=\"src/main/resources/estilo.css\" />" +
                            "</head> " +
                            "<body> " +
                            "<table> " +
                            "<thead> " +
                            "<tr> <th>Posición</th> " +
                            "<th>Equipo</th> " +
                            "<th>Puntos</th> " +
                            "<th>GF</th> " +
                            "<th>GC</th> </tr> " +
                            "</thead> " +
                            "<tbody>");

            int pos = 1;
            for (Equipo equipo : clasificacion) {
                salida.println("<tr> <td>" + (pos++) + // añadir posición
                        "</td> <td>" + equipo.getNombre() + // añadir equipo
                        "</td> <td>" + equipo.getPuntos() + // añadir puntos
                        "</td> <td>" + equipo.getGolesAFavor() + // añadir GF
                        "</td> <td>" + equipo.getGolesEnContra() + // añadir GC
                        "</td> </tr>");
            }
            salida.println("</tbody> </table> </body> </html>");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            salida.close();
        }
    }

    /**
     * Una tabla con los equipos ordenados por goles a favor (de más a menos). L
     * a tabla tendrá dos columnas, el nombre del equipo y el número de goles a favor
     * de cada equipo.
     */

    private static void escribirFicheroGolesAFavor(List<Equipo> clasificacion) {
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new BufferedWriter(new FileWriter("ClasificacionGolesAFavor.html")));
            salida.println(
                    "<html> " +
                            "<head> " +
                            "<title>Clasificación liga</title> " +
                            "<link rel=\"stylesheet\" href=\"src/main/resources/estilo.css\" />" +
                            "</head> " +
                            "<body> " +
                            "<table> " +
                            "<thead> " +
                            "<tr><th>Equipo</th> " +
                            "<th>GF</th> " +
                            "</thead> " +
                            "<tbody>");

            for (Equipo equipo : clasificacion) {
                salida.println("<tr> <td>" + equipo.getNombre() + // añadir equipo
                        "</td> <td>" + equipo.getGolesAFavor() + // añadir GF
                        "</td> </tr>");
            }
            salida.println("</tbody> </table> </body> </html>");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            salida.close();
        }
    }

    /**
     * Una tabla con los equipos ordenados por goles en contra (de más a menos).
     */

    private static void escribirFicheroGolesEnContra(List<Equipo> clasificacion) {
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new BufferedWriter(new FileWriter("ClasificacionGolesEnContra.html")));
            salida.println(
                    "<html> " +
                            "<head> " +
                            "<title>Clasificación liga</title> " +
                            "<link rel=\"stylesheet\" href=\"src/main/resources/estilo.css\" />" +
                            "</head> " +
                            "<body> " +
                            "<table> " +
                            "<thead> " +
                            "<tr><th>Equipo</th> " +
                            "<th>GC</th> " +
                            "</thead> " +
                            "<tbody>");

            for (Equipo equipo : clasificacion) {
                salida.println("<tr> <td>" + equipo.getNombre() + // añadir equipo
                        "</td> <td>" + equipo.getGolesEnContra() + // añadir GC
                        "</td> </tr>");
            }
            salida.println("</tbody> </table> </body> </html>");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            salida.close();
        }
    }
}
