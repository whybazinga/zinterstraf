import React from 'react';
import { Row, Col } from 'reactstrap';

const Empty = () => (
  <section className="container pt-5">
    <Row>
      <Col className="d-flex justify-content-center align-items-center">
        <h2>Sorry this page currently is not available... We are working on it!</h2>
      </Col>
    </Row>
  </section>
);

export default Empty;