import history from '../App/history'
import './coupons-by-category.css'

export default function CouponsByCategory(props) {

    const redirect = () => {
        if (history.location.pathname.startsWith("/company"))
            history.push('/company/my-coupons')
        else
            history.push('/customer/my-coupons')
    }

    if (props.coupons[0].id === '')
        redirect()

    const buttonDisabled = {
        attraction: true,
        automobile: true,
        electricity: true,
        food: true,
        gaming: true,
        restaurant: true,
        sport: true,
        vacation: true
    }

    for (let coupon of props.coupons) {
        switch (coupon.category) {
            case "ATTRACTION":
                buttonDisabled.attraction = false
                break;
            case "AUTOMOBILE":
                buttonDisabled.automobile = false
                break;
            case "ELECTRICITY":
                buttonDisabled.electricity = false
                break;
            case "FOOD":
                buttonDisabled.food = false
                break;
            case "GAMING":
                buttonDisabled.gaming = false
                break;
            case "RESTAURANT":
                buttonDisabled.restaurant = false
                break;
            case "SPORT":
                buttonDisabled.sport = false
                break;
            case "VACATION":
                buttonDisabled.vacation = false
                break;
            default:
                break;
        }
    }

    const handleButton = (category) => {
        props.handleSearchCategory(category)
        redirect()
    }

    return (
        <>
            <div className="CouponsByCategory-background"></div>
            <div className="CouponsByCategory">
                <div className='CouponsByCategory-close-div'>
                    <button className="btn-close" aria-label="Close" id='CouponsByCategory-close-button' onClick={redirect} title='Close'></button>
                </div>
                <h5 className='CouponsByCategory-title' title='Search coupons by the category...'>Search coupons by the category...</h5>
                <div className="CouponsByCategory-div-title-seperator"></div>
                <div>
                    <button className='btn btn-primary' title='Attraction' id="CouponsByCategory-button" onClick={() => handleButton("ATTRACTION")} disabled={buttonDisabled.attraction}>
                        Attraction
                    </button>
                    <button className='btn btn-primary' title='Automobile' id="CouponsByCategory-button" onClick={() => handleButton("AUTOMOBILE")} disabled={buttonDisabled.automobile}>
                        Automobile
                    </button>
                    <button className='btn btn-primary' title='Electricity' id="CouponsByCategory-button" onClick={() => handleButton("ELECTRICITY")} disabled={buttonDisabled.electricity}>
                        Electricity
                    </button>
                    <button className='btn btn-primary' title='Food' id="CouponsByCategory-button" onClick={() => handleButton("FOOD")} disabled={buttonDisabled.food}>
                        Food
                    </button>
                    <button className='btn btn-primary' title='Gaming' id="CouponsByCategory-button" onClick={() => handleButton("GAMING")} disabled={buttonDisabled.gaming}>
                        Gaming
                    </button>
                    <button className='btn btn-primary' title='Restaurant' id="CouponsByCategory-button" onClick={() => handleButton("RESTAURANT")} disabled={buttonDisabled.restaurant}>
                        Restaurant
                    </button>
                    <button className='btn btn-primary' title='Sport' id="CouponsByCategory-button" onClick={() => handleButton("SPORT")} disabled={buttonDisabled.sport}>
                        Sport
                    </button>
                    <button className='btn btn-primary' title='Vacation' id="CouponsByCategory-button" onClick={() => handleButton("VACATION")} disabled={buttonDisabled.vacation}>
                        Vacation
                    </button>
                </div>
                <div className="CouponsByCategory-div-seperator"></div>
                <div className="CouponsByCategory-div-seperator"></div>
            </div>
        </>
    )

}