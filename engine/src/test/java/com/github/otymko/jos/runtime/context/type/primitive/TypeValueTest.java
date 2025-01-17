/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context.type.primitive;

import com.github.otymko.jos.hosting.ScriptEngine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TypeValueTest {

  @Test
  void test() {
    var engine = new ScriptEngine();

    var stringInfo = engine.getTypeManager().getContextInfoByName("Строка").get();
    var numberInfo = engine.getTypeManager().getContextInfoByName("Число").get();

    var typeOne = new TypeValue(stringInfo);
    var typeTwo = new TypeValue(stringInfo);
    var typeNumber = new TypeValue(numberInfo);

    assertThat(typeOne).isEqualTo(typeTwo);
    assertThat(typeOne).isNotEqualTo(typeNumber);
  }

}