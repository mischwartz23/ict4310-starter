""" get get client example"""
# Author: Michael Schwartz
# File:   get2.py
# Gets an SSL-based resource

import requests

url = "https://mysite.du.edu/~mschwart/"
params = {'address': 'Denver'}
response = requests.get(url, params)

print("==================")
print(response.headers)
print("==================")

data = response.text
print("Data: %s\n" % data)
