package dominio;

public class Candidato {
    private String nombre;
    private int votos;

    public Candidato(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void incrementarVotos() {
        votos++;
    }

    public void resetearVotos() {
        votos = 0;
    }
}
