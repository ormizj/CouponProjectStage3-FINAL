import { Redirect, Route, Switch } from 'react-router-dom'
import CustomerHome from './CustomerHome/CustomerHome'
import CustomerProfile from './CustomerProfile/CustomerProfile'
import CustomerCoupons from './CustomerCoupons/CustomerCoupons'
import CouponsCustomer from './CouponsCustomer/CouponsCustomer'

export default function Customer() {

    const getContent = () => {
        return (
            <Switch>
                <Route path='/customer/my-coupons'>
                    <CustomerCoupons />
                </Route>
                <Route path='/customer/coupons'>
                    <CouponsCustomer />
                </Route>
                <Route path='/customer/profile'>
                    <CustomerProfile />
                </Route>
                <Route path='/customer'>
                    <Redirect to='/customer/home' />
                    <CustomerHome />
                </Route>
            </Switch>
        )
    }

    return (
        <div className='Customer'>
            {getContent()}
        </div>
    )

}