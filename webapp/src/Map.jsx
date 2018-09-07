import React, {Component} from 'react';
import ReactMapGL from 'react-map-gl';
import Car from './Car.jsx';
//import feathers from "@feathersjs/client";
//import rest from "@feathersjs/rest-client";
//const client = require('./js/client.js');
const TOKEN = "pk.eyJ1IjoiZmlsaW51cyIsImEiOiJjamxpb2RsY2swM2Q1M3FvYWN6cnF3M3U0In0.t-8oNaXKO_tIPO1_K9ZqXw"; //https://www.mapbox.com/help/how-access-tokens-work/


const kindaResponse =
    {
        "_embedded" : {
            "locations" : [ {
                "latitude" : 1.0,
                "longitude" : 1.0,
                "course" : 1.0,
                "_links" : {
                    "self" : {
                        "href" : "http://localhost:8080/locations/89619fd4-56e9-43ee-9cc8-15937e9a841b"
                    },
                    "location" : {
                        "href" : "http://localhost:8080/locations/89619fd4-56e9-43ee-9cc8-15937e9a841b"
                    }
                }
            }, {
                "latitude" : 37.50425779346783,
                "longitude" : -122.26155928105942,
                "course" : null,
                "_links" : {
                    "self" : {
                        "href" : "http://localhost:8080/locations/656e8b1e-9db6-4e3e-b043-3024600f8b4e"
                    },
                    "location" : {
                        "href" : "http://localhost:8080/locations/656e8b1e-9db6-4e3e-b043-3024600f8b4e"
                    }
                }
            }, {
                "latitude" : 37.5045026,
                "longitude" : -122.2606919,
                "course" : null,
                "_links" : {
                    "self" : {
                        "href" : "http://localhost:8080/locations/96515d6a-7b18-4a6e-b8ae-330c137a7131"
                    },
                    "location" : {
                        "href" : "http://localhost:8080/locations/96515d6a-7b18-4a6e-b8ae-330c137a7131"
                    }
                }
            } ]
        },
        "_links" : {
            "self" : {
                "href" : "http://localhost:8080/locations{?page,size,sort}",
                "templated" : true
            },
            "profile" : {
                "href" : "http://localhost:8080/profile/locations"
            }
        },
        "page" : {
            "size" : 20,
            "totalElements" : 3,
            "totalPages" : 1,
            "number" : 0
        }
    };

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
        //carRotation: 45,
        cars: kindaResponse


  //          [
    //        {latitude:37.80,longitude:-122.43,rotation:0,color:"blue"},
    //        {latitude:37.90,longitude:-122.50,rotation:30,color:"orange"},
    //        {latitude:37.60,longitude:-122.0,rotation:-190,color:"green"}
    //    ]
    };

    componentDidMount() {
        //var intervalId = setInterval(this.timer.bind(this), 2000);
        // store intervalId in the state so it can be accessed later:
        ////this.setState({intervalId: intervalId});

        window.addEventListener('resize', this._resize);
        this._resize();

        //client({method: 'GET', path: 'http://localhost:8080/profile'}).done(response => {
        //    console.log(response.entity._embedded);
        //    //this.setState({employees: response.entity._embedded.employees});
        //});

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
                    {this.state.cars._embedded.locations.map((car, index) =>
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