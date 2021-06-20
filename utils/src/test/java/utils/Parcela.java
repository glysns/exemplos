package utils;

public class Parcela {
	private Integer numero;
	private String descricao;
	private String vencimento;
	private Double valor;
	public Parcela(Integer numero, String descricao, String vencimento, Double valor) {
		super();
		this.numero = numero;
		this.descricao = descricao;
		this.vencimento = vencimento;
		this.valor = valor;
	}
	public Integer getNumero() {
		return numero;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getVencimento() {
		return vencimento;
	}
	public Double getValor() {
		return valor;
	}
	
	
}
