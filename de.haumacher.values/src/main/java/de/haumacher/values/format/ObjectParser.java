package de.haumacher.values.format;

import de.haumacher.values.Initializer;
import de.haumacher.values.Parser;

public abstract class ObjectParser<T> implements Parser<T>, Initializer<T> {
	
	@Override
	public Initializer<T> getDefaultInitializer() {
		return this;
	}
	
	@Override
	public T init() {
		return null;
	}
	
	@Override
	public boolean equals(T value1, T value2) {
		if (value1 == null) {
			return value2 == null;
		} else {
			return value1.equals(value2);
		}
	}

	@Override
	public int hashCode(T value) {
		if (value == null) {
			return 0;
		}

		return value.hashCode();
	}
}