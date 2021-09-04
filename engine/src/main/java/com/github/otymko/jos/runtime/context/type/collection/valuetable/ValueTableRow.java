/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context.type.collection.valuetable;

import com.github.otymko.jos.runtime.context.ContextClass;
import com.github.otymko.jos.runtime.context.ContextValue;
import com.github.otymko.jos.runtime.context.IValue;
import com.github.otymko.jos.runtime.context.type.ValueFactory;
import com.github.otymko.jos.runtime.machine.info.ContextInfo;

import java.util.HashMap;
import java.util.Map;

@ContextClass(name = "СтрокаТаблицыЗначений", alias = "ValueTableRow")
public class ValueTableRow extends ContextValue {
  public static final ContextInfo INFO = ContextInfo.createByClass(ValueTableRow.class);

  private final ValueTable _owner;
  private final Map<Integer, IValue> _data = new HashMap<>();

  public ValueTableRow(ValueTable pOwner) {
    _owner = pOwner;
    fillDefaultValues();
  }

  @Override
  public int findProperty(String propertyName) {
    return _owner.getColumns().findProperty(propertyName);
  }

  @Override
  public boolean isPropertyWriteOnly(int index) {
    return false;
  }

  @Override
  public boolean isPropertyReadOnly(int index) {
    return false;
  }

  @Override
  public IValue getPropertyValue(int index) {
    return _data.getOrDefault(index, ValueFactory.create());
  }

  IValue typeCast(int columnIndex, IValue sourceValue) {
    // TODO: Мы должны приводить значение к типу, указанному у колонки
    return sourceValue;
  }

  @Override
  public void setPropertyValue(int index, IValue value) {
    _data.put(index, typeCast(index, value));
  }

  public void deletePropertyValue(int index) {
    _data.remove(index);
  }

  private void fillDefaultValues() {
  }

  @Override
  public ContextInfo getContextInfo() {
    return INFO;
  }
}
