import { useEffect, useRef } from "react"
import image from '../../../../assests/The Coupon Project Logo.png'
import '../sidebar.css'
import history from "../../history"
import { withRouter } from 'react-router-dom'

function CompanySidebar() {

    const buttons = { home: useRef(), myCoupons: useRef(), coupons: useRef() }
    const buttonDivs = { home: useRef(), myCoupons: useRef(), coupons: useRef() }

    useEffect(() => {
        for (let button of Object.values(buttons)) {
            button.current.className = 'Sidebar-button'
        }
        for (let div of Object.values(buttonDivs)) {
            div.current.className = 'Sidebar-button-div'
        }
        switch (history.location.pathname) {
            case '/company/home':
                buttonDivs.home.current.className = 'Sidebar-button-div-clicked'
                buttons.home.current.className = 'Sidebar-button-clicked'
                break;
            case '/company/my-coupons':
                buttonDivs.myCoupons.current.className = 'Sidebar-button-div-clicked'
                buttons.myCoupons.current.className = 'Sidebar-button-clicked'
                break;
            case '/company/coupons':
                buttonDivs.coupons.current.className = 'Sidebar-button-div-clicked'
                buttons.coupons.current.className = 'Sidebar-button-clicked'
                break;
            default:
                break;
        }
    })

    return (
        <div className='CompanySidebar'>
            <img src={image} alt='logo' className='Sidebar-img' onClick={() => history.push('/company/home')} title='The Coupon Project' />
            <div className='Sidebar-img-div' onClick={() => history.push('/company/home')}></div>

            <div className='Siderbar-div-seperator'></div>
            <div className='Sidebar-button-div-clicked' ref={buttonDivs.home}>
                <button className='Sidebar-button-clicked' name='Home' ref={buttons.home} onClick={() => history.push('/company/home')} title='Home Page'>Home Page</button>
            </div>

            <div className='Siderbar-div-button-seperator'></div>
            <div className='Sidebar-button-div' ref={buttonDivs.myCoupons}>
                <button className='Sidebar-button' name='My-Coupons' ref={buttons.myCoupons} onClick={() => history.push('/company/my-coupons')} title='My Coupons'>My Coupons</button>
            </div>

            <div className='Siderbar-div-button-seperator'></div>
            <div className='Sidebar-button-div' ref={buttonDivs.coupons}>
                <button className='Sidebar-button' name='Coupons' ref={buttons.coupons} onClick={() => history.push('/company/coupons')} title='Coupons'>Coupons</button>
            </div>
        </div>
    )

}

export default withRouter(CompanySidebar)