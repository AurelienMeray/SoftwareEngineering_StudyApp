import React from 'react'
import { NavLink } from 'react-router-dom'

const SignedInLinks = () => {

    


    return(  
        <ul className="right">
            <li><NavLink to='/createroom'>Create Room</NavLink></li>
            <li><NavLink to='/searchrooms'>Search Room</NavLink></li>
            <li><NavLink to='/'>Logout</NavLink></li>
            <li><NavLink to='/' className='btn btn-floating blue lighten-2>'>Prof</NavLink></li>
        </ul>
    )
}

export default SignedInLinks