package com.github.otymko.jos.context;

import com.github.otymko.jos.ScriptEngine;
import com.github.otymko.jos.compiler.image.ModuleImage;
import lombok.Getter;

/**
 * Абстрактная реализациия объекта скрипта
 */
public abstract class ScriptDrivenObject implements RuntimeContextInstance {
  @Getter
  private final ModuleImage moduleImage;

  protected ScriptDrivenObject(ModuleImage moduleImage) {
    this.moduleImage = moduleImage;
  }

  public void initialize(ScriptEngine engine) {
    engine.getMachine().executeModuleBody(this);
  }

}
