import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import store from "./store/index";
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <Provider store={store}>
      <BrowserRouter>
        <App/>
      </BrowserRouter>
    </Provider>,
    div);
  ReactDOM.unmountComponentAtNode(div);
});

