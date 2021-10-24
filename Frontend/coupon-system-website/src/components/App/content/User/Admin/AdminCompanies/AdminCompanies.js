import AdminCompanyActionbar from './AdminCompanyActionbar/AdminCompanyActionbar'
import './admin-companies.css'
import { Form, Formik } from "formik";
import { createRef, useEffect, useRef, useState } from "react";
import AdminService from "../../../../../../services/user-services/AdminService";
import AuthenticationService from "../../../../../../services/AuthenticationService";


export default function AdminCompanies() {

    const [companies, setCompanies] = useState([{
        id: '',
        name: '',
        email: '',
        password: ''
    }])

    useEffect(() => {
        AdminService
            .getCompanies().then(
                result => {
                    setCompanies(result.data)
                },
                error => {
                    try {
                        if (error.response.data.response) {
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
    }, [])

    const companiesRef = useRef([])
    companiesRef.current = companies.map((empty, i) => companiesRef.current[i] ?? createRef());

    const getCompanies = (handleChange) => {
        if (companies[0].id === '')
            return
        return (companies.map((stream, index) => {
            return (
                <>
                    <tr ref={companiesRef.current[index]}>
                        <input type="checkbox" name="checked" value={index} id={stream.id} className='AdminCompanies-field-checkbox' onChange={handleChange} />
                        <td></td>
                        <td><label htmlFor={stream.id} className="AdminCompanies-table-id-label" id='AdminCompanies-table-id-label' title={`ID: ${stream.id}`}>{stream.id}</label></td>
                        <td><label htmlFor={stream.id} className="AdminCompanies-table-text-label" id='AdminCompanies-table-text-label' title={`NAME: ${stream.name}`}>{stream.name}</label></td>
                        <td><label htmlFor={stream.id} className="AdminCompanies-table-text-label" id='AdminCompanies-table-text-label' title={`EMAIL: ${stream.email}`}>{stream.email}</label></td>
                        <td><label htmlFor={stream.id} className="AdminCompanies-table-text-label" id='AdminCompanies-table-password-label' title={`PASSWORD: ${stream.password}`}>{stream.password}</label></td>
                    </tr>
                    <tr className="AdminCompanies-table-tr-seperator"></tr>
                </>
            )
        }))
    }

    return (
        <div className='AdminCompanies'>
            <Formik initialValues={{ checked: [] }}>
                {({ values, handleReset, handleChange }) => (<>
                    <AdminCompanyActionbar values={values} companiesRef={companiesRef} setCompanies={setCompanies} handleReset={handleReset} />
                    <Form>
                        <table className='AdminCompanies-table'>
                            <tbody>
                                <tr>
                                    <th></th>
                                    <th className='AdminCompanies-table-header' id='AdminCompanies-table-header-id' width='5%' title='ID'>ID</th>
                                    <th className='AdminCompanies-table-header' width='47.5%' title='Name'>Name</th>
                                    <th className='AdminCompanies-table-header' width='47.5%' title='Email'>Email</th>
                                </tr>
                                {getCompanies(handleChange)}
                            </tbody>
                        </table>
                    </Form>
                </>)}
            </Formik >
        </div>
    )

}