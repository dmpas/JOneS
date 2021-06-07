package com.github.otymko.jos.context.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UndefinedValueTest {

  @Test
  void test() {
    var value = ValueFactory.create();

    assertThat(value).isEqualTo(ValueFactory.create());
    assertThat(value.asString()).isEmpty();

    // TODO: еще тесты
  }

}
