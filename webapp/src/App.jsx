import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import axios from 'axios'
import { Line } from 'react-chartjs-2'
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  TimeScale,
} from 'chart.js'
import 'chartjs-adapter-moment'
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  TimeScale
)

const CurrencyTable = ({ rates, selectCurrency }) => {
  return (
    <table className="card">
      <tr>
        <th>Currency</th>
        <th>Rate</th>
        <th>Date</th>
      </tr>
      {rates.map((rate) => (
        <tr
          key={`${rate.name} ${rate.date}`}
          onClick={() => selectCurrency(rate)}
          style={{ cursor: 'pointer' }}>
          <td>{rate.name} </td>
          <td>{rate.rate}</td> <td>{rate.date}</td>
        </tr>
      ))}
    </table>
  )
}

function App() {
  const [rates, setRates] = useState()

  const [selectedCurrency, selectCurrency] = useState({
    name: 'USD',
    rate: 1.0828,
    date: '2024-07-31',
  })
  const [history, setHistory] = useState()

  const [value, setValue] = useState(100)

  useEffect(() => {
    axios.get('http://localhost:8080/latest').then((response) => {
      console.log(response)
      setRates(response.data)
    })
  }, [])

  useEffect(() => {
    if (selectedCurrency) {
      axios
        .get(`http://localhost:8080/byName/${selectedCurrency.name}`)
        .then((response) => {
          console.log(response)
          setHistory({
            labels: response.data.map((d) => d.date),
            datasets: [
              {
                label: selectedCurrency.name,
                data: response.data.map((d) => d.rate),
                borderColor: '#60cd18',
              },
            ],
          })
        })
    }
  }, [selectedCurrency])

  return (
    <div style={{ display: 'flex', width: '100%', gap: '30px' }}>
      {rates && (
        <>
          <CurrencyTable
            rates={rates.slice(0, rates.length / 3)}
            selectCurrency={selectCurrency}
          />
          <CurrencyTable
            rates={rates.slice(rates.length / 3, (2 * rates.length) / 3)}
            selectCurrency={selectCurrency}
          />
          <CurrencyTable
            rates={rates.slice((2 * rates.length) / 3, rates.length)}
            selectCurrency={selectCurrency}
          />
        </>
      )}
      {history && (
        <div
          style={{
            minWidth: '50%',
            display: 'flex',
            flexDirection: 'column',
            gap: '30px',
          }}>
          <div className="card">
            <h3>Currency calculator</h3>
            <div
              style={{
                display: 'flex',
                justifyContent: 'center',
                width: '100%',
                gap: '10px',
              }}>
              <input
                value={value}
                onChange={(e) => setValue(e.target.value)}
              />{' '}
              <span style={{ fontWeight: 'bold' }}>EUR</span>
              <span> = </span>
              <input
                value={value * selectedCurrency.rate}
                onChange={(e) =>
                  setValue(e.target.value / selectedCurrency.rate)
                }></input>{' '}
              <span>{selectedCurrency.name}</span>
            </div>
            <div style={{ marginTop: '10px' }}>
              <span style={{ color: 'gray' }}>
                1 EUR = {selectedCurrency.rate} {selectedCurrency.name}
              </span>
            </div>
          </div>
          <div className="card">
            <Line
              data={history}
              options={{
                scales: {
                  x: {
                    type: 'time',
                    time: {
                      unit: 'month',
                    },
                  },
                },
              }}
            />
          </div>
        </div>
      )}
    </div>
  )
}

export default App
