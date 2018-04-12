import React, {Component} from 'react';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem} from 'reactstrap';
import { Link } from 'react-router-dom'
import uuidv1 from "uuid";
import PropTypes from 'prop-types'

import './header.css'
import egaHome from'../../images/ega.png';
import {appGlobal} from "../../constants/appGlobal";
import {routerUrls} from "../../constants/routerUrls";


export default class Header extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.scrollTop = this.scrollTop.bind(this);
    this.renderAuthButton = this.renderAuthButton.bind(this);
    this.state = {
      isOpen: false,
      headerUrls: [
        routerUrls.home, routerUrls.matches, routerUrls.results,
        routerUrls.tables, routerUrls.teams, routerUrls.social
      ]
    };
  }

  renderAuthButton() {
    appGlobal.func.checkIfEmptyJson(this.props.authUser)

    return (
      <NavItem className="auth-btn">
        <Link className="span nav-link" to='/login'>Sign in</Link>
      </NavItem>
    )
  }

  scrollTop(e) {
    e.preventDefault();
    const scrollTo = (element, to, duration) => {
      if (duration <= 0) return;
      let difference = to - element.scrollTop;
      let perTick = difference / duration * 10;

      setTimeout(() => {
        element.scrollTop = element.scrollTop + perTick;
        if (parseInt(element.scrollTop, 10) === to) return;
        scrollTo(element, to, duration - 10);
      }, 10);
    };
    scrollTo(document.documentElement, 0, 300);
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
          <Navbar color="faded" expand="md" className="header-nav" light>
            <NavbarBrand onClick={this.scrollTop} href="#"><img src={egaHome} className="logo-home" alt="logo-header"/></NavbarBrand>
            <NavbarToggler onClick={this.toggle} />
            <Collapse className="header-links" isOpen={this.state.isOpen} navbar>
              <Nav navbar>
                {headerUrls.map( el => (
                  <NavItem key={uuidv1()}>
                    <Link className="nav-link" to={el.url}>{el.name}</Link>
                  </NavItem>
                ))}
                <NavItem>
                  <Link className="nav-link" to="/swagger-ui">Swagger</Link>
                </NavItem>
                <NavItem className="register">
                  <Link className="span nav-link" to='/login'>Sign in</Link>
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

Header.propTypes = {
  authUser: PropTypes.object.isRequired
};
