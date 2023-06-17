<template>
	<view style="padding: 10px;">
		<u--textarea v-model="message" placeholder="反馈给作者"></u--textarea>
		<u-button text="发送" @click="sendMe"></u-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				message: '',
			}
		},
		methods: {
			sendMe() {
				uni.request({
					url: 'https://cn.archeruu.com/traffic/chore/sendMe',
					method: 'Get',
					data: {
						message: this.message
					},
					success: (res) => {
						if (res.data.success) {
							uni.showToast({
								title: '发送成功，感谢反馈！',
								icon: 'none'
							})
						} else {
							uni.showToast({
								title: res.data.msg,
								icon: 'none'
							})
						}
					},
					fail: () => {
						uni.showToast({
							title: '获取数据失败',
							icon: 'none'
						})
					}
				})
			},
		}
	}
</script>

<style>
</style>