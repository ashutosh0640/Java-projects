import { useState, useEffect } from 'react'
import './App.css'

function App() {

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


  useEffect(() => {
    const fetchData = async () => {
      try {
        const username = 'user01';
        const password = 'uPassword';

        const credentials = btoa(`${username}:${password}`); // Base64 encoding of username:password

        const response = await fetch('http://localhost:8080/test', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${credentials}`, // Include the Basic Auth header
            'Content-Type': 'application/json'
          }
        });
        if (!response.ok) {
          throw new Error("Network response was not OK");
        }

        const res = await response.json();
        setData(res);

      } catch (err) {
        setError(err.message);
        console.log(err)
      } finally {
        setLoading(false);
      }
    }
    fetchData();
  }, [])


  console.log(error);
  return (
    <>
      <h1>
        Hello world!
      </h1>
      {
        loading ? (
          <p>Loading...</p>
        ) : (
          <div>
            <h2>Data from API</h2>
            <div className={`${data.length == 0 ? "text-red-600" : "text-green-600"}`}>{(data.length == 0 ? error : data)}</div>
          </div>)
      }

      <button className=' bg-gray-300 px-3 py-1 rounded-md shadow-lg hover:bg-green-400' >Fetch Data</button>
    </>
  )
}

export default App
