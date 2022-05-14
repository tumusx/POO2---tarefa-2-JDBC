package model.dao.alunoDAO;

import model.entities.Aluno;

import java.sql.SQLException;
import java.util.List;

public interface AlunoDAO {
    void insert(Aluno aluno);
    void update(Aluno aluno);
    void delet(int id);
    Aluno findById(int id);
    List<Aluno> findAll();

}
