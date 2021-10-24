import AdminCustomerActionbar from "./AdminCustomerActionbar/AdminCustomerActionbar";
import { Form, Formik } from "formik";
import './admin-customers.css'
import { createRef, useEffect, useRef, useState } from "react";
import AdminService from "../../../../../../services/user-services/AdminService";
import AuthenticationService from "../../../../../../services/AuthenticationService";


export default function AdminCustomers() {

    const [customers, setCustomers] = useState([{
        id: '',
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    }])

    useEffect(() => {
        AdminService
            .getCustomers().then(
                result => {
                    setCustomers(result.data)
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

    const customersRef = useRef([])
    customersRef.current = customers.map((empty, i) => customersRef.current[i] ?? createRef());

    const getCustomers = (handleChange) => {
        if (customers[0].id === '')
            return
        return (customers.map((stream, index) => {
            return (
                <>
                    <tr ref={customersRef.current[index]}>
                        <input type="checkbox" name="checked" value={index} id={stream.id} className='AdminCustomers-field-checkbox' onChange={handleChange} />
                        <td></td>
                        <td><label htmlFor={stream.id} className="AdminCustomers-table-id-label" id='AdminCustomers-table-id-label' title={`ID: ${stream.id}`}>{stream.id}</label></td>
                        <td><label htmlFor={stream.id} className="AdminCustomers-table-text-label" title={`FIRST NAME: ${stream.firstName}`}>{stream.firstName}</label></td>
                        <td><label htmlFor={stream.id} className="AdminCustomers-table-text-label" title={`LAST NAME: ${stream.lastName}`}>{stream.lastName}</label></td>
                        <td><label htmlFor={stream.id} className="AdminCustomers-table-text-label" title={`EMAIL: ${stream.email}`}>{stream.email}</label></td>
                        <td><label htmlFor={stream.id} className="AdminCustomers-table-text-label" id='AdminCustomers-table-password-label' title={`PASSWORD: ${stream.password}`}>{stream.password}</label></td>
                    </tr>
                    <tr className="AdminCustomers-table-tr-seperator"></tr>
                </>
            )
        }))
    }

    return (
        <div className='AdminCustomers'>
            <Formik initialValues={{ checked: [] }}>
                {({ values, handleChange, handleReset }) => (<>
                    <AdminCustomerActionbar values={values} customersRef={customersRef} setCustomers={setCustomers} handleReset={handleReset} />
                    <Form>
                        <table className='AdminCustomers-table'>
                            <tbody>
                                <tr>
                                    <th></th>
                                    <th className='AdminCustomers-table-header' id='AdminCustomers-table-header-id' width='5%' title='ID'>ID</th>
                                    <th className='AdminCustomers-table-header' width='23.75%' title='First Name'>First Name</th>
                                    <th className='AdminCustomers-table-header' width='23.75%' title='Last Name'>Last Name</th>
                                    <th className='AdminCustomers-table-header' width='47.5%' title='Email'>Email</th>
                                </tr>
                                {getCustomers(handleChange)}
                            </tbody>
                        </table>
                    </Form>
                </>)}
            </Formik >
        </div>
    )

}