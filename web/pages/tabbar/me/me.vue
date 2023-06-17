<template>
	<view class="page">
		<view class="top">
			<view class="background"></view>
		</view>
		<view class="user-card">
			<view class="card" @tap="editInfo()">
				<view class="top">
					<view class="userImage">
						<image src="/static/duck.png" style="width: 150rpx; height: 150rpx;"></image>
					</view>
				</view>
				<view class="bottom">
					<view class="left">
						<view class="user-text">{{username}}</view>
						<view class="user-phone">{{phone}}</view>
					</view>
					<view class="right flex-center">
						<u-icon class="icon" name="arrow-right"></u-icon>
					</view>
				</view>
			</view>
		</view>
		<view class="list-card">
			<view class="card">
				<view class="item item-bottom-solid" @tap="about">
					<view class="left flex-center">
						<image src="/static/about.png" class="image3"></image>
					</view>
					<view class="center">
						<text>关于</text>
					</view>
					<view class="right flex-center">
						<u-icon class="icon" name="arrow-right"></u-icon>
					</view>
				</view>
			</view>
			<view class="card">
				<view class="item item-bottom-solid" @tap="feedback">
					<view class="left flex-center">
						<image src="/static/feedback.png" class="image3"></image>
					</view>
					<view class="center">
						<text>反馈</text>
					</view>
					<view class="right flex-center">
						<u-icon class="icon" name="arrow-right"></u-icon>
					</view>
				</view>
			</view>
			<view class="card">
				<view class="item" @tap="updateVersion">
					<view class="left flex-center">
						<image src="/static/versionUpdate.png" class="image3"></image>
					</view>
					<view class="center">
						<text>版本更新</text>
					</view>
					<view class="right flex-center">
						<text style="flex-grow: 1;">v1.0.0</text>
						<u-icon class="icon" name="arrow-right"></u-icon>
					</view>
				</view>
			</view>
		</view>
		<view class="quit flex-center">
			<view class="btn flex-center" @tap="quitLogin()">
				退出登录
			</view>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				username: '',
				phone: '',
			}
		},
		onShow() {
			this.username = uni.getStorageSync('userInfo').username
			this.phone = uni.getStorageSync('userInfo').phone === null ? '未绑定手机号' : uni.getStorageSync('userInfo').phone
		},
		methods: {
			quitLogin() {
				uni.removeStorageSync('userInfo')
				uni.reLaunch({
					url: '/pages/login/login'
				})
			},
			editInfo() {
				uni.navigateTo({
					url: "/pages/editInfo/editInfo"
				})
			},
			updateVersion() {
				uni.showToast({
					title: "当前已是最新版本",
					icon: 'none'
				})
			},
			about() {
				uni.navigateTo({
					url: "/pages/appInfo/about"
				})
			},
			feedback() {
				uni.navigateTo({
					url: "/pages/appInfo/feedback"
				})
			}
		},
	}
</script>

<style lang="scss" scoped>
	.top {
		height: 250rpx;
		position: relative;

		.background {
			background-color: #5199ff;
			border-bottom-left-radius: 22px;
			border-bottom-right-radius: 22px;
			position: absolute;
			height: 180rpx;
			width: 100%;
		}
	}

	.icon {
		color: #96a1ae;
		font-size: 40rpx;
	}

	.user-card {
		height: 170rpx;
		padding: 0 15px;

		.card {
			position: relative;
			bottom: 62px;
			height: 250rpx;
			background-color: white;
			border-radius: 5px;

			.top {
				height: 30%;
				position: relative;

				.userImage {
					position: absolute;
					bottom: 24%;
					left: 10%;
					width: 150rpx;
					height: 150rpx;
					overflow: hidden;
					border-radius: 50%;
					border: 2px solid white;
				}
			}

			.bottom {
				display: flex;
				height: 70%;

				.left {
					width: 80%;
					height: 100%;
					position: relative;

					.user-text {
						width: 100%;
						font-size: 1.6em;
						padding-left: 80rpx;
						height: 50%;
					}

					.user-phone {
						color: #96a1ae;
						padding-left: 80rpx;
						height: 50%;
						width: 100%;
						font-size: 0.9em;
					}
				}

				.right {
					width: 20%;
					height: 50%;
				}
			}
		}
	}

	.list-card {
		padding: 0 15px;

		.card {
			border-radius: 5px;
			position: relative;
			background-color: white;
			border-radius: 5px;
			padding: 5px 5px;

			.item {
				display: flex;
				height: 120rpx;

				.left {
					width: 15%;

					image {
						width: 40rpx;
						height: 40rpx;
					}
				}

				.center {
					width: 65%;
					display: flex;
					justify-content: start;
					align-items: center;
					font-size: 1.0em;
					margin-left: -10px;
				}

				.right {
					width: 20%;
					justify-content: flex-end;
				}
			}
		}
	}

	.item-bottom-solid {
		border-bottom: 1px solid #d4d6da;
	}

	.quit {
		height: 95rpx;
		margin-top: 50px;

		.btn {
			background-color: #4f99ff;
			border-radius: 30px;
			width: 80%;
			color: white;
			font-size: 1.2em;
			height: 100%;
		}
	}

	.flex-center {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.image3 {
		width: 30px;
		height: 30px;
	}
</style>