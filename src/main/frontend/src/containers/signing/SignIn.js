import React from 'react';
import octicons from 'octicons';
import {Row, Col, Form, FormGroup, InputGroup, InputGroupAddon, Input, Button, FormFeedback} from 'reactstrap';
import {InnerFormSvg} from '../../components/innerHtml/InnerHtml'

import './singIn.css'
import steamPng from './steam.png';
const steamStyle = {
  width:'40px',
  borderRadius:'20px'
};

fetch('fsdf').then();

const SignIn = (props) => (
  <section className="container">
    <Row>
      <Col className="d-flex justify-content-center align-items-center">
        <Form className="box-shadow-element-noborder m-4 p-4">
          <FormGroup>
            <InputGroup>
              <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.mail.toSVG()}/></InputGroupAddon>
              <Input type="email" name="email" id="userEmail" placeholder="my-mail@gmail.com" valid/>
              <FormFeedback valid>The email is available</FormFeedback>
            </InputGroup>
          </FormGroup>
          <FormGroup>
            <InputGroup>
              <InputGroupAddon addonType="prepend"><InnerFormSvg svg={octicons.key.toSVG()}/></InputGroupAddon>
              <Input type="password" name="password" id="userPassword" placeholder="my-password123" valid/>
              <FormFeedback valid>The password is good</FormFeedback>
            </InputGroup>
          </FormGroup>
          <FormGroup className="text-right">
              <Button>Sign in</Button>
          </FormGroup>
          <hr/>
          <div className="text-center">
            <p><small className="text-muted">Sign in through the steam</small></p>
            <input type="image" src={steamPng} style={steamStyle} alt="steam-img"/>
          </div>
          <hr/>
          <div className="text-center">
            <Button color="link">Sign up directly in the system</Button>
          </div>
        </Form>
      </Col>
    </Row>
  </section>
);

export default SignIn;
