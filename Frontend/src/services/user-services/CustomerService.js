import axios from 'axios';

axios.interceptors.request.use(config => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
        const token = 'Bearer ' + user.token;
        config.headers.Authorization = token;
    }
    return config;
});

class CustomerService {

    async getCoupons() {
        return await axios.get("http://localhost:8080/customer/get-coupons")
    }

    async getCouponsCategory(category) {
        return await axios.get("http://localhost:8080/customer/get-coupons-category?category=" + category)
    }

    async getCouponsPrice(price) {
        return await axios.get("http://localhost:8080/customer/get-coupons-max-price?maxPrice=" + price)
    }

    async purchaseCoupon(couponId) {
        return await axios.put("http://localhost:8080/customer/purchase-coupon?couponId=" + couponId);
    }

    async getDetails() {
        return await axios.get("http://localhost:8080/customer/get-details");
    }

}

export default new CustomerService();