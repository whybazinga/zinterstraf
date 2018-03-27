import {AUTH_CONST} from "../constants/actionTypes";

const initialState = {
  authUser: {},
  isAuthenticated: false
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case AUTH_CONST.AUTHENTICATE:
      return { ...state, authUser: action.payload, isAuthenticated: true };
    case AUTH_CONST.LOGOUT:
      return {...state, authUser: {}, isAuthenticated: false};
    default:
      return state;
  }
};

export default reducer;