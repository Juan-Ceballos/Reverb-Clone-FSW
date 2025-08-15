import React, {useState} from 'react'

const ListingModal = ({ setIsModalOpen }) => {

    const [FormData, setFormData] = useState({
        itemName: '',
        price: ''
    })

    const handleClose = () => {
        setIsModalOpen(false)
    }

    const handleChange = (eventObject) => {
        const {name, value} = eventObject.target
        setFormData(prev => ({
            ...prev,
            [name]: value
        }))
    }

    const [error, setError] = useState('')

    const handleSubmit = async (eventObject) => {
        eventObject.preventDefault()
        setError('')

        try {
            const response = await fetch(`http://localhost:8080/user/`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify()
            })
            if (response.ok) {
                const result = await response.json()
                console.log(result)
            }
        } catch(err) {
            setError(`Network error: ${err.message}`)
            console.error(`Posting Failed:`)
        }
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
                        <input type="text" id='price-input' onChange={handleChange}/>
                    </div>  
                </form>
                <button onClick={handleSubmit}>Save</button>
                <button onClick={handleClose}>Close</button>
            </div>
            {error && (
                <div className='text-red-700'>
                    {error}
                </div>
            )}
        </div>
    )
}

export default ListingModal