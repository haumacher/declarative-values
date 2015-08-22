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

import de.haumacher.values.annotate.DefaultValue;

/**
 * Load/store algorithm for transforming the value of a {@link Kind#PRIMITIVE}
 * {@link Property} from/to its string representation.
 * 
 * @param <T>
 *        The {@link Property#getType() content type} of the using
 *        {@link Property}.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version $Revision: $ $Author: $ $Date: $
 */
public interface Parser<T> {

	/**
	 * Loads the {@link Property properties} value from its string
	 * representation.
	 * 
	 * @param text
	 *        The serialized form of the {@link Property} value.
	 * @return The parsed value used in the application.
	 */
	T parse(String text);

	/**
	 * Stores the {@link Property properties} value to its string
	 * representation.
	 * 
	 * @param value
	 *        The application value.
	 * @return The string representation used in serialized form.
	 */
	String unparse(T value);

	/**
	 * Implementation for the {@link Object#equals(Object)} method used when
	 * comparing {@link Property} values for equality.
	 * 
	 * @param value1
	 *        The first value to compare.
	 * @param value2
	 *        The second value to compare.
	 * @return Whether the first and second value are equal.
	 */
	boolean equals(T value1, T value2);

	/**
	 * Implementation of the {@link Object#hashCode()} method used when
	 * computing the hash code for {@link Property} values.
	 * 
	 * @param value
	 *        The property value.
	 * @return The hash code to use in the hash code of a {@link Value} object.
	 */
	int hashCode(T value);

	/**
	 * The default {@link Initializer} for primitive types this {@link Parser}
	 * operates on.
	 * 
	 * <p>
	 * This default {@link Initializer} is used, if the {@link Property} getter
	 * has no {@link DefaultValue} annotation providing a custom
	 * {@link Initializer}.
	 * </p>
	 * 
	 * @see DefaultValue#initializer()
	 */
	Initializer<T> getDefaultInitializer();

}