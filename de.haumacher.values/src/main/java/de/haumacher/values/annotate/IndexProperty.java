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
package de.haumacher.values.annotate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.haumacher.values.Kind;

/**
 * Annotation to a {@link Kind#INDEX} property providing the name of the
 * content's property for indexing the value collection.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version Since 0.2.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IndexProperty {

	/**
	 * Property name used for indexing a collection-valued property.
	 * 
	 * <p>
	 * A property <code>xs</code> declared as <code>Map<K, X> getXs()</code>
	 * must be annotated with the {@link IndexProperty} to be used as keys in
	 * the mapping. Semantically, the value of such a property consists of a
	 * collection of <code>X</code> values. At the API level, this collection is
	 * indexed by the value of the annotated {@link IndexProperty}.
	 * </p>
	 */
	String value();

}
