import {Marker} from 'react-map-gl';
import carTopIcon from "../images/car_top.svg";
import React from "react";
import {ReactSVG} from 'react-svg';
import Tooltip from '@material-ui/core/Tooltip'
import propTypes from "prop-types";

const markerWidth = 20;
const markerHeight = 50;
const markerStyle = {
    width: markerWidth+"pt",
    height: markerHeight+"pt",
    fill: "black"
};

export default class Car extends React.Component {
    static propTypes = {
        latitude : propTypes.number,
        longitude: propTypes.number,
        course: propTypes.number,
        color: propTypes.string,
        devname: propTypes.string
    };
    static defaultProps = {
        course: 90, //West
        color: "black",
        devname: "unknown"
    };

    render() {
        const {course, color, devname} = this.props;
        return(
            <Marker {...this.props} offsetLeft={-markerWidth/2} offsetTop={-markerHeight/2} >
                <Tooltip title={devname} placement={"left-start"}>
                    <ReactSVG src={carTopIcon} alt="&#x1F698" style={{
                        ...markerStyle,
                        fill: color,
                        transform: `rotate(${course}deg)`
                    }} />
                </Tooltip>
            </Marker>
        )
    }
}

