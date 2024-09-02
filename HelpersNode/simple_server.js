///////////////////////////////////
// Author: M. Schwartz
// Simple HTTP server
// Listens on configured port, parses requests, returns response, all paths.
///////////////////////////////////
var http = require('http');
var url = require('url');
var queryparser = require('querystring');

var port=8080; // The server object listens on port 8080

function processBody(data, req, res) {
  var body;
  if ( data.length > 0 ) {
    // Put it all back together
    body = data.join("");
    console.log("Body: ",body);
  } else {
    body = ""
    console.log("Empty body");
  }
  var urlParts = url.parse(req.url);
  var qryParts = queryparser.parse(body);
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.write('<h1>Hello World!</h1>\n'); //write a response to the client
  res.write('<h2><em>From the node server</em></h2')''
  res.write('<p>Pathname: '+JSON.stringify(urlParts.pathname)+'</p>\n');
  res.write('<p>Query:   '+JSON.stringify(qryParts)+'</p>\n');

  res.end(); //end the response

}

//create a server object:
http.createServer(function (req, res) {

  switch ( req.method ) {
    case "GET":
    var urlParts = url.parse(req.url);
    var qryParts = queryparser.parse(urlParts.query);
    console.log("Parsed "+req.url);
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write('<h1>Hello World!</h1>\n'); //write a response to the client
    res.write('<p>Pathname: '+JSON.stringify(urlParts.pathname)+'</p>\n');
    res.write('<p>Query:   '+JSON.stringify(qryParts)+'</p>\n');

    res.end(); //end the response
    break;
    case "POST":
    let data = []
    req.on('data', chunk => {
      data.push(chunk)
    });
    req.on('end', () => {
      processBody(data, req, res);
    });

    break;
  }
  console.log("Returned HTML page");
}).listen(port);
