import React, {Component} from 'react';
import './App.css';
import Header from './header/Header';
import Footer from './footer/Footer';
import Content from './content/StartPage';

class App extends Component {
  render() {
    return (
      <div className="d-flex flex-column app">
        <Header />
        <Content/>
        <Footer/>
      </div>
    );
  }
}

export default App;
