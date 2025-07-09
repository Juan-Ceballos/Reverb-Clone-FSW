import React from 'react'
import logo from '../src/assets/Reverb-Clone-Logo.svg'

const ReverbLogoIcon = () => {
    return(
        <div className='reverb-logo'>
            <a
                key='Reverb-Clone'
                href='/'
                className='nav-item-logo'
            >
                <img
                    id='reverb-clone-logo'
                    src={logo}
                    alt='reverb-clone-logo'
                    className='icon'
                >
                </img>
            </a>
        </div>
    )
}

export default ReverbLogoIcon