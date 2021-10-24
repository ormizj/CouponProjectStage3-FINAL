import { Formik, Field, Form } from 'formik'
import AuthenticationService from '../../../../../../services/AuthenticationService'
import './company-signup.css'
import history from './../../../../history'
import { isEmail } from "./../../../../../../utils/checkUtil"

export default function CompanySignup() {

    const isEmail = isEmail()

    const closeDiv = () => {
        if (window.confirm('Are you sure you want to close this window?'))
            history.push('/login')
    }

    const handleSubmit = (values) => {
        AuthenticationService.registerCompany(
            values.name,
            values.email,
            values.password
        ).then(
            () => {
                setTimeout(() => { alert("Company signup successful.") }, 0)
                history.push('/login')
            },
            error => {
                try {
                    if (error.response.data.response) {
                        if (error.response.data.response.includes("email") || error.response.data.errorCode === 'USR-006.001') {
                            setTimeout(() => { alert("A user with the email \"" + values.email + "\" already exists,\nPlease choose a different email.") }, 0)
                        } else if (error.response.data.response.includes("name"))
                            setTimeout(() => { alert("A company with the name \"" + values.name + "\" already exists,\nPlease choose a different name.") }, 0)
                        else
                            setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                        return
                    }
                }
                catch {
                    setTimeout(() => { alert("Servers are currently down, try again later.") }, 0)
                    history.push('/login')
                }
            }
        )
    }

    return (
        <>
            <div className='CompanySignup-background'></div>
            <div className='CompanySignup'>
                <div className='CompanySignup-close-div'>
                    <button className="btn-close" aria-label="Close" id='CompanySignup-close-button' onClick={closeDiv} title='Close'></button>
                </div>
                <h5 className='CompanySignup-title' title='Signup as a Company'>Signup as a Company</h5>
                <Formik
                    initialValues={{
                        name: '',
                        email: '',
                        password: ''
                    }}
                    onSubmit={handleSubmit}
                >
                    {({ values, handleChange }) => (
                        <Form>
                            <div className="CompanySignup-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanySignup-name-div' title='Name'>Name</div>
                                <Field
                                    title={values.name}
                                    name='name'
                                    className='form-control'
                                    placeholder='Name'
                                    required={true}
                                    id='CompanySignup-name-input'
                                />
                            </div>
                            <div className="CompanySignup-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanySignup-email-div' title='Email'>Email</div>
                                <input
                                    onChange={handleChange}
                                    pattern={isEmail}
                                    title={values.email}
                                    name='email'
                                    className='form-control'
                                    placeholder='Email'
                                    required={true}
                                    autoComplete='true'
                                    id='CompanySignup-email-input'
                                />
                            </div>
                            <div className="CompanySignup-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanySignup-password-div' title='Password'>Password</div>
                                <Field
                                    type='password'
                                    name='password'
                                    className='form-control'
                                    placeholder='Password'
                                    required={true}
                                    autoComplete='true'
                                    id='CompanySignup-password-input'
                                />
                            </div>
                            <div className="CompanySignup-div-seperator"></div>
                            <div className="CompanySignup-div-seperator"></div>
                            <div className='CompanySignup-submit'>
                                <button type='submit' className='btn btn-primary' title='Signup'>
                                    Signup
                                </button>
                            </div>
                            <div className="CompanySignup-div-seperator"></div>
                            <div className="CompanySignup-div-seperator"></div>
                        </Form>
                    )}
                </Formik >
            </div>
        </>
    )

}