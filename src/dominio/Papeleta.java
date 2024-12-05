package dominio;

import java.util.List;

public class Papeleta {
    private List<Candidato> listaPreferencias;

    public Papeleta(List<Candidato> listaPreferencias) {
        this.listaPreferencias = listaPreferencias;
    }

    public Candidato obtenerPrimeraPreferencia() {
        if (listaPreferencias.isEmpty()) {
            return null;
        } else {
            return listaPreferencias.get(0);
        }
    }
    

    public void eliminarCandidato(Candidato candidato) {
        listaPreferencias.remove(candidato);
    }

    public boolean validar(List<Candidato> candidatos) {
        for (Candidato candidato : listaPreferencias) {
            if (!candidatos.contains(candidato)) {
                return false;
            }
        }
        return true;
    }
}
