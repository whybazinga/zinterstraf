import React, {Component} from 'react';
import {Row, Col} from 'reactstrap';
import { Link } from 'react-router-dom'
import uuidv1 from "uuid";

import './style.css'
import egaHome from'../../images/ega.png';
import {routerUrls, externalUrls} from "../../AppRouter";
import {checkUserUrlRole} from "../../utils/authUtils";
import PropTypes from "prop-types";


export default class Footer extends Component {
  constructor(props) {
    super(props);
    const currentYear = new Date().getFullYear();
    this.state = {
      year: currentYear,
      footerUrls: [
        {
          header: 'EGA League',
          urls: [
            routerUrls.home, routerUrls.matches, routerUrls.results, routerUrls.tables,
            routerUrls.broadcast, routerUrls.teams, routerUrls.players, routerUrls.social
          ]
        },
        {
          header: 'Stats',
          urls: [routerUrls.dashboard, routerUrls.playerStats, routerUrls.teamStats]
        },
        {
          header: 'More',
          urls: [routerUrls.transfers, routerUrls.partners, routerUrls.about, routerUrls.publications, routerUrls.contact]
        },
        {
          header: 'Socials',
          urls: [externalUrls.egaTwitter, externalUrls.egaFacebook, externalUrls.egaVk, externalUrls.egaInstagram, externalUrls.egaDiscord]
        }
      ]
    };
  }
  render() {
    return (
      <footer className="container-fluid">
        <Row className="justify-content-center footer-top pt-4 pb-4">
          {this.state.footerUrls.map((topEl) => (
            <Col md="2" key={uuidv1()}>
              <h6>{topEl.header}</h6>
              {topEl.urls.map((childEl) => (
                childEl.role
                  ? checkUserUrlRole(childEl.role, this.props.authUser)
                      ? <div key={uuidv1()}>
                          <Link to={childEl.url}>{childEl.name}</Link>
                        </div>
                      : null
                  : <div key={uuidv1()}>
                      <a href={childEl.url}>{childEl.name}</a>
                    </div>
              ))}
            </Col>
          ))}
        </Row>
        <Row className="justify-content-center footer-bottom font-weight-bold pt-2 pb-2">
          <Col sm="4">
            © EUROPEAN GAMING ASSOCIATION {this.state.year}
          </Col>
          <Col sm="4" className="logo-footer-text">
            <img src={egaHome} className="logo-footer" alt="logo-footer"/>
            &nbsp;European gaming association
          </Col>
        </Row>
      </footer>
    );
  }
}

Footer.propTypes = {
  authUser: PropTypes.object.isRequired
};