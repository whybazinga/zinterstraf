import React, {Component} from 'react'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, FormFeedback} from 'reactstrap'
import './register.css'
import {Redirect, Link} from 'react-router-dom'
import uuidv1 from "uuid"
import {FluidRowTitle} from '../../components/fluidRowTitle/FluidRowTitle'
import {countryTypes} from '../../constants/countryTypes'
import PropTypes from 'prop-types'


class Register extends Component {
  constructor(props) {
    super(props);

    this.state = {
      details: [
        { name: 'First Name', value: '', required: true, inputClassName: '', hintClassName: '' },
        { name: 'Last Name', value: '', required: true, inputClassName: '', hintClassName: '' },
        { name: 'Email Address', value: '', required: true, inputClassName: '', hintClassName: '' },
        { name: 'Password', value: '', required: true, inputClassName: '', hintClassName: '' },
        { name: 'Gender', value: '', required: true, inputClassName: '', hintClassName: '' },
        { name: 'Date of Birth', value: '', required: true, inputClassName: '', hintClassName: '' },
        { name: 'Country of Residence', value: '', required: true, inputClassName: '', hintClassName: '' }
      ]
    }
  }

  render() {
    return (
      <React.Fragment>
        <FluidRowTitle title="Your Account" />
        <section className="container">
          <Row className="justify-content-center container-title pt-3 pb-3">
            <Col md="8" className="theme-blue">
              <h6>Creating a new account</h6>
            </Col>
          </Row>
          <Row className="justify-content-center pb-5 container-block">
            <Col md="3">
              one
            </Col>
            <Col md="5">
              two
            </Col>
          </Row>
        </section>
      </React.Fragment>
    );
  }
}

export default Register;

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