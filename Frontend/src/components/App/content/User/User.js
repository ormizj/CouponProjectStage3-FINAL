import { Suspense, useEffect, useRef, lazy } from 'react'
import { Route, Switch } from 'react-router-dom'
import './user.css'
import history from '../../history'
import { withRouter } from 'react-router'
const Admin = lazy(() => import('./Admin/Admin'))
const Company = lazy(() => import('./Company/Company'))
const Customer = lazy(() => import('./Customer/Customer'))

function User() {
    const userRef = useRef()

    useEffect(() => {
        switch (history.location.pathname) {
            case '/customer/home':
            case '/company/home':
            case '/admin/home':
                userRef.current.id = ''
                break;
            default:
                userRef.current.id = 'User-opacity'
                break;
        }
    })

    const getUser = () => {
        return (
            <Suspense fallback={<></>}>
                <Switch>
                    <Route path='/customer'>
                        <Customer />
                    </Route>
                    <Route path='/company'>
                        <Company />
                    </Route>
                    <Route path='/admin'>
                        <Admin />
                    </Route>
                </Switch >
            </Suspense>
        )
    }

    return (
        <div className="User">
            <div className='User-background' ref={userRef}>
                {getUser()}
            </div>
        </div >
    )

}

export default withRouter(User)