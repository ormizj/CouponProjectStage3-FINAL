import './admin-create-customer.css'
import history from './../../../../../../history'
import { Formik, Form, Field } from 'formik'
import AdminService from './../../../../../../../../services/user-services/AdminService'
import AuthenticationService from '../../../../../../../../services/AuthenticationService'

export default function AdminCreateCustomer(props) {

    const closeDiv = () => {
        if (window.confirm('Are you sure you want to close this window?'))
            history.push('/admin/customers')
    }

    const handleSubmit = (values) => {
        AdminService.addCustomer(
            values.firstName,
            values.lastName,
            values.email,
            values.password
        ).then(
            () => {
                setTimeout(() => { alert("The customer was created successfully.") }, 0)
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

    return (
        <>
            <div className='AdminAddCustomer-background'></div>
            <div className='AdminAddCustomer'>
                <div className='AdminAddCustomer-close-div'>
                    <button className="btn-close" aria-label="Close" id='AdminAddCustomer-close-button' onClick={closeDiv} title='Close'></button>
                </div>
                <h5 className='AdminAddCustomer-title' title='Create A New Customer'>Create A New Customer</h5>
                <Formik
                    initialValues={{
                        firstName: '',
                        lastName: '',
                        email: '',
                        password: ''
                    }}
                    onSubmit={handleSubmit}
                >
                    {({ values, handleChange }) => (
                        <Form>
                            <div className="AdminAddCustomer-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='AdminAddCustomer-first_name-div' title='Name'>Name</div>
                                <div className="input-group-text" title='First:'>First:</div>
                                <Field
                                    title={values.firstName}
                                    name='firstName'
                                    className='form-control'
                                    placeholder='First Name'
                                    required={true}
                                    id='AdminAddCustomer-first_name-input'
                                />
                                <div className="input-group-text" title='Last:'>Last:</div>
                                <Field
                                    title={values.lastName}
                                    name='lastName'
                                    className='form-control'
                                    placeholder='Last Name'
                                    required={true}
                                    id='AdminAddCustomer-last_name-input'
                                />
                            </div>
                            <div className="AdminAddCustomer-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='AdminAddCustomer-email-div' title='Email'>Email</div>
                                <input
                                    onChange={handleChange}
                                    pattern='[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'
                                    type='email'
                                    title={values.email}
                                    name='email'
                                    className='form-control'
                                    placeholder='Email'
                                    required={true}
                                    autoComplete='true'
                                    id='AdminAddCustomer-email-input'
                                />
                            </div>
                            <div className="AdminAddCustomer-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='AdminAddCustomer-password-div' title='Password'>Password</div>
                                <Field
                                    type='password'
                                    name='password'
                                    className='form-control'
                                    placeholder='Password'
                                    required={true}
                                    autoComplete='true'
                                    id='AdminAddCustomer-password-input'
                                />
                            </div>
                            <div className="AdminAddCustomer-div-seperator"></div>
                            <div className="AdminAddCustomer-div-seperator"></div>
                            <div className='AdminAddCustomer-submit'>
                                <button type='submit' className='btn btn-primary' title='Create Customer'>
                                    Create Customer
                                </button>
                            </div>
                            <div className="AdminAddCustomer-div-seperator"></div>
                            <div className="AdminAddCustomer-div-seperator"></div>
                        </Form>
                    )}
                </Formik >
            </div>
        </>
    )

}