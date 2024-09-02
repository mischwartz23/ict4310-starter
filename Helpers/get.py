""" get client example """
# Author: Rob Judd
# File:   get.py
# The URL has the same parts as in form.html.
# The parameters are automatically converted to
#   the right form, depending on whether GET or
#   POST is requested. (requests.get or requests.post)
# Run by invoking:
#   python get.py 
import requests
url = "http://localhost:8000/test"
params = {'address': 'Denver'}
response = requests.get(url, params)

data = response.text
print("Data: %s\n" % data)
