import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import LoginForm from './LoginForm'
import RegisterForm from './RegisterForm'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {//<LoginForm></LoginForm>
      }
      <RegisterForm></RegisterForm>
    </>
  )
}

export default App
