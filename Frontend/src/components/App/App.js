import './app.css';
import Footer from './Footer/Footer';
import Header from './Header/Header';
import User from './content/User/User';
import { Route, Switch, Router, Redirect } from 'react-router-dom'
import history from './history';
import { Suspense, lazy } from 'react';
import './../../styles/loading-div.css'
import AuthenticationService from '../../services/AuthenticationService';

const Login = lazy(() => import('./content/Login/Login'))
const AdminSidebar = lazy(() => import('./user-sidebar/AdminSidebar/AdminSidebar'))
const CompanySidebar = lazy(() => import('./user-sidebar/CompanySidebar/CompanySidebar'))
const CustomerSidebar = lazy(() => import('./user-sidebar/CustomerSidebar/CustomerSidebar'))

function App() {

  if (AuthenticationService.getCurrentUser() != null)
    switch (AuthenticationService.getCurrentUser().authorities[0].authority) {
      case "CUSTOMER":
        if (!history.location.pathname.includes("/customer"))
          history.push("/customer")
        break;
      case "COMPANY":
        if (!history.location.pathname.includes("/company"))
          history.push("/company")
        break;
      case "ADMIN":
        if (!history.location.pathname.includes("/admin"))
          history.push("/admin")
        break;
      default:
        break;
    }
  else
    if (!history.location.pathname.includes("/login"))
      history.push("/login")

  const getContent = () => {
    return (
      <Switch>
        <Route path={["/customer", "/company", "/admin"]}>
          <User />
        </Route>
        <Suspense fallback={<div className='loading-div'></div>}>
          <Route>
            <Redirect to='/login' />
            <Login />
          </Route>
        </Suspense>
      </Switch>
    )
  }

  const getSidebar = () => {
    return (
      <Suspense fallback={<></>}>
        <Switch>
          <Route path='/customer'>
            <CustomerSidebar />
          </Route>
          <Route path='/company'>
            <CompanySidebar />
          </Route>
          <Route path='/admin'>
            <AdminSidebar />
          </Route>
        </Switch>
      </Suspense>
    )
  }

  const getHeader = () => {
    return (
      <Route path={["/customer", "/company", "/admin"]}>
        <Header />
      </Route>
    )
  }

  return (
    <Router history={history}>
      <div className="App">
        {getHeader()}
        {getSidebar()}
        {getContent()}
        <Footer />
      </div>
    </Router >
  );

}

export default App;
