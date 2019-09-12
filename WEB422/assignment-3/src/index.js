/*********************************************************************************
 * WEB422 – Assignment 03
 * I declare that this assignment is my own work in accordance with Seneca Academic Policy. No part
 of this
 * assignment has been copied manually or electronically from any other source (including web sites)
 or
 * distributed to other students.
 *
 * Name: Tashi Tsering     Student ID: 114763170      Date: 06/18/2019
 *
 ********************************************************************************/
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import TeamComponent from './TeamComponent';
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<TeamComponent />, document.getElementById('root'));

serviceWorker.unregister();
