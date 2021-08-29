/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context.type.collection;

import com.github.otymko.jos.exception.MachineException;
import com.github.otymko.jos.runtime.context.ContextClass;
import com.github.otymko.jos.runtime.context.ContextProperty;
import com.github.otymko.jos.runtime.context.ContextValue;
import com.github.otymko.jos.runtime.context.IValue;
import com.github.otymko.jos.runtime.context.IndexAccessor;
import com.github.otymko.jos.runtime.context.PropertyAccessMode;
import com.github.otymko.jos.runtime.machine.info.ContextInfo;

@ContextClass(name = "КлючИЗначение", alias = "KeyAndValue")
public class V8KeyAndValue extends ContextValue implements IndexAccessor {
  public static final ContextInfo INFO = ContextInfo.createByClass(V8KeyAndValue.class);

  @ContextProperty(name = "Ключ", alias = "Key", accessMode = PropertyAccessMode.READ_ONLY)
  public final IValue key;

  @ContextProperty(name = "Значение", alias = "Value", accessMode = PropertyAccessMode.READ_ONLY)
  public final IValue value;

  public V8KeyAndValue(IValue key, IValue value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public ContextInfo getContextInfo() {
    return INFO;
  }

  @Override
  public IValue getIndexedValue(IValue index) {
    var name = index.asString();
    var position = findProperty(name);
    return getPropertyValue(position);
  }

  @Override
  public void setIndexedValue(IValue index, IValue value) {
    throw MachineException.getPropertyIsNotWritableException(index.asString());
  }

}