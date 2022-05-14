package model.dao;

import model.dao.alunoDAO.AlunoDAO;
import model.dao.alunoDAO.AlunoDAOImp;
import model.dao.cursoDAO.CursoDAO;
import model.dao.cursoDAO.CursoDAOImp;
import model.dao.disciplinaDAO.DisciplinaDAO;
import model.dao.disciplinaDAO.DisciplinaDAOImp;
import model.db.DB;

public class FactoryDAO {

	public static CursoDAO createCursoDAO() {
		return new CursoDAOImp(DB.getConexao());
	}
	public static AlunoDAO createAlunoDAO() {
		return new AlunoDAOImp(DB.getConexao());
	}
	public static DisciplinaDAO createDisciplinaDao() {
		return new DisciplinaDAOImp(DB.getConexao());
	}

}
