import {AUTH_CONST} from "../constants/actionTypes";

const initialState = {
  authUser: {},
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case AUTH_CONST.AUTHENTICATE:
      return { ...state, authUser: action.payload};
    case AUTH_CONST.LOGOUT:
      return {...state, authUser: action.payload};
    default:
      return state;
  }
};

export default reducer;