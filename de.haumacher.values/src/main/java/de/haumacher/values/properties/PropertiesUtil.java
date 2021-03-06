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
package de.haumacher.values.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import de.haumacher.values.Initializer;
import de.haumacher.values.Parser;
import de.haumacher.values.Property;
import de.haumacher.values.Value;
import de.haumacher.values.ValueDescriptor;
import de.haumacher.values.ValueFactory;

/**
 * Utility methods to load/store {@link Value} types from/to {@link Properties}
 * files.
 * 
 * @author <a href="mailto:haui@haumacher.de">Bernhard Haumacher</a>
 * @version Since 0.2.0
 */
public class PropertiesUtil {

	private static final Map<Class<?>, Class<?>> wrapperTypes = new HashMap<Class<?>, Class<?>>();
	private static final int FIRST_INDEX = 1;
	static {
		wrapperTypes.put(boolean.class, Boolean.class);
		wrapperTypes.put(byte.class, Byte.class);
		wrapperTypes.put(short.class, Short.class);
		wrapperTypes.put(char.class, Character.class);
		wrapperTypes.put(int.class, Integer.class);
		wrapperTypes.put(long.class, Long.class);
		wrapperTypes.put(float.class, Float.class);
		wrapperTypes.put(double.class, Double.class);
	}

	/**
	 * From the given {@link Properties} file, load an instance of the given
	 * {@link Value} type.
	 * 
	 * @param fileName
	 *        The name of the {@link Properties} file to load.
	 * @param type
	 *        The type defining the typed properties.
	 * @return An instance of the given {@link Value} type providing typed
	 *         access to the defined properties.
	 * @throws IOException
	 *         If accessing the file fails.
	 * 
	 * @see #load(Properties, Class)
	 */
	public static <T extends Value> T load(String fileName, Class<T> type) throws IOException {
		return load(loadProperties(fileName), type);
	}

	/**
	 * Provides typed access to the given {@link Properties}.
	 * 
	 * @param properties
	 *        The generic key/value pairs to interpret.
	 * @param type
	 *        The type defining the typed properties.
	 * @return An instance of the given {@link Value} type providing typed
	 *         access to the given untyped generic properties.
	 * 
	 * @see #load(Properties, String, Class)
	 */
	public static <T extends Value> T load(Properties properties, Class<T> type) {
		return load(properties, "", type);
	}
	
	/**
	 * Like {@link #load(String, Class)}, but interpreting only properties whose
	 * key starts with the given prefix.
	 * 
	 * @param fileName
	 *        The name of the {@link Properties} file to load.
	 * @param prefix
	 *        The prefix to strip from each key in the given {@link Properties}
	 *        file. Properties with keys that have not the given prefix are
	 *        ignored.
	 * @param type
	 *        The type defining the typed properties.
	 * @return An instance of the given {@link Value} type providing typed
	 *         access to the defined properties.
	 * @throws IOException
	 *         If accessing the file fails.
	 * 
	 * @see #load(Properties, String, Class)
	 */
	public static <T extends Value> T load(String fileName, String prefix, Class<T> type) throws IOException {
		return load(loadProperties(fileName), prefix, type);
	}

	/**
	 * Like {@link #load(Properties, Class)}, but interpreting only properties
	 * whose key starts with the given prefix.
	 * 
	 * @param properties
	 *        The generic key/value pairs to interpret.
	 * @param prefix
	 *        The prefix to strip from each key in the given {@link Properties}
	 *        file. Properties with keys that have not the given prefix are
	 *        ignored.
	 * @param type
	 *        The type defining the typed properties.
	 * @return An instance of the given {@link Value} type providing typed
	 *         access to the given untyped generic properties.
	 */
	public static <T extends Value> T load(Properties properties, String prefix, Class<T> type) {
		ValueDescriptor<T> descriptor = ValueFactory.getDescriptor(type);
		T obj = descriptor.newInstance();
		
		load(properties, prefix, obj);
		
		return obj;
	}
	
	/**
	 * Applies the contents of the given {@link Properties} file to the given
	 * {@link Value} instance.
	 * 
	 * @param fileName
	 *        The name of the {@link Properties} file to load.
	 * @param obj
	 *        The {@link Value} instance to update with the contents of the
	 *        given {@link Properties} file.
	 * @throws IOException
	 *         If accessing the file fails.
	 * 
	 * @see #load(Properties, Value)
	 */
	public static void load(String fileName, Value obj) throws IOException {
		load(loadProperties(fileName), obj);
	}

	/**
	 * Applies the contents of the given untyped {@link Properties} to the given
	 * {@link Value} instance.
	 * 
	 * @param properties
	 *        The untyped {@link Properties} contents.
	 * @param obj
	 *        The {@link Value} instance to update with the contents of the
	 *        given {@link Properties}.
	 * 
	 * @see #load(Properties, String, Value)
	 */
	public static void load(Properties properties, Value obj) {
		load(properties, "", obj);
	}
	
	/**
	 * Applies the contents of the given untyped {@link Properties} file to the
	 * given {@link Value} instance.
	 * 
	 * @param fileName
	 *        File name of an untyped {@link Properties} file.
	 * @param prefix
	 *        The prefix to strip from each key in the given {@link Properties}
	 *        file. Properties with keys that have not the given prefix are
	 *        ignored.
	 * @param obj
	 *        The {@link Value} instance to update with the contents of the
	 *        given {@link Properties}.
	 */
	public static void load(String fileName, String prefix, Value obj) throws IOException {
		load(loadProperties(fileName), prefix, obj);
	}

	/**
	 * Applies the contents of the given untyped {@link Properties} to the given
	 * {@link Value} instance.
	 * 
	 * @param properties
	 *        The untyped {@link Properties} contents.
	 * @param prefix
	 *        The prefix to strip from each key in the given {@link Properties}.
	 *        Properties with keys that have not the given prefix are ignored.
	 * @param obj
	 *        The {@link Value} instance to update with the contents of the
	 *        given {@link Properties}.
	 */
	public static void load(Properties properties, String prefix, Value obj) {
		StringBuilder keyBuffer = new StringBuilder();
		keyBuffer.append(prefix);
		loadValue(properties, keyBuffer, obj);
	}
	
	/**
	 * Stores the given {@link Value} object to a {@link Properties} file.
	 * 
	 * @param fileName
	 *        File name of an untyped {@link Properties} file to create.
	 * @param obj
	 *        {@link Value} instance to store.
	 * @throws IOException
	 *         If accessing the file fails.
	 */
	public static void save(String fileName, Value obj) throws IOException {
		storeProperties(save(new Properties(), obj), fileName);
	}

	/**
	 * Stores the given {@link Value} object into an untyped {@link Properties}
	 * map.
	 * 
	 * @param properties
	 *        Untyped {@link Properties} map to modify.
	 * @param obj
	 *        {@link Value} instance to store.
	 */
	public static Properties save(Properties properties, Value obj) {
		return save(properties, "", obj);
	}

	/**
	 * Stores the given {@link Value} object into an untyped {@link Properties}
	 * file.
	 * 
	 * @param fileName
	 *        File name of an untyped {@link Properties} file to create.
	 * @param prefix
	 *        The prefix to prepend each generated properties key with.
	 * @param obj
	 *        {@link Value} instance to store.
	 */
	public static void save(String fileName, String prefix, Value obj) throws IOException {
		storeProperties(save(new Properties(), prefix, obj), fileName);
	}

	/**
	 * Stores the given {@link Value} object into an untyped {@link Properties}
	 * map.
	 * 
	 * @param properties
	 *        Untyped {@link Properties} map to modify.
	 * @param prefix
	 *        The prefix to prepend each generated properties key with.
	 * @param obj
	 *        {@link Value} instance to store.
	 */
	public static Properties save(Properties properties, String prefix, Value obj) {
		StringBuilder keyBuffer = new StringBuilder();
		keyBuffer.append(prefix);
		saveValue(properties, keyBuffer, obj);
		return properties;
	}

	private static void saveValue(Properties properties, StringBuilder keyBuffer, Value obj) {
		ValueDescriptor<?> descriptor = obj.descriptor();
		int len1 = keyBuffer.length();
		for (Property property : descriptor.getProperties().values()) {
			Object value = obj.value(property);
			String propertyName = property.getName();
			
			switch (property.getKind()) {
			case PRIMITIVE: {
				keyBuffer.append(propertyName);
				
				savePrimitive(properties, keyBuffer, property, value);
				break;
			}
			
			case VALUE: {
				keyBuffer.append(propertyName);
				keyBuffer.append('.');
				
				saveValue(properties, keyBuffer, (Value) value);
				break;
			}
			
			case LIST: {
				keyBuffer.append(propertyName);
				keyBuffer.append('.');
				
				List<?> list = (List<?>) value;
				int len2 = keyBuffer.length();
				int n = FIRST_INDEX;
				for (Object entry : list) {
					keyBuffer.append(n++);
					keyBuffer.append('.');
					
					saveValue(properties, keyBuffer, (Value) entry);
					keyBuffer.setLength(len2);
				}
				break;
			}
			
			case INDEX: {
				keyBuffer.append(propertyName);
				keyBuffer.append('.');

				Map<?, ?> map = (Map<?, ?>) value;
				int len2 = keyBuffer.length();
				int n = FIRST_INDEX;
				for (Entry<?, ?> entry : map.entrySet()) {
					keyBuffer.append(n++);
					keyBuffer.append('.');
					
					saveValue(properties, keyBuffer, (Value) entry.getValue());
					keyBuffer.setLength(len2);
				}
				break;
			}
			
			case REFERENCE: {
				break;
			}
			}
			
			keyBuffer.setLength(len1);
		}
	}

	private static void savePrimitive(Properties properties, StringBuilder keyBuffer, Property property, Object value) {
		if (value == null) {
			properties.remove(keyBuffer.toString());
		} else {
			properties.setProperty(keyBuffer.toString(), property.getParser().unparse(value));
		}
	}
	
	private static void loadValue(Properties properties, StringBuilder keyBuffer, Value obj) {
		ValueDescriptor<?> descriptor = obj.descriptor();
		int len1 = keyBuffer.length();
		for (Property property : descriptor.getProperties().values()) {
			String propertyName = property.getName();
			
			switch (property.getKind()) {
			case PRIMITIVE: {
				keyBuffer.append(propertyName);
				
				Object value = loadPrimitive(properties, keyBuffer, property);
				obj.putValue(property, value);
				break;
			}
			
			case VALUE: {
				keyBuffer.append(propertyName);
				keyBuffer.append('.');
				
				loadValue(properties, keyBuffer, (Value) obj.value(property));
				break;
			}
			
			case LIST: {
				keyBuffer.append(propertyName);
				keyBuffer.append('.');
				
				@SuppressWarnings("unchecked")
				List<Value> list = (List<Value>) obj.value(property);
				
				Value nullValue = (Value) ValueFactory.getDescriptor(property.getType()).newInstance();

				int len2 = keyBuffer.length();
				int n = FIRST_INDEX;
				while (true) {
					keyBuffer.append(n++);
					keyBuffer.append('.');
					
					Value entryValue = (Value) ValueFactory.getDescriptor(property.getType()).newInstance();
					loadValue(properties, keyBuffer, entryValue);
					
					if (entryValue.equals(nullValue)) {
						break;
					}
					
					list.add(entryValue);
					
					keyBuffer.setLength(len2);
				}
				break;
			}
			
			case INDEX: {
				keyBuffer.append(propertyName);
				keyBuffer.append('.');
				
				@SuppressWarnings("unchecked")
				Map<Object, Value> map = (Map<Object, Value>) obj.value(property);

				Value nullValue = (Value) ValueFactory.getDescriptor(property.getType()).newInstance();

				int len2 = keyBuffer.length();
				int n = FIRST_INDEX;
				while (true) {
					keyBuffer.append(n++);
					keyBuffer.append('.');
					
					Value entryValue = (Value) ValueFactory.getDescriptor(property.getType()).newInstance();
					loadValue(properties, keyBuffer, entryValue);
					
					if (nullValue.equals(entryValue)) {
						break;
					}
					
					Object key = entryValue.value(property.getIndexProperty());
					map.put(key, entryValue);
					
					keyBuffer.setLength(len2);
				}
				break;
			}
			
			case REFERENCE:
				continue;
			}
			
			keyBuffer.setLength(len1);
		}
	}
	
	private static Object loadPrimitive(Properties properties, StringBuilder keyBuffer, Property property) {
		String valueSource = properties.getProperty(keyBuffer.toString());
		
		if (valueSource == null) {
			Initializer<Object> initializer = property.getInitializer();
			return initializer.init();
		} else {
			Parser<Object> parser = property.getParser();
			return parser.parse(valueSource);
		}
	}

	/**
	 * Reads the given {@link Properties} file.
	 * 
	 * @param fileName
	 *        The file name to access.
	 * @return The untyped {@link Properties} read.
	 * @throws IOException
	 *         If accessing the file fails.
	 */
	public static Properties loadProperties(String fileName) throws IOException {
		Properties result = new Properties();
		FileInputStream in = new FileInputStream(fileName);
		try {
			result.load(in);
		} finally {
			in.close();
		}
		return result;
	}

	/**
	 * Stores the given untyped {@link Properties} to a file.
	 * 
	 * @param properties
	 *        The {@link Properties} to store.
	 * @param fileName
	 *        The file name to create.
	 * @throws IOException
	 *         If accessing the file fails.
	 * 
	 * @see #storeProperties(Properties, String, String)
	 */
	public static void storeProperties(Properties properties, String fileName) throws IOException {
		storeProperties(properties, fileName, null);
	}

	/**
	 * Stores the given untyped {@link Properties} to a file.
	 * 
	 * @param properties
	 *        The {@link Properties} to store.
	 * @param fileName
	 *        The file name to create.
	 * @param comment
	 *        The file header comment to use.
	 * @throws IOException
	 *         If accessing the file fails.
	 */
	public static void storeProperties(Properties properties, String fileName, String comment) throws IOException {
		FileOutputStream out = new FileOutputStream(fileName);
		try {
			properties.store(out, comment);
		} finally {
			out.close();
		}
	}

}
