import React, {Component} from 'react';
import './App.css';
import Header from './components/header/Header';
import Footer from './components/footer/Footer';
import {
  Route,
  Link,
  Redirect,
  withRouter,
  Switch
} from 'react-router-dom'

import StartPage from './components/startPage/StartPage';
import SignIn from './containers/signing/SignIn';

class App extends Component {
  render() {
    return (
      <div className="d-flex flex-column app">
        <Header/>
        <Content/>
        <Footer/>
      </div>
    );
  }
}

export default App;

const Content = () => (
  <main className="content">
    <Switch>
      <Route exact path='/' component={StartPage}/>
      <Route path='/sign-in' component={SignIn}/>
      <PrivateRoute path='/protected' component={StartPage}/>
    </Switch>
  </main>
);


const fakeAuth = {
  isAuthenticated: false,
  authenticate(cb) {
    this.isAuthenticated = true;
    setTimeout(cb, 100)
  },
  signout(cb) {
    this.isAuthenticated = false;
    setTimeout(cb, 100)
  }
};

const PrivateRoute = ({component: Component, ...rest}) => (
  <Route {...rest} render={(props) => (
    fakeAuth.isAuthenticated === true
      ? <Component {...props} />
      : <Redirect to='/sign-in'/>
  )}/>
);