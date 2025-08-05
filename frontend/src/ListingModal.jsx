import React, {useState} from 'react'

const ListingModal = ({ setIsModalOpen }) => {

    const handleClose = () => [
        setIsModalOpen(false)
    ]

    return(
        <div className="modalBackground">
            <div className="modalContainer">
                <form action="">
                    <input type="text" />
                </form>
                <button>Save</button>
                <button onClick={handleClose}>Close</button>
            </div>
        </div>
    )
}

export default ListingModal