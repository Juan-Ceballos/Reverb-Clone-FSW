import React from 'react'

const SubNavBar = () => {
    const navItems = [
        {name: 'Guitar', href: ''},
        {name: 'Drums', href: ''},
        {name: 'Keyboards and Synths', href: ''},
        {name: 'Recording Gear', href: ''},
        {name: 'Pedals and Amplifiers', href: ''}
    ]

    return (
        <div>
            <div className='sub-navbar'>
                {
                    navItems.map((item) => {
                        return(
                            <a 
                                key={item.name}
                                href={item.href}
                                className="nav-items"
                            >
                                {item.name}
                            </a>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default SubNavBar
