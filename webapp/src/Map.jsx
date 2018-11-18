import React, {Component} from 'react';
import ReactMapGL, {NavigationControl} from 'react-map-gl';
import Car from './Car.jsx';
import getData from "./getdata.js";

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

class Map extends Component {
    constructor(props) {
        super(props);

        this.state = {
            viewport: {
                width: this.props.width || window.innerWidth,
                height: this.props.height || window.innerHeight,
                latitude: 37.7577,
                longitude: -122.4376,
                zoom: 9,
                name: "Routerra",

            },
            cars: []
        };
    }

    componentDidMount() {
        var intervalId = setInterval(this.timer.bind(this), 10000);
        // store intervalId in the state so it can be accessed later:
        this.setState({intervalId: intervalId});

        window.addEventListener('resize', this._resize);
        this._resize();

        this._doAjax();
    }

    _doAjax () {
        getData('devices')
            .then(data => {
                if (!data) return;
                console.info("cars found:", data.content);
                this.setState({cars: data.content});
            })
    }

    componentWillUnmount() {
        // use intervalId from the state to clear the interval
        if (this.state.intervalId)
            clearInterval(this.state.intervalId);
        window.removeEventListener('resize', this._resize);
    }

    timer() {
        this._doAjax();
    }

    render() {
        console.log("render map:",this.state);
        return (
            <div id="mapContainer" style={mapContainerStyle}>
                <ReactMapGL
                    {...this.state.viewport}
                    onViewportChange={(viewport) => this._updateViewport(viewport)}
                    mapboxApiAccessToken={TOKEN}
                    mapStyle='mapbox://styles/mapbox/basic-v9'
                >
                    <div className="nav" style={navStyle}>
                        <NavigationControl  onViewportChange={(viewport) => this._updateViewport(viewport)} />
                    </div>
                    {this.state.cars.map((car, index) =>
                        <Car {...car.lastLocation} {...car} key={car.id}/>
                    )}
                </ReactMapGL>
            </div>
        );
    }

    _resize = () => {
        console.log("_resize:");
        this.setState({
            viewport: {
                ...this.state.viewport,
                width: this.props.width || window.innerWidth,
                height: this.props.height || window.innerHeight
            }
        });
    };

    _updateViewport = (viewport) => {
        console.log("_updateViewport:", viewport);
        this.setState({viewport});
    }
}

export default Map;