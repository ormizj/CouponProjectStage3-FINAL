import { useEffect, useState } from 'react'
import AuthenticationService from '../../../../../../services/AuthenticationService'
import AdminService from '../../../../../../services/user-services/AdminService'
import './view-coupons.css'

export default function ViewCoupons() {

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
    }, [])

    const getCoupons = () => {
        if (coupons[0].id === '')
            return
        return (coupons.map((stream, index) => {
            return (
                <>
                    <tr key={index}>
                        <input id={stream.id} className='ViewCoupons-input' />
                        <td></td>
                        <td className='ViewCoupons-table-image'>
                            <div className='ViewCoupons-table-image-div' style={{ backgroundImage: "url(" + stream.image + ")" }}>
                                <label htmlFor={stream.id} className="ViewCoupons-table-text-label" ></label>
                            </div>
                        </td>
                        <td><label htmlFor={stream.id} className="ViewCoupons-table-text-label" title={`STOCK: ${stream.amount}`}>{stream.amount}</label></td>
                        <td><label htmlFor={stream.id} className="ViewCoupons-table-text-label" title={`TITLE: ${stream.title}`}>{stream.title}</label></td>
                        <td><label htmlFor={stream.id} className="ViewCoupons-table-text-label" id="ViewCoupons-table-description" title={`DESCRIPTION: ${stream.description}`}>{stream.description}</label></td>
                        <td><label htmlFor={stream.id} className="ViewCoupons-table-text-label" title={`PRICE: $${stream.price}`}>${stream.price}</label></td>
                        <td><label htmlFor={stream.id} className="ViewCoupons-table-text-label" title={`CATEGORY: ${stream.category}`} id='CustomerCoupons-table-category'>{stream.category}</label></td>
                        <td><label htmlFor={stream.id} className="ViewCoupons-table-text-label" title={`RELEASE DATE: ${stream.startDate}`}>{stream.startDate}</label></td>
                        <td><label htmlFor={stream.id} className="ViewCoupons-table-text-label" title={`EXPIRATION DATE: ${stream.endDate}`}>{stream.endDate}</label></td>
                    </tr>
                    <tr className="ViewCoupons-table-tr-seperator"> </tr>
                </>
            )
        }))
    }


    return (
        <div className='ViewCoupons'>
            <table className='ViewCoupons-table'>
                <tbody>
                    <tr>
                        <th></th>
                        <th className='ViewCoupons-table-header' width='6%' ></th>
                        <th className='ViewCoupons-table-header' width='7%' title='Stock'>Stock</th>
                        <th className='ViewCoupons-table-header' width='20%' title='Title'>Title</th>
                        <th className='ViewCoupons-table-header' width='30%' title='Description'>Description</th>
                        <th className='ViewCoupons-table-header' width='7%' title='Price'>Price</th>
                        <th className='ViewCoupons-table-header' width='12%' title='Category'>Category</th>
                        <th className='ViewCoupons-table-header' width='9%' title='Release Date'>Release Date</th>
                        <th className='ViewCoupons-table-header' width='9%' title='Expiration Date'>Expiration Date</th>
                    </tr>
                    {getCoupons()}
                </tbody>
            </table>
        </div >
    )

}