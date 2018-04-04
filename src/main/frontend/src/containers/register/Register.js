import React, {Component} from 'react'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, FormFeedback} from 'reactstrap'
import './register.css'
import {Redirect, Link} from 'react-router-dom'
import uuidv1 from "uuid"
import FluidRowTitle from '../../components/fluidRowTitle/FluidRowTitle'


class Register extends Component {
  constructor(props) {
    super(props);

    this.state = {

    }
  }

  render() {
    return (
      <div>
        <FluidRowTitle title="Your Account" />
        <section className="container">
          <Row className="justify-content-center signing-child-titles pt-3 pb-3">
            <Col md="8">
              <h6>Creating a new account</h6>
            </Col>
          </Row>
          <Row className="justify-content-center pb-5">
            <Col md="4">
            </Col>
            <Col md="8">
            </Col>
          </Row>
        </section>
      </div>
    );
  }
}




/*
  onRegister() {
    if (!this.checkIfSigningFieldsValid(true)) return;

    fetchPostJsonResponse(signingConst.signUpUrl, {
      'username': this.state.username.value,
      'password': this.state.password.value,
      'type': 'direct'
    }).then((json) => {

    })

  }
*/