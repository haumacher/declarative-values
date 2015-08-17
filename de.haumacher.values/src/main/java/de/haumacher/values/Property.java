package de.haumacher.values;

public interface Property {

	ValueDescriptor<?> getDescriptor();

	String getName();
	
	Kind getKind();

	Class<?> getType();
	
	Property getIndexProperty();

	Parser<Object> getParser();
	
	Initializer<Object> getInitializer();
	
}
