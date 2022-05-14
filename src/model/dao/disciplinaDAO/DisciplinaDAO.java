package model.dao.disciplinaDAO;

import model.entities.Disciplina;

import java.sql.SQLException;
import java.util.List;

public interface DisciplinaDAO {
    void insert(Disciplina disciplina);
    void update(Disciplina disciplina);
    void deletById(int id);
    Disciplina findById(int id);
    List<Disciplina> findAll();
}
