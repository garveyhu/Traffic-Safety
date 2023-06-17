<template>
	<view class="content">
		<view class="homeworklist u-skeleton">
			<view class="list" v-for="(item,index) in list" :key="index">
				<view class="left u-flex-col" style="height: 180rpx;" @click="taskLeftClick(item)">
					<view class="title u-line-2 u-skeleton-fillet" style="align-items: flex-start">
						{{item.title}}
					</view>
					<view class="foot u-flex">
						<text :style="'height: 30rpx;color: '+textColor"
							class="u-flex-3 u-skeleton-fillet">{{item.text}}</text>
						<text style="float: right;margin-right: 10rpx;height: 30rpx;"
							class="u-flex-3 u-skeleton-fillet">{{item.subTitle}}</text>
					</view>
				</view>
				<view class="right u-flex-col u-skeleton-fillet"
					style="height: 130rpx;justify-content: space-around;align-items: baseline; padding-top: 25px;">
					<view class="u-skeleton-fillet">
						<u-tag :text="item.tab.text" mode="dark" shape="circle" :bg-color="item.tab.color"
							v-if="item.tab" @click="taskRightClick(item)" />
					</view>
				</view>
			</view>
		</view>
		<u-skeleton :loading="loading" :animation="true"></u-skeleton>
	</view>
</template>

<script>
	import {
		none
	} from 'ol/centerconstraint'
	export default {
		onPullDownRefresh() {
			this.getTaskList()
			uni.showToast({
				title: '已获取最新数据',
				icon: 'none'
			})
			uni.stopPullDownRefresh(); // 停止下拉刷新
		},
		data() {
			return {
				list: [],
				loading: false,
				textColor: '#cb3642',
			}
		},
		onLoad() {
			this.getTaskList()
		},
		methods: {
			getTaskList() {
				uni.request({
					url: 'https://cn.archeruu.com/traffic/taskInfo/getTaskList',
					method: 'GET',
					success: (res) => {
						if (res.data.success) {
							let taskInfos = res.data.data;
							let promiseArr = taskInfos.map(obj => this.transformObject(obj));
							Promise.all(promiseArr)
								.then((taskInfos) => {
									console.log(taskInfos);
									this.list = taskInfos;
								})
								.catch((err) => {
									console.error(err);
								});
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
			taskLeftClick(item) {
				let taskInfos = JSON.stringify(this.reverseTransformObject(item))
				console.log(taskInfos)
				uni.navigateTo({
					url: '/pages/listInfo/taskInfo?taskInfo=' + taskInfos
				})
			},
			taskRightClick(item) {
				console.log('点击标签跳转')
				uni.reLaunch({
				  url: '/pages/tabbar/map/map?taskInfo=' + JSON.stringify(this.reverseTransformObject(item)) + '&random=' + Math.random()
				})
			},



			transformObject(obj) {
				const apiUrl = 'https://cn.archeruu.com/traffic/chore/getTag';
				const params = {
					content: obj.taskDetails
				};
				return new Promise((resolve, reject) => {
					uni.request({
						url: apiUrl,
						data: params,
						success: (res) => {
							if (res.data.success) {
								obj.tab = {
									text: res.data.data.substr(0, 2),
									color: '#9acedc'
								};
								obj.text = obj.taskName;
								obj.title = obj.taskDetails;
								obj.subTitle = obj.createTime;
								delete obj.taskName;
								delete obj.taskDetails;
								delete obj.createTime;
								resolve(obj);
							} else {
								reject(res.data.message);
							}
						},
						fail: (err) => {
							reject(err);
						}
					});
				});
			},
			reverseTransformObject(obj) {
				if(obj.tab) {
					obj.taskName = obj.text;
					obj.taskDetails = obj.title;
					obj.createTime = obj.subTitle;
					delete obj.text;
					delete obj.title;
					delete obj.subTitle;
					delete obj.tab;
					return obj;
				}else {
					return obj
				}
			},
		},
	}
</script>

<style lang="scss">
	.homeworklist {
		width: 100%;
		margin: 0rpx 0;


	}

	.left {
		width: 70%;
		height: 160rpx;
		padding: 15rpx 0rpx 10rpx 30rpx;
		flex-direction: column;
		position: absolute;
		justify-content: center;

		.title {
			width: 90%;
			font-size: 28rpx;
			color: $uni-text-color;
			padding: 5rpx 5rpx;
			height: 100rpx;

		}

		.foot {
			width: 75%;
			padding-top: 15rpx;
			padding-bottom: 5rpx;
			padding-left: 2rpx;
			font-size: 20rpx;
			color: $uni-text-color-grey;
		}

	}

	.right {
		width: 14%;
		float: right;
		position: relative;
		flex-direction: column;
		text-align: center;
		line-height: 130rpx;
		margin-right: 15rpx;
		height: 160rpx;

		.u-icon {}
	}

	.list {
		box-shadow: 0upx 0upx 8upx #eee;
		border-radius: 20rpx;
		height: 200rpx;
		width: 90%;
		margin-left: 5%;
		padding: 10upx;
		margin-bottom: 10rpx;

	}
</style>