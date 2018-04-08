import React, {Component} from 'react'
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, FormFeedback, Label} from 'reactstrap'
import {Redirect, Link} from 'react-router-dom'
import PropTypes from 'prop-types'

import './register.css'
import {FluidRowTitle} from '../../components/fluidRowTitle/FluidRowTitle'
import {FormDynamicInput} from "../../components/formDynamicInput/FormDynamicInput"
import {inputTypes} from "../../constants/inputTypes";
import {FormRowErrorHint} from "../../components/formRowErrorHint/FormRowErrorHint";
import {countryTypes} from '../../constants/countryTypes'
import {genderTypes} from "../../constants/genderTypes";


class Register extends Component {
  constructor(props) {
    super(props);
    this.onChangeElement = this.onChangeElement.bind(this);
    this.state = {
      inputs: {
        fName: {type: inputTypes.text, txt: 'First Name', value: '', required: true, warn: '', hintTxt: ''},
        lName: {type: inputTypes.text, txt: 'Last Name', value: '', required: true, warn: '', hintTxt: ''},
        email: {type: inputTypes.email, txt: 'Email Address', value: '', required: true, warn: '', hintTxt: ''},
        pass: {type: inputTypes.password, txt: 'Password', value: '', required: true, warn: '', hintTxt: ''},
        gender: {type: inputTypes.select, txt: 'Gender', value: '', required: true, warn: '', hintTxt: '', opt: genderTypes},
        dob: {type: inputTypes.date, txt: 'Date of Birth', value: '', required: true, warn: '', hintTxt: ''},
        cor: {type: inputTypes.select, txt: 'Country of Residence', value: '', required: true, warn: '', hintTxt: '', opt: countryTypes}
      }
    }
  }

  onChangeElement(e) {
    const targetId = e.target.id;
    const state = {
      inputs: {
        ...this.state.inputs,
      }
    };
    state.inputs[targetId] = {
      ...this.state.inputs[targetId],
      value: e.target.value
    };
    this.setState(state);
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
          <Row className="justify-content-center pb-5">
            <Col md="8">
              <Form className="container-block p-4 rounded-border">
                {Object.keys(this.state.inputs).map((id, index) => (
                  <React.Fragment key={id}>
                    <FormGroup row>
                      <Label for={id} md={4} >{this.state.inputs[id].txt}{this.state.inputs[id].required && '*'}</Label>
                      <Col md={8}>
                        <FormDynamicInput key={index} type={this.state.inputs[id].type} id={id} options={this.state.inputs[id].opt || []} value={this.state.inputs[id].value} onChange={this.onChangeElement}/>
                      </Col>
                    </FormGroup>
                    <FormRowErrorHint txt=""/>
                  </React.Fragment>
                ))}

              </Form>
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