import React, { Component } from 'react';
import App from "./App";
import {createStore} from "redux";
import { Provider } from 'react-redux'
import combineReducers from "./redux/combine";

const store = createStore(combineReducers);

export default class AppWrapper extends Component {
  render() {
    return (
      <Provider store={store}>
        <App/>
      </Provider>
    )
  }


}
