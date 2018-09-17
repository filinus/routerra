import React, { Component } from 'react';
import './App.css';
import Map from './Map.jsx';
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import getData from "./getdata.js";


//const cfg = require('dotenv').config();
//console.log(cfg);

const kindaRespone = {content: []};

class App extends Component {
  constructor(props) {
        super(props);
        this.state = {
            response: kindaRespone
        }
  }

  componentDidMount() {
      getData("fleets")
          .then(data => {
              console.log("received fleet list");
              this.setState({response: data});
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
                      {this.state.response.content.map((fleet, index) =>
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
