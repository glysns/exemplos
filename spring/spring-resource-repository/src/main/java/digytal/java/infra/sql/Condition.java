package digytal.java.infra.sql;
import java.util.Map.Entry;

public class Condition {
	public String field;
	public Operator comparator = Operator.EQUALS;
	public Object value;
	public Operator logic = Operator.AND;
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

	public static Condition of(String field,Object value) {
		return of(field,Operator.EQUALS, value);
	}
	public static Condition of(String field, Operator comparator, Object value) {
		return of(field,comparator, value,true, Operator.AND);
	}
	public static Condition of(Entry<String, Object> condition) {
		return of(condition,Operator.EQUALS);
	}
	public static Condition of(Entry<String, Object> condition, Operator comparator) {
		return of(condition, comparator,Operator.AND);
	}
	public static Condition of(Entry<String, Object> condition, Operator comparator, Operator logic) {
		return of(condition.getKey(), comparator, condition.getValue(), true, logic);
	}
	public static Condition of(String field, Operator comparator, Object value, boolean like, Operator logic) {
		Object v = validate(value, like);
		Condition condition = new Condition();
		comparator = like?Operator.LIKE:comparator;
		condition.comparator=comparator;
		condition.logic=logic;
		condition.field=field;
		condition.value=v;
		return condition;
		
	}
	private static Object validate(Object value, boolean like) {
		if(value !=null && !value.toString().isEmpty()) {
			String v = value.toString();
			if(v.matches("-?\\d+"))
				value = Integer.valueOf(v);
			else if(v.matches("-?\\d+\\.\\d+"))
				value = Double.valueOf(v);
			else if(like) {
				value="%" + value+"%";
			}
			System.out.println(value.getClass());
			return value ;
		}else
			return null;
		
	}
	@Override
	public String toString() {
		return "Condition [field=" + field + ", comparator=" + comparator + ", value=" + value + ", logic=" + logic
				+ "]";
	}
	
}