import React, {Component} from 'react';
import ReactMapGL from 'react-map-gl';
import Car from './Car.jsx';
import axios from 'axios';

const TOKEN = "pk.eyJ1IjoiZmlsaW51cyIsImEiOiJjamxpb2RsY2swM2Q1M3FvYWN6cnF3M3U0In0.t-8oNaXKO_tIPO1_K9ZqXw"; //https://www.mapbox.com/help/how-access-tokens-work/

const mapContainerStyle = {
    width: "100%",
    height: "100%",
    textAlign: "left"
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
        //var intervalId = setInterval(this.timer.bind(this), 2000);
        // store intervalId in the state so it can be accessed later:
        ////this.setState({intervalId: intervalId});

        window.addEventListener('resize', this._resize);
        this._resize();

        const config = {headers: {'Access-Control-Allow-Origin': '*'}};
        axios.get("http://localhost:8080/devices", config)
            .then(res => {
                console.log("received locations response:", res);
                const data = res.data;
                if (res.status===200 && data && data.content) {
                    console.info("cars found:", data.content);
                    this.setState({cars: data.content});
                } else {
                    console.error("bad response");
                }
            })

    }

    componentWillUnmount() {
        // use intervalId from the state to clear the interval
        ////if (this.state.intervalId)
        ////    clearInterval(this.state.intervalId);
        window.removeEventListener('resize', this._resize);
    }

    // timer() {
    //     this.state.cars.map((car, index) => {
    //         car.rotation += index - 0.7;
    //         car.longitude += (index - 1.7) * 0.002;
    //     });
    //     this.setState({cars: this.state.cars });
    // }

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
                        <Car {...car.lastLocation} color={car.color} key={index}/>
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