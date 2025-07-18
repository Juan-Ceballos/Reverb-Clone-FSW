import './App.css'
import LoginForm from './LoginForm'
import RegisterForm from './RegisterForm'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import ProfilePage from './Profile'
import NavBar from './NavBar'
import SubNavBar from './SubNavBar'

function App() {
  // Main component entrypoint, based on address routes to component
  return (
    <>
      <NavBar></NavBar>
      <SubNavBar></SubNavBar>
      <BrowserRouter>
        <Routes>
          <Route path="/register" element={<RegisterForm></RegisterForm>}>
          </Route>
          <Route path="/login" element={<LoginForm></LoginForm>}>
          </Route>
          <Route path="/user/:username" element={<ProfilePage></ProfilePage>}>
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
