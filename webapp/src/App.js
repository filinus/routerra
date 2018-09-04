import React, { Component } from 'react';
import './App.css';
import Map from './Map.jsx';
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button';


//const cfg = require('dotenv').config();
//console.log(cfg);

class App extends Component {
  render() {
    return (
      <div className="App">
          <AppBar position="static">
              <Toolbar>
                  <Typography variant="title" color="inherit"  className={"icon big car"}>
                      Routerra Demo App
                  </Typography>
                  <Button>Button 1</Button>
                  <Button>Button 2</Button>
              </Toolbar>
          </AppBar>

      <Map/>
      </div>
    );
  }
}

export default App;
