""" post client example """
# Author: Rob Judd
# File:   get.py

import urllib.parse
import requests

url = "http://localhost:8000/"
myobj = {'somekey': 'somevalue'}

response = requests.post(url, data=myobj)
v = urllib.parse.parse_qsl(response.text)

print(response.text)
print(v)
