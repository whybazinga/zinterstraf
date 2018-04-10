export const loginConst = Object.freeze({
  signInUrl: '/oauth/token',
  getAuthUserUrl: '/user/get-current-user',
  tokenFlows: {
    passwordFlow: {
      grantType: 'password',
      clientId: 'clientIdPassword',
      clientSecret: 'secret'
    },
    clientCredFlow: {
      grantType: 'client_credentials',
    },
  },
  signInRequest: {
    username: 'username',
    password: 'password',
    grantType: 'grant_type'
  },
  signInResponse: {
    accessToken: 'access_token',
    refreshToken: 'refresh_token',
    refreshTokenExpires: 365*24,
    expires: 'expires_in',
    error: 'error',
    errorDescription: 'error_description',
    errorDescriptionDefaultVal: 'Something went wrong during login'
  },
  authBenefits: [
    'Fantasy EGA League game',
    'Exclusive fan services',
    'Customised site content',
    'Favourite Club information and notifications'
  ],
  loginButtons: {
    system: {val: 'system', name: 'Log in'},
    facebook: {val: 'facebook', name: 'Facebook'},
    vk: {val: 'vk', name: 'VK'},
    google: {val: 'google', name: 'Google'},
    twitter: {val: 'twitter', name: 'Twitter'}
  }
});