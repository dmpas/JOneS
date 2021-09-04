/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context.type.collection.valuetable;

import com.github.otymko.jos.exception.MachineException;
import com.github.otymko.jos.runtime.context.*;
import com.github.otymko.jos.runtime.context.type.ValueFactory;
import com.github.otymko.jos.runtime.context.type.collection.CollectionIterator;
import com.github.otymko.jos.runtime.context.type.primitive.NumberValue;
import com.github.otymko.jos.runtime.machine.info.ContextInfo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ContextClass(name = "ТаблицаЗначений", alias = "ValueTable")
public class ValueTable extends ContextValue implements IndexAccessor, CollectionIterable<ValueTableRow> {
  public static final ContextInfo INFO = ContextInfo.createByClass(ValueTable.class);

  private final List<ValueTableRow> _rows = new ArrayList<>();

  private ValueTable() {
    columns = new ValueTableColumnCollection(this);
  }

  @ContextConstructor
  public static ValueTable constructor() {
    return new ValueTable();
  }

  @Getter
  @ContextProperty(name = "Колонки", alias = "Columns", accessMode = PropertyAccessMode.READ_ONLY)
  public final ValueTableColumnCollection columns;

  @ContextMethod(name = "Добавить", alias = "Add")
  public ValueTableRow add() {
    final var row = new ValueTableRow(this);
    _rows.add(row);
    return row;
  }

  @ContextMethod(name = "Вставить", alias = "Insert")
  public ValueTableRow insert(IValue index) {
    final var row = new ValueTableRow(this);
    _rows.add((int)index.asNumber(), row);
    return row;
  }

  @ContextMethod(name = "Индекс", alias = "IndexOf")
  public IValue indexOf(IValue pRow) {
    final var row = pRow.getRawValue();
    if (!(row instanceof ValueTableRow)) {
      throw MachineException.typeNotSupportedException("");
    }
    return ValueFactory.create(_rows.indexOf((ValueTableRow) row));
  }

  @ContextMethod(name = "Количество", alias = "Count")
  public IValue count() {
    return ValueFactory.create(_rows.size());
  }


  @ContextMethod(name = "Очистить", alias = "Clear")
  public void clear() {
    _rows.clear();
  }

  @ContextMethod(name = "Получить", alias = "Get")
  public IValue get(IValue index) {
    return _rows.get((int)index.asNumber());
  }

  ValueTableRow getRow(IValue pRow) {
    final var row = pRow.getRawValue();
    if (row instanceof ValueTableRow) {
      return (ValueTableRow) row;
    }
    if (row instanceof NumberValue) {
      return _rows.get((int)row.asNumber());
    }
    throw MachineException.typeNotSupportedException(""); // TODO: внятное исключение
  }

  // TODO: вынести как helper
  int evalDestination(int indexSource, IValue offset, Collection c) {
    var indexDestination = (indexSource + (int)offset.asNumber()) % c.size();
    while (indexDestination < 0) indexDestination += c.size();
    return indexDestination;
  }

  @ContextMethod(name = "Сдвинуть", alias = "Move")
  public void move(IValue pRow, IValue offset) {
    final var row = getRow(pRow);
    final var indexSource = _rows.indexOf(row);
    final var indexDestination = evalDestination(indexSource, offset, _rows);

    _rows.remove(indexSource);
    _rows.add(indexDestination, row);
  }

  @ContextMethod(name = "Удалить", alias = "Delete")
  public void delete(IValue pRow) {
    final var row = getRow(pRow);
    _rows.remove(row);
  }

  public void columnsChanged() {

  }

  @Override
  public ContextInfo getContextInfo() {
    return INFO;
  }

  @Override
  public IteratorValue iterator() {
    return new IteratorValue(new CollectionIterator<>(_rows));
  }

  @Override
  public IValue getIndexedValue(IValue index) {
    return _rows.get((int)index.asNumber());
  }

  @Override
  public void setIndexedValue(IValue index, IValue value) {
    throw MachineException.getPropertyIsNotWritableException("");
  }
}
