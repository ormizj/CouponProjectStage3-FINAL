import axios from 'axios';
import history from './../components/App/history'

class AuthenticationService {

    logIn = async (email, password, userType) => {
        return await axios.post("http://localhost:8080/login-manager/login?email=" + email + "&password=" + password + "&userType=" + userType)
            .then(response => {
                if (response.data) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            })
            .catch(err => {
                throw err;
            }
            );
    }

    logOut() {
        localStorage.removeItem("user");
        history.push('/login');
    }

    registerCustomer = async (firstName, lastName, email, password) => {
        return await axios.post("http://localhost:8080/login-manager/register-customer",
            { firstName, lastName, email, password }
        );
    }

    registerCompany = async (name, email, password) => {
        return await axios.post("http://localhost:8080/login-manager/register-company",
            { name, email, password }
        );
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }

}

export default new AuthenticationService();