package digytal.java.infra.sql;

import java.util.ArrayList;
import java.util.List;

import digytal.java.model.produto.ProdutoView;

public class Condition {
	public String field;
	public Operator comparator = Operator.EQUALS;
	public Object value;
	public Operator logic = Operator.AND;
	//https://docs.microsoft.com/pt-br/sql/t-sql/language-elements/comparison-operators-transact-sql?view=sql-server-ver15
	public enum Operator {
		AND("AND"),
		OR("OR"),
		EQUALS("="), 
		BETWEEN("BETWEEN"),
		GREATER_THAN(">"),
		LESS_THAN("<"), 
		GREATER_EQUAL(">="), 
		LESS_EQUAL("<="), 
		NOT_EQUAL("!="),
		LIKE("LIKE"), 
		EXISTS("EXISTS"),
		IN("IN"),
		IS("IS"),
		EMPTY(""),
		; 
		
		String symbol;
		
		Operator(String op) {
			this.symbol = op;
		}		
	}
	public static Condition condition(String field, Object value) {
		return condition(field, Condition.Operator.EQUALS, value, Condition.Operator.AND);
	}
	public static Condition condition(String field, Condition.Operator comparator, Object value) {
		return condition(field, comparator, value, Condition.Operator.AND);
	}
	public static Condition condition(String field, Condition.Operator comparator, Object value, Condition.Operator logic) {
		return condition(field, comparator, value, true, logic);
	}
	public static Condition condition(String field, Condition.Operator comparator, Object value, boolean like, Condition.Operator logic) {
		Object v = value;
		if( like && value instanceof String) {
			comparator = Condition.Operator.LIKE;
			v="%" + value+"%";
		}
		Condition condition = new Condition();
		condition.comparator=comparator;
		condition.logic=logic;
		condition.field=field;
		condition.value=v;
		return condition;
		
	}
	public static void main(String[] args) throws Exception {
		List<Condition> conditions = new ArrayList<Condition>();
		Condition c = new Condition();
		c.field="nome";
		c.value="FORD";
		conditions.add(c);
		String sql = JPQLUtil.of(ProdutoView.class).conditions(conditions).sql();
		System.out.println(sql);
	}

}
