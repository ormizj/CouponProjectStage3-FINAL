import { Redirect, Route, Switch } from 'react-router-dom'
import CompanyHome from './CompanyHome/CompanyHome'
import CompanyProfile from './CompanyProfile/CompanyProfile'
import CompanyCoupons from './CompanyCoupons/CompanyCoupons'
import ViewCoupons from '../view/ViewCoupons/ViewCoupons'

export default function Company() {
    const getContent = () => {
        return (
            <Switch>
                <Route path='/company/my-coupons'>
                    <CompanyCoupons />
                </Route>
                <Route path='/company/coupons'>
                    <ViewCoupons />
                </Route>
                <Route path='/company/profile'>
                    <CompanyProfile />
                </Route>
                <Route path='/company'>
                    <Redirect to='/company/home' />
                    <CompanyHome />
                </Route>
            </Switch>
        )
    }

    return (
        <div className='Company'>
            {getContent()}
        </div>
    )
}


