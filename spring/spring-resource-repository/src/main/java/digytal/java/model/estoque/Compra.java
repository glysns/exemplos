package digytal.java.model.estoque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Compra {
	public Integer id;
	public String nota;
	public List<CompraItem> itens = new ArrayList<CompraItem>();
}
