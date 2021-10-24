import './../../../../../../../styles/action-sidebar.css'
import { Redirect, Route, Switch } from 'react-router-dom'
import history from '../../../../../history'
import AdminCreateCompany from './AdminCreateCompany/AdminCreateCompany'
import AdminUpdateCompany from './AdminUpdateCompany/AdminUpdateCompany'
import AdminService from '../../../../../../../services/user-services/AdminService'
import AuthenticationService from '../../../../../../../services/AuthenticationService'
import { isMinimum } from '../../../../../../../utils/checkUtil'

export default function AdminCompanyActionbar(props) {

    const resetSelection = () => {
        for (let index of Object.values(props.values.checked)) {
            if (props.companiesRef.current[index]) {
                const currentRef = props.companiesRef.current[index].current
                currentRef.children[0].checked = false
            }
        }
        props.handleReset()
    }

    const handleAllCompanies = () => {
        AdminService
            .getCompanies().then(
                result => {
                    props.setCompanies(result.data)
                    resetSelection()
                },
                error => {
                    try {
                        if (error.response.data.response) {
                            setTimeout(() => { alert("There are not any companies.") }, 0)
                            props.setCompanies([{ id: '' }])
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
        const userOption = prompt("Enter the id of the company you want to find.")
        if (isMinimum(userOption, 1))
            AdminService
                .getCompany(userOption).then(
                    result => {
                        props.setCompanies([result.data])
                        resetSelection()
                    },
                    error => {
                        try {
                            if (error.response.data.response) {
                                setTimeout(() => { alert("No company with the id of \"" + userOption + "\" was found.") }, 0)
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
        let userOption = prompt("Enter the email of the company you want to find.")
        if (userOption != null)
            AdminService
                .getCompanyByEmail(userOption).then(
                    result => {
                        props.setCompanies([result.data])
                        resetSelection()
                    },
                    error => {
                        try {
                            if (error.response.data.response) {
                                setTimeout(() => { alert("No company with the email of \"" + userOption + "\" was found.") }, 0)
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

    const handleUpdateCompany = () => {
        if (props.values.checked.length === 0)
            setTimeout(() => { alert("You need to select atleast one company to update.") }, 0)
        else if (props.values.checked.length > 1)
            setTimeout(() => { alert("You can only update one company at a time.") }, 0)
        else
            history.push('/admin/companies/update-company')
    }

    const handleDeleteCompanies = () => {
        if (props.values.checked.length === 0)
            setTimeout(() => { alert("You need to select atleast one company to delete.") }, 0)
        else
            for (let index of Object.values(props.values.checked)) {
                const currentRef = props.companiesRef.current[index].current
                const company = "ID: " + currentRef.children[2].children[0].innerHTML +
                    "\nName: " + currentRef.children[3].children[0].innerHTML +
                    "\nEmail: " + currentRef.children[4].children[0].innerHTML
                AdminService
                    .deleteCompany(currentRef.children[0].id).then(
                        () => {
                            setTimeout(() => { alert(company + "\nCompany deletion was successful.") }, 0)
                            handleAllCompanies();
                        },
                        error => {
                            try {
                                if (error.response.data.response) {
                                    setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                                    handleAllCompanies()
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
            <div className='AdminCompanyActionbar'>
                <div className='action-sidebar'>
                    <button className='action-sidebar-button' title='All Companies' onClick={handleAllCompanies}>All Companies</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Search ID' onClick={handleSearchId}>Search ID</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Search Email' onClick={handleSearchEmail}>Search Email</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Create Company' onClick={() => history.push('/admin/companies/create-company')}>Create Company</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Update Company' onClick={handleUpdateCompany}>Update Company</button>
                    <div className='action-sidebar-button-seperator'></div>
                    <button className='action-sidebar-button' title='Delete Companies' onClick={handleDeleteCompanies}>Delete Companies</button>
                </div>
            </div>
            <Switch>
                <Route path='/admin/companies' exact>
                </Route>
                <Route path={'/admin/companies/create-company'}>
                    <AdminCreateCompany handleAllCompanies={handleAllCompanies} />
                </Route>
                <Route path={'/admin/companies/update-company'}>
                    <AdminUpdateCompany companiesRef={props.companiesRef} values={props.values} handleAllCompanies={handleAllCompanies} />
                </Route>
                <Route path={'/admin/companies'}>
                    <Redirect to='/admin/home' />
                </Route>
            </Switch>
        </>
    )

}