package model.entities;

public class Disciplina {
    private Integer idDisciplina;
    private String nomeDisciplina;
    private Integer cargaHoraria;

    public Disciplina() {

    }

    public Disciplina(Integer idDisciplina, String nomeDisciplina, Integer cargaHoraria) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cargaHoraria == null) ? 0 : cargaHoraria.hashCode());
        result = prime * result + ((idDisciplina == null) ? 0 : idDisciplina.hashCode());
        result = prime * result + ((nomeDisciplina == null) ? 0 : nomeDisciplina.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disciplina other = (Disciplina) obj;
        if (cargaHoraria == null) {
            if (other.cargaHoraria != null)
                return false;
        } else if (!cargaHoraria.equals(other.cargaHoraria))
            return false;
        if (idDisciplina == null) {
            if (other.idDisciplina != null)
                return false;
        } else if (!idDisciplina.equals(other.idDisciplina))
            return false;
        if (nomeDisciplina == null) {
            if (other.nomeDisciplina != null)
                return false;
        } else if (!nomeDisciplina.equals(other.nomeDisciplina))
            return false;
        return true;
    }
}
