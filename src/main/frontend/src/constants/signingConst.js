export const signingConst = Object.freeze({
  signInUrl: '/oauth/token',
  signUpUrl: '/user/register',
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
  signInResponse: {
    accessToken: 'access_token',
    refreshToken: 'refresh_token',
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
    system: { val: 'system', name: 'Log in'},
    facebook: {val: 'facebook', name: 'Facebook'},
    vk: {val: 'vk', name: 'VK'},
    google: {val: 'google', name: 'Google'},
    twitter: {val: 'twitter', name: 'Twitter'}
  }
});