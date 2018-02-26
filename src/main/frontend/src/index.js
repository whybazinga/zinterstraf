import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';
import 'bootstrap/dist/css/bootstrap.css'
import registerServiceWorker from './registerServiceWorker';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from "./store/index";
//import { addMatch } from "./actions/matchActions";

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>,
  document.getElementById('root')
);
registerServiceWorker();
/*
window.store = store;
console.log(store.getState());
store.subscribe(() => console.log('Look ma, Redux!!'));
store.dispatch( addMatch({ name: 'EG vs VG', id: 1 }) );

window.addMatch = addMatch;
*/