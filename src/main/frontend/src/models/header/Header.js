import React, {Component} from 'react';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem} from 'reactstrap';
import './header.css'
import donutHome from'./donut-home.png';
import { Link } from 'react-router-dom'


export default class Header extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.state = {
      isOpen: false
    };
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  render() {
    return (
      <header>
        <div className="navigation-fixed">
          <Navbar color="faded" expand="md" className="header-nav" dark>
            <NavbarBrand href="#"><img src={donutHome} className="rounded-circle logo-home" /></NavbarBrand>
            <NavbarToggler onClick={this.toggle} />
            <Collapse isOpen={this.state.isOpen} navbar>
              <Nav className="ml-auto" navbar>
                <NavItem>
                  <Link className="span nav-link" to='/'>Home</Link>
                </NavItem>
                <NavItem>
                  <Link className="span nav-link" to='/register'>Register</Link>
                </NavItem>
              </Nav>
            </Collapse>
          </Navbar>
        </div>
        <div className="navigation-empty"/>
      </header>
    );
  }
}
