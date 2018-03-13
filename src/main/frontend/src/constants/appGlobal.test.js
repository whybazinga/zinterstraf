import React from 'react';
import {appGlobal} from "./appGlobal";

it('getting url with no errors', () => {
  console.log(appGlobal.common.func.getFullUrlByPath(appGlobal.auth.signInUrl));

  console.log(appGlobal.common.func.getFormEncodedParams({
    'username': 'test',
    'password': 'test1',
    'grant_type': 'test2'
  }));

  fetch('http://localhost:8080' + appGlobal.auth.signInUrl, {
    method: appGlobal.common.methods.POST,
    headers: appGlobal.auth.getAuthHeader,
    body: appGlobal.common.func.getFormEncodedParams({
      'username': 't@t.t',
      'password': '12345',
      'grant_type': appGlobal.auth.grantType
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