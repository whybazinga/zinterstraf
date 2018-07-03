package com.vvopaa.zinterstraf.service;

import java.util.List;

public interface ServiceTemplate <T> {
  List<T> saveList(List<T> entityList);
}
