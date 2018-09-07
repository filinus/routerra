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


const kindaRespone =
    {
        "_embedded" : {
            "fleets" : [ {
                "fleetname" : "UPS South San Francisco",
                "_links" : {
                    "self" : {
                        "href" : "http://localhost:8080/fleets/89619fd4-56e9-43ee-9cc8-15937e9a841b"
                    },
                    "fleet" : {
                        "href" : "http://localhost:8080/fleets/89619fd4-56e9-43ee-9cc8-15937e9a841b"
                    }
                }
            }, {
                "fleetname" : "Val Garage",
                "_links" : {
                    "self" : {
                        "href" : "http://localhost:8080/fleets/89619fd4-56e9-43ee-9cc8-15937e9a841c"
                    },
                    "fleet" : {
                        "href" : "http://localhost:8080/fleets/89619fd4-56e9-43ee-9cc8-15937e9a841c"
                    }
                }
            }, {
                "fleetname" : "Kelly Moore Painting",
                "_links" : {
                    "self" : {
                        "href" : "http://localhost:8080/fleets/89619fd4-56e9-43ee-9cc8-15937e9a841d"
                    },
                    "fleet" : {
                        "href" : "http://localhost:8080/fleets/89619fd4-56e9-43ee-9cc8-15937e9a841d"
                    }
                }
            } ]
        },
        "_links" : {
            "self" : {
                "href" : "http://localhost:8080/fleets{?page,size,sort}",
                "templated" : true
            },
            "profile" : {
                "href" : "http://localhost:8080/profile/fleets"
            }
        },
        "page" : {
            "size" : 20,
            "totalElements" : 3,
            "totalPages" : 1,
            "number" : 0
        }
    };

class App extends Component {
  constructor(props) {
        super(props);
        this.state = {
            response: kindaRespone
        }
  }

  componentDidMount() {
      var config = {
          headers: {'Access-Control-Allow-Origin': '*'}
      };
      axios.get("http://localhost:8080/fleets", config)
          .then(res => {
              const fleetsData = res.data;
              console.log(fleetsData);
              this.setState({ response:fleetsData });
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
                  <Select autoWidth={true} displayEmpty={false}>
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
