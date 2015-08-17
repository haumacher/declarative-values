package de.haumacher.values.internal;

import de.haumacher.values.internal.ValueDescriptorImpl.ValueImpl;


interface MethodHandler {

	Object handlePropertyAccess(Object self, ValueImpl impl, Object...args);

}
