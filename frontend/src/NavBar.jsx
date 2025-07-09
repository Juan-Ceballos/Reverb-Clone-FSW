import React from 'react'
import logo from '../src/assets/Reverb-Clone-Logo.svg'

const NavBar = () => {

    const navItems = [
        {name: 'Reverb-Clone', href:'/'},
        {name: 'Search Bar', href:''},
        {name: 'Sign Up', href:'/login'},
        {name: 'Log In', href:'/register'}
    ]

    return (
        <div>
            <div className='main-navbar'>
                {navItems.map((item) => {
                    if(item.name != "Reverb-Clone") {
                        return(
                            <a 
                                key={item.name}
                                href={item.href}
                                className="nav-items"
                            >
                                {item.name}
                            </a>
                        )
                    } else {
                        return(
                            <a
                                key={item.name}
                                href={item.href}
                                className="nav-item-logo"
                            >
                                <img 
                                    id='reverb-clone-logo' 
                                    src={logo} 
                                    alt='reverb-clone-logo'
                                    className='icon'
                                >
                                </img>  
                            </a>
                        )
                    }
            })}
            </div> 
        </div>
    )
}

export default NavBar