import React from 'react'

const ProfileNavBar = () => {
    const navItems = [
        {name: 'My Collection', href: ''},
        {name: 'My Account', href: ''},
    ]

    return (
        <div>
            <div className='prof-navbar'>
                {
                    navItems.map((item) => {
                        return(
                            <a 
                                key={item.name}
                                href={item.href}
                                className="prof-nav-items"
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

export default ProfileNavBar
