import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const ListingForm = () => {
    const navigate = useNavigate()
    const [isModalOpen, setIsModalOpen] = useState(false)
    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState('')
    const [error, setError] = useState('')

    const [formData, setFormData] = useState({
        itemName: '',
        itemImage: '',
        description: '',
        price: '',
        category: '',
        brand: '',
        finish: '',
        model: '',
        year: '',
        serialNo: '',
    })

    const handleOpen = () => {
        setIsModalOpen(true)
    }

    const handleClose = () => {
        setIsModalOpen(false)
    }

    const handleChange = (eventObject) => {
        const {name, value} = eventObject.target
        setFormData((prev) => ({...prev, [name]: value}))
    }

    const handleSubmit = async(eventObject) => {
        eventObject.preventDefault()
        setLoading(true)
        setMessage('')
        setError('')
        console.log("Handle Submit")
        try {
            // add listing controller or profile and then whats the fetch url
            const response = await fetch('http://localhost:8080/addlisting', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            if(response.ok) {
                setMessage('Added Listing!')
            } else {
                const errorText = await response.text()
                setError(`Added Listing Failed: ${response.status} - ${errorText || response.status}`)
            }
        } catch(err) {
            setError(`Network error: ${err.message}`)
            console.error(`adding listing error:`, err)
        } finally {
            setLoading(false)
        }
    }

    // UI
    return(
        <div>
            <button onClick={handleOpen} className='openModalBtn'>
                Add An Item
            </button>
            {isModalOpen && <ListingModal setIsModalOpen={setIsModalOpen} className='modal'></ListingModal>}
        </div>
    )
}

export default ListingForm