import { useEffect, useRef } from "react"
import image from '../../../../assests/The Coupon Project Logo.png'
import '../sidebar.css'
import history from "../../history"
import { withRouter } from 'react-router-dom'

function AdminSidebar() {

    const buttons = { home: useRef(), coupons: useRef(), companies: useRef(), customers: useRef(), logs: useRef() }
    const buttonDivs = { home: useRef(), coupons: useRef(), companies: useRef(), customers: useRef(), logs: useRef() }

    useEffect(() => {
        for (let button of Object.values(buttons)) {
            button.current.className = 'Sidebar-button'
        }
        for (let div of Object.values(buttonDivs)) {
            div.current.className = 'Sidebar-button-div'
        }
        switch (history.location.pathname) {
            case '/admin/home':
                buttonDivs.home.current.className = 'Sidebar-button-div-clicked'
                buttons.home.current.className = 'Sidebar-button-clicked'
                break;
            case '/admin/customers':
                buttonDivs.customers.current.className = 'Sidebar-button-div-clicked'
                buttons.customers.current.className = 'Sidebar-button-clicked'
                break;
            case '/admin/companies':
                buttonDivs.companies.current.className = 'Sidebar-button-div-clicked'
                buttons.companies.current.className = 'Sidebar-button-clicked'
                break;
            case '/admin/coupons':
                buttonDivs.coupons.current.className = 'Sidebar-button-div-clicked'
                buttons.coupons.current.className = 'Sidebar-button-clicked'
                break;
            case '/admin/logs':
                buttonDivs.logs.current.className = 'Sidebar-button-div-clicked'
                buttons.logs.current.className = 'Sidebar-button-clicked'
                break;
            default:
                break;
        }
    })

    return (
        <div className='AdminSidebar'>
            <img src={image} alt='logo' className='Sidebar-img' onClick={() => history.push('/admin/home')} title='The Coupon Project' />
            <div className='Sidebar-img-div' onClick={() => history.push('/admin/home')}></div>

            <div className='Siderbar-div-seperator'></div>
            <div className='Sidebar-button-div-clicked' ref={buttonDivs.home}>
                <button className='Sidebar-button-clicked' name='Home' ref={buttons.home} onClick={() => history.push('/admin/home')} title='Home Page'>Home Page</button>
            </div>

            <div className='Siderbar-div-button-seperator'></div>
            <div className='Sidebar-button-div' ref={buttonDivs.customers}>
                <button className='Sidebar-button' name='Customers' ref={buttons.customers} onClick={() => history.push('/admin/customers')} title='Customers'>Customers</button>
            </div>

            <div className='Siderbar-div-button-seperator'></div>
            <div className='Sidebar-button-div' ref={buttonDivs.companies}>
                <button className='Sidebar-button' name='Companies' ref={buttons.companies} onClick={() => history.push('/admin/companies')} title='Companies'>Companies</button>
            </div>

            <div className='Siderbar-div-button-seperator'></div>
            <div className='Sidebar-button-div' ref={buttonDivs.coupons}>
                <button className='Sidebar-button' name='Coupons' ref={buttons.coupons} onClick={() => history.push('/admin/coupons')} title='Coupons'>Coupons</button>
            </div>

            <div className='Siderbar-div-button-seperator'></div>
            <div className='Sidebar-button-div' ref={buttonDivs.logs}>
                <button className='Sidebar-button' name='Logs' ref={buttons.logs} onClick={() => history.push('/admin/logs')} title='Logs'>Logs</button>
            </div>
        </div>
    )

}

export default withRouter(AdminSidebar)