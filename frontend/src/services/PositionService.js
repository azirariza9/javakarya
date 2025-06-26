import axios from 'axios'

const API_URL = 'http://localhost:8080/api/positions'

const getPositions = () => {
  return axios.get(API_URL)
}

export default {
  getPositions
}