import React from 'react'
import { Row, Col } from 'reactstrap'
import './fluidRowTitle.css'

export const FluidRowTitle = ({title}) => (
  <section className="container-fluid">
    <Row className="fluid-row-title theme-blue pt-5 pb-5">
      <Col className="d-flex align-items-center offset-sm-2">
        <span>{title}</span>
      </Col>
    </Row>
  </section>
);
