import { createStore, applyMiddleware } from "redux";
import { createReducer } from "../reducers/index";
import thunk from 'redux-thunk';

const store = createStore(createReducer, applyMiddleware(thunk));

export default store;
