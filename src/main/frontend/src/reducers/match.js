import {ADD_MATCH} from "../constants/actionTypes";

const initialState = {
  matches: [],
  abc: [
    'fds'
  ]
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_MATCH:
      return { ...state, matches: [...state.matches, action.payload] };
    default:
      return state;
  }
};

export default reducer;