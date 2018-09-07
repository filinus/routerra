import React, { Component } from 'react';
import './App.css';
import Map from './Map.jsx';
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import axios from 'axios';


//const cfg = require('dotenv').config();
//console.log(cfg);

const kindaRespone = {_embedded: {fleets: []}};

class App extends Component {
  constructor(props) {
        super(props);
        this.state = {
            response: kindaRespone
        }
  }

  componentDidMount() {
      const config = {headers: {'Access-Control-Allow-Origin': '*'}};
      axios.get("http://localhost:8080/fleets", config)
          .then(res => {
              console.log("received fleet list");
              const data = res.data;
              if (res.status===200 && data && data._embedded && data._embedded.fleets) {
                  this.setState({response: data});
              }
          })
  }

  render() {
    return (
      <div className="App">
          <AppBar position="static">
              <Toolbar>
                  <Typography variant="title" color="inherit"  className={"icon big car VDivider"}>
                      Routerra Demo App
                  </Typography>
                  <Select autoWidth={true} displayEmpty={true}>
                      {this.state.response._embedded.fleets.map((fleet, index) =>
                          <MenuItem key={"fleet"+index}>{fleet.fleetname}</MenuItem>
                      )}
                  </Select>
              </Toolbar>
          </AppBar>

      <Map/>
      </div>
    );
  }
}

export default App;
