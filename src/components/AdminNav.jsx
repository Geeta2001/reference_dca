import React from 'react'

export default function AdminNav() {
  return ( <>
      <nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">DevCom</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Remove feed</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Remove response</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Block/Unblock Developer</a>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <div class="float-left">
      <button type="button" class="btn btn-outline-primary">Log out</button>
    </div><br/>
      </ul>
    </div>
  </div>
</nav>
</>
  )
}

