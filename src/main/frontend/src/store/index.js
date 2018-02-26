import { createStore } from "redux";
import { createReducer } from "../reducers/index";

const store = createStore(createReducer);

export default store;
