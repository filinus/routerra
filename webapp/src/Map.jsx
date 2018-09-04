import React, {Component} from 'react';
import ReactMapGL from 'react-map-gl';
import Car from './Car.jsx';

const TOKEN = "PUT_YOUR_MAPBOX_ACCESS_TOKEN_HERE"; //https://www.mapbox.com/help/how-access-tokens-work/

const mapContainerStyle = {
    width: "100%",
    height: "100%",
    textAlign: "left"
};

class Map extends Component {

    state = {
        viewport: {
            width: this.props.width || window.innerWidth,
            height: this.props.height || window.innerHeight,
            latitude: 37.7577,
            longitude: -122.4376,
            zoom: 9,
            name: "Routerra",

        },
        carRotation: 45,
        cars: [
            {latitude:37.80,longitude:-122.43,rotation:0,color:"blue"},
            {latitude:37.90,longitude:-122.50,rotation:30,color:"orange"},
            {latitude:37.60,longitude:-122.0,rotation:-190,color:"green"}
        ]
    };

    componentDidMount() {
        var intervalId = setInterval(this.timer.bind(this), 2000);
        // store intervalId in the state so it can be accessed later:
        this.setState({intervalId: intervalId});

        window.addEventListener('resize', this._resize);
        this._resize();
    }

    componentWillUnmount() {
        // use intervalId from the state to clear the interval
        if (this.state.intervalId)
            clearInterval(this.state.intervalId);
        window.removeEventListener('resize', this._resize);
    }

    timer() {
        this.state.cars.map((car, index) => {
            car.rotation += index - 0.7;
            car.longitude += (index - 1.7) * 0.002;
        });
        this.setState({cars: this.state.cars });
    }

    render() {
        return (
            <div id="mapContainer" style={mapContainerStyle}>
                <ReactMapGL
                    {...this.state.viewport}
                    onViewportChange={(viewport) => this.setState({viewport})}
                    mapboxApiAccessToken={TOKEN}
                    mapStyle='mapbox://styles/mapbox/basic-v9'
                >
                    {this.state.cars.map((car, index) =>
                        <Car {...car} key={index}/>
                    )}
                </ReactMapGL>
            </div>
        );
    }

    _resize = () => {
        this.setState({
            viewport: {
                ...this.state.viewport,
                width: this.props.width || window.innerWidth,
                height: this.props.height || window.innerHeight
            }
        });
    };

    _updateViewport = (viewport) => {
        this.setState({viewport});
    }
}

export default Map;