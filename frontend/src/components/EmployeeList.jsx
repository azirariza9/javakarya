import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import EmployeeService from '../services/EmployeeService'
import DeleteConfirmation from './DeleteConfirmation'
import { FaEdit, FaTrash } from 'react-icons/fa'

const EmployeeList = () => {
    const [employees, setEmployees] = useState([])
    const [showDeleteDialog, setShowDeleteDialog] = useState(false)
    const [selectedEmployee, setSelectedEmployee] = useState(null)
    const [currentPage, setCurrentPage] = useState(0)
    const [totalPages, setTotalPages] = useState(0)

    useEffect(() => {
        fetchEmployees()
    }, [currentPage])

    const fetchEmployees = async () => {
        try {
            const response = await EmployeeService.getEmployees(currentPage)
            setEmployees(response.data.content)
            setTotalPages(response.data.totalPages)
        } catch (error) {
            console.error("Error fetching employees:", error)
        }
    }

    const handleDeleteClick = (employee) => {
        setSelectedEmployee(employee)
        setShowDeleteDialog(true)
    }

    const handleDeleteConfirm = async () => {
        if (selectedEmployee) {
            try {
                await EmployeeService.deleteEmployee(selectedEmployee.id)
                fetchEmployees()
            } catch (error) {
                console.error("Error deleting employee:", error)
            }
            setShowDeleteDialog(false)
        }
    }

    const formatDate = (dateString) => {
        const options = { day: 'numeric', month: 'long', year: 'numeric' }
        return new Date(dateString).toLocaleDateString('id-ID', options)
    }
    return (
        <div className="employee-list">
            <h1>Master Karyawan</h1>

            <div className="header">
                <h2>List Karyawan</h2>
                <Link to="/add" className="btn-add">
                    Tambah
                </Link>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Tanggal Lahir</th>
                        <th>Jabatan</th>
                        <th>NIP</th>
                        <th>Jenis Kelamin</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee, index) => (
                        <tr key={employee.id}>
                            <td>{currentPage * 25 + index + 1}</td>
                            <td>{employee.name}</td>
                            <td>{formatDate(employee.birth_date)}</td>
                            <td>{employee.position_id.name}</td>
                            <td>{employee.id_number}</td>
                            <td>{employee.gender === 1 ? 'Pria' : 'Wanita'}</td>
                            <td className="actions">
                                <Link to={`/edit/${employee.id}`} className="btn-edit">
                                    <FaEdit />
                                </Link>
                                <button
                                    onClick={() => handleDeleteClick(employee)}
                                    className="btn-delete"
                                >
                                    <FaTrash />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="pagination">
                <button
                    disabled={currentPage === 0}
                    onClick={() => setCurrentPage(currentPage - 1)}
                >
                    Previous
                </button>
                <span>Page {currentPage + 1} of {totalPages}</span>
                <button
                    disabled={currentPage >= totalPages - 1}
                    onClick={() => setCurrentPage(currentPage + 1)}
                >
                    Next
                </button>
            </div>

            {showDeleteDialog && (
                <DeleteConfirmation
                    onConfirm={handleDeleteConfirm}
                    onCancel={() => setShowDeleteDialog(false)}
                />
            )}
        </div>
    )
}

export default EmployeeList
