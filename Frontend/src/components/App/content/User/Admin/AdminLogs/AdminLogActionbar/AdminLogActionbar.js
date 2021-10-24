import AuthenticationService from '../../../../../../../services/AuthenticationService'
import AdminService from '../../../../../../../services/user-services/AdminService'
import './../../../../../../../styles/action-sidebar.css'

export default function AdminLogActionbar(props) {

    const handleClearLogs = () => {
        AdminService
            .clearLogs().then(
                () => {
                    setTimeout(() => { alert("Logs cleared successfully.") }, 0)
                    props.getLogsList()
                },
                error => {
                    try {
                        if (error.response.data.response) {
                            setTimeout(() => { alert("There are not any logs.") }, 0)
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
        <div className='AdminLogActionbar'>
            <div className='action-sidebar'>
                <button className='action-sidebar-button' title='Clear Logs' onClick={handleClearLogs}>Clear Logs</button>
            </div >
        </div >
    )
}