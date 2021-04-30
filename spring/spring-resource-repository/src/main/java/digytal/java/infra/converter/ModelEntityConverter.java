package digytal.java.infra.converter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

class ModelEntityConverter extends ModelConveter {
	ModelEntityConverter(Object src) {
		super(src);
		// TODO Auto-generated constructor stub
	}
	public Object newInstance() throws Exception{
		Class srcClass = src.getClass();
		Object other =Class.forName(srcClass.getPackage().getName()+"."+srcClass.getSimpleName()+"Entity").newInstance();
		return converter(other);
	}
	@Override
	public Object converter(Object other) throws Exception {
		for(Field field: src.getClass().getDeclaredFields()) {
			Object value= field.get(src);
			if(value!=null) {
				if(isCollection(value)) {
					List list = get(field, other);
					for(Object i: (List) value) {
						add(list, i); //convert(i)
					}
				}else {
					if(isDomainClass(field.getType())) {
						value = getInstance(value).newInstance();
					}
					set(other,field.getName(),value);
				}
				
			}
		}
		return other;
	}
	

}
