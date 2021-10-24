import './customer-signup.css'
import { Formik, Field, Form } from 'formik'
import AuthenticationService from '../../../../../../services/AuthenticationService'
import history from './../../../../history'
import { isEmail } from '../../../../../../utils/checkUtil'

export default function CustomerSignup() {

    const isEmail = isEmail()

    const closeDiv = () => {
        if (window.confirm('Are you sure you want to close this window?'))
            history.push('/login')
    }

    const handleSubmit = (values) => {
        AuthenticationService.registerCustomer(
            values.firstName,
            values.lastName,
            values.email,
            values.password
        ).then(
            () => {
                setTimeout(() => { alert("Customer signup successful.") }, 0)
                history.push('/login')
            },
            error => {
                try {
                    if (error.response.data.response) {
                        if (error.response.data.errorCode === 'CST-003.002' || error.response.data.errorCode === 'USR-006.001') {
                            setTimeout(() => { alert("A user with the email \"" + values.email + "\" already exists,\nPlease choose a different email.") }, 0)
                        } else
                            setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                        return
                    }
                } catch {
                    setTimeout(() => { alert("Servers are currently down, try again later.") }, 0)
                    history.push('/login')
                }
            }
        )
    }

    return (
        <>
            <div className='CustomerSignup-background'></div>
            <div className='CustomerSignup'>
                <div className='CustomerSignup-close-div'>
                    <button className="btn-close" aria-label="Close" id='CustomerSignup-close-button' onClick={closeDiv} title='Close'></button>
                </div>
                <h5 className='CustomerSignup-title' title='Signup as a Customer'>Signup as a Customer</h5>
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
                            <div className="CustomerSignup-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CustomerSignup-first_name-div' title='Name'>Name</div>
                                <div className="input-group-text" title='First:'>First:</div>
                                <Field
                                    title={values.firstName}
                                    name='firstName'
                                    className='form-control'
                                    placeholder='First Name'
                                    required={true}
                                    id='CustomerSignup-first_name-input'
                                />
                                <div className="input-group-text" title='Last:'>Last:</div>
                                <Field
                                    title={values.lastName}
                                    name='lastName'
                                    className='form-control'
                                    placeholder='Last Name'
                                    required={true}
                                    id='CustomerSignup-last_name-input'
                                />
                            </div>
                            <div className="CustomerSignup-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CustomerSignup-email-div' title='Email'>Email</div>
                                <input
                                    onChange={handleChange}
                                    pattern={isEmail}
                                    type='email'
                                    title={values.email}
                                    name='email'
                                    className='form-control'
                                    placeholder='Email'
                                    required={true}
                                    autoComplete='true'
                                    id='CustomerSignup-email-input'
                                />
                            </div>
                            <div className="CustomerSignup-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CustomerSignup-password-div' title='Password'>Password</div>
                                <Field
                                    type='password'
                                    name='password'
                                    className='form-control'
                                    placeholder='Password'
                                    required={true}
                                    autoComplete='true'
                                    id='CustomerSignup-password-input'
                                />
                            </div>
                            <div className="CustomerSignup-div-seperator"></div>
                            <div className="CustomerSignup-div-seperator"></div>
                            <div className='CustomerSignup-submit'>
                                <button type='submit' className='btn btn-primary' title='Signup'>
                                    Signup
                                </button>
                            </div>
                            <div className="CustomerSignup-div-seperator"></div>
                            <div className="CustomerSignup-div-seperator"></div>
                        </Form>
                    )}
                </Formik >
            </div>
        </>
    )

}