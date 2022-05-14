package model.dao.disciplinaDAO;

import model.db.DB;
import model.entities.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImp implements DisciplinaDAO {
    private Connection conexao;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public DisciplinaDAOImp(Connection conexao) {
        this.conexao = conexao;
    }
    @Override
    public void insert(Disciplina disciplina) {
        try {
            preparedStatement = conexao.prepareStatement("INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, disciplina.getNomeDisciplina());
            preparedStatement.setInt(2, disciplina.getCargaHoraria());

            int linhas = preparedStatement.executeUpdate();

            if (linhas > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    disciplina.setIdDisciplina(resultSet.getInt(1));
                }
                System.out.println("    |     Disciplina [ "
                        + disciplina.getIdDisciplina() + " | "
                        + disciplina.getNomeDisciplina() + " | "
                        + disciplina.getCargaHoraria() + " ] "
                        + " foi criado com sucesso!");
            } else {
                System.out.println("    Não foi possível cadastrar o Disciplina!");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(preparedStatement);
            DB.fechaResultSet(resultSet);
        }

    }

    @Override
    public void update(Disciplina disciplina) {
        try {
            preparedStatement = conexao.prepareStatement("UPDATE disciplina SET nomedisciplina = ?,  cargahoraria = ?"
                    + "WHERE iddisciplina = ?");
            preparedStatement.setString(1, disciplina.getNomeDisciplina());
            preparedStatement.setInt(2, disciplina.getCargaHoraria());
            preparedStatement.setInt(3, disciplina.getIdDisciplina());

            int linhas = preparedStatement.executeUpdate();
            if (linhas > 0) {
                System.out.println("    Disciplina [ "
                        + disciplina.getIdDisciplina() + " | "
                        + disciplina.getNomeDisciplina() + " | "
                        + disciplina.getCargaHoraria() + " ] "
                        + " alterado com sucesso!");
            } else {
                System.out.println("Não foi realizada a alteração do Disciplina!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(preparedStatement);
        }

    }

    @Override
    public void deletById(int id) {
        try {
            preparedStatement = conexao.prepareStatement("DELETE FROM disciplina WHERE iddisciplina = ?");
            preparedStatement.setInt(1, id);

            int linhas = preparedStatement.executeUpdate();
            if (linhas > 0) {
                System.out.println("    Disciplina [" + id + "] excluído com sucesso!");
            } else {
                System.out.println("    Não foi possível excluir o Disciplina id[" + id + "] !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(preparedStatement);
        }
    }

    @Override
    public Disciplina findById(int id) {
        try {
            preparedStatement = conexao.prepareStatement("SELECT * FROM disciplina WHERE iddisciplina = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Disciplina obj = new Disciplina();
                obj.setIdDisciplina(resultSet.getInt(1));
                obj.setNomeDisciplina(resultSet.getString(2));
                obj.setCargaHoraria(resultSet.getInt(3));
                return obj;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(preparedStatement);
            DB.fechaResultSet(resultSet);
        }
        return null;
    }

    @Override
    public List<Disciplina> findAll() {
        List<Disciplina> lista = new ArrayList<>();

        try {
            preparedStatement = conexao.prepareStatement("SELECT * FROM disciplina");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Disciplina c = new Disciplina(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DB.fechaPreparedStatement(preparedStatement);
            DB.fechaResultSet(resultSet);
        }
        return lista;
    }
}
