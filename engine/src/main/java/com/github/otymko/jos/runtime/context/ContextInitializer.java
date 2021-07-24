package com.github.otymko.jos.runtime.context;

import com.github.otymko.jos.runtime.context.global.SystemGlobalContext;
import com.github.otymko.jos.runtime.context.label.ContextMethod;
import com.github.otymko.jos.runtime.machine.MachineInstance;
import com.github.otymko.jos.runtime.machine.info.MethodInfo;
import com.github.otymko.jos.runtime.machine.info.ParameterInfo;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ContextInitializer {

  public void initialize(MachineInstance machineInstance) {
    machineInstance.implementContext(new SystemGlobalContext());
  }

  public MethodInfo[] getContextMethods(Class<? extends RuntimeContextInstance> targetClass) {
    List<MethodInfo> methods = new ArrayList<>();
    for (var method : targetClass.getMethods()) {
      var contextMethod = method.getAnnotation(ContextMethod.class);
      if (contextMethod == null) {
        continue;
      }
      var parameters = getMethodParameters(method);
      var info = new MethodInfo(contextMethod.name(), contextMethod.alias(), false, parameters, method);
      methods.add(info);
    }
    return methods.toArray(new MethodInfo[0]);
  }

  public ParameterInfo[] getMethodParameters(Method method) {
    var length = method.getParameters().length;
    if (length == 0) {
      return new ParameterInfo[0];
    }
    var parameters = new ParameterInfo[length];
    var index = 0;
    for (var parameter : method.getParameters()) {
      var name = parameter.getName();
      var parameterInfo = new ParameterInfo(name);
      parameters[index] = parameterInfo;
      index++;
    }
    return parameters;
  }

}