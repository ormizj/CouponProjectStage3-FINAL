import './company-update-coupon.css'
import history from '../../../../../../history'
import { Formik, Form, Field } from 'formik'
import { getToday } from './../../../../../../../../utils/dateUtil'
import CompanyService from '../../../../../../../../services/user-services/CompanyService'
import AuthenticationService from '../../../../../../../../services/AuthenticationService'

export default function CompanyUpdateCoupon(props) {

    let valid = true
    let currentRef
    let coupon

    if (!props.values.checked[0]) {
        valid = false
        history.push("/company/my-coupons")
    }
    else {
        currentRef = props.couponsRef.current[props.values.checked[0]].current
        coupon = {
            amount: currentRef.children[3].children[0].innerHTML,
            category: currentRef.children[7].children[0].innerHTML,
            description: currentRef.children[5].children[0].innerHTML,
            endDate: currentRef.children[9].children[0].innerHTML,
            image: currentRef.children[2].children[0].style.backgroundImage.slice(5, -2),
            price: currentRef.children[6].children[0].innerHTML.substring(1, currentRef.children[6].children[0].innerHTML.length),
            startDate: currentRef.children[8].children[0].innerHTML,
            title: currentRef.children[4].children[0].innerHTML
        }
    }

    const closeDiv = () => {
        if (window.confirm('Are you sure you want to close this window?'))
            history.push('/company/my-coupons')
    }

    const handleSubmit = (values) => {
        CompanyService.updateCoupon(
            currentRef.children[0].id,
            values.amount,
            values.category,
            values.description,
            values.endDate,
            values.startDate,
            values.price,
            values.image,
            values.title
        ).then(
            () => {
                setTimeout(() => { alert("The coupon was updated successfully.") }, 0)
                history.push('/company/my-coupons')
                props.handleAllCoupons()
            },
            error => {
                try {
                    if (error.response.data.response) {
                        if (error.response.data.errorCode === "CPN-002.002")
                            setTimeout(() => { alert("You already own a coupon with the title: \"" + values.title + "\"\nPlease choose a different title.") }, 0)
                        else {
                            setTimeout(() => { alert("Action Failed\n" + error.response.data.response) }, 0)
                            history.push('/company/my-coupons')
                            props.handleAllCoupons()
                        }
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

    const getBody = () => {
        if (valid)
            return (
                <>
                    <div className='CompanyUpdateCoupon-background'></div>
                    <div className="CompanyUpdateCoupon">
                        <div className='CompanyUpdateCoupon-close-div'>
                            <button className="btn-close" aria-label="Close" id='CompanyUpdateCoupon-close-button' onClick={closeDiv} title='Close'></button>
                        </div>
                        <h5 className='CompanyUpdateCoupon-title' title='Update An Existing Coupon'>Update An Existing Coupon</h5>
                        <Formik
                            initialValues={{
                                amount: coupon.amount,
                                category: coupon.category,
                                description: coupon.description,
                                endDate: coupon.endDate,
                                image: coupon.image,
                                price: coupon.price,
                                startDate: coupon.startDate,
                                title: coupon.title
                            }}
                            onSubmit={handleSubmit}
                        >
                            {({ values, dirty }) => (
                                <Form>
                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                    <table className="CompanyUpdateCoupon-table">
                                        <tbody>
                                            <tr>
                                                <th width="50%" title='From:'>From:</th>
                                                <th width="50%" title='To:'>To:</th>
                                            </tr>
                                            <tr>
                                                {/* ------------------------------From:------------------------------ */}
                                                <td>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            className='form-control'
                                                            placeholder='Title'
                                                            disabled={true}
                                                            title={coupon.title}
                                                            value={coupon.title}
                                                            id='CompanyUpdateCoupon-from-input'
                                                        />
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-title-div' title='Title'>Title</div>
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            className='form-control'
                                                            placeholder='Description'
                                                            disabled={true}
                                                            title={coupon.description}
                                                            value={coupon.description}
                                                            id='CompanyUpdateCoupon-from-input'
                                                        />
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-description-div' title='Description'>Description</div>
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            className='form-select'
                                                            disabled={true}
                                                            placeholder="-"
                                                            title={coupon.category.charAt(0) + coupon.category.toLowerCase().substring(1, coupon.category.length)}
                                                            value={coupon.category.charAt(0) + coupon.category.toLowerCase().substring(1, coupon.category.length)}
                                                            id='CompanyUpdateCoupon-from-input'
                                                        />
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-category-div' title='Category'>Category</div>
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            className='form-control'
                                                            placeholder='http://www.example.com/image.png'
                                                            value={coupon.image}
                                                            title={coupon.image}
                                                            disabled={true}
                                                            id='CompanyUpdateCoupon-from-input'
                                                        />
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-image-div' title='Image URL'>Image URL</div>
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-price-div' title='Price'>Price</div>
                                                        <div className="input-group-text" title='$'>$</div>
                                                        <Field
                                                            className='form-control'
                                                            placeholder='0.00'
                                                            value={coupon.price}
                                                            title={coupon.price}
                                                            disabled={true}
                                                            id='CompanyUpdateCoupon-from-input'

                                                        />
                                                        <Field
                                                            className='form-control'
                                                            value={coupon.amount}
                                                            title={coupon.amount}
                                                            placeholder='0'
                                                            disabled={true}
                                                            id='CompanyUpdateCoupon-from-amount-input'
                                                        />

                                                        <div className="input-group-text" id='CompanyUpdateCoupon-amount-div' title='Amount'>Amount</div>
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-from-div' title='From:'>From:</div>
                                                        <Field
                                                            type='date'
                                                            className='form-control'
                                                            disabled={true}
                                                            value={coupon.startDate}
                                                            title={coupon.startDate}
                                                            id='CompanyUpdateCoupon-from-input'
                                                        />
                                                        <div className="input-group-text" title='To:'>To:</div>
                                                        <Field
                                                            type='date'
                                                            className='form-control'
                                                            disabled={true}
                                                            value={coupon.endDate}
                                                            title={coupon.endDate}
                                                            id='CompanyUpdateCoupon-from-input'
                                                        />
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-date-div' title='Date'>Date</div>
                                                    </div>
                                                </td>
                                                {/* ------------------------------To:------------------------------ */}
                                                <td>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            title={values.title}
                                                            name='title'
                                                            className='form-control'
                                                            placeholder='Title'
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-wide-input'
                                                        />

                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            title={values.description}
                                                            name='description'
                                                            className='form-control'
                                                            placeholder='Description'
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-wide-input'
                                                        />
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            className='form-select'
                                                            component="select"
                                                            name="category"
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-wide-input'
                                                            title={values.category}
                                                        >
                                                            <option value="">-</option>
                                                            <option value="ATTRACTION">Attraction</option>
                                                            <option value="AUTOMOBILE">Automobile</option>
                                                            <option value="ELECTRICITY">Electricity</option>
                                                            <option value="FOOD">Food</option>
                                                            <option value="GAMING">Gaming</option>
                                                            <option value="RESTAURANT">Restaurant</option>
                                                            <option value="SPORT">Sport</option>
                                                            <option value="VACATION">Vacation</option>
                                                        </Field>
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            type='url'
                                                            name='image'
                                                            className='form-control'
                                                            placeholder='http://www.example.com/image.png'
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-wide-input'
                                                            title={values.image}
                                                        />
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <Field
                                                            type='number'
                                                            min='0'
                                                            name='amount'
                                                            className='form-control'
                                                            placeholder='0'
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-left-input'
                                                            title={values.amount}
                                                        />
                                                        <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-to-price-div' title='Price'>Price</div>
                                                        <div className="input-group-text" title='$'>$</div>
                                                        <Field
                                                            step='.01'
                                                            type='number'
                                                            min='0'
                                                            name='price'
                                                            className='form-control'
                                                            placeholder='0.00'
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-right-input'
                                                            title={values.price}
                                                        />
                                                    </div>
                                                    <div className="CompanyUpdateCoupon-div-seperator"></div>
                                                    <div className="input-group">
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-to-left-div' title='From:'>From:</div>
                                                        <Field
                                                            min={coupon.startDate}
                                                            type='date'
                                                            name='startDate'
                                                            className='form-control'
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-input'
                                                            title={values.startDate}
                                                        />
                                                        <div className="input-group-text" id='CompanyUpdateCoupon-end_date-div' title='To'>To:</div>
                                                        <Field
                                                            min={getToday()}
                                                            type='date'
                                                            name='endDate'
                                                            className='form-control'
                                                            required={true}
                                                            id='CompanyUpdateCoupon-to-right-input'
                                                            title={values.endDate}
                                                        />
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div className='CompanyUpdateCoupon-submit'>
                                        <div className="CompanyUpdateCoupon-div-seperator"></div>
                                        <div className="CompanyUpdateCoupon-div-seperator"></div>
                                        <button type='submit' className='btn btn-primary' id='CompanyUpdateCoupon-submit-button' title='Update Coupon' disabled={!dirty}>
                                            Update Coupon
                                        </button>
                                        <div className="CompanyUpdateCoupon-div-seperator"></div>
                                        <div className="CompanyUpdateCoupon-div-seperator"></div>
                                    </div>
                                </Form>
                            )}
                        </Formik >

                    </div>

                </>
            )
        else return (<></>)
    }

    return (
        <>{getBody()}</>
    )

}