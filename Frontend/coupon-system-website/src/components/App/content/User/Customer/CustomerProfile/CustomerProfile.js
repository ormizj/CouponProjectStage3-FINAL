import { Formik, Form, Field } from 'formik'
import './customer-profile.css'
import companyImage from './Generic Person.png'
import CustomerService from '../../../../../../services/user-services/CustomerService'
import AdminService from '../../../../../../services/user-services/AdminService'
import { useEffect, useState } from 'react'
import AuthenticationService from '../../../../../../services/AuthenticationService'
import { isEmail } from '../../../../../../utils/checkUtil'

export default function CustomerProfile() {

    const isEmail = isEmail()
    const [customer, setCustomer] = useState({ email: '', firstName: '', lastName: '', password: '', id: '' })

    const getCustomerDetails = () => {
        CustomerService
            .getDetails().then(
                result => {
                    setCustomer(result.data)
                },
                error => {
                    try {
                        if (error.response.data.response) {
                            setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
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

    useEffect(() => {
        getCustomerDetails()
    }, [])

    const handleSubmit = (values, { setFieldValue }) => {
        let password = values.password
        if (password === '') {
            password = customer.password;
        }
        AdminService
            .updateCustomer(
                customer.id,
                values.firstName,
                values.lastName,
                values.email,
                password
            ).then(() => {
                setTimeout(() => { alert('Profile updated successfully.') }, (0))
                getCustomerDetails()
                setFieldValue('password', '')
            },
                error => {
                    try {
                        if (error.response.data.response) {
                            if (error.response.data.errorCode === 'CST-003.002' || error.response.data.errorCode === 'USR-006.001') {
                                setTimeout(() => { alert("A user with the email \"" + values.email + "\" already exists,\nPlease choose a different email.") }, 0)
                            } else {
                                setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                                getCustomerDetails()
                            }
                            setFieldValue('password', '')
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
        <div className='CustomerProfile'>
            <Formik
                enableReinitialize={true}
                initialValues={{
                    email: customer.email,
                    firstName: customer.firstName,
                    lastName: customer.lastName,
                    password: ''
                }}
                onSubmit={(values, { setFieldValue }) => handleSubmit(values, { setFieldValue })}
            >
                {({ values, dirty, handleChange }) => (<>
                    <Form>
                        <table className='CustomerProfile-table'>
                            <tbody>
                                <tr >
                                    <th width="33.33%"></th>
                                    <th width="33.33%"></th>
                                </tr>
                                <tr >
                                    <td className='CustomerProfile-table-td'>
                                        <img className='CustomerProfile-img' src={companyImage} alt='CompanyImg'></img>
                                    </td>
                                    <td className='CustomerProfile-table-td' id='CustomerProfile-table-input-td'>
                                        <div className='CustomerProfile-button-div'></div>
                                        <div className='CustomerProfile-input-outer-div'>
                                            <div className="input-group" id='CustomerProfile-inner-input-div'>
                                                <div className="input-group-text" title='First Name'>First Name</div>
                                                <Field
                                                    title={values.firstName}
                                                    name='firstName'
                                                    className='form-control'
                                                    placeholder='First Name'
                                                    required={true}
                                                />
                                            </div>
                                            <div className="input-group" id='CustomerProfile-inner-input-div'>
                                                <div className="input-group-text" title='Last Name'>Last Name</div>
                                                <Field
                                                    title={values.lastName}
                                                    name='lastName'
                                                    className='form-control'
                                                    placeholder='Last Name'
                                                    required={true}
                                                />
                                            </div>
                                            <div className="input-group" id='CustomerProfile-inner-input-div'>
                                                <div className="input-group-text" title='Email' id='CustomerProfile-email-div'>Email</div>
                                                <input
                                                    value={values.email}
                                                    onChange={handleChange}
                                                    pattern={isEmail}
                                                    type='email'
                                                    title={values.email}
                                                    name='email'
                                                    className='form-control'
                                                    placeholder='Email'
                                                    autoComplete='true'
                                                    required={true}
                                                />
                                            </div>
                                            <div className="input-group" id='CustomerProfile-inner-input-div'>
                                                <div className="input-group-text" title='Password' id='CustomerProfile-password-div'>Password</div>
                                                <Field
                                                    type='password'
                                                    name='password'
                                                    className='form-control'
                                                    placeholder='Password'
                                                    autoComplete='true'
                                                />
                                            </div>
                                            <div className='CustomerProfile-seperator-div'></div>
                                            <div className='CustomerProfile-button-div'>
                                                <button type='submit' className='btn btn-primary' title='Update Profile' disabled={!dirty}>
                                                    Update Profile
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </Form>
                </>)}
            </Formik>
        </div>
    )

}