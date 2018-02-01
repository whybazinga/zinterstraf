import React, {Component} from 'react';

export default class Footer extends Component {
  constructor(props) {
    super(props);
    const currentYear = new Date().getFullYear();

    this.state = {
      year: currentYear
    };
  }
  render() {
    return (
      <footer className="d-flex flex-column footer">
        <div className="ft-1 text-center pt-1">
          <p className="font-weight-bold">UniversalSite© Copyright {this.state.year} Privacy Policy</p>
        </div>
      </footer>
    );
  }
}
