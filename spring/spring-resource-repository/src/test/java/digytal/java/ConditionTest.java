package digytal.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import digytal.java.infra.sql.Condition;

public class ConditionTest {
	@Test
	public void validateTrueFalseTest() {
		Map<String, Object>filters = new HashMap<String,Object>();
		filters.put("valido", true);
		
		List<Condition> conditions = filters.entrySet().stream().map(c->{
			return Condition.of(c);
		}).collect(Collectors.toList());
		
		assertEquals(true, conditions.get(0).value);
		
		
	}
	@Test
	public void validateTest() {
		Map<String, Object>filters = new HashMap<String,Object>();
		filters.put("tema", "1");
		filters.put("preco", "2.75");
		
		List<Condition> conditions = filters.entrySet().stream().map(c->{
			return Condition.of(c);
		}).collect(Collectors.toList());
		
		assertEquals(1, conditions.get(1).value);
		assertEquals(2.75, conditions.get(0).value);
		
		
	}
	// https://docs.microsoft.com/pt-br/dotnet/standard/base-types/quantifiers-in-regular-expressions
	//validar
	//comparar
	//formatar
	@Test
	public void regexIntTest() {
		Object value="-167";
		String exp="-?\\d+";
		boolean isInt=value.toString().matches(exp);
		
		assertTrue(isInt);
		assertEquals(-167, Integer.valueOf(value.toString()));
		
		value="9876544";
		isInt=value.toString().matches(exp);
		
		assertTrue(isInt);
		assertEquals(9876544, Integer.valueOf(value.toString()));
	}
	@Test
	public void regexDoubleTest() {
		Object value="-1.67";
		String exp="-?\\d+\\.\\d+";
		boolean isInt=value.toString().matches(exp);
		
		assertTrue(isInt);
		assertEquals(-1.67, Double.valueOf(value.toString()));
		
		value="1234.569";
		isInt=value.toString().matches(exp);
		
		assertTrue(isInt);
		assertEquals(1234.569, Double.valueOf(value.toString()));
		
		
	}
}
