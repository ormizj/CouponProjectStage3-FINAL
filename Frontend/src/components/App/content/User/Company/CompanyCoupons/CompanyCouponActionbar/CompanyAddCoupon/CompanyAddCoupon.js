import './company-add-coupon.css'
import history from './../../../../../../history'
import { Formik, Form, Field } from 'formik'
import { getToday } from './../../../../../../../../utils/dateUtil'
import CompanyService from './../../../../../../../../services/user-services/CompanyService'
import AuthenticationService from '../../../../../../../../services/AuthenticationService'

export default function CompanyAddCoupon(props) {

    const closeDiv = () => {
        if (window.confirm('Are you sure you want to close this window?'))
            history.push('/company/my-coupons')
    }

    const handleSubmit = (values) => {
        CompanyService.createCoupon(
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
                setTimeout(() => { alert("The coupon was created successfully.") }, 0)
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

    return (
        <>
            <div className='CompanyAddCoupon-background'></div>
            <div className='CompanyAddCoupon'>
                <div className='CompanyAddCoupon-close-div'>
                    <button className="btn-close" aria-label="Close" id='CompanyAddCoupon-close-button' onClick={closeDiv} title='Close'></button>
                </div>
                <h5 className='CompanyAddCoupon-title' title='Add A New Coupon'>Add A New Coupon</h5>
                <Formik
                    initialValues={{
                        amount: '',
                        category: '',
                        description: '',
                        endDate: '',
                        image: '',
                        price: '',
                        startDate: getToday(),
                        title: ''
                    }}
                    onSubmit={handleSubmit}
                >
                    {({ values }) => (
                        <Form>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanyAddCoupon-title-div' title='Title'>Title</div>
                                <Field
                                    title={values.title}
                                    name='title'
                                    className='form-control'
                                    placeholder='Title'
                                    required={true}
                                    id='CompanyAddCoupon-input'
                                />
                            </div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanyAddCoupon-description-div' title='Description'>Description</div>
                                <Field
                                    title={values.description}
                                    name='description'
                                    className='form-control'
                                    placeholder='Description'
                                    required={true}
                                    id='CompanyAddCoupon-input'
                                />
                            </div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanyAddCoupon-category-div' title='Category'>Category</div>
                                <Field
                                    className='form-select'
                                    component="select"
                                    name="category"
                                    required={true}
                                    id='CompanyAddCoupon-input'
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
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanyAddCoupon-image-div' title='Image URL'>Image URL</div>
                                <Field
                                    type='url'
                                    name='image'
                                    className='form-control'
                                    placeholder='http://www.example.com/image.png'
                                    required={true}
                                    id='CompanyAddCoupon-input'
                                    title={values.image}
                                />
                            </div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanyAddCoupon-price-div' title='Price'>Price</div>
                                <div className="input-group-text" title='$'>$</div>
                                <Field
                                    step='.01'
                                    type='number'
                                    min='0'
                                    name='price'
                                    className='form-control'
                                    placeholder='0.00'
                                    required={true}
                                    title={values.price}
                                />
                                <div className="CompanyAddCoupon-div-seperator"></div>
                                <div className="input-group-text" title='Amount'>Amount</div>
                                <Field
                                    type='number'
                                    min='0'
                                    name='amount'
                                    className='form-control'
                                    placeholder='0'
                                    required={true}
                                    id='CompanyAddCoupon-input'
                                    title={values.amount}
                                />
                            </div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="input-group">
                                <div className="input-group-text" id='CompanyAddCoupon-start_date-div' title='Date'>Date</div>
                                <div className="input-group-text" title='From:'>From:</div>
                                <Field
                                    min={getToday()}
                                    type='date'
                                    name='startDate'
                                    className='form-control'
                                    required={true}
                                    id='CompanyAddCoupon-input'
                                    title={values.startDate}
                                />
                                <div className="input-group-text" id='CompanyAddCoupon-end_date-div' title='To:'>To:</div>
                                <Field
                                    min={values.startDate}
                                    type='date'
                                    name='endDate'
                                    className='form-control'
                                    required={true}
                                    id='CompanyAddCoupon-input'
                                    title={values.endDate}
                                />
                            </div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className='CompanyAddCoupon-submit'>
                                <button type='submit' className='btn btn-primary' title='Add Coupon'>
                                    Add Coupon
                                </button>
                            </div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                            <div className="CompanyAddCoupon-div-seperator"></div>
                        </Form>
                    )}
                </Formik >
            </div>
        </>
    )

}