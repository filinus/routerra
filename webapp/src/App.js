import React, { Component } from 'react';
import './App.css';
import { createStore } from 'redux'
import { Provider } from 'react-redux'
import Map from './Map.jsx';
import AppMenu from "./AppMenu";
import userReducer from "./userReducer";

console.log(process.env.NODE_ENV || "n/a", process.env.PUBLIC_URL || "n/a");

const store = createStore(userReducer);

class App extends Component {
  render() {
    return (
      <Provider store={store}>
          <div className="App">
              <AppMenu/>
              <Map/>
          </div>
      </Provider>
    );
  }
}

export default App;
