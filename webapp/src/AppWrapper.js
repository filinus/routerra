import React, { Component } from 'react';
import './App';
import App from "./App";
import {createStore, combineReducers} from "redux";
import { Provider } from 'react-redux'
import userReducer from "./userReducer";
import deviceReducer from "./deviceReducer";

const store = createStore(combineReducers({user:userReducer, device: deviceReducer}));

export default class AppWrapper extends Component {
  render() {
    return (
      <Provider store={store}>
        <App/>
      </Provider>
    )
  }


}
