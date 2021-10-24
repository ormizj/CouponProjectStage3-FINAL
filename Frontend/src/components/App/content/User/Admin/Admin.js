import AdminHome from './AdminHome/AdminHome'
import { Redirect, Route, Switch } from 'react-router-dom'
import AdminLogs from './AdminLogs/AdminLogs'
import AdminCompanies from './AdminCompanies/AdminCompanies'
import AdminCustomers from './AdminCustomers/AdminCustomers'
import ViewCoupons from '../view/ViewCoupons/ViewCoupons'

export default function Admin() {

    const getContent = () => {
        return (
            <Switch>
                <Route path='/admin/customers'>
                    <AdminCustomers />
                </Route>
                <Route path='/admin/companies'>
                    <AdminCompanies />
                </Route>
                <Route path='/admin/coupons'>
                    <ViewCoupons />
                </Route>
                <Route path='/admin/logs'>
                    <AdminLogs />
                </Route>
                <Route path='/admin'>
                    <Redirect to='/admin/home' />
                    <AdminHome />
                </Route>
            </Switch>
        )
    }

    return (
        <div className='Admin'>
            {getContent()}
        </div>
    )

}