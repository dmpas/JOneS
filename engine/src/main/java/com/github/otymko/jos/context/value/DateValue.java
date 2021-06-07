package com.github.otymko.jos.context.value;

import com.github.otymko.jos.context.DataType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

public class DateValue extends GenericValue {
  private static final Predicate<String> IS_EMPTY_DATE = view -> view.equals("00000000") || view.equals("000000000000")
    || view.equals("00000000000000");
  private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

  private final Date value;

  public DateValue(Date value) {
    this.value = value;
    setDataType(DataType.DATE);
  }

  @Override
  public Date asDate() {
    return value;
  }

  @Override
  public String asString() {
    return DEFAULT_FORMAT.format(value);
  }

  @Override
  public int compareTo(Value object) {
    if (object.getDataType() == DataType.DATE) {
      return value.compareTo(object.asDate());
    }
    return super.compareTo(object);
  }

  public static Value parse(String view) {
    Value result;
    String format;
    if (view.length() == 14) {
      format = "yyyyMMddHHmmss";
    } else if (view.length() == 8) {
      format = "yyyyMMdd";
    } else if (view.length() == 12) {
      format = "yyyyMMddHHmm";
    } else {
      throw new RuntimeException("Преобразование к типу 'Дата' не поддерживается");
    }
    if (IS_EMPTY_DATE.test(view)) {
      result = ValueFactory.create(new Date());
    } else {
      Date date;
      try {
        var dateFormat = new SimpleDateFormat(format);
        date = dateFormat.parse(view);
      } catch (ParseException exception) {
        throw new RuntimeException("Преобразование к типу 'Дата' не поддерживается");
      }
      result = ValueFactory.create(date);
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Value)) {
      return false;
    }
    var baseValue = (Value) obj;
    return baseValue.getDataType() == DataType.DATE && value.equals(baseValue.asDate());
  }

}
