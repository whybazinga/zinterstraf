import React, {Component} from 'react';
import './App.css';
import Header from './components/header/Header';
import Footer from './components/footer/Footer';
import { Switch, Route } from 'react-router-dom'


import Start from './components/startPage/StartPage';
import SignIn from './containers/signing/SignIn'

class App extends Component {
  render() {
    return (
      <div className="d-flex flex-column app">
        <Header />
        <Content />
        <Footer />
      </div>
    );
  }
}

export default App;

const Content = () => (
    <main>
        <Switch>
            <Route exact path='/' component={Start}/>
            <Route path='/sign-in' component={SignIn}/>
        </Switch>
    </main>
);
