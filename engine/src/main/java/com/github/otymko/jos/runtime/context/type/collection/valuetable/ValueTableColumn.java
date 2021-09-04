/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context.type.collection.valuetable;

import com.github.otymko.jos.exception.MachineException;
import com.github.otymko.jos.runtime.context.ContextClass;
import com.github.otymko.jos.runtime.context.ContextProperty;
import com.github.otymko.jos.runtime.context.ContextValue;
import com.github.otymko.jos.runtime.context.IValue;
import com.github.otymko.jos.runtime.context.type.ValueFactory;
import com.github.otymko.jos.runtime.context.type.primitive.StringValue;
import com.github.otymko.jos.runtime.machine.info.ContextInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@ContextClass(name="КолонкаТаблицыЗначений", alias="ValueTableColumn")
public class ValueTableColumn extends ContextValue {
  public static final ContextInfo INFO = ContextInfo.createByClass(ValueTableColumn.class);

  private final ValueTableColumnCollection _owner;


  ValueTableColumn(ValueTableColumnCollection pOwner, int pId, String pName) {
    _owner = pOwner;
    comparableName = pName.toLowerCase();
    name = (StringValue) ValueFactory.create(pName);
    title = (StringValue) ValueFactory.create("");
    width = ValueFactory.create(0);
    id = pId;
  }

  @Getter
  private final int id;

  @Getter
  @Setter
  @ContextProperty(name = "Заголовок", alias = "Title")
  private StringValue title;

  @Getter
  @ContextProperty(name = "Имя", alias = "Name")
  public StringValue name;

  public void setName(IValue value) {
    final var existingColumn = _owner.findProperty(value.asString());
    if (existingColumn != -1) {
      throw MachineException.invalidPropertyNameStructureException(value.asString());
    }
    name = (StringValue) value;
    comparableName = name.asString().toLowerCase();
  }

  @Getter
  private String comparableName;

  @Getter
  @Setter
  @ContextProperty(name = "ТипЗначения", alias = "ValueType")
  private IValue valueType = null;

  @Getter
  @Setter
  @ContextProperty(name = "Ширина", alias = "Width")
  private IValue width;

  @Override
  public ContextInfo getContextInfo() {
    return INFO;
  }
}
