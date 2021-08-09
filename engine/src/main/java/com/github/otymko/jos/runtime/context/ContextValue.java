/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context;

import com.github.otymko.jos.runtime.RuntimeContext;
import com.github.otymko.jos.runtime.context.type.DataType;

import java.util.Date;

public abstract class ContextValue implements IValue, ContextType, RuntimeContext {

  @Override
  public float asNumber() {
    throw new RuntimeException("Not supported");
  }

  @Override
  public Date asDate() {
    throw new RuntimeException("Not supported");
  }

  @Override
  public boolean asBoolean() {
    throw new RuntimeException("Not supported");
  }

  @Override
  public String asString() {
    return getContextInfo().getName();
  }

  @Override
  public IValue getRawValue() {
    return this;
  }

  @Override
  public DataType getDataType() {
    return DataType.TYPE;
  }

  @Override
  public int compareTo(IValue o) {
    throw new RuntimeException("Not supported");
  }

}
