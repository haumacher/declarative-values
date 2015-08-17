package de.haumacher.values;

public interface Parser<T> {
	T parse(String text);

	String unparse(T value);

	boolean equals(T value1, T value2);

	int hashCode(T value);

	Initializer<T> getDefaultInitializer();

}