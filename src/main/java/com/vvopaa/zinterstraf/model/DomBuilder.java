package com.vvopaa.zinterstraf.model;

import org.jsoup.nodes.Element;

public interface DomBuilder extends Builder {
  String TXT_WRITTEN = "Local param was written. Exit from function.";
  String SRC_ATTRIBUTE = "src";

  void buildByDomElements(Element domEl);
}
