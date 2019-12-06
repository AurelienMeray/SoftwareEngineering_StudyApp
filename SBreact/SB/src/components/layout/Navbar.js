import React from 'react'
import { Link } from 'react-router-dom'
import SignedInLinks from './SignedInLinks'
import SignedOutLinks from './SignedOutLinks'
import mainLogo from './logo.png'

const Navbar = (props) =>  {

   

    console.log(props)

    var rendered


    if(props.isLoggedIn){
        rendered = (<SignedInLinks/>)
    }
    else {
        rendered = (<SignedOutLinks/>)
    }

    return(  
        <nav className="nav-wrapper blue darken-3">
            <div className="container">
                <Link to='/signin' className="brand-logo">
                    <img width="160" alt='StuddyBud' src={mainLogo}></img>
                </Link>
                {rendered}
                
            </div>
        </nav>
    )
}

export default Navbar