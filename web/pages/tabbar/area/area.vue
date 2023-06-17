<template>
	<view>
		<!-- 		##########导航栏###########
		<view class="custom-nav">
			<view class="custom-nav-left"><u-icon name="arrow-left" @click="uniNavigateBack"></u-icon></view>
			<view class="custom-nav-title">新闻资讯</view>
			<view class="custom-nav-right"><u-icon name="arrow-right" @click="uniNavigateFront"></u-icon></view>
		</view> -->
		<!-- <web-view :src="newsUrl" :webview-styles="{'height': `calc(100vh - ${navHeight}rpx)`}" ref="webview" :update-title="false" :fullscreen="false">
		</web-view> -->
		 <view class="header">页面头部</view>
		<web-view :src="newsUrl" ref="webview" :fullscreen="false">
		</web-view>
		<!-- 固定在页面底部的块级元素 -->
		    <view class="fixed-block">我是固定在页面底部的块级元素</view>
	</view>
</template>

<script>
	export default {
		onBackPress(e) {
			this.$scope
				.$getAppWebview()
				.children()[0]
				.back();
			return true;
		},
		onUnload() {
			this.uniNavigateBack()
		},
		data() {
			return {
				newsUrl: "https://www.zgjtb.com/m/index.html",
				// newsUrl: "https://www.mot.gov.cn/jiaotongyaowen/202305/t20230508_3823119.html",
				// height: 0,
				navHeight: 88,
			};
		},
		mounted() {
			uni.getSystemInfo({
				success: (res) => {
					this.height = res.windowHeight;
				},
			});
		},
		methods: {
			uniNavigateBack() {
				this.$refs.webview.goBack(-1)
			},
			uniNavigateFront() {
				this.$refs.webview.goForward(1);
			},
			navigateBack() {
				// 在这里可以自定义返回事件
				uni.navigateBack({
					delta: 1, // 返回的页面数，如果 delta 大于现有页面数，则返回到首页
				});
			},
		}
	};
</script>

<style lang="scss" scoped>
	// 页面头部样式
	.header {
	  height: 100rpx;
	  background-color: #fff;
	}
	
	// 固定在页面底部的块级元素样式
	.fixed-block {
	  position: fixed;
	  bottom: 0;
	  left: 0;
	  right: 0;
	  height: 100rpx;
	  background-color: #ccc;
	}
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
		padding-left: 10rpx;
		font-size: 32rpx;
	}

	.custom-nav-right {
		font-size: 30rpx;
		color: #007aff;
	}
</style>