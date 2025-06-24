import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const NavBar = () => {

    const navItems = [
        {name: 'Reverb-Clone', href:'/'},
        {name: 'Sign Up', href:'/login'},
        {name: 'Log In', href:'/register'}
    ]

    return (
        <div>
            <div className='main-navbar'>
                {navItems.map((item) => {
                return(
                    <a 
                    key={item.name}
                    href={item.href}
                    className="nav-items"
                    >
                    {item.name}
                    </a>
                )
            })}
            </div> 
        </div>
    )
}

export default NavBar