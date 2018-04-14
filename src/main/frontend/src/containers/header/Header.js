import React, {Component} from 'react';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, ButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem} from 'reactstrap';
import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'

import './header.css'
import egaHome from'../../images/ega.png';
import {routerUrls} from "../../AppRouter";
import {checkUserUrlRole, isUserAuthorized} from "../../utils/authUtils";


export default class Header extends Component {
  constructor(props) {
    super(props);
    this.toggleNav = this.toggleNav.bind(this);
    this.toggleNavBtn = this.toggleNavBtn.bind(this);
    this.scrollTop = this.scrollTop.bind(this);
    this.state = {
      isOpenNav: false,
      isOpenNavBtn: false,
      headerUrls: [
        routerUrls.home, routerUrls.matches, routerUrls.results, routerUrls.tables,
        routerUrls.teams, routerUrls.social, routerUrls.swagger
      ]
    };
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

  toggleNav() {
    this.setState({
      isOpenNav: !this.state.isOpenNav
    });
  }

  toggleNavBtn() {
    this.setState({
      isOpenNavBtn: !this.state.isOpenNavBtn
    });
  }

  render() {
    return (
      <header>
        <div className="navigation-fixed">
          <Navbar color="faded" expand="md" className="header-nav" light>
            <NavbarBrand onClick={this.scrollTop} href="#"><img src={egaHome} className="logo-home" alt="logo-header"/></NavbarBrand>
            <NavbarToggler onClick={this.toggleNav} />
            <Collapse className="header-links" isOpen={this.state.isOpenNav} navbar>
              <Nav navbar>
                {this.state.headerUrls.map((el, index) => (
                  checkUserUrlRole(el.role, this.props.authUser)
                    ? <NavItem key={index}>
                        <Link className="nav-link" to={el.url}>{el.name}</Link>
                      </NavItem>
                    : null
                ))}
                <NavItem className="auth-btn">
                  {isUserAuthorized(this.props.authUser)
                    ? <ButtonDropdown isOpen={this.state.isOpenNavBtn} toggle={this.toggleNavBtn}>
                        <DropdownToggle caret>
                          Profile
                        </DropdownToggle>
                        <DropdownMenu>
                          <DropdownItem>Profile settings</DropdownItem>
                          <DropdownItem>My team</DropdownItem>
                          <DropdownItem divider />
                          <DropdownItem>Logout</DropdownItem>
                        </DropdownMenu>
                      </ButtonDropdown>
                    : <Link className="nav-link" to={routerUrls.login.url}>{routerUrls.login.name}</Link>
                  }
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
