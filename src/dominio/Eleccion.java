package dominio;

import java.util.ArrayList;
import java.util.List;

public class Eleccion {
    private List<Candidato> candidatos;
    private List<Papeleta> papeletas;

    public Eleccion(List<Candidato> candidatos, List<Papeleta> papeletas) {
        this.candidatos = candidatos;
        this.papeletas = papeletas;
    }

    public void realizarRecuento() {
        for (Candidato candidato : candidatos) {
            candidato.resetearVotos();
        }

        for (Papeleta papeleta : papeletas) {
            Candidato preferido = papeleta.obtenerPrimeraPreferencia();
            if (preferido != null) {
                preferido.incrementarVotos();
            }
        }
    }

    public void eliminarCandidatoConMenosVotos() {
        Candidato menorVotos = null;

        for (Candidato candidato : candidatos) {
            if (menorVotos == null || candidato.getVotos() < menorVotos.getVotos()) {
                menorVotos = candidato;
            }
        }

        if (menorVotos != null) {
            candidatos.remove(menorVotos);

            for (Papeleta papeleta : papeletas) {
                papeleta.eliminarCandidato(menorVotos);
            }
        }
    }

    public boolean comprobarMayoríaAbsoluta() {
        int totalVotos = 0;

        for (Candidato candidato : candidatos) {
            totalVotos += candidato.getVotos();
        }

        for (Candidato candidato : candidatos) {
            if (candidato.getVotos() > totalVotos / 2) {
                System.out.println("Ganador: " + candidato.getNombre());
                return true;
            }
        }

        return false;
    }

    public boolean validarPapeletas() {
        for (Papeleta papeleta : papeletas) {
            if (!papeleta.validar(candidatos)) {
                return false;
            }
        }
        return true;
    }

    public Candidato[] getCandidatos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCandidatos'");
    }

    public ArrayList<Candidato> getPapeletas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPapeletas'");
    }
    

}