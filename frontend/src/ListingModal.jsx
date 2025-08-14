import React, {useState} from 'react'

const ListingModal = ({ setIsModalOpen }) => {

    const handleClose = () => {
        setIsModalOpen(false)
    }

    const handleSubmit = () => {
        return
    }

    return(
        <div className="modalBackground">
            <div className="modalContainer">
                <form action="">
                    <div className='item-name-field'>
                        <label>Item Name</label>
                        <input type="text" />
                    </div>
                    <div className='price-field'>
                        <label for="price-input">Price</label>
                        <input type="text" id='price-input'/>
                    </div>  
                </form>
                <button onClick={handleSubmit}>Save</button>
                <button onClick={handleClose}>Close</button>
            </div>
        </div>
    )
}

export default ListingModal