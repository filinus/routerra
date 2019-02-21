import React from 'react';
import ReactMapGL, {NavigationControl} from 'react-map-gl';
import Car from './Car.jsx';
import getData from "./getdata";
import propTypes from "prop-types";
import {bindActionCreators} from "redux";
import * as deviceActions from "./redux/deviceActions";
import {connect} from 'react-redux';

const TOKEN = process.env.REACT_APP_MAPBOX_TOKEN; //https://www.mapbox.com/help/how-access-tokens-work/

const mapContainerStyle = {
    width: "100%",
    height: "100%",
    textAlign: "left"
};

const navStyle = {
    position: 'absolute',
    top: 0,
    left: 0,
    padding: '10px'
};

class Map extends React.Component {
    static propTypes = {
        carsOnMap : propTypes.arrayOf(propTypes.object)
    };

    state = {
        viewport: {
            width: this.props.width || window.innerWidth,
            height: this.props.height || window.innerHeight,
            latitude: 37.7577,
            longitude: -122.4376,
            zoom: 9,
            name: "Routerra",

        },
        carsOnMap: []
    };

    componentDidMount() {
        var intervalId = setInterval(this.timer, 30000);
        // store intervalId in the state so it can be accessed later:
        this.setState({intervalId: intervalId});

        window.addEventListener('resize', this._resize);
        this._resize();

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
        window.removeEventListener('resize', this._resize);
    }

    timer = () => {
        this._doAjax();
    };

    render() {
        //console.log("rendering map");
        return (
            <div id="mapContainer" style={mapContainerStyle}>
                <ReactMapGL
                    {...this.state.viewport}
                    onViewportChange={this._updateViewport}
                    mapboxApiAccessToken={TOKEN}
                    mapStyle='mapbox://styles/mapbox/basic-v9'
                >
                    <div className="nav" style={navStyle}>
                        <NavigationControl  onViewportChange={this._updateViewport} />
                    </div>
                    {this.props.carsOnMap.map((car, index) =>
                        <Car {...car.lastLocation} {...car} key={car.id} index={index}/>
                    )}
                </ReactMapGL>
            </div>
        );
    }

    _resize = () => {
        //console.log("_resize:");
        this.setState({
            viewport: {
                ...this.state.viewport,
                width: this.props.width || window.innerWidth,
                height: this.props.height || window.innerHeight
            }
        });
    };

    _updateViewport = (viewport) => {
        //console.log("_updateViewport:", viewport);
        this.setState({viewport});
    }
}

const mapStateToProps = state => ({
    carsOnMap: state.device.devices
});

const mapDispatchToProps = dispatch => ({
    deviceActions: bindActionCreators(deviceActions, dispatch)
});

export default connect(mapStateToProps , mapDispatchToProps)(Map);