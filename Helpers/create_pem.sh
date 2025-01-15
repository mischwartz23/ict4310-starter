#!/bin/sh

# Note: added -newkey and 2048 key length to avoid KEY_TOO_SMALL error.
openssl req -new -x509 -keyout localhost.pem -newkey rsa:2048 -out localhost.pem -days 365 -nodes
