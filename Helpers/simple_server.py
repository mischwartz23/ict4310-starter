#!/usr/bin/env python3
"""
Very simple HTTP server in python for serving static files and POST requests.
Usage::
    python simple_server.py [<port>]
"""

# Author: Rob Judd
# File:   simple_server.py

from http.server import SimpleHTTPRequestHandler, HTTPServer
import logging
import urllib.parse
import os.path
# import json

class HTTPRequestHandler(SimpleHTTPRequestHandler):
    """ HTTP request handler """

    def _set_response(self):
        """Sends additional headers and marks the response as ready to send the body."""
        self.send_response(200)
        self.end_headers()

    def do_GET(self):
        """
        Handles GET requests.

        GET requests are those where all the data is encoded into the URL, e.g.
        http://localhost:8080/form?spam=1&eggs=2

        If the path referred to in the URL exists on the file system below the working
        directory of the server (e.g. www) then it will return the file. Otherwise it
        logs the request back to the user.

        """

        request = urllib.parse.urlparse(self.path)
        file = self.directory + request.path

        if os.path.exists(file):
            super().do_GET()
        else:
            logging.info("GET request,\nPath: %s\nHeaders:\n%s\n",
                         str(self.path), str(self.headers))
            logging.info("File %s not found.", file)
            self._set_response()
            self.wfile.write("GET request for {}".format(self.path).encode('utf-8'))

    def do_POST(self):
        """
        Handles POST requests.

        A POST request is where the data for the request is embedded in the body of the request.
        It has the same format as the query string of a GET request and therefore may be
        interpretted the same way.

        In order to read the request body we have to know how much data to read, this is
        called the Content-Length. It is a required part of the HTTP standard so it may
        be relied upon to be present.
        """

        content_length = int(self.headers['Content-Length']) # <--- Gets the size of data
        post_data = self.rfile.read(content_length) # <--- Gets the data itself
        logging.info("POST request,\nPath: %s\nHeaders:\n%s\n\nBody:\n%s\n",
                     str(self.path), str(self.headers), post_data.decode('utf-8'))

        # Here we'll make up some data to respond with and send it back to the caller.
        data = urllib.parse.urlencode({'spam': 1, 'eggs': 2, 'bacon': 0})
        data = data.encode('ascii')
        self._set_response()
        self.wfile.write(data)


def run(server_class=HTTPServer, handler_class=HTTPRequestHandler, port=8000):
    """
    Initialize and run the HTTPServer.

    HTTPServer is a builtin Python class that contains the basics necessary to implement
    an HTTP server. In order to use it you create an instance of the server, tell it
    what port to listen on and give it a RequestHandler class to actually process requests.
    This is typically a subclass of BaseHTTPRequestHandler. In order to use it you must
    override at least one of do_GET or do_POST.

    Args:
      server_class: the name of the server class to instantiate for the web server.
      handler_class: the name of the class to use for the RequestHandler.
      port: the port to listen on, must be greater than 1024.
    """
    logging.basicConfig(level=logging.INFO)
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)
    logging.info('Starting httpd...\n')
    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        pass
    httpd.server_close()
    logging.info('Stopping httpd...\n')

if __name__ == '__main__':
    from sys import argv

    if len(argv) == 2:
        run(port=int(argv[1]))
    else:
        run()
