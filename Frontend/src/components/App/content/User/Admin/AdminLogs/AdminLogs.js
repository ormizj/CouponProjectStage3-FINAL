import AdminLogActionbar from "./AdminLogActionbar/AdminLogActionbar";
import './admin-logs.css'
import { useEffect, useState } from "react";
import AdminService from "../../../../../../services/user-services/AdminService";
import AuthenticationService from "../../../../../../services/AuthenticationService";

export default function AdminLogs() {

    const [logs, setLogs] = useState([{
        id: '',
        entity: '',
        action: '',
        executiveEntity: '',
        executiveId: '',
        entityId: '',
        oldAttributes: '',
        newAttributes: '',
        date: '',
        time: ''
    }])

    const getLogsList = () => {
        AdminService
            .getLogs().then(
                result => {
                    setLogs(result.data)
                },
                error => {
                    try {
                        if (error.response.data.response) {
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

    useEffect(() => {
        getLogsList()
    }, [])

    const getLogs = () => {
        if (logs[0].id === '')
            return
        return (logs.map((stream, index) => {
            return (
                <>
                    <tr >
                        <input id={stream.id} className='AdminLogs-field-checkbox' />
                        <td></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-id-label" id='AdminLogs-table-id-label' title={`ID: ${stream.id}`}>{stream.id}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`EXECUTIVE ID: ${stream.executiveId}`} id='AdminLogs-table-id-label'>{stream.executiveId}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`EXECUTIVE MODEL: ${stream.executiveEntity}`} id='AdminLogs-table-enum-label'>{stream.executiveEntity}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`EXECUTIVE ACTION: ${stream.action}`} id='AdminLogs-table-enum-label'>{stream.action}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`MODEL ID: ${stream.entityId}`} id='AdminLogs-table-id-label'>{stream.entityId}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`MODEL TYPE: ${stream.entity}`} id='AdminLogs-table-enum-label'>{stream.entity}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`OLD ATTRIBUTES: ${stream.oldAttributes}`}>{stream.oldAttributes}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`NEW ATTRIBUTES: ${stream.newAttributes}`}>{stream.newAttributes}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`DATE: ${stream.date}`}>{stream.date}</label></td>
                        <td><label htmlFor={stream.id} className="AdminLogs-table-text-label" title={`TIME: ${stream.time}`}>{stream.time}</label></td>
                    </tr>
                    <tr className="AdminLogs-table-tr-seperator"> </tr>
                </>
            )
        }))
    }

    return (
        <div className='AdminLogs'>
            <div className='AdminLogs'>
                <AdminLogActionbar getLogsList={getLogsList} />
                <table className='AdminLogs-table'>
                    <tbody>
                        <tr>
                            <th></th>
                            <th className='AdminLogs-table-header' id='AdminLogs-table-header-id' width='5%' title='ID'>ID</th>
                            <th className='AdminLogs-table-header' width='5%' title='Executive ID'>E. ID</th>
                            <th className='AdminLogs-table-header' width='10%' title='Executive Model'>E. Model</th>
                            <th className='AdminLogs-table-header' width='10%' title='Executive Action'>E. Action</th>
                            <th className='AdminLogs-table-header' width='5%' title='Model ID'>M. ID</th>
                            <th className='AdminLogs-table-header' width='10%' title='Model Type'>M. Type</th>
                            <th className='AdminLogs-table-header' width='15.5%' title='Old Attributes'>Old Attributes</th>
                            <th className='AdminLogs-table-header' width='15.5%' title='New Attributes'>New Attributes</th>
                            <th className='AdminLogs-table-header' width='12%' title='Date'>Date</th>
                            <th className='AdminLogs-table-header' width='12%' title='Time'>Time</th>
                        </tr>
                        {getLogs()}
                    </tbody>
                </table>
            </div >
        </div >
    )

}