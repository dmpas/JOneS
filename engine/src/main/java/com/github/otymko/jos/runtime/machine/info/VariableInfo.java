package com.github.otymko.jos.runtime.machine.info;

import lombok.Value;

/**
 * Информация о переменной для исполнения
 */
@Value
public class VariableInfo {
  String name;
  // тип переменной: переменная / свойство типа
}