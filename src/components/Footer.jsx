import React from 'react'
import { MDBCol, MDBContainer, MDBRow, MDBFooter } from "mdbreact";
export default function Footer() {
  return ( 
    <div>
    <div class="fixed-bottom">
    <MDBFooter color="blue" className="font-small pt-4 mt-4">
  <MDBContainer fluid className="text-center text-md-left">
    <MDBRow>
      <MDBCol md="6">
        <h5 className="title">Devcom</h5>
        <p>
        “Trust is built with consistency.” 
        </p>
      </MDBCol>
    </MDBRow>
  </MDBContainer>
  <div className="footer-copyright text-center py-3">
    <MDBContainer fluid>
      &copy; {new Date().getFullYear()} Copyright: <a> devcom.com </a>
    </MDBContainer>
  </div>
</MDBFooter>
    </div>
    </div>
  )
}
