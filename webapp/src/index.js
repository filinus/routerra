import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import {createStore, combineReducers} from "redux";
import { Provider } from 'react-redux'
import userReducer from "./userReducer";
import deviceReducer from "./deviceReducer";

const store = createStore(combineReducers({user:userReducer, device: deviceReducer}));
ReactDOM.render(<Provider store={store}><App /></Provider>, document.getElementById('root'));
registerServiceWorker();
