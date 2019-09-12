/*********************************************************************************
 *  WEB422 – Assignment 2
 *  I declare that this assignment is my own work in accordance with Seneca  Academic Policy.
 *  No part of this assignment has been copied manually or electronically from any other source
 *  (including web sites) or distributed to other students.
 *
 *  Name: Tashi Tsering Student ID: 114763170 Date: May 29th, 2019
 *
 *
 ********************************************************************************/

// Import jQuery, which will also expose $ on the global `window` Object.
import $ from './jquery-es';
// After jQuery is loaded, we can load the Bootstrap JS, which depends on jQuery.
import 'bootstrap';

// Place your imports for Moment.js and Lodash here...
import moment from 'moment';
import _ from 'lodash';

// The rest of your code can go here.  You're also welcome to split
// your code up into multiple files using ES modules and import/export.

let employeesModel = [];

function initializeEmployeesModel() {
  $.ajax({
    url: 'https://web422-api-server.herokuapp.com/employees',
    type: 'GET',
    contentType: 'application/json'
  })
    .done(function(data) {
      employeesModel = data;
      refreshEmployeeRows(employeesModel);
    })
    .fail(function() {
      showGenericModal('Error', 'Unable to get Employees');
    });
}

function showGenericModal(title, message) {
  $('.modal-title').html(title);
  $('.modal-body').html(message);
  $('#genericModal').modal({});
}

function refreshEmployeeRows(employees) {
  $('#employees-table').empty();
  let rowsTemplate = _.template(
    '<% _.forEach(employees, function(employee) { %>' +
      '<div class="row body-row" data-id=<%- employee._id %>>' +
      '<div class="col-xs-4 body-column"><%- employee.FirstName %></div>' +
      '<div class="col-xs-4 body-column"><%- employee.LastName %></div>' +
      '<div class="col-xs-4 body-column"><%- employee.Position.PositionName %></div>' +
      '</div>' +
      '<% }); %>'
  );

  let rows = rowsTemplate({
    employees: employees
  });
  $('#employees-table').html(rows);
}

function getFilteredEmployeesModel(filterString) {
  return _.filter(employeesModel, function(employee) {
    if (
      employee.FirstName.toLowerCase().includes(filterString.toLowerCase()) ||
      employee.LastName.toLowerCase().includes(filterString.toLowerCase()) ||
      employee.Position.PositionName.toLowerCase().includes(filterString.toLowerCase())
    ) {
      return true;
    }
    return false;
  });
}

function getEmployeeModelById(id) {
  let retVal = null;
  for (let i = 0; i < employeesModel.length; i++) {
    if (employeesModel[i]._id === id) {
      retVal = _.cloneDeep(employeesModel[i]);
    }
  }
  return retVal;
}

$(document).ready(function() {
  initializeEmployeesModel();

  $('#employee-search').on('keyup', function() {
    let emp = getFilteredEmployeesModel($(this).val());
    refreshEmployeeRows(emp);
  });

  $(document).on('click', '.body-row', function() {
    let employee = getEmployeeModelById($(this).attr('data-id'));

    if (employee !== null) {
      employee.HireDate = moment(employee.HireDate).format('LL');

      let modalContentTemplate = _.template(
        '<strong>Address:</strong> <%- employee.AddressStreet %> <%- employee.AddressCity %>, <%- employee.AddressState %>. <%- employee.AddressZip %></br>' +
          '<strong>Phone Number:</strong> <%- employee.PhoneNum %> ext: <%- employee.Extension %></br>' +
          '<strong>Hire Date:</strong> <%- employee.HireDate %>'
      );
      let modalContent = modalContentTemplate({
        employee: employee
      });

      showGenericModal(employee.FirstName + ' ' + employee.LastName, modalContent);
    }
  });
});
