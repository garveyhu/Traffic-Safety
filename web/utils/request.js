import axios from 'axios'

// create an axios instance: <https://axios-http.com/docs/instance>
const service = axios.create({
    baseURL: 'http://192.168.2.2:4000',
    timeout: 10 * 1000
})

// request interceptor
service.interceptors.request.use(
    config => {
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

service.interceptors.response.use(
    response => {
        return response
    },
    error => {
        return Promise.reject(error)
    }
)

export default service