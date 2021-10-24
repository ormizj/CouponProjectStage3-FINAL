import { Form, Formik } from "formik";
import CouponsCustomerActionbar from './CouponsCustomerActionbar/CouponsCustomerActionbar'
import './coupons-customer.css'
import { createRef, useEffect, useRef, useState } from "react";
import AdminService from "../../../../../../services/user-services/AdminService";
import AuthenticationService from "../../../../../../services/AuthenticationService";

export default function CustomerCustomer() {

    const [coupons, setCoupons] = useState([{
        id: '',
        amount: '',
        category: '',
        description: '',
        endDate: '',
        startDate: '',
        price: '',
        image: '',
        title: ''
    }])

    const getCouponsList = () => {
        AdminService
            .getCoupons().then(
                result => {
                    setCoupons(result.data)
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
    }

    useEffect(() => {
        getCouponsList()
    }, [])

    const couponsRef = useRef([])
    couponsRef.current = coupons.map((empty, i) => couponsRef.current[i] ?? createRef());

    const getCoupons = (handleChange) => {
        if (coupons[0].id === '')
            return
        return (coupons.map((stream, index) => {
            return (
                <>
                    <tr key={index} ref={couponsRef.current[index]}>
                        <input type="checkbox" name="checked" value={index} id={stream.id} className='CouponsCustomer-field-checkbox' onChange={handleChange} />
                        <td></td>
                        <td className='CouponsCustomer-table-image'>
                            <div className='CouponsCustomer-table-image-div' style={{ backgroundImage: "url(" + stream.image + ")" }}>
                                <label htmlFor={stream.id} className="CouponsCustomer-table-text-label" ></label>
                            </div>
                        </td>
                        <td><label htmlFor={stream.id} className="CouponsCustomer-table-text-label" title={`STOCK: ${stream.amount}`}>{stream.amount}</label></td>
                        <td><label htmlFor={stream.id} className="CouponsCustomer-table-text-label" title={`TITLE: ${stream.title}`}>{stream.title}</label></td>
                        <td><label htmlFor={stream.id} className="CouponsCustomer-table-text-label" id="CouponsCustomer-table-description" title={`DESCRIPTION: ${stream.description}`}>{stream.description}</label></td>
                        <td><label htmlFor={stream.id} className="CouponsCustomer-table-text-label" title={`PRICE: $${stream.price}`}>${stream.price}</label></td>
                        <td><label htmlFor={stream.id} className="CouponsCustomer-table-text-label" title={`CATEGORY: ${stream.category}`} id='CouponsCustomer-table-category'>{stream.category}</label></td>
                        <td><label htmlFor={stream.id} className="CouponsCustomer-table-text-label" title={`RELEASE DATE: ${stream.startDate}`}>{stream.startDate}</label></td>
                        <td><label htmlFor={stream.id} className="CouponsCustomer-table-text-label" title={`EXPIRATION DATE: ${stream.endDate}`}>{stream.endDate}</label></td>
                    </tr>
                    <tr className="CouponsCustomer-table-tr-seperator"></tr>
                </>
            )
        }))
    }

    return (
        <div className="CouponsCustomer">
            <Formik initialValues={{ checked: [] }}>
                {({ values, handleChange, handleReset }) => (<>
                    <CouponsCustomerActionbar values={values} couponsRef={couponsRef} getCouponsList={getCouponsList} handleReset={handleReset} />
                    <Form>
                        <table className='CouponsCustomer-table'>
                            <tbody>
                                <tr>
                                    <th></th>
                                    <th className='CouponsCustomer-table-header' width='6%' ></th>
                                    <th className='CouponsCustomer-table-header' width='7%' title='Stock'>Stock</th>
                                    <th className='CouponsCustomer-table-header' width='20%' title='Title'>Title</th>
                                    <th className='CouponsCustomer-table-header' width='30%' title='Description'>Description</th>
                                    <th className='CouponsCustomer-table-header' width='7%' title='Price'>Price</th>
                                    <th className='CouponsCustomer-table-header' width='12%' title='Category'>Category</th>
                                    <th className='CouponsCustomer-table-header' width='9%' title='Release Date'>Release Date</th>
                                    <th className='CouponsCustomer-table-header' width='9%' title='Expiration Date'>Expiration Date</th>
                                </tr>
                                {getCoupons(handleChange)}
                            </tbody>
                        </table>
                    </Form>
                </>)}
            </Formik >
        </div >
    )

}

