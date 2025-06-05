import { useState } from 'react'
import './App.css'
import LoginForm from './LoginForm'
import RegisterForm from './RegisterForm'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import ProfilePage from './Profile'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/register" element={<RegisterForm></RegisterForm>}>
          </Route>
          <Route path="/login" element={<LoginForm></LoginForm>}>
          </Route>
          <Route path="/username/:username" element={<ProfilePage></ProfilePage>}>
          </Route>
        </Routes>
      </BrowserRouter>
      <RegisterForm></RegisterForm>
    </>
  )
}

export default App
