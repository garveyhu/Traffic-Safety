<template>
	<view>
		<!-- ##########导航栏########### -->
		<view class="custom-nav">
			<view class="custom-nav-left"><u-icon name="arrow-left" @click="uniNavigateBack"></u-icon></view>
			<view class="custom-nav-title">执勤任务</view>
			<view class="custom-nav-right" @tap="updateTaskInfo">保存</view>
		</view>

		<!-- $$$$$$$$$$信息详情列表$$$$$$$$$$ -->
		<view>
			<view class="pages">
				<view class="child_page">
					<view class="child_page_line">
						<text style="flex-grow: 1;">经度</text>
						<text style="flex-shrink: 0;">{{taskInfo.longitude}}</text>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line">
						<text style="flex-grow: 1;">维度</text>
						<text style="flex-shrink: 0;">{{taskInfo.latitude}}</text>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line">
						<text style="flex-grow: 1;">任务地址</text>
						<text style="flex-shrink: 0;">{{ truncateString(taskInfo.markerAddress, 13) }}</text>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line" @click="editTaskName">
						<text style="flex-grow: 1;">任务名</text>
						<text style="flex-shrink: 0;">{{ taskInfo.taskName ? truncateString(taskInfo.taskName, 12) : '' }}</text>
						<u-icon name="arrow-right"></u-icon>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line" @click="editTaskDetails">
						<text style="flex-grow: 1;">任务详情</text>
						<text style="flex-shrink: 0;">{{ taskInfo.taskDetails ? truncateString(taskInfo.taskDetails, 12) : '' }}</text>
						<u-icon name="arrow-right"></u-icon>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line" @click="editTaskStartTime">
						<text style="flex-grow: 1;">任务开始时间</text>
						<text style="flex-shrink: 0;">{{ taskInfo.startTime ? formatDateTime(taskInfo.startTime) : '' }}</text>
						<u-icon name="arrow-right"></u-icon>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line" @click="editTaskEndTime">
						<text style="flex-grow: 1;">任务结束时间</text>
						<text style="flex-shrink: 0;">{{ taskInfo.endTime ? formatDateTime(taskInfo.endTime) : '' }}</text>
						<u-icon name="arrow-right"></u-icon>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line">
						<text style="flex-grow: 1;">最后更新人</text>
						<text style="flex-shrink: 0;">{{ taskInfo.creatorName ? taskInfo.creatorName : '' }}</text>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line">
						<text style="flex-grow: 1;">最后更新时间</text>
						<text style="flex-shrink: 0;">{{ taskInfo.createTime ? taskInfo.createTime : '' }}</text>
					</view>
				</view>
			</view>
		</view>
		<!--*************popup*****************-->
		<!-- 任务名 -->
		<u-popup :show="showEditTaskName" mode="bottom" @close="closeEditTaskName" @open="openEditTaskName" :round="20">
			<view class="u_popup">
				<u--textarea class="u_textarea_autoHeight" v-model="taskInfo.taskName" placeholder="请输入任务名" autoHeight
					:focus="true" :adjustPosition="false"></u--textarea>
			</view>
		</u-popup>
		<!-- 任务详情 -->
		<u-popup :show="showEditTaskDetails" mode="bottom" @close="closeEditTaskDetails" @open="openEditTaskDetails"
			:round="20">
			<view class="u_popup">
				<u--textarea class="u_textarea_autoHeight" v-model="taskInfo.taskDetails" placeholder="请输入任务详情"
					autoHeight :focus="true" :adjustPosition="false"></u--textarea>
			</view>
		</u-popup>
		<!-- 任务开始时间 -->
		<u-datetime-picker :show="showEditTaskStartTime" v-model="taskInfo.startTime" mode="datetime" @cancel="showEditTaskStartTime = false" @confirm="showEditTaskStartTime = false"></u-datetime-picker>
		<!-- 任务结束时间 -->
		<u-datetime-picker :show="showEditTaskEndTime" v-model="taskInfo.endTime" mode="datetime" @cancel="showEditTaskEndTime = false" @confirm="showEditTaskEndTime = false"></u-datetime-picker>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				// $$$$$$$$$$信息详情列表$$$$$$$$$$
				taskInfo: {},

				// *************popup*****************
				showEditTaskName: false,
				showEditTaskDetails: false,
				showEditTaskStartTime: false,
				showEditTaskEndTime: false,
			}
		},
		onLoad(options) {
			this.taskInfo = JSON.parse(options.taskInfo)
		},
		methods: {
			// tools
			getCurrentDateTimeString() {
				let date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				month = month < 10 ? '0' + month : month;
				let day = date.getDate();
				day = day < 10 ? '0' + day : day;
				let hour = date.getHours();
				hour = hour < 10 ? '0' + hour : hour;
				let minute = date.getMinutes();
				minute = minute < 10 ? '0' + minute : minute;
				let second = date.getSeconds();
				second = second < 10 ? '0' + second : second;
				return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
			},
			formatDateTime(timestamp) {
			  if (!timestamp) return ''; 
			  const date = new Date(timestamp);
			  const year = date.getFullYear();
			  const month = ('0' + (date.getMonth() + 1)).slice(-2);
			  const day = ('0' + date.getDate()).slice(-2);
			  const hour = ('0' + date.getHours()).slice(-2);
			  const minute = ('0' + date.getMinutes()).slice(-2);
			  const second = ('0' + date.getSeconds()).slice(-2);
			  return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
			},
			truncateString(str, sum) {
			  if (str.length > sum) {
			    str = str.substring(0, sum-1) + '...';
			  }
			  return str;
			},


			// ##########导航栏###########
			uniNavigateBack() {
				uni.navigateBack()
			},
			updateTaskInfo() {
				this.taskInfo.creatorName = uni.getStorageSync('userInfo').username
				this.taskInfo.createTime = this.getCurrentDateTimeString()
				// POST API
				uni.request({
					url: 'https://cn.archeruu.com/traffic/taskInfo/updateTaskById',
					method: 'POST',
					data: this.taskInfo,
					success: (res) => {
						if (res.data.success) {
							uni.showToast({
								title: '保存成功',
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

			// *************popup*****************
			editTaskName() {
				this.showEditTaskName = true
			},
			closeEditTaskName() {
				this.showEditTaskName = false
			},
			openEditTaskName() {},
			editTaskDetails() {
				this.showEditTaskDetails = true
			},
			closeEditTaskDetails() {
				this.showEditTaskDetails = false
			},
			openEditTaskDetails() {},
			editTaskStartTime() {
				this.showEditTaskStartTime = true
			},
			editTaskEndTime() {
				this.showEditTaskEndTime = !this.showEditTaskEndTime
			},
		}
	}
</script>

<style lang="scss" scoped>
	/* ##########导航栏########### */
	.custom-nav {
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 88rpx;
		padding: 50rpx 40rpx 0rpx 40rpx;
		background-color: #fff;
		box-shadow: 0 0 20rpx rgba(0, 0, 0, 0.1);
	}

	.custom-nav-title {
		padding-left: 20rpx;
		font-size: 32rpx;
	}

	.custom-nav-right {
		font-size: 30rpx;
		color: #007aff;
	}

	/* $$$$$$$$$$信息详情列表$$$$$$$$$$ */
	.Horizontal_line {
		height: 0.1px;
		background-color: #dbdbe2
	}

	.pages {
		background-color: #f5f5fa;
		padding: 20rpx;
	}

	.child_page {
		background-color: #ffffff;
		padding: 20rpx;
		margin-top: 30rpx;
		border-radius: 20rpx;
	}

	.child_page_line {
		padding: 30rpx 15rpx 30rpx 15rpx;
		display: flex;
		justify-content: space-between;
	}

	/* *************popup***************** */
	.u_popup {
		height: 410px;
	}

	.u_textarea_autoHeight {
		padding: 20px 15px 18px 15px;
	}
</style>