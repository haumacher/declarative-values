/*
 * Copyright (c) 2015, Bernhard Haumacher. 
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

import java.awt.Color;

import de.haumacher.values.Factory;
import de.haumacher.values.ValueFactory;
import de.haumacher.values.annotate.ValueParser;
import de.haumacher.values.format.ObjectParser;
import junit.framework.TestCase;

/**
 * Test case for the {@link ValueParser} annotation.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 */
@SuppressWarnings("javadoc")
public class TestCustomParser extends TestCase {

	public interface A {

		Factory<A> FACTORY = ValueFactory.newFactory(A.class);

		@ValueParser(ColorParser.class)
		Color getColor1();

		void setColor1(Color value);

		@ValueParser(ColorParser.class)
		Color getColor2();

		void setColor2(Color value);

		class ColorParser extends ObjectParser<Color> {

			@Override
			public Color parse(String text) {
				if (text.charAt(0) != '#') {
					throw new IllegalArgumentException("Color value must start with a '#' char.");
				}
				return new Color(Integer.parseInt(text.substring(1), 16));
			}

			@Override
			public String unparse(Color value) {
				return '#' + prefix(Integer.toHexString(value.getRGB() & 0xFFFFFF));
			}

			private String prefix(String hexString) {
				return "000000".substring(hexString.length()) + hexString;
			}

		}
	}

	public void testColor() {
		A a1 = A.FACTORY.newInstance();
		a1.setColor1(new Color(255, 255, 255));
		assertNull(a1.getColor2());
		A a2 = TestPrimitives.storeLoad(A.class, a1);
		assertNull(a2.getColor2());
	}

}
