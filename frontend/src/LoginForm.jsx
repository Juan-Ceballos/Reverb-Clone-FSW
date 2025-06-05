import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

// arrow function anonymys
const LoginForm = () => {
    const navigate = useNavigate()
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
                const result = await response.json()
                if(result.token) {
                    setMessage(`Login successful: ${formData.username}`)
                    console.log('Login successful:', result)
                    navigate(`user/${formData.username}`)
                } else {
                    setMessage('Login successful!')
                    navigate(`user/${formData.username}`)
                }
            } else {
                const errorText = await response.text()
                setError(`Login failed: ${response.status} - ${errorText || response.statusText}`)
            }
        } catch(err) {
            setError(`Network error: ${err.message}`)
            console.error(`Login error:`, err)
        } finally {
            setLoading(false)
        }
    }

    // UI
    return (
        <div>
            <h1>Sign in to your account</h1>
            <div>
                <label htmlFor="username" className='sr-only'>
                    Username
                </label>
                <input
                    id='username'
                    name='username' 
                    type="text" 
                    required 
                    className='appearance-none'
                    placeholder='Username'
                    value={formData.username}
                    onChange={handleChange}
                    onKeyDown={(eventObject) => eventObject.key === 'Enter' && handleSubmit(eventObject)}
                />
            </div>

            <div>
                <label htmlFor="password" className='sr-only'>
                    Password
                </label>
                <input
                    id='password'
                    name='password' 
                    type="password" // text
                    required
                    className='appearance-none'
                    placeholder='Password'
                    value={formData.password}
                    onChange={handleChange}
                    onKeyDown={(eventObject) => eventObject.key === 'Enter' && handleSubmit(eventObject)}
                />
            </div>

            {error && (
                <div className='text-red-700'>
                    {error}
                </div>
            )}

            {message && (
                <div className='text-green-700'>
                    {message}
                </div>
            )}

            <div>
                <button
                    type="button"
                    disabled={loading}
                    className='justify-center'
                    onClick={handleSubmit}
                >
                    {loading ? (
                        <div className='flex items-center'>
                            <div className='animate-spin rounded-full'></div>
                                Signing in...
                            </div>
                            ) : (
                                'Sign in'
                            )}
                </button>
            </div>

        </div>
    )
}

export default LoginForm
