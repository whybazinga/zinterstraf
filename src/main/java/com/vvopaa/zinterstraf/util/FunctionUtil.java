package com.vvopaa.zinterstraf.util;

import java.util.Optional;

public class FunctionUtil {

  private static FunctionUtil instance;


  public static FunctionUtil getInstance() {
    if(instance == null){
      synchronized (FunctionUtil.class) {
        if(instance == null){
          instance = new FunctionUtil();
        }
      }
    }
    return instance;
  }


  public FunctionUtil runFunctionIfTrue(boolean isRun, Runnable func) {
    Optional<Boolean> optBool = Optional.of(isRun).filter(b -> b);
    optBool.ifPresent(b -> func.run());
    return optBool.isPresent() ? null : this;
  }

}
