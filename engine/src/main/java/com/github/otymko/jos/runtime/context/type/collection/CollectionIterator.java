/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.context.type.collection;

import com.github.otymko.jos.runtime.context.IValue;

import java.util.Collection;
import java.util.Iterator;

public class CollectionIterator<T extends IValue>implements Iterator<IValue> {

  private final Iterator<T> _innerIterator;

  public CollectionIterator(Collection<T> collection) {
    _innerIterator = collection.iterator();
  }

  @Override
  public boolean hasNext() {
    return _innerIterator.hasNext();
  }

  @Override
  public IValue next() {
    return _innerIterator.next();
  }
}
