import React from 'react'

const SaveConfirmation = ({ onConfirm, onCancel }) => {
  return (
    <div className="dialog-overlay">
      <div className="confirmation-dialog">
        <h3>Konfirmasi</h3>
        <p>Apakah anda akan menyimpan data ini?</p>
        <div className="dialog-actions">
          <button onClick={onCancel} className="btn-no">No</button>
          <button onClick={onConfirm} className="btn-yes">Yes</button>
        </div>
      </div>
    </div>
  )
}

export default SaveConfirmation