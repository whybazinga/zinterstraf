export const settings = (() => {
  return Object.freeze({
    auth: {
      signInUrl: '/oauth/token',
      grantType: 'password',
      getAuthHeader: {
        'Authorization': 'Basic '+btoa("clientIdPassword:secret"),
        'Content-type': "application/x-www-form-urlencoded; charset=utf-8"
      }
    },
    common: {
      methods: {
        POST: 'post',
        GET: 'get'
      },
      func: {
        getFullUrlByPath: (path) => (window.location.protocol + '//' + window.location.host + path),
        getFormEncodedParams: (json) => ( Object.keys(json).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(json[key])).join('&') )
      }
    },
    isDebug: true
  });

  
})();