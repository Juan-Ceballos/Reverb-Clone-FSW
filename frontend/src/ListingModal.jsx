import React, {useState} from 'react'

const ListingModal = ({ setIsModalOpen }) => {

    const handleClose = () => [
        setIsModalOpen(false)
    ]

    return(
        <div className="modalBackground">
            <div className="modalContainer">
                <form action="">
                    <div className='price-field'>
                        <label for="price-input">Price</label>
                        <input type="text" id='price-input'/>
                    </div>  
                </form>
                <button>Save</button>
                <button onClick={handleClose}>Close</button>
            </div>
        </div>
    )
}

export default ListingModal