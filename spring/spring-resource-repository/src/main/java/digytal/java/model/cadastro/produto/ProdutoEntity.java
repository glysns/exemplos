package digytal.java.model.cadastro.produto;

import digytal.java.commons.Model;
import digytal.java.model.cadastro.marca.Marca;
import digytal.java.model.cadastro.marca.MarcaEntity;

public class ProdutoEntity extends Produto implements Model {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	private MarcaEntity marca;
	public MarcaEntity getMarca() {
		return marca;
	}
	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}
}
