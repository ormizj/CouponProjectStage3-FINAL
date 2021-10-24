import './header.css'
import history from '../history'
import { Route } from 'react-router-dom'
import AuthenticationService from './../../../services/AuthenticationService'
import Idle from 'react-idle'
import { useRef } from 'react'

export default function Header() {

    const limit = useRef(false)

    const idleLogout = () => {
        if (limit.current === false) {
            limit.current = true
            setTimeout(() => { alert("Logged out due to inactivity.") }, 0)
            AuthenticationService.logOut();
        }
    }

    const profile = () => {
        if (history.location.pathname.startsWith('/customer'))
            history.push('/customer/profile')
        if (history.location.pathname.startsWith('/company'))
            history.push('/company/profile')
    }

    const getProfile = () => {
        return (
            <div className='Header-button-div'>
                <Route path={['/customer', '/company']}>
                    <button className='Header-profile' onClick={() => profile()} title='Profile'>Profile</button>
                </Route>
                <button className='Header-logout' onClick={() => AuthenticationService.logOut()} title='Logout'>Logout</button>
            </div>
        )
    }

    return (<div className='Header'>
        <Idle
            timeout={1000 * 60 * 30}
            render={({ idle }) => <>{idle ? idleLogout() : undefined}</>}
        />
        {getProfile()}
    </div>)
}