import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './models/App';
import 'bootstrap/dist/css/bootstrap.css'
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
