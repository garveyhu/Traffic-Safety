<!-- 登录页面 -->
<template>
	<view>
		<view class="img-a">
			<view class="t-b">
				你好
				<br />
				欢迎使用执勤无忧
			</view>
		</view>
		<view class="login-view" style="">
			<view class="t-login">
				<form class="cl">
					<view class="t-a">
						<text class="txt">账号</text>
						<input type="text" name="username" placeholder="请输入您的账号" maxlength="11" v-model="username" />
					</view>
					<view class="t-a">
						<text class="txt">密码</text>
						<input type="password" name="password" maxlength="18" placeholder="请输入您的密码" v-model="password" />
					</view>
					<button @tap="login()">登 录</button>
					<view class="reg" @tap="reg()">注 册</view>
				</form>
				<view class="t-f"><text>—————— 第三方账号登录 ——————</text></view>
				<view class="t-e cl">
					<view class="t-g" @tap="wxLogin()"><image src="@/static/wx.png"></image></view>
					<view class="t-g" @tap="qqLogin()"><image src="@/static/qq.png"></image></view>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
export default {
	data() {
		return {
			username: '', //账号
			password: '' , //密码
		};
	},
	onLoad() {
	},
	methods: {
		//当前登录按钮操作
		login() {
			var that = this;
			if (!that.username) {
				uni.showToast({ title: '请输入您的账号', icon: 'none' });
				return;
			}
			if (!that.password) {
				uni.showToast({ title: '请输入您的密码', icon: 'none' });
				return;
			}
			// POST API
			uni.request({
				url: 'https://cn.archeruu.com/traffic/user/vertifyUser',
				method: 'POST',
				data: {
					username: this.username,
					password: this.password,
				},
				success: (res) => {
					if (res.data.success){
						uni.setStorageSync('userInfo', res.data.data)
						console.log(uni.getStorageSync('userInfo'))
						uni.switchTab({
							url: '/pages/tabbar/map/map'
						})
					} else {
						console.log(res.data)
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
		//注册按钮点击
		reg() {
			uni.navigateTo({
				url: '/pages/login/register'
			})
			// uni.showToast({ title: '注册跳转', icon: 'none' });
		},
		//等三方微信登录
		wxLogin() {
			uni.showToast({ title: '敬请期待', icon: 'none' });
		},
		//第三方QQ登录
		qqLogin() {
			uni.showToast({ title: '敬请期待', icon: 'none' });
		}
	}
};
</script>
<style>
.txt {
	font-size: 32rpx;
	font-weight: bold;
	color: #333333;
}
.img-a {
	width: 100%;
	height: 450rpx;
	background-image: url(/static/head.png);
	background-size: 100%;
}
.reg {
	font-size: 28rpx;
	color: #fff;
	height: 90rpx;
	line-height: 90rpx;
	border-radius: 50rpx;
	font-weight: bold;
	background: #f5f6fa;
	color: #000000;
	text-align: center;
	margin-top: 30rpx;
}

.login-view {
	width: 100%;
	position: relative;
	margin-top: -120rpx;
	background-color: #ffffff;
	border-radius: 8% 8% 0% 0;
}

.t-login {
	width: 600rpx;
	margin: 0 auto;
	font-size: 28rpx;
	padding-top: 80rpx;
}

.t-login button {
	font-size: 28rpx;
	background: #2796f2;
	color: #fff;
	height: 90rpx;
	line-height: 90rpx;
	border-radius: 50rpx;
	font-weight: bold;
}

.t-login input {
	height: 90rpx;
	line-height: 90rpx;
	margin-bottom: 50rpx;
	border-bottom: 1px solid #e9e9e9;
	font-size: 28rpx;
}

.t-login .t-a {
	position: relative;
}

.t-b {
	text-align: left;
	font-size: 42rpx;
	color: #ffffff;
	padding: 130rpx 0 0 70rpx;
	font-weight: bold;
	line-height: 70rpx;
}

.t-login .t-c {
	position: absolute;
	right: 22rpx;
	top: 22rpx;
	background: #5677fc;
	color: #fff;
	font-size: 24rpx;
	border-radius: 50rpx;
	height: 50rpx;
	line-height: 50rpx;
	padding: 0 25rpx;
}

.t-login .t-d {
	text-align: center;
	color: #999;
	margin: 80rpx 0;
}

.t-login .t-e {
	text-align: center;
	width: 250rpx;
	margin: 80rpx auto 0;
}

.t-login .t-g {
	float: left;
	width: 50%;
}

.t-login .t-e image {
	width: 50rpx;
	height: 50rpx;
}

.t-login .t-f {
	text-align: center;
	margin: 150rpx 0 0 0;
	color: #666;
}

.t-login .t-f text {
	margin-left: 20rpx;
	color: #aaaaaa;
	font-size: 27rpx;
}

.t-login .uni-input-placeholder {
	color: #aeaeae;
}

.cl {
	zoom: 1;
}

.cl:after {
	clear: both;
	display: block;
	visibility: hidden;
	height: 0;
	content: '\20';
}
</style>
