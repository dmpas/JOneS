package com.github.otymko.jos.runtime.context.type;

import com.github.otymko.jos.runtime.context.IValue;
import com.github.otymko.jos.runtime.context.type.primitive.BooleanValue;
import com.github.otymko.jos.runtime.context.type.primitive.DateValue;
import com.github.otymko.jos.runtime.context.type.primitive.NullValue;
import com.github.otymko.jos.runtime.context.type.primitive.NumberValue;
import com.github.otymko.jos.runtime.context.type.primitive.StringValue;
import com.github.otymko.jos.runtime.context.type.primitive.UndefinedValue;

import java.util.Date;

public class ValueFactory {

  private ValueFactory() {
    // none
  }

  public static IValue create() {
    return UndefinedValue.VALUE;
  }

  public static IValue create(String value) {
    return StringValue.create(value);
  }

  public static IValue create(float value) {
    return NumberValue.create(value);
  }

  public static IValue create(int value) {
    return NumberValue.create(value);
  }

  public static IValue create(Date value) {
    return new DateValue(value);
  }

  public static IValue create(boolean value) {
    return value ? BooleanValue.TRUE : BooleanValue.FALSE;
  }

  // invalidValue?

  public static IValue createNullValue() {
    return NullValue.VALUE;
  }

  // object

  public static IValue parse(String view, DataType type) {
    return null;
  }

}