/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context;

import com.github.otymko.jos.runtime.IVariable;
import com.github.otymko.jos.runtime.RuntimeContext;
import com.github.otymko.jos.runtime.machine.info.MethodInfo;

public interface AttachableContext extends RuntimeContext, ContextType {

  default MethodInfo[] getMethods() {
    return getContextInfo().getMethods();
  }

  default IVariable[] getVariables() {
    return new IVariable[0];
  }

}
