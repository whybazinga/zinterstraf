package com.vvopaa.zinterstraf.util;


import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpUtil {
  // LATER
  public static ResponseEntity<?> requestExternalUrl(Map<String, String> header, Map<String, String> body, String url) {
    RestTemplate template = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    header.forEach(headers::add);
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
    headers.add("Accept", MediaType.APPLICATION_JSON.toString());

    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
    requestBody.add("message_id", "msgid");
    requestBody.add("message", "qwerty");
    requestBody.add("client_id", "111");
    requestBody.add("secret_key", "222");

    HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

    return template.exchange("http://www.dota2.com/procircuit", HttpMethod.POST, formEntity, String.class);


  }
}
