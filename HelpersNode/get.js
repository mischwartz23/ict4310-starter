////////////////////////////////
// Author: Instructor
// Requires the 'node-fetch' module
// calls both get and post methods serially
////////////////////////////////
var fetch = require('node-fetch');

var url="http://localhost:8080/check"

function postContent(url,queryString) {
  return fetch(url, {
       method: 'post',
       headers: {
         'Accept': 'application/html, text/html, text/plain',
         'Content-Type': 'text/plain'
      },
      body: queryString
    }).then (function(response) {
       return response.text();
    }).then ( function (text) {
      console.log(text);
    });
}

function getContent(url,queryString) {
  return fetch(url+"?"+queryString, {
      method: 'get',
      headers: {
        'Accept': 'application/html, text/plain, text/html',
        'Content-Type': 'text/plain'
      }
    }).then( function (response) {
      return response.text();
    }).then (function (text) {
       console.log(text);
    });
}


postContent(url,"a=3&b=4").then(()=>console.log("Done with post"));

getContent(url,"a=4&b=5").then(() => console.log("Done with get"));
