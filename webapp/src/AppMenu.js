import React, { Component } from 'react';
import './App.css';
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import getData from "./getdata.js";
import propTypes from 'prop-types';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as userActions from './userActions.js';
import { STATE_KEY as USER_STATE_KEY, initialState } from './userReducer.js';


const kindaResponse = {content: []};

class AppMenu extends Component {
  constructor(props) {
    super(props);
    this.state = {
        response: kindaResponse
    }
  }

  static doLogin() {
     console.log("do login");
  }

  handleLogIn(e, {name}) {
    //const msg = loginTab('/auth/github');
    //msg.then(user => {
    //    this.props.userActions.injectUser(user);
    //});
  }

  handleLogOut(e, {name}) {
    this.props.userActions.logoutUser();
  }

  componentDidMount() {
      getData("fleets")
          .then(data => {
              if (!data) return;
              console.log("received fleet list");
              this.setState({response: data});
          })
  }

  render() {
    const {isAuthenticated, currentUser} = this.props;
    //const loginButton =  isAuthenticated ? "logout":"login";
    return (
      <AppBar position="static">
          <Toolbar>
              <Typography variant="title" color="inherit"  className={"icon big car VDivider"}>
                  Routerra Demo App
              </Typography>
              <Select autoWidth={true} displayEmpty={true}>
                  {this.state.response.content.map((fleet, index) =>
                      <MenuItem key={"fleet"+index}>{fleet.fleetname}</MenuItem>
                  )}
              </Select>
              {!isAuthenticated && (
                  <Button color="inherit" onClick={AppMenu.doLogin()}>Login</Button>
              )}
              {isAuthenticated && (
                  <Button color="inherit">Logout</Button>
              )}
          </Toolbar>
      </AppBar>
    );
  }
}

AppMenu.PropTypes = {
    isAuthenticated: propTypes.bool,
    currentUser: propTypes.object
};

const mapStateToProps = state => (
    (!(USER_STATE_KEY in state))
    ? initialState
    : {
        isAuthenticated: state[USER_STATE_KEY].isAuthenticated,
        currentUser: state[USER_STATE_KEY].user
      }
);

const mapDispatchToProps = dispatch => ({
    userActions: bindActionCreators(userActions, dispatch)
});

export default connect(mapStateToProps , mapDispatchToProps)(AppMenu);

