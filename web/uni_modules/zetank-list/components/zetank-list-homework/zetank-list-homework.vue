<template>
	<view class="content">
		<view class="homeworklist u-skeleton" >
			<view class="list" @click="clicklist" v-for="(item,index) in list":key="index">
				<view class="left u-flex-col" style="height: 180rpx;">
					<view class="title u-line-2 u-skeleton-fillet" style="align-items: flex-start">
						{{item.title}}
					</view>
					<view class="foot u-flex">
						<text :style="'height: 30rpx;color: '+textColor" class="u-flex-3 u-skeleton-fillet">{{item.text}}</text>
						<text style="float: right;margin-right: 10rpx;height: 30rpx;" class="u-flex-3 u-skeleton-fillet">{{item.subTitle}}</text>
					</view>
					
					</view>
				<view class="right u-flex-col u-skeleton-fillet"  style="height: 130rpx;justify-content: space-around;align-items: baseline; padding-top: 25px;">
					<view class="u-skeleton-fillet">
						<u-tag :text="item.tab.text" mode="dark" shape="circle" :bg-color="item.tab.color" v-if="item.tab" />
					</view>
					
				</view>
			</view>
		</view>
		<u-skeleton :loading="loading" :animation="true"></u-skeleton>
	</view>
</template>

<script>
	export default {
		name:"zetank-list-homework",
		data() {
			return {
				DoHWnum:"123",
			};
		},
		props:{
			list:{
				style:Array,
				default(){
					return null	 
					/* 
					 [{
					 		 title:'题标题题标题题标题题标题题标题题标题题标题题标题标题题标题题标题',
					 		 subTitle:'副标题',
					 		 text:'文字',
					 		 tab:{
					 			 text:'标签',
					 			 color:'#9acedc'
					 		 }
					 	 }]
					 */
					
				}
			},
			loading:{
				style:Boolean,
				default:true
			},
			textColor:{
				style:String,
				default:'#cb3642'
			}
		},
		methods:{
			clicklist(){
				this.$emit("clicklist")
			},

			
		},
		created() {
			
		}
	}
</script>

<style lang="scss">
	.homeworklist{
		width: 100%;
		margin:0rpx 0;
		
		
	}
	.left{
		width: 70%;
		height: 160rpx;
		padding: 15rpx 0rpx 10rpx 30rpx;
		flex-direction:column ;
		position: absolute;
		justify-content:center;	
		
		.title{
			width: 90%;
			font-size: 28rpx;
			color: $uni-text-color;
			padding: 5rpx 5rpx;
			height: 100rpx;
			
		}
		.foot{
			width: 75%;
			padding-top: 15rpx;
			padding-bottom: 5rpx;
			padding-left: 2rpx;
			font-size: 20rpx;
			color: $uni-text-color-grey;
		}
		
	}
	.right{
		width: 14%;
		float: right;
		position: relative;
		flex-direction:column ;
		text-align: center;
		line-height: 130rpx;
		margin-right: 15rpx;
		height: 160rpx;
		.u-icon{
			
		}
	}
	.list{
		box-shadow: 0upx 0upx 8upx #eee;
		border-radius: 20rpx;
		height: 200rpx;
		width: 90%;
		margin-left: 5%;
		padding: 10upx;
		margin-bottom: 10rpx;
		
	}	
</style>
