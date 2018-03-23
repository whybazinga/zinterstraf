import React, {Component} from 'react';
import SwaggerUi, {presets} from 'swagger-ui';
import 'swagger-ui/dist/swagger-ui.css';

class SwaggerTest extends Component {
  componentDidMount() {
    try {
      SwaggerUi({
        dom_id: '#swaggerContainer',
        url: 'http://' + window.location.host + '/v2/swagger.json',
        presets: [presets.apis],
      });
    } catch(err) {
      console.log('SWAGGER UI ERROR' + err.message);
    }

  }

  render() {
    return (
      <div id="swaggerContainer" />
    );
  }
}

export default SwaggerTest;