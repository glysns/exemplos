package digytal.java.model.produto;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import digytal.java.commons.Model;
import digytal.java.model.marca.MarcaEntity;

@Entity
@Table(name = "tab_produto")
public class ProdutoEntity extends Produto implements Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private MarcaEntity marca;
	
	@ManyToOne
	@JoinColumn(name = "cd_marca")
	public MarcaEntity getMarca() {
		return marca;
	}
	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}
	
	@Column(name = "cd_categoria")
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	@Column(name = "fl_ativo")
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@ElementCollection
	@CollectionTable(name = "tab_prod_tagas", joinColumns = @JoinColumn(name = "cd_produto"))
    @Column(name = "cd_tag")
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
