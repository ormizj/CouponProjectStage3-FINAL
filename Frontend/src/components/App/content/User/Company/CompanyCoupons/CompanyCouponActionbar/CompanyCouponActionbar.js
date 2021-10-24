import history from './../../../../../history'
import { Redirect, Route, Switch } from 'react-router-dom'
import CompanyAddCoupon from './CompanyAddCoupon/CompanyAddCoupon'
import './../../../../../../../styles/action-sidebar.css'
import CompanyUpdateCoupon from './CompanyUpdateCoupon/CompanyUpdateCoupon'
import CompanyService from './../../../../../../../services/user-services/CompanyService'
import AuthenticationService from '../../../../../../../services/AuthenticationService'
import CouponsByCategory from '../../../../../../CouponsByCategory/CouponsByCategory'
import { isMinimum } from '../../../../../../../utils/checkUtil'

export default function CompanyCouponActionbar(props) {

    const resetSelection = () => {
        for (let index of Object.values(props.values.checked)) {
            if (props.couponsRef.current[index]) {
                const currentRef = props.couponsRef.current[index].current
                currentRef.children[0].checked = false
            }
        }
        props.handleReset()
    }

    const handleAllCoupons = () => {
        CompanyService
            .getCoupons().then(
                result => {
                    props.setCoupons(result.data)
                    resetSelection()
                },
                error => {
                    try {
                        if (error.response.data.response) {
                            setTimeout(() => { alert("You do not own any coupons.") }, 0)
                            props.setCoupons([{ id: '' }])
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
            CompanyService
                .getCouponsPrice(userOption).then(
                    result => {
                        props.setCoupons(result.data)
                        resetSelection()
                    },
                    error => {
                        try {
                            if (error.response.data.response) {
                                setTimeout(() => { alert("No coupons with the minimum price of \"" + userOption + " \"were found.") }, 0)
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
        history.push('/company/my-coupons/search-by-category')
    }

    const handleSearchCategory = (category) => {
        CompanyService
            .getCouponsCategory(category).then(
                result => {
                    props.setCoupons(result.data)
                    resetSelection()
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

    const handleUpdateCoupon = () => {
        if (props.values.checked.length === 0)
            setTimeout(() => { alert("You need to select atleast one coupon to update.") }, 0)
        else if (props.values.checked.length > 1)
            setTimeout(() => { alert("You can only update one coupon at a time.") }, 0)
        else
            history.push('/company/my-coupons/update-coupon')
    }

    const handleDeleteCoupon = () => {
        if (props.values.checked.length === 0)
            setTimeout(() => { alert("You need to select atleast one coupon to delete.") }, 0)
        else
            for (let index of Object.values(props.values.checked)) {
                const currentRef = props.couponsRef.current[index].current
                const coupon = "Stock: " + currentRef.children[3].children[0].innerHTML +
                    "\nTitle: " + currentRef.children[4].children[0].innerHTML +
                    "\nDescription: " + currentRef.children[5].children[0].innerHTML +
                    "\nPrice: " + currentRef.children[6].children[0].innerHTML +
                    "\nCategory: " + currentRef.children[7].children[0].innerHTML +
                    "\nRelease Date: " + currentRef.children[8].children[0].innerHTML +
                    "\nExpiration Date: " + currentRef.children[9].children[0].innerHTML
                CompanyService
                    .deleteCoupon(currentRef.children[0].id).then(
                        () => {
                            setTimeout(() => { alert(coupon + "\nCoupon deletion was successful.") }, 0)
                            handleAllCoupons()
                        },
                        error => {
                            try {
                                if (error.response.data.response) {
                                    setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                                    handleAllCoupons()
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
    }

    return (
        <>
            <div className='CompanyCouponActionbar'>
                <div className='action-sidebar'>
                    <button className='action-sidebar-button' title='Search Price' onClick={handleAllCoupons}>All Coupons</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Search Price' onClick={handleSearchPrice}>Search Price</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Search Category' onClick={handleSearchCategoryPush}>Search Category</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Add Coupon' onClick={() => history.push('/company/my-coupons/add-coupon')}>Add Coupon</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Update Coupon' onClick={handleUpdateCoupon}>Update Coupon</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Delete Coupons' onClick={handleDeleteCoupon}>Delete Coupons</button>
                </div>
            </div>
            <Switch>
                <Route path='/company/my-coupons' exact>
                </Route>
                <Route path={'/company/my-coupons/add-coupon'}>
                    <CompanyAddCoupon handleAllCoupons={handleAllCoupons} />
                </Route>
                <Route path={'/company/my-coupons/update-coupon'}>
                    <CompanyUpdateCoupon couponsRef={props.couponsRef} values={props.values} handleAllCoupons={handleAllCoupons} />
                </Route>
                <Route path={'/company/my-coupons/search-by-category'}>
                    <CouponsByCategory handleSearchCategory={handleSearchCategory} coupons={props.coupons} />
                </Route>
                <Route path={'/company/my-coupons'}>
                    <Redirect to='/company/home' />
                </Route>
            </Switch>
        </>
    )

}