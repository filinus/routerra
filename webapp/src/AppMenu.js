import React, {Component} from 'react';
import './App.css';
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import getData from "./getdata";
import propTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as userActions from './userActions';
//import {STATE_KEY as USER_STATE_KEY, initialState} from './userReducer';
import loginTab from './openWindow';
import * as querystring from 'querystring'
import * as randomstring from 'randomstring'

const kindaResponse = {content: []};

class AppMenu extends Component {
    constructor(props) {
        super(props);
        this.state = {
            response: kindaResponse,
        };
    }

    handleLogIn(e) { // handleLogIn(e, {name}) {
        console.log("do github login, state:", this.state);
        const callBackUrl = process.env.PUBLIC_URL + '/loginsuccess.html';
        //https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/#web-application-flow
        //https://github.com/settings/applications/956071
        const loginParams = {
            client_id: 'e456e08e1451867af4bb',
            redirect_uri: callBackUrl,
            state: randomstring.generate() // 32 of [0-9 a-z A-Z]  https://www.npmjs.com/package/randomstring
        };
        const loginUrl = 'https://github.com/login/oauth/authorize?' + querystring.encode(loginParams);
        const msg = loginTab(loginUrl);
        msg.then(user => {
            console.log("received user", user);
            this.props.userActions.injectUser(user);
        });
    }

    handleLogOut(e) { //handleLogOut(e, {name}) {
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
        return (
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="title" color="inherit" className={"icon big car VDivider"}>
                        Routerra Demo App
                    </Typography>
                    <Select autoWidth={true} displayEmpty={true}>
                        {this.state.response.content.map((fleet, index) =>
                            <MenuItem key={"fleet" + index}>{fleet.fleetname}</MenuItem>
                        )}
                    </Select>
                    {!isAuthenticated && (
                        <Button color="inherit" onClick={this.handleLogIn.bind(this)}>Login</Button>
                    )}
                    {isAuthenticated && (
                        <Button color="inherit" onClick={this.handleLogOut.bind(this)}>Logout {currentUser}</Button>
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

const mapStateToProps = state => ({
    isAuthenticated: state.isAuthenticated,
    currentUser: state.user
});

const mapDispatchToProps = dispatch => ({
    userActions: bindActionCreators(userActions, dispatch)
});

export default connect(mapStateToProps , mapDispatchToProps)(AppMenu);

