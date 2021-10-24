import history from '../../../history'
import './signup.css'

export default function Signup() {

    return (
        <>
            <div className='Signup-background'></div>
            <div className='Signup'>
                <div className='Signup-close-div'>
                    <button className="btn-close" aria-label="Close" id='Signup-close-button' onClick={() => history.push('/login')} title='Close'></button>
                </div>
                <h5 className='Signup-title' title='Signup as a...'>Signup as a...</h5>
                <div className="Signup-div-seperator"></div>
                <div className='Signup-submit'>
                    <button type='submit' className='btn btn-primary' title='Customer' id='Signup-customer' onClick={() => history.push("/login/signup/customer")}>
                        Customer
                    </button>
                    <button type='submit' className='btn btn-primary' title='Company' onClick={() => history.push("/login/signup/company")}>
                        Company
                    </button>
                </div>
                <div className="Signup-div-seperator"></div>
                <div className="Signup-div-seperator"></div>
            </div>
        </>
    )

}