/*
 * Copyright (c) 2012-2015, Bernhard Haumacher. 
 * All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
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