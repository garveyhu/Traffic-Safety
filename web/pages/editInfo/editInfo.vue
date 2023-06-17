<template>
	<view>
		<!-- ##########导航栏########### -->
		<view class="custom-nav">
			<view class="custom-nav-left"><u-icon name="arrow-left" @click="uniNavigateBack"></u-icon></view>
			<view class="custom-nav-title">编辑资料</view>
			<view class="custom-nav-right" @tap="updateUserInfo">保存</view>
		</view>

		<!-- $$$$$$$$$$信息详情列表$$$$$$$$$$ -->
		<view>
			<view class="pages">
				<view class="child_page">
					<view class="child_page_line">
						<text style="flex-grow: 1;">头像</text>
						<view style="flex-shrink: 0;"><u-avatar :src="avatar"></u-avatar></view>
						<u-icon name="arrow-right"></u-icon>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line">
						<text style="flex-grow: 1;">用户名</text>
						<text style="flex-shrink: 0;">{{userInfo.username}}</text>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line" @click="editPhone">
						<text style="flex-grow: 1;">手机号</text>
						<text style="flex-shrink: 0;">{{userInfo.phone}}</text>
						<u-icon name="arrow-right"></u-icon>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line" @click="editEmail">
						<text style="flex-grow: 1;">邮箱</text>
						<text style="flex-shrink: 0;">{{userInfo.email}}</text>
						<u-icon name="arrow-right"></u-icon>
					</view>
					<view class="Horizontal_line "></view>
					<view class="child_page_line">
						<text style="flex-grow: 1;">角色</text>
						<text style="flex-shrink: 0;">{{userInfo.role}}</text>
					</view>
				</view>
			</view>
		</view>
		<!--*************popup*****************-->
		<!-- 手机号 -->
		<u-popup :show="showEditPhone" mode="bottom" @close="closeEditPhone" @open="openEditPhone" :round="20">
			<view class="u_popup">
				<u--textarea class="u_textarea_autoHeight" v-model="userInfo.phone" placeholder="请输入手机号" autoHeight
					:focus="true" :adjustPosition="false"></u--textarea>
			</view>
		</u-popup>
		<!-- 邮箱 -->
		<u-popup :show="showEditEmail" mode="bottom" @close="closeEditEmail" @open="openEditEmail" :round="20">
			<view class="u_popup">
				<u--textarea class="u_textarea_autoHeight" v-model="userInfo.email" placeholder="请输入邮箱" autoHeight
					:focus="true" :adjustPosition="false"></u--textarea>
			</view>
		</u-popup>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				// $$$$$$$$$$信息详情列表$$$$$$$$$$
				userInfo: {},
				avatar: '/static/duck.png',

				// *************popup*****************
				showEditPhone: false,
				showEditEmail: false,
			}
		},
		onLoad() {
			this.userInfo = uni.getStorageSync('userInfo')
		},
		methods: {
			// ##########导航栏###########
			uniNavigateBack() {
				uni.navigateBack()
			},
			updateUserInfo() {
				// POST API
				uni.request({
					url: 'https://cn.archeruu.com/traffic/user/updateUser',
					method: 'POST',
					data: this.userInfo,
					success: (res) => {
						if (res.data.success) {
							this.refreshUserInfo()
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
			refreshUserInfo() {
				uni.request({
					url: 'https://cn.archeruu.com/traffic/user/getUserById',
					method: 'GET',
					data: {
						id: this.userInfo.id
					},
					success: (res) => {
						if (res.data.success) {
							uni.setStorageSync('userInfo', res.data.data)
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
			editPhone() {
				this.showEditPhone = true
			},
			closeEditPhone() {
				this.showEditPhone = false
			},
			openEditPhone() {},
			editEmail() {
				this.showEditEmail = true
			},
			closeEditEmail() {
				this.showEditEmail = false
			},
			openEditEmail() {},
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