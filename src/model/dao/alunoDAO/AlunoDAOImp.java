package model.dao.alunoDAO;

import model.dao.alunoDAO.AlunoDAO;
import model.db.DB;
import model.entities.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImp implements AlunoDAO {
    private Connection conexao;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public AlunoDAOImp(Connection conexaoDB) {
        this.conexao = conexaoDB;
    }

    @Override
    public void insert(Aluno aluno) {
        try {
            preparedStatement = conexao.prepareStatement("INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getSexo());
            preparedStatement.setDate(3, new Date(aluno.getDt_nasc().getTime()));
            int linhasEx = preparedStatement.executeUpdate();

            if (linhasEx > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    aluno.setId(resultSet.getInt(1));
                }
                System.out.println("    Aluno [ "
                        + aluno.getId() + " | "
                        + aluno.getNome() + " ] "
                        + " foi criado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar aluno");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Aluno aluno) {
        try {
            preparedStatement = conexao.prepareStatement("UPDATE aluno SET nome = ?, sexo=?, dt_nasc=? "
                    + "	WHERE idaluno=?");
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getSexo());
            preparedStatement.setDate(3, new java.sql.Date(aluno.getDt_nasc().getTime()));
            preparedStatement.setInt(4, aluno.getId());

            int linhas = preparedStatement.executeUpdate();
            if (linhas > 0) {
                System.out.println("    Aluno [ "
                        + aluno.getId() + " | "
                        + aluno.getNome() + " ] "
                        + " alterado com sucesso!");
            } else {
                System.out.println("    Não foi realizada a alteração do Aluno!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(preparedStatement);
        }
    }

    @Override
    public void delet(int id) {

        try {
            preparedStatement = conexao.prepareStatement("DELETE FROM aluno WHERE idaluno = ?");
            preparedStatement.setInt(1, id);
            int linhasAr = preparedStatement.executeUpdate();

            if (linhasAr > 0) {
                System.out.println("Aluno excluido: " + id);
            } else {
                System.out.println("Erro ao excluir aluno");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            DB.fechaConexao();
        }

    }

    @Override
    public Aluno findById(int id) {
        try {
            preparedStatement = conexao.prepareStatement(
                    "SELECT a.*, c.nomecurso FROM aluno a WHERE idaluno = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Aluno a = new Aluno();
                a.setId(resultSet.getInt(0));
                a.setNome(resultSet.getString(1));
                a.setSexo(resultSet.getString(2));
                a.setDt_nasc(resultSet.getDate(3));
                return a;
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
    public List<Aluno> findAll() {
        List<Aluno> listar = new ArrayList<Aluno>();
        try {
            preparedStatement = conexao.prepareStatement("SELECT  * FROM aluno");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Aluno aluno = new Aluno(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4));
                listar.add(aluno);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(preparedStatement);
            DB.fechaConexao();
        }
        return listar;
    }
}
