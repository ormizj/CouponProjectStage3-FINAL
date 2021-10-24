import '../../../../../../../styles/action-sidebar.css';
import CustomerService from '../../../../../../../services/user-services/CustomerService';
import AuthenticationService from '../../../../../../../services/AuthenticationService';

export default function CouponsCustomerActionbar(props) {

    const resetSelection = () => {
        for (let index of Object.values(props.values.checked)) {
            if (props.couponsRef.current[index]) {
                const currentRef = props.couponsRef.current[index].current
                currentRef.children[0].checked = false
            }
        }
        props.handleReset()
    }

    const handlePurchaseCoupons = () => {
        if (props.values.checked.length === 0)
            setTimeout(() => { alert("You need to select atleast one coupon to purchase.") }, 0)
        for (let index of Object.values(props.values.checked)) {
            const currentRef = props.couponsRef.current[index].current
            const coupon = "Stock: " + currentRef.children[3].children[0].innerHTML +
                "\nTitle: " + currentRef.children[4].children[0].innerHTML +
                "\nDescription: " + currentRef.children[5].children[0].innerHTML +
                "\nPrice: " + currentRef.children[6].children[0].innerHTML +
                "\nCategory: " + currentRef.children[7].children[0].innerHTML +
                "\nRelease Date: " + currentRef.children[8].children[0].innerHTML +
                "\nExpiration Date: " + currentRef.children[9].children[0].innerHTML
            CustomerService
                .purchaseCoupon(currentRef.children[0].id).then(
                    () => {
                        setTimeout(() => { alert(coupon + "\nCoupon purchase was successful.") }, 0)
                        props.getCouponsList()
                        resetSelection()
                    },
                    error => {
                        try {
                            if (error.response.data.response) {
                                if (error.response.data.errorCode === "CPN-002.011")
                                    setTimeout(() => { alert(coupon + "\nCoupon purchase was unsuccessful, you already own the coupon.") }, 0)
                                else if (error.response.data.errorCode === "CPN-002.012")
                                    setTimeout(() => { alert(coupon + "\nCoupon purchase was unsuccessful, coupon is out of stock.") }, 0)
                                else {
                                    setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                                    props.getCouponsList()
                                }
                                resetSelection()
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
    }

    return (
        <div className='CouponsCustomerActionbar'>
            <div className='action-sidebar'>
                <button className='action-sidebar-button' title='Purchase Coupons' onClick={handlePurchaseCoupons}>Purchase Coupons</button>
            </div>
        </div>
    )

}