import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const ListingForm = () => {
    const navigate = useNavigate()
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

    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState('')
    const [error, setError] = useState('')

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
            <button>
                Add An Item
            </button>
        </div>
    )
}

export default ListingForm