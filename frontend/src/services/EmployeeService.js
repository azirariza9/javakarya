import axios from 'axios'

const API_URL = 'http://localhost:8080/api/employees'


const getEmployees = (page = 0, size = 25) => {
  return axios.get(`${API_URL}/index?page=${page}&size=${size}`)
}

const getEmployeeWithPositions = (id) => {
  return axios.get(`${API_URL}/${id}/add-edit`)
}

const createEmployee = (employee) => {
  return axios.post(API_URL, employee)
}

const updateEmployee = (id, employee) => {
  return axios.put(`${API_URL}/${id}`, employee)
}

const deleteEmployee = (id) => {
  return axios.delete(`${API_URL}/${id}`)
}

export default {
  getEmployees,
  getEmployeeWithPositions,
  createEmployee,
  updateEmployee,
  deleteEmployee
}