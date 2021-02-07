import * as React from 'react';
import MapGL, {NavigationControl} from 'react-map-gl';

const TOKEN = process.env.REACT_APP_MAPBOX_TOKEN; //https://www.mapbox.com/help/how-access-tokens-work/
const navStyle = {
    position: 'absolute',
    top: 0,
    left: 0,
    padding: '7px'
};

const MAPBOXSTYLE = "mapbox://styles/mapbox/streets-v10"

function SmartMap(props) {
    const [viewport, setViewport] = React.useState({
        latitude: 37.7577,
        longitude: -122.4376,
        zoom: 9,
        bearing: 0,
        pitch: 0
    });

    console.debug("SmartMap, process.env:", process.env, "props:", props)
    return (
            <MapGL
                {...viewport}
                width="100vw"
                height="100vh"
                onViewportChange={(viewport) => setViewport(viewport)}
                mapboxApiAccessToken={TOKEN}
                mapStyle={MAPBOXSTYLE}
                {...props}
            >
                <div className="nav" style={navStyle}>
                    <NavigationControl />
                </div>
                {props.children}
            </MapGL>
    );
}

export default SmartMap
