import {Marker} from 'react-map-gl';
import carTopIcon from "./images/car_top.svg";
import React from "react";
import ReactSVG from 'react-svg';

const markerWidth = 20;
const markerHeight = 50;
const markerStyle = {
    width: markerWidth+"px",
    height: markerHeight+"px",
    fill: 0
};

export default class Car extends Marker {
    constructor(props) {
        super(props);
        this.state = {
            latitude: props.latitude,
            longitude: props.longitude,
            course: props.course || 0, //North
            color: props.color || 0 //black
        };
    }

    render () {
        return(
            <Marker {...this.props} offsetLeft={-markerWidth/2} offsetTop={-markerHeight/2} >
                <ReactSVG src={carTopIcon} alt="&#x1F698" svgStyle={{
                    ...markerStyle,
                    fill: this.props.color,
                    transform: "rotate("+(this.props.course||0)+"deg)"
                }} />
            </Marker>
        )
    }
}

 /*  */
