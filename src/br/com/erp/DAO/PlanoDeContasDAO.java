package br.com.erp.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.erp.entities.PlanoDeContas;
import br.com.erp.util.ConnectionFactory;

public class PlanoDeContasDAO extends ConnectionFactory implements GenericDAO<PlanoDeContas> {

	@Override
	public PlanoDeContas insert(PlanoDeContas t) throws Exception {
		SQL = "INSERT INTO PLANODECONTAS VALUES(?,?,?,?,?,?,?,TRUE)";
		ps = connection.prepareStatement(SQL);
		ps.setInt(1, t.getId());
		ps.setString(2, t.getConta());
		ps.setInt(3, t.getNatureza());
		ps.setInt(4, t.getTipo());
		ps.setInt(5, t.getNivel());
		ps.setInt(6, t.getPai());
		ps.setBoolean(7, t.isResultado());
		ps.executeUpdate();
		//closeConnection();
		return t;
	}

	@Override
	public void update(PlanoDeContas t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public PlanoDeContas getObject(int id) throws Exception {
		PlanoDeContas p = new PlanoDeContas();
		SQL = "SELECT * FROM PLANODECONTAS WHERE IDCONTA=? AND ATIVA=TRUE";
		ps = connection.prepareStatement(SQL);
		ps.setInt(1, id);

		rs = ps.executeQuery();

		while (rs.next()) {
			p.setId(rs.getInt("idconta"));
			p.setConta(rs.getString("conta"));
			p.setNatureza(rs.getInt("natureza"));
			p.setTipo(rs.getInt("tipo"));
			p.setNivel(rs.getInt("nivel"));
			p.setPai(rs.getInt("pai"));
			p.setResultado(rs.getBoolean("resultado"));
		}
		//closeConnection();
		return p;
	}

	@Override
	public List<PlanoDeContas> readAll() throws Exception {
		List<PlanoDeContas> pp = new ArrayList<>();
		SQL = "SELECT * FROM PLANODECONTAS";
		ps = connection.prepareStatement(SQL);

		rs = ps.executeQuery();

		while (rs.next()) {
			PlanoDeContas p = new PlanoDeContas();
			p.setId(rs.getInt("idconta"));
			p.setConta(rs.getString("conta"));
			p.setNatureza(rs.getInt("natureza"));
			p.setTipo(rs.getInt("tipo"));
			p.setNivel(rs.getInt("nivel"));
			p.setPai(rs.getInt("pai"));
			p.setResultado(rs.getBoolean("resultado"));
			pp.add(p);
		}
		//closeConnection();
		return pp;
	}

	/**
	 * get the next id to be inserted
	 * 
	 * @param idPai
	 * @return next free id
	 */
	public int getNextId(int idPai) throws Exception {
		int nextId = 0;
		int count = getContasCount(idPai);
		//logic for idPai==0;
		if (idPai == 0) {		
			//logic for idpai=0 and number of contas = 0
			if (count == 0) {
				nextId = 1000000;
			//logic for idpai=0 and number of contas > 0;	
			} else {
				nextId = (getLastChildId(idPai) / 1000000 + 1) * 1000000;
			}
		} 
		//logic for idpai!=0
		else {			
			int nivelPai = getObject(idPai).getNivel();
			int nivelFilho = nivelPai + 1;
			int divisor = divisorPorNivel(nivelFilho);
			
			//logic for idPai != 0 and number of contas =0;
			if (count == 0) {				
				nextId = (idPai/divisor+1)*divisor;
			//logic for idPai !=0 and number of contas >0	
			}else {
				int codigo = getLastChildId(idPai);
				nextId = (codigo/divisor+1)*divisor;
			}
		}
		return nextId;
	}

	private int getContasCount(int idPai) throws Exception {
		int count = 0;
		SQL = "SELECT COUNT(IDCONTA) AS QDT FROM PLANODECONTAS WHERE PAI=?";
		ps = connection.prepareStatement(SQL);
		ps.setInt(1, idPai);

		rs = ps.executeQuery();

		if (rs.next()) {
			count = rs.getInt("qdt");
		}
		
		return count;
	}

	private int getLastChildId(int idPai) throws Exception {
		int ultimoId = 0;
		SQL = "SELECT MAX(IDCONTA) AS CODIGO FROM PLANODECONTAS WHERE PAI=?";
		ps = connection.prepareStatement(SQL);
		ps.setInt(1, idPai);

		rs = ps.executeQuery();

		if (rs.next()) {
			ultimoId = rs.getInt("codigo");
		}		
		return ultimoId;
	}

	private int divisorPorNivel(int nivel) {
		switch (nivel) {
		case 1:
			return 1000000;
		case 2:
			return 10000;
		case 3:
			return 100;
		case 4:
			return 1;
		default:
			throw new IndexOutOfBoundsException("nivel inválido");
		}
	}

}
