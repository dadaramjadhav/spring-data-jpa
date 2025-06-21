import { useState } from 'react'
import axios from 'axios'
function App() {
  const [custList, setCustList] = useState([])

  const fetchCustomer = () => {
    axios
      .get('http://localhost:8080/')
      .then(res => { setCustList(res.data.content); console.log(res.data.content) })
      .catch(er => console.log(er.message))
  }

  return (
    <>
      <h2>Fetching customer</h2>
      <button onClick={fetchCustomer}>Fetch customers</button>
      <hr></hr>
      {
        // custList.map((c, in)=>(<h3></h3>))
        custList.map((c,i)=>(<h3>{c.id}</h3>))
      }
    </>
  )
}

export default App
