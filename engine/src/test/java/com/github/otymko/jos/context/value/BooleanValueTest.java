/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.context.value;

import com.github.otymko.jos.runtime.context.type.ValueFactory;
import com.github.otymko.jos.runtime.context.type.primitive.BooleanValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanValueTest {

  @Test
  void test() {
    assertThat(BooleanValue.TRUE.asBoolean()).isTrue();
    assertThat(BooleanValue.FALSE.asBoolean()).isFalse();

    assertThat(BooleanValue.TRUE).isSameAs(ValueFactory.create(true));
    assertThat(BooleanValue.FALSE).isSameAs(ValueFactory.create(false));

    assertThat(0f).isEqualTo(BooleanValue.FALSE.asNumber().floatValue());
    assertThat(1f).isEqualTo(BooleanValue.TRUE.asNumber().floatValue());

    assertThat(BooleanValue.TRUE.compareTo(BooleanValue.FALSE) > 0).isTrue();

    // TODO: boolean -> date, boolean -> object
  }

}
