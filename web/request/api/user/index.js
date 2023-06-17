import axios from '@/utils/request'

const user = {
	getUserList(query) {
		return axios.get('/user/getUserList')
	}
	
}

export default user