import React from 'react';
import ReactDOM from 'react-dom';
import AppWrapper from '../AppWrapper';

it('renders without crashing', (itDone) => {
  const div = document.createElement('div');
  ReactDOM.render(<AppWrapper />, div);
  ReactDOM.unmountComponentAtNode(div);
  itDone()
}, 10);
