import { Formik, Form, Field } from 'formik'
import { useEffect, useState } from 'react'
import './company-profile.css'
import companyImage from './Generic Company.jpeg'
import CompanyService from './../../../../../../services/user-services/CompanyService'
import AdminService from '../../../../../../services/user-services/AdminService'
import AuthenticationService from '../../../../../../services/AuthenticationService'

export default function CompanyProfile() {

    const [company, setCompany] = useState({ email: '', name: '', password: '', id: '' })

    const getCompanyDetails = () => {
        CompanyService
            .getDetails().then(
                result => {
                    setCompany(result.data)
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
        getCompanyDetails()
    }, [])

    const handleSubmit = (values, { setFieldValue }) => {
        let password = values.password
        if (password === '') {
            password = company.password;
        }
        AdminService
            .updateCompany(
                company.id,
                values.name,
                values.email,
                password
            ).then(() => {
                setTimeout(() => { alert('Profile updated successfully.') }, (0))
                getCompanyDetails()
                setFieldValue('password', '')
            },
                error => {
                    try {
                        if (error.response.data.response) {
                            if (error.response.data.response.includes("email") || error.response.data.errorCode === 'USR-006.001') {
                                setTimeout(() => { alert("A user with the email \"" + values.email + "\" already exists,\nPlease choose a different email.") }, 0)
                            } else if (error.response.data.response.includes("name"))
                                setTimeout(() => { alert("A company with the name \"" + values.name + "\" already exists,\nPlease choose a different name.") }, 0)
                            else {
                                setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                                getCompanyDetails()
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
        <div className='CompanyProfile'>
            <Formik
                enableReinitialize={true}
                initialValues={{
                    email: company.email,
                    name: company.name,
                    password: ''
                }}
                onSubmit={(values, { setFieldValue }) => handleSubmit(values, { setFieldValue })}
            >
                {({ values, dirty, handleChange }) => (<>
                    <Form>
                        <table className='CompanyProfile-table'>
                            <tbody>
                                <tr >
                                    <th width="33.33%"></th>
                                    <th width="33.33%"></th>
                                </tr>
                                <tr >
                                    <td className='CompanyProfile-table-td'>
                                        <img className='CompanyProfile-img' src={companyImage} alt='CompanyImg'></img>
                                    </td>
                                    <td className='CompanyProfile-table-td' id='CompanyProfile-table-input-td'>
                                        <div className='CompanyProfile-seperator-div'></div>
                                        <div className='CompanyProfile-input-outer-div'>
                                            <div className="input-group" id='CompanyProfile-inner-input-div'>
                                                <div className="input-group-text" title='Name' id='CompanyProfile-name-div'>Name</div>
                                                <Field
                                                    title={values.name}
                                                    name='name'
                                                    className='form-control'
                                                    placeholder='Name'
                                                    required={true}
                                                />
                                            </div>
                                            <div className="input-group" id='CompanyProfile-inner-input-div'>
                                                <div className="input-group-text" title='Email' id='CompanyProfile-email-div'>Email</div>
                                                <input
                                                    value={values.email}
                                                    onChange={handleChange}
                                                    pattern='[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'
                                                    type='email'
                                                    title={values.email}
                                                    name='email'
                                                    className='form-control'
                                                    autoComplete='true'
                                                    placeholder='Email'
                                                    required={true}
                                                />
                                            </div>
                                            <div className="input-group" id='CompanyProfile-inner-input-div'>
                                                <div className="input-group-text" title='Password'>Password</div>
                                                <Field
                                                    type='password'
                                                    name='password'
                                                    className='form-control'
                                                    placeholder='Password'
                                                    autoComplete='true'
                                                />
                                            </div>
                                            <div className='CompanyProfile-seperator-div'></div>
                                            <div className='CompanyProfile-button-div'>
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