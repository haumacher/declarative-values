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
 * {@link PropertyHandler} that implements a {@link Property} setter.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 */
class SetHandler extends PropertyHandler {

	public SetHandler(int index) {
		super(index);
	}

	@Override
	public Object handlePropertyAccess(Object self, ValueImpl impl, Object... args) {
		impl.values[index] = args[0];
		return null;
	}

}
