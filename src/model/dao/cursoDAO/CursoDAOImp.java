package model.dao.cursoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.dao.cursoDAO.CursoDAO;
import model.entities.Curso;

public class CursoDAOImp implements CursoDAO {

	private Connection conexao;
	
	public CursoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Curso obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO curso (nomecurso) VALUES (?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeCurso());
			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIdcurso(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso !");
			}
			else {
				System.out.println("N�o foi poss�vel cadastrar o curso !");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Curso obj) {
		
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	public Curso findById(Integer id) {
		
		return null;
	}

	@Override
	public List<Curso> findAll() {
		
		return null;
	}

}
