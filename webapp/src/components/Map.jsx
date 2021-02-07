import * as React from 'react';
import SmartMap from "./SmartMap";
import Car from "./Car";
import {bindActionCreators} from "redux";
import * as deviceActions from "../redux/deviceActions";
import getData from "../js/getdata";
import propTypes from "prop-types";
import {connect} from 'react-redux';

const colors = ['red','green','blue'];
const getColor = index => colors[index % colors.length]

class Map extends React.Component {
    static propTypes = {
        carsOnMap : propTypes.arrayOf(propTypes.object)
    };

    state = {
        carsOnMap: []
    };

    componentDidMount() {
        var intervalId = setInterval(this.timer, 30000);
        // store intervalId in the state so it can be accessed later:
        this.setState({intervalId: intervalId});

        this._doAjax();
    }

    _doAjax = () => {
        getData('devices')
            .then(data => {
                if (!data) return;
                //console.info("found cars in ajax:", data.content);
                this.props.deviceActions.injectDeviceList(data.content);
            })
    };

    componentWillUnmount() {
        // use intervalId from the state to clear the interval
        if (this.state.intervalId)
            clearInterval(this.state.intervalId);
    }

    timer = () => {
        this._doAjax();
    };

    render() {
        return (
            <SmartMap {...this.props}>
                {this.props.carsOnMap.map((car, index) => (
                    <Car {...car.lastLocation} {...car} key={car.id} index={index} color={getColor(index)}/>
                ))}
            </SmartMap>
        );
    };
}

const mapStateToProps = state => ({
    carsOnMap: state.device.devices
});

const mapDispatchToProps = dispatch => ({
    deviceActions: bindActionCreators(deviceActions, dispatch)
});

export default connect(mapStateToProps , mapDispatchToProps)(Map);