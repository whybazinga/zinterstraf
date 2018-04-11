import React from 'react';
import {appGlobal,fetchPostJsonResponse} from "./appGlobal";

it('getting url with no errors', () => {
  console.log(appGlobal.func.getFullUrlByPath(appGlobal.auth.signInUrl));

  console.log(appGlobal.func.getFormEncodedParams({
    'username': 'test',
    'password': 'test1',
    'grant_type': 'test2'
  }));

  fetchPostJsonResponse('http://localhost:8080/oauth/token', {
      'username': 't@t.t',
      'password': '12345',
      'grant_type': appGlobal.auth.grantType
  }).then((json) => {
    console.log(json['access_token'] || json);
  }).catch((error) => {
    console.log('Error: ' + error.message)
  });
});