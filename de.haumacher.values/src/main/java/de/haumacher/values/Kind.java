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

import java.util.List;
import java.util.Map;

/**
 * Classification of {@link Property properties}.
 * 
 * @see Property#getKind()
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version $Revision: $ $Author: $ $Date: $
 */
public enum Kind {

	/**
	 * A primitive (unstructured) property that is represented as a single
	 * string in serialized form.
	 * 
	 * <p>
	 * The {@link Property#getType()} of a primitive property not necessarily is
	 * a Java primitive type. Instead, a primitive property is considered atomic
	 * - not a {@link Value} type itself. Primitive properties are read and
	 * written to by {@link Parser} implementations.
	 * </p>
	 * 
	 * @see Property#getParser()
	 */
	PRIMITIVE,

	/**
	 * A property whose type itself is of a structured {@link Value} type.
	 * 
	 * <p>
	 * The content of a {@link Property} of kind value is considered a
	 * containment relation. The value referenced by a {@link Kind#VALUE}
	 * property is considered a contents of the {@link Value} owning that
	 * property.
	 * </p>
	 */
	VALUE,

	/**
	 * A property that consists of a {@link List} of structured {@link Value}
	 * types.
	 */
	LIST,

	/**
	 * A property that consists of an indexed collection of structured
	 * {@link Value} type.
	 * 
	 * <p>
	 * The content type of an indexed property is {@link Map}. The value type of
	 * this map is {@link Property#getType()}. This key type of this map is the
	 * type of the property referenced by {@link Property#getIndexProperty()}.
	 * </p>
	 */
	INDEX,

	/**
	 * Like {@link Kind#VALUE} but not implying a containment relation.
	 * 
	 * <p>
	 * Currently not implemented.
	 * </p>
	 */
	REFERENCE;
	
}
