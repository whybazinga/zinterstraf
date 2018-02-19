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
      <footer>
        <div className="ft-1 text-center font-weight-bold p-3">
          Zinterstraf© {this.state.year} Privacy Policy
        </div>
      </footer>
    );
  }
}
