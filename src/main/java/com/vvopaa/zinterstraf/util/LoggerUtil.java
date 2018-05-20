package com.vvopaa.zinterstraf.util;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;

public class LoggerUtil {

  public static <T> String logClassFunctionInfo(Class<T> className) {
    Optional<Class<T>> optClassName = Optional.ofNullable(className);
    String methodName = optClassName.isPresent() ? optClassName.get().getEnclosingMethod().getName() : "No Class was given";
    return MessageFormat.format("{0} done at: {1}", methodName, LocalDateTime.now());
  }

}
