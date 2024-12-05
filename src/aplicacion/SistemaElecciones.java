package aplicacion;

import dominio.*;
import java.util.*;

public class SistemaElecciones {
    private Eleccion eleccion;

    public SistemaElecciones() {
        this.eleccion = new Eleccion(new ArrayList<>(), new ArrayList<>());
    }

    public void iniciarEleccion() {
        Scanner scanner = new Scanner(System.in);

        // Crear candidatos
        System.out.print("Introduce el número de candidatos: ");
        int numCandidatos = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        for (int i = 0; i < numCandidatos; i++) {
            System.out.print("Introduce el nombre del candidato " + (i + 1) + ": ");
            String nombreCandidato = scanner.nextLine();
            eleccion.getCandidatos().add(new Candidato(nombreCandidato));
        }

        // Crear papeletas
        System.out.print("Introduce el número de papeletas: ");
        int numPapeletas = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        for (int i = 0; i < numPapeletas; i++) {
            List<Candidato> preferencias = new ArrayList<>();
            System.out.println("Introduce las preferencias para la papeleta " + (i + 1) + ":");
            for (int j = 0; j < numCandidatos; j++) {
                System.out.print("Preferencia " + (j + 1) + ": ");
                String nombrePreferencia = scanner.nextLine();
                Candidato candidato = encontrarCandidato(nombrePreferencia);
                if (candidato != null) {
                    preferencias.add(candidato);
                } else {
                    System.out.println("Candidato no encontrado. Intente nuevamente.");
                    j--; // Reintentar la misma preferencia
                }
            }
            eleccion.getPapeletas().add(new Papeleta(preferencias));
        }

        // Validar papeletas
        if (!eleccion.validarPapeletas()) {
            System.out.println("Papeletas no válidas. Elección no puede iniciar.");
        }
    }

    public void mostrarResultados() {
        while (true) {
            eleccion.realizarRecuento();

            if (eleccion.comprobarMayoríaAbsoluta()) {
                break;
            }

            eleccion.eliminarCandidatoConMenosVotos();
        }

        // Mostrar resultados finales
        System.out.println("Resultados Finales:");
        for (Candidato candidato : eleccion.getCandidatos()) {
            System.out.println(candidato.getNombre() + ": " + candidato.getVotos() + " votos");
        }

        // Comprobar si hay empate
        List<Candidato> candidatosConMasVotos = new ArrayList<>();
        int maxVotos = 0;
        for (Candidato candidato : eleccion.getCandidatos()) {
            if (candidato.getVotos() > maxVotos) {
                maxVotos = candidato.getVotos();
                candidatosConMasVotos.clear();
                candidatosConMasVotos.add(candidato);
            } else if (candidato.getVotos() == maxVotos) {
                candidatosConMasVotos.add(candidato);
            }
        }

        if (candidatosConMasVotos.size() > 1) {
            System.out.println("Empate entre los candidatos:");
            for (Candidato candidato : candidatosConMasVotos) {
                System.out.println(candidato.getNombre());
            }
        } else {
            System.out.println("Ganador: " + candidatosConMasVotos.get(0).getNombre());
        }
    }

    private Candidato encontrarCandidato(String nombre) {
        for (Candidato candidato : eleccion.getCandidatos()) {
            if (candidato.getNombre().equalsIgnoreCase(nombre)) {
                return candidato;
            }
        }
        return null;
    }
}
