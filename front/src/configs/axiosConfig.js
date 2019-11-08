import qs from 'qs';
if (!global.$post) {
  global.$post = {};
  global.$get = {};
}
var axios = require('axios')
var apiUrl = 'https://quiet-cove-89524.herokuapp.com/main'

global.$get = function(section, form) {
  let headers = {
    'Content-Type': 'application/json'
  }
  return axios({
    method: 'GET',
    url: apiUrl + section,
    data: JSON.stringify(form),
    headers: headers
  })
}

global.$post = function(section, form) {
  let headers = {
    'Content-Type': 'application/json'
  }
  return axios({
    method: 'POST',
    url: apiUrl + section,
    data: JSON.stringify(form),
    headers: headers
  })
}