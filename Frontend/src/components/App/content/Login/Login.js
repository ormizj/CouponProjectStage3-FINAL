import './login.css'
import image from './../../../../assests/The Coupon Project Logo.png'
import * as Yup from "yup";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import history from '../../history';
import AuthenticationService from '../../../../services/AuthenticationService';
import { Route, Switch } from 'react-router';
import Signup from './Signup/Signup';
import CompanySignup from './Signup/CompanySignup/CompanySignup';
import CustomerSignup from './Signup/CustomerSignup/CustomerSignup';

export default function Login() {

    const handleSubmit = (values, { setFieldValue }) => {
        AuthenticationService
            .logIn(
                values.email,
                values.password,
                values.type
            )
            .then(() => {
                switch (values.type) {
                    case 'CUSTOMER':
                        history.push('/customer/home')
                        break;
                    case 'COMPANY':
                        history.push('/company/home')
                        break;
                    case 'ADMIN':
                        history.push('/admin/home')
                        break;
                    default:
                        break;
                }
            },
                error => {
                    if (error.response)
                        setTimeout(() => { alert("Login Failed\n" + error.response.data.response) }, 0)
                    else
                        setTimeout(() => { alert("Servers are currently down, try again later.") }, 0)
                    setFieldValue('password', '')
                }
            )
    };

    const validationSchema = Yup.object({
        email: Yup.string().email('Invalid email format').required('Required'),
        password: Yup.string().required('Required')
    })

    return (
        <>
            <Switch>
                <Route path='/login/signup/Customer'>
                    <CustomerSignup />
                </Route>
                <Route path='/login/signup/company'>
                    <CompanySignup />
                </Route>
                <Route path='/login/signup'>
                    <Signup />
                </Route>
            </Switch>
            <div className='Login'>
                <img src={image} alt='Login img' width='26%' className='Login-img' />
                <div className='Login-formik'>
                    <Formik initialValues={{ email: '', password: '', type: 'CUSTOMER' }}
                        onSubmit={(values, { setFieldValue }) => handleSubmit(values, { setFieldValue })}
                        validationSchema={validationSchema}
                    >
                        {({ isValid, dirty, values }) => (
                            <Form>
                                <div className="form-group" id='Login-padding'>
                                    <label className='Login-label'>
                                        Enter Email:
                                    </label>
                                    <Field
                                        type='email'
                                        name='email'
                                        className='form-control'
                                        placeholder='Email'
                                        autoComplete='true'
                                        title={values.email}
                                        required={true}
                                    />
                                </div>
                                <div className='Login-alert' title='Required / Invalid email format'>
                                    <ErrorMessage component='div' name='email' className='alert alert-primary' id='Login-alert' />
                                </div>
                                <div className="form-group" id='Login-padding'>
                                    <label className='Login-label'>
                                        Enter Password:
                                    </label>
                                    <Field
                                        type='password'
                                        name='password'
                                        className='form-control'
                                        placeholder='Password'
                                        autoComplete='true'
                                        required={true}
                                    />
                                </div>
                                <div className='Login-alert' title='Required'>
                                    <ErrorMessage component='div' name='password' className='alert alert-primary' id='Login-alert' />
                                </div>
                                <div className="form-group" id='Login-padding'>
                                    <label className='Login-label'>
                                        Login as...
                                    </label>
                                    <Field
                                        className='form-select'
                                        component="select"
                                        name="type"
                                        title={values.type}
                                    >
                                        <option value="CUSTOMER">Customer</option>
                                        <option value="COMPANY">Company</option>
                                        <option value="ADMIN">Admin</option>
                                    </Field>
                                </div>
                                <div className='Login-submit' id='Login-padding'>
                                    <input type="button" className='btn btn-primary' title='Signup' value="Signup" id='Login-signup' onClick={() => history.push("/login/signup")} />
                                    <button type='submit' className='btn btn-primary' disabled={!(isValid && dirty)} title='Login'>
                                        Login
                                    </button>
                                </div>
                            </Form>
                        )}
                    </Formik>
                </div>
            </div >
        </>
    )

}
