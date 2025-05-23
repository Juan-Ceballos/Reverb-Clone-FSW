import React, {useState} from 'react'

// arrow function anonymys
const LoginForm = () => {
    // array destructuring, useState is an array with two values
    // useState array first element = formData, array 2 = setFormData
    // each value is an object of {username, password}
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    })


    // different use states of component
    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState('')
    const [error, setError] = useState('')

    // handles username or password input when changes
    // handles based on name and sets new value
    const handleChange = (eventObject) => {
        const {name, value} = eventObject.target
        setFormData(prev => ({
            //... copies prev, 
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async (eventObject) => {
        error.preventDefault()
        setLoading(true)
        setMessage('')
        setError('')

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            })
            if(response.ok) {
                const result = await response.text()
                setMessage(`Login successful: ${result}`)
                console.log('Login successful:', result)
            } else {
                setError(`Login failed: ${response.status} - ${response.statusText}`)
            }
        } catch(err) {
            setError(`Network error: ${err.message}`)
            console.error(`Login error:`, err)
        } finally {
            setLoading(false)
        }
    }

    return (
        <div>
            <h1>Sign in to your account</h1>
        </div>
    )
}

export default LoginForm
