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
package de.haumacher.values.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Common utility methods.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version Since 0.2.0
 */
public class Util {

	/**
	 * Null-safe equality comparison.
	 */
	public static boolean equals(Object o1, Object o2) {
		if (o1 == null) {
			return o2 == null;
		} else {
			return o1.equals(o2);
		}
	}

	/**
	 * Replaces <code>null</code> with the empty string.
	 */
	public static String nonNull(String s) {
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

	/**
	 * Whether the given {@link String} is <code>null</code> or empty.
	 */
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		} else {
			return s.length() == 0;
		}
	}
	
	/**
	 * Whether the given {@link String} is neither <code>null</code> nor empty.
	 */
	public static boolean notEmpty(String s) {
		if (s == null) {
			return false;
		} else {
			return s.length() > 0;
		}
	}

	/**
	 * Replaces the empty {@link Collection} with <code>null</code>.
	 */
	public static <T> Collection<T> nonEmpty(Collection<T> collection) {
		if (collection.isEmpty()) {
			return null;
		}
		return collection;
	}

	/**
	 * Replaces <code>null</code> with the empty {@link Collection}.
	 */
	public static <T> Collection<T> nonNull(Collection<T> collection) {
		if (collection == null) {
			return Collections.emptyList();
		}
		return collection;
	}

	/**
	 * Replaces the empty {@link List} with <code>null</code>.
	 */
	public static <T> List<T> nonEmpty(List<T> collection) {
		if (collection.isEmpty()) {
			return null;
		}
		return collection;
	}
	
	/**
	 * Repleces <code>null</code> with the empty {@link List}.
	 */
	public static <T> List<T> nonNull(List<T> collection) {
		if (collection == null) {
			return Collections.emptyList();
		}
		return collection;
	}
	
	/**
	 * Replaces the empty {@link Set} with <code>null</code>.
	 */
	public static <T> Set<T> nonEmpty(Set<T> collection) {
		if (collection.isEmpty()) {
			return null;
		}
		return collection;
	}

	/**
	 * Replaces <code>null</code> with the empty set.
	 */
	public static <T> Set<T> nonNull(Set<T> collection) {
		if (collection == null) {
			return Collections.emptySet();
		}
		return collection;
	}

	/**
	 * Converts a collection of {@link String}s to a {@link String} array.
	 */
	public static String[] toStringArray(Collection<String> list) {
		return list.toArray(new String[list.size()]);
	}
	
}
