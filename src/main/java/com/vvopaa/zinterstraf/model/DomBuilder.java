package com.vvopaa.zinterstraf.model;

import org.jsoup.nodes.Element;

public interface DomBuilder extends Builder {

  DomBuilder buildByDomElements(Element domEl);
}
