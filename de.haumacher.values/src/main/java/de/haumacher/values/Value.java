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

/**
 * Common super-interface of all value types.
 * 
 * <p>
 * A value interface describes a structured value by defining properties. A
 * property is defined by its getter method. The return type of the property's
 * getter is the type of the property. The name of the property is derived from
 * the name of it's getter method, see {@link Property#getName()}. A property
 * may have an optional setter method. Even if no setter method is present in a
 * {@link Value} interface, the value of a Property can be updated using the
 * generic {@link #putValue(Property, Object)} method in the common
 * {@link Value} interface.
 * </p>
 * 
 * <p>
 * The {@link Value} interface provides generic access to all properties of the
 * concrete value type.
 * </p>
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version $Revision: $ $Author: $ $Date: $
 */
public interface Value {

	/**
	 * The {@link ValueDescriptor} providing a generic lookup mechanism for
	 * properties of this {@link Value} type.
	 */
	ValueDescriptor<?> descriptor();

	/**
	 * The value of the given {@link Property} of this instance.
	 * 
	 * @param property
	 *        The {@link Property} to access on this instance.
	 * @return The value of the requested property.
	 * 
	 * @see ValueDescriptor#getProperties()
	 */
	Object value(Property property);
	
	/**
	 * Updates the value of the given {@link Property} to the given value.
	 * 
	 * @param property
	 *        The {@link Property} to update.
	 * @param value
	 *        The new value to set for the given property.
	 */
	void putValue(Property property, Object value);
	
}
