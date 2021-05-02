package digytal.java.model.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("select p.*, m.nome as marca, c.nome as categoria from tab_produto p inner join tab_marca m on "
		+ " p.cd_marca = m.id inner join tab_categoria c on p.cd_categoria = c.id")
public class ProdutoView {
	@Id
	public Integer id;
	
	@Column(name = "nome")
	public String nome;
	
	@Column(name = "cd_categoria")
	public Integer categoriaId;
	
	@Column(name = "categoria")
	public String categoriaNome;
	
	@Column(name = "cd_marca")
	public Integer marcaId;
	
	@Column(name = "marca")
	public String marcaNome;
	
	@Column(name = "fl_ativo")
	public boolean ativo;
	
	@Column(name = "ano_fabric")
	public Integer anoFabricacao;
	
	public Double preco;
	
}
