import React, {Component} from 'react';
import './startPage.css';
import { Container, Row, Col } from 'reactstrap';
import glaImg from'./gla.png';
import virtImg from'./virtucon.jpg';

export default class StartPage extends Component {

  render() {
    return (
      <section className="container-fluid content">
        <Row>
          <Col xl="3">
            <img src={glaImg} className="rounded-circle" style="width:200px;height:200px;" />
            <img src={virtImg} className="rounded-circle" style="width:200px;height:200px;" />
          </Col>
          <Col xl="9">
            <h2 className="color-nat-black text-capitalize">Hail to the legendary Zinterstraf</h2>
            <p>
              <strong>Our values:</strong> We believe in free expression and think every voice has the power to impact the world.
            </p>
            <p>
              <strong>Our principles:</strong> On our site, you should feel safe expressing your unique point of view with every post.
              We want everyone to discover a wide variety of voices and perspectives, and for that reason we allow strong opinions and controversial
              views. So it’s our job to make your experience as safe as we can. But if you do experience abuse or harassment on Twitter,
              that can jeopardize your freedom of expression. We won’t let Twitter be a place where you are intimidated, harassed, or silenced.
            </p>
            <p>
              <strong>Our approach:</strong> Free expression is a human right. Everyone has a voice, and the right to use it. On UniversalSite,
              you should feel safe expressing your unique point of view with every post – and it’s our job to make that happen.
              But sometimes posts can cross a line and are abusive or threatening. To keep you safe, we build tools
              so you can control what you see and who you interact with; work with a community of online safety experts to fight abuse everywhere;
              and develop and enforce policies to prohibit abusive behavior.
            </p>
            <p>
              <strong>About GLA:</strong> The GLA has bases scattered across the globe. Over a period of several years, the GLA steadily built up
              their strength and support in the Third World, mainly within the Middle East and Central Asia. While the group possessed a
              number of 'main' bases, their destruction did little to halt the GLA's spread of influence. For this reason, it
              is debatable whether or not the organization possessed a 'nerve center' at all. In the First GLA War, the GLA's main
              base of operations is in Akmola of Kazakhstan and was destroyed by a Sino-American force during Last Call.
            </p>
            <p>
              <strong>GLA Resources:</strong> The GLA began with a financial injection from a number of shadowy, wealthy backers (many of whom,
              such as Prince Kassad who would go on to become GLA generals), and gained much of its funding hence from raiding resources,
              such as the United Nations' foreign aid supplies. They also had ties with the Black Market which brought them a steady cash
              flow as well as access to relatively advanced weapons.
            </p>
            <p>
              The GLA was highly resourceful, raiding old Soviet weapon dumps and employing ex-Soviet scientists and even scavenging spare parts
              from the battlefield. While GLA vehicles could not match their more modern counterparts on the battlefield, GLA commanders were
              highly devious and unscrupulous, but many American and Chinese generals underestimated them at their peril.
            </p>
          </Col>
        </Row>
        <Row>
          <Col>
            <div className="text-capitalize color-nat-black pb-2">In order to clarify everything watch this video</div>
            <div className="embed-responsive embed-responsive-16by9 div-video align-self-center">
              <iframe className="embed-responsive-item" src="https://www.youtube.com/embed/WrdnerqWtX4?rel=0">
                Your browser doesn't support iFrames!
              </iframe>
            </div>
          </Col>
        </Row>
        <Row>
          <Col>
            <h3 className="text-capitalize color-nat-black text-center">Contact Us</h3>
            <p>Contact me and i will get back to you within 24 hours.</p>
            <p>* Minsk, Belarus</p>
            <p>* 8-029-202-03-27</p>
            <p>* vvopaa@mail.com</p>
          </Col>
          <Col>
            <div id="googleMap">The map doesn't work... something has gone wrong.</div>
          </Col>
        </Row>
      </section>
    );
  }
}
