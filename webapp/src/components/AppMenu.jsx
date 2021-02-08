import React from 'react';
import './App.css';
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import getData from "../js/getdata";
import propTypes from 'prop-types';
import {connect} from 'react-redux';
import * as userActions from '../redux/userActions';
import githubUser from "../js/githublogin";
import {bindActionCreators} from "redux";

const kindaResponse = {content: []};

class AppMenu extends React.Component {
    state = {
        response: kindaResponse,
    };

    handleLogIn = () => { // handleLogIn(e, {name}) {
        const that = this;
        const promise = githubUser();
        promise.then(resolvedUserObject => {
            console.debug("githubUser resolved:", resolvedUserObject);
            that.props.userActions.injectUser(resolvedUserObject);
        });
    };

    handleLogOut = () => { //handleLogOut(e, {name}) {
        this.props.userActions.logoutUser();
    };

    componentDidMount() {
        getData("fleets")
            .then(data => {
                if (!data) return;
                console.log("received fleet list");
                if (this._isMounted) {
                    this.setState({response: data});
                }
            })
    }

    componentWillUnmount () {
        this._isMounted = false;
    }

    render() {
        console.debug(this.props);
        const {isAuthenticated, currentUser} = this.props;
        return (
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h5" noWrap={true} color="inherit" className={"icon big car VDivider"}>
                        Routerra Demo App
                    </Typography>
                    <Select autoWidth={true} displayEmpty={true} value={"fleet0"}>
                        {this.state.response.content.map((fleet, index) =>
                            <MenuItem key={"fleet" + index}>{fleet.fleetname}</MenuItem>
                        )}
                    </Select>
                    {!isAuthenticated && (
                        <Button color="inherit" onClick={this.handleLogIn}>Login</Button>
                    )}
                    {isAuthenticated && (
                        <Button color="inherit" onClick={this.handleLogOut}>Logout {currentUser.user||'FIXME'}</Button>
                    )}
                </Toolbar>
            </AppBar>
        );
    }
}

AppMenu.propTypes = {
    isAuthenticated: propTypes.bool,
    currentUser: propTypes.object
};

const mapStateToProps = state => ({
    isAuthenticated: state.user.isAuthenticated,
    currentUser: state.user.user
});

const mapDispatchToProps = dispatch => ({
    userActions: bindActionCreators(userActions, dispatch)
});

export default connect(mapStateToProps , mapDispatchToProps)(AppMenu);

