import './admin-update-company.css'
import history from '../../../../../../history'
import { Formik, Form, Field } from 'formik'
import AdminService from '../../../../../../../../services/user-services/AdminService'
import AuthenticationService from '../../../../../../../../services/AuthenticationService'

export default function AdminUpdateCompany(props) {

    let valid = true
    let currentRef
    let company

    if (!props.values.checked[0]) {
        valid = false
        history.push("/admin/companies")
    }
    else {
        currentRef = props.companiesRef.current[props.values.checked[0]].current
        company = {
            name: currentRef.children[3].children[0].innerHTML,
            email: currentRef.children[4].children[0].innerHTML,
            password: currentRef.children[5].children[0].innerHTML,
        }
    }

    const closeDiv = () => {
        if (window.confirm('Are you sure you want to close this window?'))
            history.push('/admin/companies')
    }

    const handleSubmit = (values) => {
        let password = values.password
        if (password === '') {
            password = company.password;
        }
        AdminService.updateCompany(
            currentRef.children[0].id,
            values.name,
            values.email,
            password
        ).then(
            () => {
                setTimeout(() => { alert("The company was updated successfully.") }, 0)
                history.push('/admin/companies')
                props.handleAllCompanies()
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
                            props.handleAllCompanies()
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
                    <div className='AdminUpdateCompany-background'></div>
                    <div className="AdminUpdateCompany">
                        <div className='AdminUpdateCompany-close-div'>
                            <button className="btn-close" aria-label="Close" id='AdminUpdateCompany-close-button' onClick={closeDiv} title='Close'></button>
                        </div>
                        <h5 className='AdminUpdateCompany-title' title='Update An Existing Company'>Update An Existing Company</h5>
                        <Formik
                            initialValues={{
                                name: company.name,
                                email: company.email,
                                password: ''
                            }}
                            onSubmit={handleSubmit}
                        >
                            {({ values, dirty, handleChange }) => (
                                <Form>
                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                    <table className="AdminUpdateCompany-table">
                                        <tbody>
                                            <tr>
                                                <th width="50%" title='From:'>From:</th>
                                                <th width="50%" title='To:'>To:</th>
                                            </tr>
                                            <tr>
                                                {/* ------------------------------From:------------------------------ */}
                                                <td>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            value={company.name}
                                                            title={company.name}
                                                            className='form-control'
                                                            placeholder='Name'
                                                            disabled={true}
                                                            id='AdminUpdateCompany-from-name-input'
                                                        />
                                                        <div className="input-group-text" title='Name' id='AdminUpdateCompany-name-div'>Name</div>
                                                    </div>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            value={company.email}
                                                            type='email'
                                                            title={company.email}
                                                            className='form-control'
                                                            placeholder='Email'
                                                            disabled={true}
                                                            id='AdminUpdateCompany-from-email-input'
                                                        />
                                                        <div className="input-group-text" title='Email' id='AdminUpdateCompany-email-div'>Email</div>
                                                    </div>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            value=''
                                                            className='form-control'
                                                            placeholder='Password'
                                                            disabled={true}
                                                            id='AdminUpdateCompany-from-password-input'
                                                            title='Password'
                                                        />
                                                        <div className="input-group-text" title='Password' id='AdminUpdateCompany-password-div'>Password</div>
                                                    </div>
                                                </td>
                                                {/* ------------------------------To:------------------------------ */}
                                                <td>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            title={values.name}
                                                            name='name'
                                                            className='form-control'
                                                            placeholder='Name'
                                                            required={true}
                                                            id='AdminUpdateCompany-to-name-input'
                                                        />
                                                    </div>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
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
                                                            id='AdminUpdateCompany-to-email-input'
                                                        />
                                                    </div>
                                                    <div className="AdminUpdateCompany-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            type='password'
                                                            name='password'
                                                            className='form-control'
                                                            placeholder='Password'
                                                            autoComplete='true'
                                                            id='AdminUpdateCompany-to-password-input'
                                                        />
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div className='AdminUpdateCompany-submit'>
                                        <div className="AdminUpdateCompany-div-seperator"></div>
                                        <div className="AdminUpdateCompany-div-seperator"></div>
                                        <button type='submit' className='btn btn-primary' id='AdminUpdateCompany-submit-button' title='Update Company' disabled={!dirty}>
                                            Update Company
                                        </button>
                                        <div className="AdminUpdateCompany-div-seperator"></div>
                                        <div className="AdminUpdateCompany-div-seperator"></div>
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