/*********************************************************************************
 *  WEB422 – Assignment 1
 *  I declare that this assignment is my own work in accordance with Seneca  Academic Policy.
 *  No part of this assignment has been copied manually or electronically from any other source
 *  (including web sites) or distributed to other students.
 *
 *  Name: Tashi Tsering Student ID: 114763170 Date: May 14th, 2019
 *
 *
 ********************************************************************************/

$(document).ready(function() {
    console.log('jQuery Working');

    $('#teams-menu').on('click', function(event) {
        event.preventDefault();
        $.get('https://web422-api-server.herokuapp.com/teams').done(function(data) {
            $('.well')
                .empty()
                .append('<h3>Teams<h3>')
                .append('#teams-menu')
                .html('<pre>' + prettyPrintJson.toHtml(data) + '</pre>');
        });
    });

    $('#employees-menu').on('click', function(event) {
        event.preventDefault();
        $.get('https://web422-api-server.herokuapp.com/employees').done(function(data) {
            $('.well')
                .empty()
                .append('<h3>Employees<h3>')
                .append('#employees-menu')
                .html('<pre>' + prettyPrintJson.toHtml(data) + '</pre>');
        });
    });

    $('#projects-menu').on('click', function(event) {
        event.preventDefault();
        $.get('https://web422-api-server.herokuapp.com/projects').done(function(data) {
            $('.well')
                .empty()
                .append('<h3>Projects<h3>')
                .append('#projects-menu')
                .html('<pre>' + prettyPrintJson.toHtml(data) + '</pre>');
        });
    });
    $('#positions-menu').on('click', function(event) {
        event.preventDefault();
        $.get('https://web422-api-server.herokuapp.com/positions').done(function(data) {
            $('.well')
                .empty()
                .append('<h3>Positions<h3>')
                .append('#positions-menu')
                .html('<pre>' + prettyPrintJson.toHtml(data) + '</pre>');
        });
    });
});
