import {Marker} from 'react-map-gl';
import carTopIcon from "./images/car_top.svg";
import React from "react";
import ReactSVG from 'react-svg';
import Tooltip from '@material-ui/core/Tooltip'
import propTypes from "prop-types";

const markerWidth = 20;
const markerHeight = 50;
const markerStyle = {
    width: markerWidth+"pt",
    height: markerHeight+"pt",
    fill: 0
};

export default class Car extends Marker {
    static propTypes = {
        latitude : propTypes.number,
        longitude: propTypes.number,
        course: propTypes.number,
        color: propTypes.number,
        devname: propTypes.string
    };
    static defaultProps = {
        course: 90, //West
        color: 0, //black
        devname: "unknown"
    };

    state = {
        latitude: props.latitude,
        longitude: props.longitude,
        course: props.course || 0, //North
        color: props.color || 0, //black
        devname: props.devname || "unknown"
    };

    render () {
        //console.debug("render the car:", this.props);
        const {course, color, devname} = this.state;

        return(
            <Marker {...this.props} offsetLeft={-markerWidth/2} offsetTop={-markerHeight/2} >
                <Tooltip title={devname}>
                    <ReactSVG src={carTopIcon} alt="&#x1F698" svgStyle={{
                        ...markerStyle,
                        fill: color,
                        transform: `rotate(${course}deg)`
                    }} />
                </Tooltip>
            </Marker>
        )
    }
}

