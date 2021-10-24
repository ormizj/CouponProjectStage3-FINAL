import { useEffect, useRef } from 'react'
import './footer.css'
import history from '../history'
import { withRouter } from 'react-router'


function Footer() {

    const footerRef = useRef()

    useEffect(() => {
        if (history.location.pathname.includes("/login"))
            footerRef.current.id = "Footer-fixed"
        else
            footerRef.current.id = "Footer-not-fixed"
    })

    return (
        <div className="Footer" id="Footer-not-fixed" ref={footerRef}>
            <a className='Footer-link' href="http://localhost:8080/swagger" target="_blank" rel="noopener noreferrer" title="Swagger API Documentation">
                Swagger API Documentation
            </a>
            <label className='Footer-rights' onClick={() => alert('All rights reserved')} title='©Kaspi Industry LTD'>©Kaspi Industry LTD</label>
            <a className='Footer-link' href="https://github.com/spiderpig60/CouponProjectStage3-FINAL" target="_blank" rel="noopener noreferrer" title="Our project's Github">
                Our project's Github
            </a>
            <label className='Footer-shove'></label>
        </div>
    )

}

export default withRouter(Footer)