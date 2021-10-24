import axios from 'axios';

axios.interceptors.request.use(config => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
        const token = 'Bearer ' + user.token;
        config.headers.Authorization = token;
    }
    return config;
});

class CompanyService {

    async createCoupon(
        amount,
        category,
        description,
        endDate,
        startDate,
        price,
        image,
        title) {
        return await axios.post("http://localhost:8080/company/add-coupon",
            { amount, category, description, endDate, startDate, price, image, title }
        )
    }

    async updateCoupon(
        id,
        amount,
        category,
        description,
        endDate,
        startDate,
        price,
        image,
        title
    ) {
        return await axios.put("http://localhost:8080/company/update-coupon",
            { id, amount, category, description, endDate, startDate, price, image, title }
        )
    }

    async deleteCoupon(id) {
        return await axios.delete("http://localhost:8080/company/delete-coupon?couponId=" + id)
    }

    async getCoupons() {
        return await axios.get("http://localhost:8080/company/get-coupons")
    }

    async getCouponsCategory(category) {
        return await axios.get("http://localhost:8080/company/get-coupons-category?category=" + category)
    }

    async getCouponsPrice(price) {
        return await axios.get("http://localhost:8080/company/get-coupons-max-price?maxPrice=" + price)
    }

    async getDetails() {
        return await axios.get("http://localhost:8080/company/get-details");
    }

}

export default new CompanyService();