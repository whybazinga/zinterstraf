package com.vvopaa.zinterstraf.util;

public class FunctionUtil {

  private static FunctionUtil instance;


  public static FunctionUtil getInstance() {
    if (instance == null) {
      synchronized (FunctionUtil.class) {
        if (instance == null) {
          instance = new FunctionUtil();
        }
      }
    }
    return instance;
  }


  public FunctionUtil runFunctionIfTrue(boolean isRun, Runnable func) {
    if (isRun) {
      func.run();
      return this;
    }
    return null;
  }

}
