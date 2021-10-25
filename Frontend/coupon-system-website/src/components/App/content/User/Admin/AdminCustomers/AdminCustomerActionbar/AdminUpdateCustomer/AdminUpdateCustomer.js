import './admin-update-customer.css'
import history from '../../../../../../history'
import { Formik, Form, Field } from 'formik'
import AdminService from '../../../../../../../../services/user-services/AdminService'
import AuthenticationService from '../../../../../../../../services/AuthenticationService'

export default function AdminUpdateCustomer(props) {

    let valid = true
    let currentRef
    let customer

    if (!props.values.checked[0]) {
        valid = false
        history.push("/admin/customers")
    }
    else {
        currentRef = props.customersRef.current[props.values.checked[0]].current
        customer = {
            firstName: currentRef.children[3].children[0].innerHTML,
            lastName: currentRef.children[4].children[0].innerHTML,
            email: currentRef.children[5].children[0].innerHTML,
            password: currentRef.children[6].children[0].innerHTML,
        }
    }

    const closeDiv = () => {
        if (window.confirm('Are you sure you want to close this window?'))
            history.push('/admin/customers')
    }

    const handleSubmit = (values) => {
        let password = values.password
        if (password === '') {
            password = customer.password;
        }
        AdminService.updateCustomer(
            currentRef.children[0].id,
            values.firstName,
            values.lastName,
            values.email,
            password
        ).then(
            () => {
                setTimeout(() => { alert("The customer was updated successfully.") }, 0)
                history.push('/admin/customers')
                props.handleAllCustomers()
            },
            error => {
                try {
                    if (error.response.data.response) {
                        if (error.response.data.errorCode === 'CST-003.002' || error.response.data.errorCode === 'USR-006.001') {
                            setTimeout(() => { alert("A user with the email \"" + values.email + "\" already exists,\nPlease choose a different email.") }, 0)
                        } else {
                            setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                            props.handleAllCustomers()
                        }
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

    const getBody = () => {
        if (valid)
            return (
                <>
                    <div className='AdminUpdateCustomer-background'></div>
                    <div className="AdminUpdateCustomer">
                        <div className='AdminUpdateCustomer-close-div'>
                            <button className="btn-close" aria-label="Close" id='AdminUpdateCustomer-close-button' onClick={closeDiv} title='Close'></button>
                        </div>
                        <h5 className='AdminUpdateCustomer-title' title='Update An Existing Customer'>Update An Existing Customer</h5>
                        <Formik
                            initialValues={{
                                firstName: customer.firstName,
                                lastName: customer.lastName,
                                email: customer.email,
                                password: ''
                            }}
                            onSubmit={handleSubmit}
                        >
                            {({ values, dirty, handleChange }) => (
                                <Form>
                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                    <table className="AdminUpdateCustomer-table">
                                        <tbody>
                                            <tr>
                                                <th width="50%" title='From:'>From:</th>
                                                <th width="50%" title='To:'>To:</th>
                                            </tr>
                                            <tr>
                                                {/* ------------------------------From:------------------------------ */}
                                                <td>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="input-group">
                                                        <div className="input-group-text" title='First Name:' id='AdminUpdateCustomer-first-div'>First:</div>
                                                        <Field
                                                            value={customer.firstName}
                                                            title={customer.firstName}
                                                            className='form-control'
                                                            placeholder='First Name'
                                                            disabled={true}
                                                            id='AdminUpdateCustomer-from-first-input'
                                                        />
                                                        <div className="input-group-text" title='Last Name:'>Last:</div>
                                                        <Field
                                                            value={customer.lastName}
                                                            title={customer.lastName}
                                                            className='form-control'
                                                            placeholder='Last Name'
                                                            disabled={true}
                                                            id='AdminUpdateCustomer-from-last-input'
                                                        />
                                                        <div className="input-group-text" title='Name' id='AdminUpdateCustomer-name-div'>Name</div>
                                                    </div>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            value={customer.email}
                                                            type='email'
                                                            title={customer.email}
                                                            className='form-control'
                                                            placeholder='Email'
                                                            disabled={true}
                                                            id='AdminUpdateCustomer-from-email-input'
                                                        />
                                                        <div className="input-group-text" title='Email' id='AdminUpdateCustomer-email-div'>Email</div>
                                                    </div>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            value=''
                                                            className='form-control'
                                                            placeholder='Password'
                                                            disabled={true}
                                                            id='AdminUpdateCustomer-from-password-input'
                                                            title='Password'
                                                        />
                                                        <div className="input-group-text" title='Password' id='AdminUpdateCustomer-password-div'>Password</div>
                                                    </div>
                                                </td>
                                                {/* ------------------------------To:------------------------------ */}
                                                <td>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="input-group">
                                                        <div className="input-group-text" title='First Name:' id='AdminUpdateCustomer-to-left-div' >First:</div>
                                                        <Field
                                                            title={values.firstName}
                                                            name='firstName'
                                                            className='form-control'
                                                            placeholder='First Name'
                                                            required={true}
                                                            id='AdminUpdateCustomer-first_name-input'
                                                        />
                                                        <div className="input-group-text" title='Last Name:'>Last:</div>
                                                        <Field
                                                            title={values.lastName}
                                                            name='lastName'
                                                            className='form-control'
                                                            placeholder='Last Name'
                                                            required={true}
                                                            id='AdminUpdateCustomer-to-right-input'
                                                        />
                                                    </div>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="input-group">
                                                        <input
                                                            value={values.email}
                                                            onChange={handleChange}
                                                            pattern='[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'
                                                            type='email'
                                                            title={values.email}
                                                            name='email'
                                                            className='form-control'
                                                            placeholder='Email'
                                                            required={true}
                                                            autoComplete='true'
                                                            id='AdminUpdateCustomer-to-email-input'
                                                        />
                                                    </div>
                                                    <div className="AdminUpdateCustomer-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            type='password'
                                                            name='password'
                                                            className='form-control'
                                                            placeholder='Password'
                                                            autoComplete='true'
                                                            id='AdminUpdateCustomer-to-password-input'
                                                        />
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div className='AdminUpdateCustomer-submit'>
                                        <div className="AdminUpdateCustomer-div-seperator"></div>
                                        <div className="AdminUpdateCustomer-div-seperator"></div>
                                        <button type='submit' className='btn btn-primary' id='AdminUpdateCustomer-submit-button' title='Update Customer' disabled={!dirty}>
                                            Update Customer
                                        </button>
                                        <div className="AdminUpdateCustomer-div-seperator"></div>
                                        <div className="AdminUpdateCustomer-div-seperator"></div>
                                    </div>
                                </Form>
                            )}
                        </Formik >
                    </div>
                </>
            )
        else return (<></>)
    }

    return (
        <>{getBody()}</>
    )

}