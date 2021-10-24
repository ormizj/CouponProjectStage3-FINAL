import history from './../../../../../history'
import { Redirect, Route, Switch } from 'react-router-dom'
import '../../../../../../../styles/action-sidebar.css'
import CustomerService from '../../../../../../../services/user-services/CustomerService'
import AuthenticationService from '../../../../../../../services/AuthenticationService'
import CouponsByCategory from '../../../../../../CouponsByCategory/CouponsByCategory'
import { isMinimum } from '../../../../../../../utils/checkUtil'

export default function CustomerCouponActionbar(props) {

    const handleAllCoupons = () => {
        CustomerService
            .getCoupons().then(
                result => {
                    props.setCoupons(result.data)
                },
                error => {
                    try {
                        if (error.response.data.response) {
                            setTimeout(() => { alert("You do not own any coupons.") }, 0)
                            return
                        }
                        if (error.response) {
                            setTimeout(() => { alert("Login expired, please login again.") }, 0)
                            AuthenticationService.logOut();
                        }
                    } catch {
                        setTimeout(() => { alert("Servers are currently down, try again later.") }, 0)
                        AuthenticationService.logOut();
                    }
                }
            )
    }

    const handleSearchCategoryPush = () => {
        handleAllCoupons()
        history.push('/customer/my-coupons/search-by-category')
    }

    const handleSearchCategory = (category) => {
        CustomerService
            .getCouponsCategory(category).then(
                result => {
                    props.setCoupons(result.data)
                },
                error => {
                    try {
                        if (error.response.data.response) {
                            setTimeout(() => { alert("No coupons with the category of \"" + category + "\" were found.") }, 0)
                            return
                        }
                        if (error.response) {
                            setTimeout(() => { alert("Login expired, please login again.") }, 0)
                            AuthenticationService.logOut();
                        }
                    } catch {
                        setTimeout(() => { alert("Servers are currently down, try again later.") }, 0)
                        AuthenticationService.logOut();
                    }
                }
            )
    }

    const handleSearchPrice = () => {
        const userOption = prompt("Enter the maximum price of the coupons you want to find.")
        if (isMinimum(userOption, 0))
            CustomerService
                .getCouponsPrice(userOption).then(
                    result => {
                        props.setCoupons(result.data)
                    },
                    error => {
                        try {
                            if (error.response.data.response) {
                                setTimeout(() => { alert("No coupons with the minimum price of \"" + userOption + "\" were found.") }, 0)
                                return
                            }
                            if (error.response) {
                                setTimeout(() => { alert("Login expired, please login again.") }, 0)
                                AuthenticationService.logOut();
                            }
                        } catch {
                            setTimeout(() => { alert("Servers are currently down, try again later.") }, 0)
                            AuthenticationService.logOut();
                        }
                    }
                )
    }

    return (
        <>
            <div className='CustomerCouponActionbar'>
                <div className='action-sidebar'>
                    <button className='action-sidebar-button' onClick={handleAllCoupons} title='All Coupons'>All Coupons</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' onClick={handleSearchPrice} title='Search Price'>Search Price</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' onClick={handleSearchCategoryPush} title='Search Category'>Search Category</button>
                </div>
            </div>
            <Switch>
                <Route path='/customer/my-coupons' exact>
                </Route>
                <Route path={'/customer/my-coupons/search-by-category'}>
                    <CouponsByCategory handleSearchCategory={handleSearchCategory} coupons={props.coupons} />
                </Route>
                <Route path={'/customer/my-coupons'}>
                    <Redirect to='/customer/home' />
                </Route>
            </Switch>
        </>
    )

}