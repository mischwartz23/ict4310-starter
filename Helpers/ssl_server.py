""" Simple SSL server """
# Author: Michael Foukarakis (stackOverflow: Python 3 Simple HTTPS server)
# Modified by: Michael Schwartz
# File:   ssl_server.py

# MIS: Instructions for creating the keyfile. Use localhost for the common name
# MIS: Note: key length must be longer than default (1024) to be acceptable to TLS
# creating the keyfile:
# openssl req -new -x509 -keyout localhost.pem -out localhost.pem -days 365 -nodes -pkeyopt rsa_keygen_bits:4096

import http.server
import ssl

certfile = 'localhost.pem'
keyfile = 'localhost.pem'

server_address = ('localhost', 4443)
httpd = http.server.HTTPServer(server_address, http.server.SimpleHTTPRequestHandler)

# MIS: Replacing deprecated  method ssl.wrap_socket with the replacement method SSLContext.wrap_socket
context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
context.load_cert_chain(certfile=certfile, keyfile=keyfile, password=None)
httpd.socket = context.wrap_socket( httpd.socket, server_side=True) 

# MIS: Added a 'ready' message
print ("Server ready on", server_address[0], "port", server_address[1])

httpd.serve_forever()
