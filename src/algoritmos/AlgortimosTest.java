package algoritmos;

import tdas.ABBPrecipitacionesTDA;

import java.util.Random;

public class AlgortimosTest {

    public static void main(String[] args) {
        ABBPrecipitacionesTDA arbol = new implementacion.ArbolPrecipitaciones();
        arbol.inicializar();
        cargoArbol(arbol);

        // Test medicionesMes
        testMedicionesMes(arbol);
        System.out.println("---------------------------------");
        testCampoMasLLuvisoHistoria(arbol);
        System.out.println("---------------------------------");
        testMesMasLluvioso(arbol);
        System.out.println("---------------------------------");
        testPromedioLluviaEnUnDia(arbol, 2024, 2, 15);
        System.out.println("---------------------------------");
        testMedicionesCampoMes(arbol, "Mithras", 2024, 2);
        System.out.println("---------------------------------");


    }

    public static void cargoArbol(ABBPrecipitacionesTDA arbol) {
        String[] campos = {"Mithras", "Capella", "Acrisia", "Ampion", "Arcadia", "Apllo", "Athena", "Aurora", "Azure",
                "Booties", "Cheron", "Denab", "Euterpe", "Faunus", "Fortuna", "Heaven", "Helpomenia", "Hera",
                "Hesperos", "Inferno", "Ishtar", "Janius", "Keresia", "Livia", "Marduce", "Melpomenia", "Nerva",
                "Nexon", "Osiris", "Pallas", "Pallena", "Paradesio", "Philos", "Polux", "Proclas", "Rhea",
                "Saon", "Seloney", "Savana", "Smitheus", "Solaria", "Styx", "Teranova", "Tethys", "Theia",
                "Thera", "Thomkins World", "Verdanis", "Vishnu", "Xang Lo"};

        String[] periodos = {"202401","202402","202403","202404","202405","202406","202407","202408","202409","202410","202411","202412",
                "202301","202302","202303","202304","202305","202306","202307","202308","202309","202310","202311","202312",
                "202501","202502","202503","202504","202505","202506","202507","202508","202509","202510","202511","202512"};

        Random r = new Random();
        int dias;
        int cantidad;
        int dia;
        int precipitacion;
        for(int i = 0; i<campos.length; i++) {
            for(int j = 0; j<periodos.length; j++) {
                String mesStr = periodos[j].substring(4, 6);
                if(mesStr.equalsIgnoreCase("02")) {
                    dias = 28; // Febrero: 1-28
                } else if (mesStr.equals("04") || mesStr.equals("06") || mesStr.equals("09") || mesStr.equals("11")) {
                    dias = 30; // Meses de 30 días: 1-30
                } else {
                    dias = 31; // Meses de 31 días: 1-31
                }
                cantidad = r.nextInt(15) + 1;
                for(int x = 0; x < cantidad; x++) {
                    dia = r.nextInt(dias) + 1;
                    precipitacion = r.nextInt(200) + 20;

                    arbol.agregarMedicion(campos[i], periodos[j].substring(0, 4), periodos[j].substring(4, 6), dia, precipitacion);

                }
            }
        }
        System.out.println("Termine");
    }

    public static void testMedicionesCampoMes(ABBPrecipitacionesTDA arbol, String campo, int anio, int mes) {
        Algoritmos algoritmos = new Algoritmos(arbol);
        tdas.ColaPrioridadTDA resultado = algoritmos.medicionesCampoMes(campo, anio, mes);
        System.out.println("Mediciones para el campo " + campo + " en " + anio + "-" + mes + ":");
        while (!resultado.colaVacia()) {
            int dia = resultado.primero();
            int promedio = resultado.prioridad();
            System.out.println("Día: " + dia + " - Promedio: " + promedio);
            resultado.desacolar();
        }
    }

    public static void testMesMasLluvioso(ABBPrecipitacionesTDA arbol){
        Algoritmos algoritmos = new Algoritmos(arbol);
        int mesMasLluvioso = algoritmos.mesMasLluvioso();
        System.out.println("Mes más lluvioso: " + mesMasLluvioso);
    }

    public static void testCampoMasLLuvisoHistoria(ABBPrecipitacionesTDA arbol){
        Algoritmos algoritmos = new Algoritmos(arbol);
        String campoMasLluvioso = algoritmos.campoMasLLuvisoHistoria();
        System.out.println("Campo más lluvioso de la historia: " + campoMasLluvioso);
    }

    public static void testPromedioLluviaEnUnDia(ABBPrecipitacionesTDA arbol, int anio, int mes, int dia){
        Algoritmos algoritmos = new Algoritmos(arbol);
        float promedio = algoritmos.promedioLluviaEnUnDia(anio, mes, dia);
        System.out.println("Promedio de lluvia en " + anio + "-" + mes + "-" + dia + ": " + promedio);
    }

    public static void testMedicionesMes(ABBPrecipitacionesTDA arbol) {
        Algoritmos algoritmos = new Algoritmos(arbol);
        int anio = 2024;
        int mes = 2; //

        System.out.println("Test medicionesMes para año " + anio + ", mes " + mes);
        tdas.ColaPrioridadTDA resultado = algoritmos.medicionesMes(anio, mes);

        // Siempre usar 32 para el array (índices 1 a 31)
        int[] promedios = new int[32];

        while (!resultado.colaVacia()) {
            int dia = resultado.primero();
            int promedio = resultado.prioridad();
            if (dia > 0 && dia <= 31) {
                promedios[dia] = promedio;
            }
            resultado.desacolar();
        }

        // Mostrar solo hasta el último día real del mes
        for (int dia = 1; dia <= 31; dia++) {
            if (promedios[dia] > 0) {
                System.out.println("Día: " + dia + " - Promedio: " + promedios[dia]);
            }
        }
    }
}
