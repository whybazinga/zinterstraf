import React from 'react';
import {settings} from "./settings";

it('getting url with no errors', () => {
  console.log(settings.common.func.getFullUrlByPath(settings.auth.signInUrl));

  console.log(settings.common.func.getFormEncodedParams({
    'username': 'test',
    'password': 'test1',
    'grant_type': 'test2'
  }));

  fetch('http://localhost:8080' + settings.auth.signInUrl, {
    method: settings.common.methods.POST,
    headers: settings.auth.getAuthHeader,
    body: settings.common.func.getFormEncodedParams({
      'username': 't@t.t',
      'password': '12345',
      'grant_type': settings.auth.grantType
    })
  }).then((response) => {
    for(let i in response) {
      console.log(i);
    }
    console.log(response);
  }).catch((error) => {
    console.log('Error: ' + error.message)
  });
});