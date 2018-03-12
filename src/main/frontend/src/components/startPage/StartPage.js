import React, {Component} from 'react';
import './startPage.css';
import { Row, Col, ListGroup, ListGroupItem  } from 'reactstrap';
import glaPng from './gla.png'
import { connect } from "react-redux";
import { addMatch } from "../../actions/matchActions";
import uuidv1 from "uuid";

export default class StartPage extends Component {

  constructor(props) {
    super(props);

  }


  render() {
    return (
      <section className="container">
        <Row>
          <Col xl="4">
            <div className="box-shadow-element m-lg-5">
              <h4 className="color-nat-black text-capitalize text-center">Matchweek 1</h4>
              <MatchList />
              <Form />
            </div>
            <div className="box-shadow-element m-lg-5">
              <div className="color-nat-black text-capitalize text-center ega-league-header">EGA LEAGUE</div>
              <div className="color-nat-black text-capitalize text-center">
                <img src={glaPng} alt=""/>
              </div>
            </div>
          </Col>
          <Col xl="8">
            <div className="box-shadow-element m-lg-5 p-3">
              <h2 className="color-nat-black text-capitalize text-center">Hail to the legendary EGA</h2>
              <p>
                <strong>Our values:</strong> We believe in free expression and think every voice has the power to impact the world.
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
            </div>
          </Col>
        </Row>
        <Row>
          <Col className="text-center">
            <div className="text-capitalize color-nat-black pb-2">In order to clarify everything watch this video</div>
            <div className="start-video">
              <div className="embed-responsive embed-responsive-16by9 div-video">
                <iframe className="embed-responsive-item" src="https://www.youtube.com/embed/WrdnerqWtX4?rel=0" title="papich">
                  Your browser doesn't support iFrames!
                </iframe>
              </div>
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

const mapStateToProps = state => {
  return { matches: state.match.matches };
};
const ConnectedList = ({matches}) => (
  <ListGroup>
    {matches.map((el, key) => (
      <ListGroupItem className="justify-content-between" key={el.id}>
        {el.title} - {key}
      </ListGroupItem>
    ))}
  </ListGroup>
);
const MatchList = connect(mapStateToProps)(ConnectedList);

function mapDispatchToProps(match) {
  return (dispatch) => {
    setTimeout(() => {
      dispatch(addMatch(match));
    }, 1000)
  }
}

class ConnectedForm extends Component {
  constructor() {
    super();

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      title: ""
    };
  }

  handleChange(event) {
    this.setState({ [event.target.id]: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();
    debugger;
    const { title } = this.state;
    const id = uuidv1();

    this.props.mapDispatchToProps({ title, id });
    this.setState({ title: "" });
  }

  render() {
    const { title } = this.state;
    return (
      <form onSubmit={this.handleSubmit}>
        <div className="form-group">
          <label htmlFor="title">Title</label>
          <input
            type="text"
            className="form-control"
            id="title"
            value={title}
            onChange={this.handleChange}
          />
        </div>
        <button type="submit" className="btn btn-success btn-lg">
          SAVE
        </button>
      </form>
    );
  }
}
const Form = connect(null, {mapDispatchToProps})(ConnectedForm);

/*
export const myMap = () => {
  let myCenter = new google.maps.LatLng(53.896094, 27.539808);
  let mapProp = { center: myCenter, zoom: 12, scrollwheel: true, draggable: true, mapTypeId: google.maps.MapTypeId.ROADMAP };
  let map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
  let marker = new google.maps.Marker({ position: myCenter });
  marker.setMap(map);
};

<Script
  url="https://maps.googleapis.com/maps/api/js?key=AIzaSyCKEnRn7tFszOtb2WC8swGoQ-DQbf41wiw&callback=myMap"
  onCreate={this.handleScriptCreate.bind(this)}
  onError={this.handleScriptError.bind(this)}
  onLoad={this.handleScriptLoad.bind(this)}
/>
*/