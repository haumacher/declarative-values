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
package de.haumacher.values;

import java.util.HashMap;
import java.util.Map;

import de.haumacher.values.internal.ValueDescriptorImpl;

public class ValueFactory {

	private static final Map<Class<?>, ValueDescriptorImpl<?>> DESCRIPTORS_BY_CLASS = new HashMap<Class<?>, ValueDescriptorImpl<?>>();

	public static synchronized <T> Factory<T> newFactory(final Class<T> valueInterface) {
		return new Factory<T>() {
			@Override
			public T newInstance() {
				return ValueFactory.newInstance(valueInterface);
			}
		};
	}
	
	public static synchronized <T> T newInstance(Class<T> valueInterface) {
		return getDescriptor(valueInterface).newInstance();
	}
	
	public static synchronized <T> ValueDescriptor<T> getDescriptor(Class<T> valueInterface) {
		@SuppressWarnings("unchecked")
		ValueDescriptorImpl<T> result = (ValueDescriptorImpl<T>) DESCRIPTORS_BY_CLASS.get(valueInterface);
		if (result == null) {
			result = new ValueDescriptorImpl<T>(valueInterface);
			DESCRIPTORS_BY_CLASS.put(valueInterface, result);
			
			// Prevent stack overflow in mutable recursive structures.
			result.init();
		}
		return result;
	}

}
