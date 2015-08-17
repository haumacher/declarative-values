package de.haumacher.values.internal;

import de.haumacher.values.internal.ValueDescriptorImpl.ValueImpl;



class SetHandler extends PropertyHandler {

	public SetHandler(int index) {
		super(index);
	}

	@Override
	public Object handlePropertyAccess(Object self, ValueImpl impl, Object[] args) {
		impl.values[index] = args[0];
		return null;
	}

}
