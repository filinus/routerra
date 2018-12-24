import React, { Component } from 'react';
import './App.css';
import {bindActionCreators} from 'redux'
import Map from './Map.jsx';
import AppMenu from "./AppMenu.jsx";
import propTypes from "prop-types";
import * as userActions from "./userActions";
import {connect} from 'react-redux';

console.log(process.env.NODE_ENV || "n/a", process.env.PUBLIC_URL || "n/a");

class App extends Component {
  render() {
    const {isAuthenticated} = this.props;
    return (
      <div className="App">
          <AppMenu/>
          {isAuthenticated && (
              <Map/>
          )}
          {!isAuthenticated && (
              <div>login using one of oath provider to get access to your personalized map</div>
          )}
      </div>
    );
  }
}

App.propTypes = {
    isAuthenticated: propTypes.bool
};

const mapStateToProps = state => ({
    isAuthenticated: state.user.isAuthenticated
});

const mapDispatchToProps = dispatch => ({
    userActions: bindActionCreators(userActions, dispatch)
});

export default connect(mapStateToProps , mapDispatchToProps)(App);
