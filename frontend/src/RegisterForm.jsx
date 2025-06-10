// register form to do
import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const RegisterForm = () => {
    const navigate = useNavigate()

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
        const response = await fetch('http://localhost:8080/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            if(response.ok) {
                setMessage('Registered User!')
                navigate(`user/${formData.username}`)
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

    return (
        <div>
            <h1>Register with username and password</h1>
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
                                Regestring...
                            </div>
                            ) : (
                                'Register'
                            )}
                </button>
            </div>
        </div>
    )
}

export default RegisterForm