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
package test.de.haumacher.values;

import de.haumacher.values.Factory;
import de.haumacher.values.Property;
import de.haumacher.values.Value;
import de.haumacher.values.ValueFactory;
import de.haumacher.values.annotate.Name;
import junit.framework.TestCase;

/**
 * Test the {@link Name} annotation for properties.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 */
@SuppressWarnings("javadoc")
public class TestExplicitPropertyName extends TestCase {
	
	public interface A extends Value {
		
		Factory<A> FACTORY = ValueFactory.newFactory(A.class);
		
		String getFoo();
		
		@Name("barProperty")
		String getBar();
		
		boolean isOk();
	}
	
	public interface NonUniqueNames extends Value {
		
		@Name("xxx")
		String getFoo();
		
		@Name("xxx")
		String getBar();
		
	}
	
	public void testName() {
		A a1 = A.FACTORY.newInstance();
		
		Property fooProperty = a1.descriptor().getProperties().get("foo");
		assertNotNull(fooProperty);
		
		a1.putValue(fooProperty, "foo-value");
		assertEquals("foo-value", a1.getFoo());
		
		Property okProperty = a1.descriptor().getProperties().get("ok");
		assertNotNull(okProperty);

		assertEquals(false, a1.isOk());
		a1.putValue(okProperty, true);
		assertEquals(true, a1.isOk());

		Property barProperty = a1.descriptor().getProperties().get("barProperty");
		assertNotNull(barProperty);
		
		a1.putValue(barProperty, "bar-value");
		assertEquals("bar-value", a1.getBar());
		
		Property notBarProperty = a1.descriptor().getProperties().get("bar");
		assertNull(notBarProperty);
	}
	
	public void testUniquenessCheck() {
		try {
			ValueFactory.getDescriptor(NonUniqueNames.class);
			fail("Must not allow properties with non-unique names.");
		} catch (IllegalArgumentException ex) {
			assertContains("unique", ex.getMessage());
		}
	}

	private static void assertContains(String part, String message) {
		assertTrue("Does not contain '" + part + "': " + message, message.contains(part));
		
	}


}
