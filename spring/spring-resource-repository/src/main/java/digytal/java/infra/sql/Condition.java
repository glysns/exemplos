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

	public static Condition of(String field,Object value,Class ... clsEnum) {
		return of(field,Operator.EQUALS, value,clsEnum);
	}
	public static Condition of(String field, Operator comparator, Object value,Class ... clsEnum) {
		return of(field,comparator, value,true, Operator.AND,clsEnum);
	}
	public static Condition of(Entry<String, Object> condition,Class ... clsEnum) {
		return of(condition,Operator.EQUALS,clsEnum);
	}
	public static Condition of(Entry<String, Object> condition, Operator comparator,Class ... clsEnum) {
		return of(condition, comparator,Operator.AND,clsEnum);
	}
	public static Condition of(Entry<String, Object> condition, Operator comparator, Operator logic, Class ... clsEnum) {
		return of(condition.getKey(), comparator, condition.getValue(), true, logic,clsEnum);
	}
	public static Condition of(String field, Operator comparator, Object value, boolean like, Operator logic, Class ... clsEnum) {
		value = prepare(value, like);
		Condition condition = new Condition();
		if(clsEnum.length>0)
			value = Enum.valueOf(clsEnum[0], value.toString().toUpperCase().replaceAll("\\%", ""));
		else if( like && value instanceof String) {
			comparator = Operator.LIKE;
		}
		
		condition.field=field;
		condition.comparator=comparator;
		condition.value=value;
		condition.logic=logic;
		return condition;
		
	}
	private static Object prepare(Object value, boolean like) {
		if(value !=null && !value.toString().isEmpty()) {
			String v = value.toString();
			if(v.matches("-?\\d+"))
				value = Integer.valueOf(v);
			else if(v.matches("-?\\d+\\.\\d+"))
				value = Double.valueOf(v);
			else if(v.matches("true|false"))
				value = Boolean.valueOf(v);
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