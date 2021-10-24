import './../../../../../../../styles/action-sidebar.css'
import { Redirect, Route, Switch } from 'react-router-dom'
import history from '../../../../../history'
import AdminCreateCustomer from './AdminCreateCustomer/AdminCreateCustomer'
import AdminUpdateCustomer from './AdminUpdateCustomer/AdminUpdateCustomer'
import AdminService from '../../../../../../../services/user-services/AdminService'
import AuthenticationService from '../../../../../../../services/AuthenticationService'
import { isMinimum } from '../../../../../../../utils/checkUtil'

export default function AdminCustomerActionbar(props) {

    const resetSelection = () => {
        for (let index of Object.values(props.values.checked)) {
            if (props.customersRef.current[index]) {
                const currentRef = props.customersRef.current[index].current
                currentRef.children[0].checked = false
            }
        }
        props.handleReset()
    }

    const handleAllCustomers = () => {
        AdminService
            .getCustomers().then(
                result => {
                    props.setCustomers(result.data)
                    resetSelection()
                },
                error => {
                    try {
                        if (error.response.data.response) {
                            setTimeout(() => { alert("There are not any customers.") }, 0)
                            props.setCustomers([{ id: '' }])
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

    const handleSearchId = () => {
        const userOption = prompt("Enter the id of the customer you want to find.")
        if (isMinimum(userOption, 1))
            AdminService
                .getCustomer(userOption).then(
                    result => {
                        props.setCustomers([result.data])
                        resetSelection()
                    },
                    error => {
                        try {
                            if (error.response.data.response) {
                                setTimeout(() => { alert("No customer with the id of \"" + userOption + "\" was found.") }, 0)
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

    const handleSearchEmail = () => {
        let userOption = prompt("Enter the email of the customer you want to find.")
        if (userOption != null)
            AdminService
                .getCustomerByEmail(userOption).then(
                    result => {
                        props.setCustomers([result.data])
                        resetSelection()
                    },
                    error => {
                        try {
                            if (error.response.data.response) {
                                setTimeout(() => { alert("No customer with the email of \"" + userOption + "\" was found.") }, 0)
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

    const handleUpdateCustomer = () => {
        if (props.values.checked.length === 0)
            setTimeout(() => { alert("You need to select atleast one customer to update.") }, 0)
        else if (props.values.checked.length > 1)
            setTimeout(() => { alert("You can only update one customer at a time.") }, 0)
        else
            history.push('/admin/customers/update-customer')
    }

    const handleDeleteCustomers = () => {
        if (props.values.checked.length === 0)
            setTimeout(() => { alert("You need to select atleast one customer to delete.") }, 0)
        else
            for (let index of Object.values(props.values.checked)) {
                const currentRef = props.customersRef.current[index].current
                const customer = "ID: " + currentRef.children[2].children[0].innerHTML +
                    "\nFirst Name: " + currentRef.children[3].children[0].innerHTML +
                    "\nLast Name: " + currentRef.children[4].children[0].innerHTML +
                    "\nEmail: " + currentRef.children[5].children[0].innerHTML
                AdminService
                    .deleteCustomer(currentRef.children[0].id).then(
                        () => {
                            setTimeout(() => { alert(customer + "\nCustomer deletion was successful.") }, 0)
                            handleAllCustomers()
                        },
                        error => {
                            try {
                                if (error.response.data.response) {
                                    setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                                    handleAllCustomers()
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
            <div className='AdminCustomerActionbar'>
                <div className='action-sidebar'>
                    <button className='action-sidebar-button' title='All Customers' onClick={handleAllCustomers}>All Customers</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Search ID' onClick={handleSearchId}>Search ID</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Search Email' onClick={handleSearchEmail}>Search Email</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Create Customer' onClick={() => history.push('/admin/customers/create-customer')}>Create Customer</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Update Customer' onClick={handleUpdateCustomer}>Update Customer</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Delete Customers' onClick={handleDeleteCustomers}>Delete Customers</button>
                </div>
            </div>
            <Switch>
                <Route path='/admin/customers' exact>
                </Route>
                <Route path={'/admin/customers/create-customer'}>
                    <AdminCreateCustomer handleAllCustomers={handleAllCustomers} />
                </Route>
                <Route path={'/admin/customers/update-customer'}>
                    <AdminUpdateCustomer handleAllCustomers={handleAllCustomers} customersRef={props.customersRef} values={props.values} />
                </Route>
                <Route path={'/admin/customers'}>
                    <Redirect to='/admin/home' />
                </Route>
            </Switch>
        </>
    )

}