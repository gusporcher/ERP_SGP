package br.com.erp.util;

import java.util.List;

import br.com.erp.DAO.PlanoDeContasDAO;
import br.com.erp.entities.PlanoDeContas;

public class Teste {

	public static void main(String[] args) throws Exception {
		List<PlanoDeContas>ps = new PlanoDeContasDAO().readAll();
		
		for(PlanoDeContas p: ps) {
			System.out.println(p.formatCodigo(p.getId())+" - "+p.getNivel());
		}
		System.out.println(new PlanoDeContasDAO().getNextId(1010000));
	}

}
