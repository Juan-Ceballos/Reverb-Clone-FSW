import React from 'react'
import ReverbLogoIcon from './Reverb-Clone-Icon'
import MainSearchBar from './MainSearchBar'

const NavBar = () => {

    const navItems = [
        {name: 'Sign Up', href:'/register'},
        {name: 'Log In', href:'/login'}
    ]

    return (
        <div>
            <div className='nav-bar-container'>
                <ReverbLogoIcon></ReverbLogoIcon>
                <MainSearchBar></MainSearchBar>
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
        </div>
    )
}

export default NavBar