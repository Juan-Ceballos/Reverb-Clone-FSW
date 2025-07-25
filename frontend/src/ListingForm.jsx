import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const ListingForm = () => {
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

    const handleChange = (eventObject) => {
        const {name, value} = eventObject.target
        setFormData((prev) => ({...prev, [name]: value}))
    }

    const handleSubmit = async(eventObject) => {
        console.log("Handle Submit")
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