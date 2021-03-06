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
package de.haumacher.values.internal;

import de.haumacher.values.Property;
import de.haumacher.values.internal.ValueDescriptorImpl.ValueImpl;

/**
 * {@link MethodHandler} for a certain {@link Property}.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 */
abstract class PropertyHandler implements MethodHandler {

	/**
	 * The storage index of the property's value.
	 * 
	 * @see ValueImpl#values
	 */
	protected final int index;

	public PropertyHandler(int index) {
		this.index = index;
	}
	
}
