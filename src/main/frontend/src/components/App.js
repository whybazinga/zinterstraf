import React, {Component} from 'react';
import './App.css';
import Header from './header/Header';
import Footer from './footer/Footer';
import { Switch, Route } from 'react-router-dom'


import StartPage from './startPage/StartPage';


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
            <Route exact path='/' component={StartPage}/>
            <Route path='/register' component={StartPage}/>
        </Switch>
    </main>
);
