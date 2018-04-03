import React, {Component} from 'react';
import SwaggerUi, {presets} from 'swagger-ui';
import 'swagger-ui/dist/swagger-ui.css';
import './swagger.css'

class SwaggerTest extends Component {
  componentDidMount() {
      SwaggerUi({
        dom_id: '#swaggerContainer',
        url: 'http://localhost:8080/v2/api-docs', //'http://' + window.location.host + '/v2/api-docs'
        presets: [presets.apis]
      });
  }

  render() {
    return (
      <div id="swaggerContainer" />
    );
  }
}

export default SwaggerTest;