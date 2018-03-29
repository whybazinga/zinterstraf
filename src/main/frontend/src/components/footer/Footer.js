import React, {Component} from 'react';
import {Row, Col} from 'reactstrap';
import { Link } from 'react-router-dom'
import './style.css'
import egaHome from'../ega.png';
import uuidv1 from "uuid";

export default class Footer extends Component {
  constructor(props) {
    super(props);
    const currentYear = new Date().getFullYear();
    this.state = {
      year: currentYear
    };
  }
  render() {
    return (
      <footer className="container-fluid">
        <Row className="justify-content-center footer-top pt-4 pb-4">
          {footerUrls.map((topEl) => {
            return(<Col md="2" key={uuidv1()}>
              <h6 key={uuidv1()}>{topEl.header}</h6>
              {topEl.urls.map((childEl) => (
                <div key={uuidv1()}>
                  <Link to={childEl.url} key={uuidv1()}>{childEl.name}</Link>
                </div>
              ))}
            </Col>);
          })}
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

const footerUrls = [
  {
    header: 'EGA League',
    urls: [
      {url: '/', name: 'Home'},
      {url: '/', name: 'Matches'},
      {url: '/', name: 'Results'},
      {url: '/', name: 'Tables'},
      {url: '/', name: 'Broadcast'},
      {url: '/', name: 'Clubs'},
      {url: '/', name: 'Players'},
      {url: '/', name: 'Social'}
    ]
  },
  {
    header: 'Stats',
    urls: [
      {url: '/', name: 'Dashboard'},
      {url: '/', name: 'Player Stats'},
      {url: '/', name: 'Club Stats'}
    ]
  },
  {
    header: 'More',
    urls: [
      {url: '/', name: 'Transfers'},
      {url: '/', name: 'Partners'},
      {url: '/', name: 'About'},
      {url: '/', name: 'Publications'},
      {url: '/', name: 'Contact'}
    ]
  },
  {
    header: 'Socials',
    urls: [
      {url: '/', name: 'EGA on Twitter'},
      {url: '/', name: 'EGA on Facebook'},
      {url: '/', name: 'EGA on VK'},
      {url: '/', name: 'EGA on Instagram'},
      {url: '/', name: 'EGA on Discord'}
    ]
  }
];
