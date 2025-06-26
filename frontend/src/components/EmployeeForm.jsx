import React, { useState, useEffect } from 'react'
import { useNavigate, useParams, Link } from 'react-router-dom'
import EmployeeService from '../services/EmployeeService'
import PositionService from '../services/PositionService'
import SaveConfirmation from './SaveConfirmation'
import { FaArrowLeft } from 'react-icons/fa'

const EmployeeForm = () => {
  const navigate = useNavigate()
  const { id } = useParams()
  const [positions, setPositions] = useState([])
  const [showSaveDialog, setShowSaveDialog] = useState(false)
  const [isSubmitting, setIsSubmitting] = useState(false)
  
  const [employee, setEmployee] = useState({
    name: '',
    birth_date: '',
    position_id: { id: '' },
    id_number: '',
    gender: 0
  })

  useEffect(() => {
    const fetchData = async () => {
      try {
       
        const positionsResponse = await PositionService.getPositions()
        setPositions(positionsResponse.data)
        
       
        if (id) {
          const employeeResponse = await EmployeeService.getEmployeeWithPositions(id)
          const emp = employeeResponse.data.employee
          setEmployee({
            ...emp,
            birth_date: emp.birth_date.split('T')[0] 
          })
        }
      } catch (error) {
        console.error("Error fetching data:", error)
      }
    }
    
    fetchData()
  }, [id])

  const handleChange = (e) => {
    const { name, value } = e.target
    setEmployee(prev => ({ ...prev, [name]: value }))
  }

  const handlePositionChange = (e) => {
    const positionId = e.target.value
    setEmployee(prev => ({
      ...prev,
      position_id: { id: positionId }
    }))
  }

  const handleGenderChange = (e) => {
    setEmployee(prev => ({ ...prev, gender: parseInt(e.target.value) }))
  }

  const handleNumericInput = (e) => {
    const value = e.target.value.replace(/\D/g, '')
    setEmployee(prev => ({ ...prev, id_number: value }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    setShowSaveDialog(true)
  }

  const handleSaveConfirm = async () => {
    setIsSubmitting(true)
    try {
      const employeeData = {
        ...employee,
        birth_date: new Date(employee.birth_date).toISOString()
      }
      
      if (id) {
        await EmployeeService.updateEmployee(id, employeeData)
      } else {
        await EmployeeService.createEmployee(employeeData)
      }
      
      navigate('/')
    } catch (error) {
      console.error("Error saving employee:", error)
    } finally {
      setIsSubmitting(false)
      setShowSaveDialog(false)
    }
  }

  return (
    <div className="employee-form">
      <h1>Master Karyawan</h1>
      <h2>{id ? 'Edit Data Karyawan' : 'Add Data Karyawan'}</h2>
      
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Nama</label>
          <input
            type="text"
            name="name"
            value={employee.name}
            onChange={handleChange}
            required
          />
        </div>
        
        <div className="form-group">
          <label>Tanggal Lahir</label>
          <input
            type="date"
            name="birth_date"
            value={employee.birth_date}
            onChange={handleChange}
            required
          />
        </div>
        
        <div className="form-group">
          <label>Jabatan</label>
          <select
            value={employee.position_id.id}
            onChange={handlePositionChange}
            required
          >
            <option value="">== Pilih Jabatan ==</option>
            {positions.map(position => (
              <option key={position.id} value={position.id}>
                {position.name}
              </option>
            ))}
          </select>
        </div>
        
        <div className="form-group">
          <label>NIP</label>
          <input
            type="text"
            name="id_number"
            value={employee.id_number}
            onChange={handleNumericInput}
            required
          />
          <p className="form-note">Inputan "NIP" hanya boleh diisi dengan angka</p>
        </div>
        
        <div className="form-group">
          <label>Jenis Kelamin</label>
          <div className="radio-group">
            <label>
              <input
                type="radio"
                name="gender"
                value={1}
                checked={employee.gender === 1}
                onChange={handleGenderChange}
              />
              Pria
            </label>
            <label>
              <input
                type="radio"
                name="gender"
                value={2}
                checked={employee.gender === 2}
                onChange={handleGenderChange}
              />
              Wanita
            </label>
          </div>
        </div>
        
        <div className="form-actions">
          <Link to="/" className="btn-back">
            <FaArrowLeft /> Kembali
          </Link>
          <button 
            type="submit" 
            className="btn-save"
            disabled={isSubmitting}
          >
            {isSubmitting ? 'Menyimpan...' : 'Simpan'}
          </button>
        </div>
      </form>
      
      {showSaveDialog && (
        <SaveConfirmation 
          onConfirm={handleSaveConfirm}
          onCancel={() => setShowSaveDialog(false)}
        />
      )}
    </div>
  )
}

export default EmployeeForm