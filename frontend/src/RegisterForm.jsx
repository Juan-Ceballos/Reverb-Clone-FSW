// register form to do
import React, {useState} from 'react'

const RegisterForm = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
    })

    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState('')
    const [error, setError] = useState('')

    const handleChange = (eventObject) => {
        const {name, value} = eventObject.target
        setFormData(prev => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async(eventObject) => {
        eventObject.preventDefault()
        setLoading(true)
        setMessage('')
        setError('')

        try {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            if(response.ok) {
                
                setMessage('Registered User!')
            } else {
                const errorText = await response.text()
                setError(`Registration failed: ${response.status} - ${errorText || response.statusText}`)
            }

    } catch(err) {
        setError(`Network error: ${err.message}`)
        console.error(`Register error:`, err)
    } finally {
        setLoading(false)
    }
    }

    
}

