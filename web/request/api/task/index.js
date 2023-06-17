import axios from '@/utils/request'

const task = {
	getTaskList(query) {
		return axios.get('/user/getUserList')
	}
	
}

export default task