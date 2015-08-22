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

import java.lang.reflect.InvocationHandler;

import de.haumacher.values.Value;
import de.haumacher.values.internal.ValueDescriptorImpl.ValueImpl;

/**
 * Implementation of a single method of a {@link Value} instance.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 */
interface MethodHandler {

	/**
	 * Handles the invocation of a single method within an
	 * {@link InvocationHandler} for a generic proxy implementation of a
	 * {@link Value} type.
	 * 
	 * @param self
	 *        The proxy implementing the concrete {@link Value} instance.
	 * @param impl
	 *        The internal object providing the state an method implementations.
	 * @param args
	 *        The method arguments
	 * @return The result of the implemented method.
	 */
	Object handlePropertyAccess(Object self, ValueImpl impl, Object...args);

}
