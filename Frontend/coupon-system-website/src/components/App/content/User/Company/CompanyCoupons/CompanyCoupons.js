import { createRef, useEffect, useRef, useState } from 'react'
import CompanyCouponActionbar from './CompanyCouponActionbar/CompanyCouponActionbar'
import CompanyService from './../../../../../../services/user-services/CompanyService'
import './company-coupons.css'
import { Form, Formik } from 'formik'
import AuthenticationService from '../../../../../../services/AuthenticationService'

export default function CompanyCoupons() {

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

    useEffect(() => {
        CompanyService
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
                        <input type="checkbox" name="checked" value={index} id={stream.id} className='CompanyCoupons-field-checkbox' onChange={handleChange} />
                        <td></td>
                        <td className='CompanyCoupons-table-image'>
                            <div className='CompanyCoupons-table-image-div' style={{ backgroundImage: "url(" + stream.image + ")" }}>
                                <label htmlFor={stream.id} className="CompanyCoupons-table-text-label" ></label>
                            </div>
                        </td>
                        <td><label htmlFor={stream.id} className="CompanyCoupons-table-text-label" title={`STOCK: ${stream.amount}`}>{stream.amount}</label></td>
                        <td><label htmlFor={stream.id} className="CompanyCoupons-table-text-label" title={`TITLE: ${stream.title}`}>{stream.title}</label></td>
                        <td><label htmlFor={stream.id} className="CompanyCoupons-table-text-label" id="CouponsCompany-table-description" title={`DESCRIPTION: ${stream.description}`}>{stream.description}</label></td>
                        <td><label htmlFor={stream.id} className="CompanyCoupons-table-text-label" title={`PRICE: $${stream.price}`}>${stream.price}</label></td>
                        <td><label htmlFor={stream.id} className="CompanyCoupons-table-text-label" title={`CATEGORY: ${stream.category}`} id='CompanyCoupons-table-category'>{stream.category}</label></td>
                        <td><label htmlFor={stream.id} className="CompanyCoupons-table-text-label" title={`RELEASE DATE: ${stream.startDate}`}>{stream.startDate}</label></td>
                        <td><label htmlFor={stream.id} className="CompanyCoupons-table-text-label" title={`EXPIRATION DATE: ${stream.endDate}`}>{stream.endDate}</label></td>
                    </tr>
                    <tr className="CompanyCoupons-table-tr-seperator"></tr>
                </>
            )
        }))
    }

    return (
        <div className='CompanyCoupons'>

            <Formik initialValues={{ checked: [] }}>
                {({ values, handleChange, handleReset }) => (<>
                    <CompanyCouponActionbar values={values} couponsRef={couponsRef} coupons={coupons} setCoupons={setCoupons} handleReset={handleReset} />
                    <Form>
                        <table className='CompanyCoupons-table'>
                            <tbody>
                                <tr>
                                    <th></th>
                                    <th className='CompanyCoupons-table-header' width='6%' ></th>
                                    <th className='CompanyCoupons-table-header' width='7%' title='Stock'>Stock</th>
                                    <th className='CompanyCoupons-table-header' width='20%' title='Title'>Title</th>
                                    <th className='CompanyCoupons-table-header' width='30%' title='Description'>Description</th>
                                    <th className='CompanyCoupons-table-header' width='7%' title='Price'>Price</th>
                                    <th className='CompanyCoupons-table-header' width='12%' title='Category'>Category</th>
                                    <th className='CompanyCoupons-table-header' width='9%' title='Release Date'>Release Date</th>
                                    <th className='CompanyCoupons-table-header' width='9%' title='Expiration Date'>Expiration Date</th>
                                </tr>
                                {getCoupons(handleChange)}
                            </tbody>
                        </table>
                    </Form>
                </>)}
            </Formik >
        </div>
    )

}