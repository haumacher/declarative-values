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


import java.util.Properties;

import de.haumacher.values.Value;
import de.haumacher.values.ValueFactory;
import de.haumacher.values.properties.PropertiesUtil;
import junit.framework.TestCase;

/**
 * Test case for primitive properties of {@link Value} interfaces.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 */
@SuppressWarnings("javadoc")
public class TestPrimitives extends TestCase {
	public interface AllPrimitives {
		
		boolean getBoolean();
		void setBoolean(boolean value);
		
		byte getByte();
		void setByte(byte value);
		
		char getChar();
		void setChar(char value);
		
		int getInt();
		void setInt(int value);
		
		long getLong();
		void setLong(long value);
		
		float getFloat();
		void setFloat(float value);
		
		double getDouble();
		void setDouble(double value);
	}
	
	public interface AllPrimitiveWrappers {
		
		Boolean getBoolean();
		void setBoolean(Boolean value);
		
		Byte getByte();
		void setByte(Byte value);
		
		Character getChar();
		void setChar(Character value);
		
		Integer getInt();
		void setInt(Integer value);
		
		Long getLong();
		void setLong(Long value);
		
		Float getFloat();
		void setFloat(Float value);
		
		Double getDouble();
		void setDouble(Double value);
	}
	
	public interface A {

		String getX();
		void setX(String value);
		
		B getB();
		void setB(B value);
		
		interface B {
			String getY();
			void setY(String value);
		}
		
	}
	
	public void testPrimitiveNullValues() {
		AllPrimitives p = ValueFactory.newInstance(AllPrimitives.class);
		assertNull(p);
	}

	private void assertNull(AllPrimitives p) {
		assertEquals(false, p.getBoolean());
		assertEquals((byte)0, p.getByte());
		assertEquals((char)0, p.getChar());
		assertEquals(0, p.getInt());
		assertEquals(0L, p.getLong());
		assertEquals(0F, p.getFloat());
		assertEquals(0D, p.getDouble());
	}

	public void testPrimitiveWrapperNullValues() {
		AllPrimitiveWrappers v = ValueFactory.newInstance(AllPrimitiveWrappers.class);
		assertNull(v);
		assertNull(storeLoad(AllPrimitiveWrappers.class, v));
	}

	private void assertNull(AllPrimitiveWrappers p) {
		assertNull(p.getBoolean());
		assertNull(p.getByte());
		assertNull(p.getChar());
		assertNull(p.getInt());
		assertNull(p.getLong());
		assertNull(p.getFloat());
		assertNull(p.getDouble());
	}

	public void testPrimitiveValues() {
		AllPrimitives v = ValueFactory.newInstance(AllPrimitives.class);
		
		fill(v);
		assertFilled(v);
		assertFilled(storeLoad(AllPrimitives.class, v));
		clear(v);
		assertNull(v);
	}

	private void fill(AllPrimitives p) {
		p.setBoolean(true);
		p.setByte((byte) 2);
		p.setChar((char) 3);
		p.setInt(4);
		p.setLong(5L);
		p.setFloat(6F);
		p.setDouble(7D);
	}

	private void clear(AllPrimitives p) {
		p.setBoolean(false);
		p.setByte((byte) 0);
		p.setChar((char) 0);
		p.setInt(0);
		p.setLong(0L);
		p.setFloat(0F);
		p.setDouble(0D);
	}
	
	private void assertFilled(AllPrimitives p) {
		assertEquals(true, p.getBoolean());
		assertEquals((byte)2, p.getByte());
		assertEquals((char)3, p.getChar());
		assertEquals(4, p.getInt());
		assertEquals(5L, p.getLong());
		assertEquals(6F, p.getFloat());
		assertEquals(7D, p.getDouble());
	}

	public void testPrimitiveWrapperValues() {
		AllPrimitiveWrappers v = ValueFactory.newInstance(AllPrimitiveWrappers.class);
		
		fill(v);
		assertFilled(v);
		assertFilled(storeLoad(AllPrimitiveWrappers.class, v));
		clear(v);
		assertNull(v);
	}

	private void fill(AllPrimitiveWrappers p) {
		p.setBoolean(true);
		p.setByte((byte) 2);
		p.setChar((char) 3);
		p.setInt(4);
		p.setLong(5L);
		p.setFloat(6F);
		p.setDouble(7D);
	}
	
	private void clear(AllPrimitiveWrappers p) {
		p.setBoolean(null);
		p.setByte(null);
		p.setChar(null);
		p.setInt(null);
		p.setLong(null);
		p.setFloat(null);
		p.setDouble(null);
	}

	private void assertFilled(AllPrimitiveWrappers p) {
		assertEquals((Object)true, p.getBoolean());
		assertEquals((Object)(byte)2, p.getByte());
		assertEquals((Object)(char)3, p.getChar());
		assertEquals((Object)4, p.getInt());
		assertEquals((Object)5L, p.getLong());
		assertEquals(6F, p.getFloat());
		assertEquals(7D, p.getDouble());
	}

	public void testNested() {
		A v = ValueFactory.newInstance(A.class);
		
		assertNull(v);
		assertNull(storeLoad(A.class, v));
		fill(v);
		assertFilled(v);
		assertFilled(storeLoad(A.class, v));
	}

	private void assertNull(A v) {
		assertNull(v.getX());
		assertNull(v.getB().getY());
	}

	private void fill(A v) {
		v.setX("X");
		v.getB().setY("Y");
	}

	private void assertFilled(A v) {
		assertEquals("X", v.getX());
		assertEquals("Y", v.getB().getY());
	}
	
	public static <T> T storeLoad(Class<T> type, T value) {
		Properties storage = new Properties();
		PropertiesUtil.save(storage, (Value) value);
		T result = ValueFactory.newInstance(type);
		PropertiesUtil.load(storage, (Value) result);
		
		assertEquals(value, result);
		
		return result;
	}

}
