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

import de.haumacher.values.annotate.Name;

/**
 * Description of a single property of a {@link Value} type.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version Since 0.2.0
 */
public interface Property {

	/**
	 * The {@link ValueDescriptor} owning this {@link Property}.
	 */
	ValueDescriptor<?> getDescriptor();

	/**
	 * The name of this {@link Property}.
	 * 
	 * <p>
	 * The name of a {@link Property} is either defined by the getter method in
	 * its {@link Value} type by removing the getter prefix and converting the
	 * first character of the remaining name to lower-case, or by a {@link Name}
	 * annotation on the getter method.
	 * </p>
	 */
	String getName();

	/**
	 * The content type of this {@link Property}.
	 * 
	 * <p>
	 * Values {@link Value#putValue(Property, Object) stored} to this property,
	 * must be compatible with this type.
	 * </p>
	 */
	Class<?> getType();
	
	/**
	 * The content {@link Kind} of this {@link Property}.
	 */
	Kind getKind();

	/**
	 * This {@link Initializer} of this property providing its initial value
	 * after {@link ValueFactory#newInstance(Class) construction}.
	 */
	Initializer<Object> getInitializer();

	/**
	 * The {@link Parser} for a {@link Kind#PRIMITIVE} property that is able to
	 * de-serialize/serialize its value from/to a string representation.
	 */
	Parser<Object> getParser();
	
	/**
	 * For an {@link Kind#INDEX} {@link Property}, the {@link Property} of the
	 * {@link #getType() content type} that is used to index the content
	 * collection.
	 */
	Property getIndexProperty();

}
