package br.com.erp.entities;

public class PlanoDeContas extends AbstractEntity {
	// CONSTANTES DE NATUREZA
	public static final int CREDORA = 0;
	public static final int DEVEDORA = 1;

	// CONSTANTES DE TIPO DE CONTA
	public static final int ANALITICA = 0;
	public static final int SINTETICA = 1;

	private String conta;
	private int natureza;
	private int tipo;
	private int nivel;
	private int pai;
	private boolean resultado;

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public int getNatureza() {
		return natureza;
	}

	public void setNatureza(int natureza) {
		this.natureza = natureza;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPai() {
		return pai;
	}

	public void setPai(int pai) {
		this.pai = pai;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	/**
	 * converts natureza into its String value;
	 * @return String value of natureza	
	 */
	public String formatNatureza() {
		switch (natureza) {
		case CREDORA:
			return "Credora";
		case DEVEDORA:
			return "Devedora";
		default:
			throw new IndexOutOfBoundsException("natureza inválida");
		}
	}
	
	/**
	 * converts tipo de conta into its String value;
	 * @return String value of tipo de conta  
	 */
	public String formatTipo() {
		switch (tipo) {
		case ANALITICA:
			return "Analítica";
		case SINTETICA:
			return "Sintética";
		default:
			throw new IndexOutOfBoundsException("tipo de conta inválido");
		}
	}
	
	/**
	 * puts a mask on an account's id
	 * @param id of account
	 * @return formated id (0.00.00.00)
	 */
	public static String formatCodigo(int id) throws IllegalArgumentException{
		if(id<1000000) {
			throw new IllegalArgumentException("O id da conta não pode ser menor que 1.000.000");
		}
		
		if(id>=2000000) {
			throw new IllegalArgumentException("O id da conta não pode superior a 1.999.999");
		}
		String codigo = String.valueOf(id);
		return codigo.replaceAll("(\\d{1})(\\d{2})(\\d{2})(\\d{2})", "$1.$2.$3.$4");
	}

}
