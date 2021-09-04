/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context.type.collection.valuetable;

import com.github.otymko.jos.exception.MachineException;
import com.github.otymko.jos.runtime.context.*;
import com.github.otymko.jos.runtime.context.type.DataType;
import com.github.otymko.jos.runtime.context.type.ValueFactory;
import com.github.otymko.jos.runtime.context.type.collection.CollectionIterator;
import com.github.otymko.jos.runtime.context.type.primitive.NumberValue;
import com.github.otymko.jos.runtime.context.type.primitive.StringValue;
import com.github.otymko.jos.runtime.machine.MachineInstance;
import com.github.otymko.jos.runtime.machine.info.ContextInfo;

import java.util.*;

@ContextClass(name = "КоллекцияКолонокТаблицыЗначений", alias="ValueTableColumnCollection")
public class ValueTableColumnCollection extends ContextValue implements IndexAccessor, CollectionIterable<ValueTableColumn> {
  public static final ContextInfo INFO = ContextInfo.createByClass(ValueTableColumnCollection.class);

  private final ValueTable _owner;
  private final List<ValueTableColumn> _columns = new ArrayList<>();
  private final Map<Integer, ValueTableColumn> _ids = new HashMap<>();
  private int _lastColumnId = 0;

  public ValueTableColumnCollection(ValueTable owner) {
    _owner = owner;
  }

  private static String extractStringParam(IValue value, String name) {
    if (value == null) {
      return "";
    }
    final var raw = value.getRawValue();
    try {
      return raw.asString();
    } catch (Exception e) {
      throw new IllegalArgumentException(String.format("Неверный тип параметра %s", name));
    }
  }

  ValueTableColumn makeColumn(IValue name, IValue type, IValue title, IValue width) {
    final var columnName = extractStringParam(name, "Имя");

    if (columnName.isBlank()) {
      // Имя не заполнено
      throw new IllegalArgumentException("Указано неверное имя колонки");
    }
    if (findProperty(columnName) != -1) {
      // Имя уже занято
      throw new IllegalArgumentException("Указано неверное имя колонки");
    }

    _lastColumnId += 1;
    final var newColumn = new ValueTableColumn(this, _lastColumnId, columnName);

    _ids.put(_lastColumnId, newColumn);

    if (type != null) {
      newColumn.setValueType(type);
    }
    if (title != null) {
      newColumn.setTitle((StringValue) ValueFactory.create(extractStringParam(title, "Заголовок")));
    }
    if (width != null) {
      newColumn.setWidth(width);
    }
    return newColumn;
  }

  @ContextMethod(name = "Добавить", alias = "Add")
  public ValueTableColumn add(IValue name, IValue type, IValue title, IValue width) {
    final var newColumn = makeColumn(name, type, title, width);
    _columns.add(newColumn);
    _owner.columnsChanged();
    return newColumn;
  }

  @ContextMethod(name = "Вставить", alias = "Insert")
  public ValueTableColumn insert(IValue index, IValue name, IValue type, IValue title, IValue width) {
    final var newColumn = makeColumn(name, type, title, width);
    _columns.add((int)index.asNumber(), newColumn);
    _owner.columnsChanged();
    return newColumn;
  }

  @ContextMethod(name = "Индекс", alias = "IndexOf")
  public IValue indexOf(IValue pColumn) {
    final var column = pColumn.getRawValue();
    if (!(column instanceof ValueTableColumn)) {
      throw MachineException.typeNotSupportedException("");
    }
    return ValueFactory.create(_columns.indexOf((ValueTableColumn) column));
  }

  @ContextMethod(name = "Количество", alias = "Count")
  public IValue count() {
    return ValueFactory.create(_columns.size());
  }

  @ContextMethod(name = "Найти", alias = "Find")
  public IValue find(IValue columnName) {
    final var column = findColumn(columnName.asString());
    if (column == null) {
      return ValueFactory.create();
    }
    return column;
  }

  @ContextMethod(name = "Очистить", alias = "Clear")
  public void clear() {
    _columns.clear();
    _ids.clear();
    _owner.columnsChanged();
  }

  @ContextMethod(name = "Получить", alias = "Get")
  public IValue get(IValue index) {
    return _columns.get((int)index.asNumber());
  }

  ValueTableColumn getColumn(IValue pColumn) {
    final var column = pColumn.getRawValue();
    if (column instanceof ValueTableColumn) {
      return (ValueTableColumn) column;
    }
    if (column instanceof NumberValue) {
      return _columns.get((int)column.asNumber());
    }
    if (column instanceof StringValue) {
      final var index = findProperty(column.asString());
      return _ids.get(index);
    }
    throw MachineException.getPropertyNotFoundException(""); // TODO: внятное исключение
  }

  // TODO: вынести как helper
  int evalDestination(int indexSource, IValue offset, Collection c) {
    var indexDestination = (indexSource + (int)offset.asNumber()) % c.size();
    while (indexDestination < 0) indexDestination += c.size();
    return indexDestination;
  }

  @ContextMethod(name = "Сдвинуть", alias = "Move")
  public void move(IValue pColumn, IValue offset) {
    final var column = getColumn(pColumn);
    final var indexSource = (int)indexOf(column).asNumber();
    final var indexDestination = evalDestination(indexSource, offset, _columns);

    _columns.remove(indexSource);
    _columns.add(indexDestination, column);
  }

  @ContextMethod(name = "Удалить", alias = "Delete")
  public void delete(IValue pColumn) {
    final var column = getColumn(pColumn);
    _columns.remove(column);
    _ids.remove(column.getId());
    _owner.columnsChanged();
  }

  ValueTableColumn findColumn(String propertyName) {
    for (var p : _columns) {
      if (p.getComparableName().equalsIgnoreCase(propertyName)) {
        return p;
      }
    }
    return null;
  }

  @Override
  public int findProperty(String propertyName) {
    final var column = findColumn(propertyName);
    if (column == null) {
      return -1;
    }
    return column.getId();
  }

  @Override
  public boolean isPropertyReadOnly(int index) {
    return true;
  }

  @Override
  public boolean isPropertyWriteOnly(int index) {
    return false;
  }

  @Override
  public IValue getPropertyValue(int index) {
    final var column = _ids.getOrDefault(index, null);
    if (column == null) {
      throw MachineException.getPropertyNotFoundException("");
    }
    return column;
  }

  @Override
  public ContextInfo getContextInfo() {
    return INFO;
  }

  @Override
  public IteratorValue iterator() {
    return new IteratorValue(new CollectionIterator<>(_columns));
  }

  @Override
  public IValue getIndexedValue(IValue index) {
    return getColumn(index);
  }

  @Override
  public void setIndexedValue(IValue index, IValue value) {
    throw MachineException.getPropertyIsNotWritableException("");
  }

}
