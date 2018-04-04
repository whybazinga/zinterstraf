import React from 'react'
import { Row, Col, Button } from 'reactstrap'
import errorGif from './404.gif'
import './empty.css'

const Empty = (props) => (
  <section className="container pt-2 pb-2">
    <Row>
      <Col className="d-flex justify-content-center align-items-center">
        <img src={errorGif} className="empty-img rounded-border" alt="error-gif"/>
        <Button className="empty-back-btn" color="info" onClick={props.history.goBack}>Go Back</Button>
      </Col>
    </Row>
  </section>
);

export default Empty;