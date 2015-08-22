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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import de.haumacher.values.internal.ValueDescriptorImpl;

/**
 * Central point for analyzing and reflectively instantiating {@link Value}
 * types.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version Since 0.2.0
 */
public class ValueFactory {

	private static final ConcurrentMap<Class<?>, ValueDescriptor<?>> DESCRIPTORS_BY_CLASS = new ConcurrentHashMap<Class<?>, ValueDescriptor<?>>();

	/**
	 * Creates a {@link Factory} for the {@link Value} interface.
	 * 
	 * @param valueInterface
	 *        The interface class describing the {@link Value} type. Even if
	 *        it's not required that the given interface extends {@link Value},
	 *        the instances created by the provided {@link Factory} implicitly
	 *        also implement the generic {@link Value} interface.
	 * @return A {@link Factory} for the given {@link Value} interface.
	 */

	public static synchronized <T> Factory<T> newFactory(final Class<T> valueInterface) {
		final ValueDescriptor<T> descriptor = ValueFactory.getDescriptor(valueInterface);
		return new Factory<T>() {
			@Override
			public T newInstance() {
				return descriptor.newInstance();
			}
		};
	}
	
	/**
	 * Creates a new instance of the given {@link Value} interface.
	 * 
	 * <p>
	 * This method is a short-cut of {@link #getDescriptor(Class)} and
	 * {@link ValueDescriptor#newInstance()}.
	 * </p>
	 * 
	 * @param valueInterface
	 *        The {@link Value} interface to instantiate.
	 * @return A new instance of the given type also implicitly implementing
	 *         {@link Value}.
	 */
	public static synchronized <T> T newInstance(Class<T> valueInterface) {
		return getDescriptor(valueInterface).newInstance();
	}
	
	/**
	 * Looks up the {@link ValueDescriptor} for the given {@link Value}
	 * interface.
	 * 
	 * @param valueInterface
	 *        The value interface to be analyzed.
	 * @return The {@link ValueDescriptor} providing access to the
	 *         {@link Property properties} defined be the given interface.
	 */
	public static synchronized <T> ValueDescriptor<T> getDescriptor(Class<T> valueInterface) {
		@SuppressWarnings("unchecked")
		ValueDescriptor<T> existingDescriptor = (ValueDescriptor<T>) DESCRIPTORS_BY_CLASS.get(valueInterface);
		if (existingDescriptor != null) {
			return existingDescriptor;
		}

		ValueDescriptorImpl<T> newDescriptor = new ValueDescriptorImpl<T>(valueInterface);
		@SuppressWarnings("unchecked")
		ValueDescriptor<T> clash = (ValueDescriptor<T>) DESCRIPTORS_BY_CLASS
				.putIfAbsent(valueInterface, newDescriptor);
		if (clash != null) {
			return clash;
		}

		// Prevent stack overflow in mutable recursive structures.
		newDescriptor.init();
		return newDescriptor;
	}

}
