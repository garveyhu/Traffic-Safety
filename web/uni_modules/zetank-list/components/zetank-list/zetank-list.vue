<template>
	<view class="u-skeleton">
		<view class="list " @click="clicklist" v-for="(item,index) in list":key='index'>
			<view class="list-cell ">
				<view style="height: 200rpx;">
					<view >
						<image class="list-image u-skeleton-fillet" v-bind:src="item.imgurl || ''" mode="aspectFit" style="background-color: #ffffff;"></image>
					</view>
					<view style="height: 100%;display: flex;flex-wrap: wrap;align-content: space-around">
						<view class="list-tile u-skeleton-fillet u-flex"style="width: 320rpx;height: 100rpx;">
							<view class="u-line-2">
								{{item.title || ''}}
							</view>
						</view>
						<view class="list-content" >
							<view v-if="showsubTitle" class="baoming u-skeleton-fillet" style="width: 200rpx;height: 50rpx;">{{item.subTitle || ''}}</view>
							<view v-if="showtab" class="u-skeleton-fillet" style="height: 50rpx;width: 200rpx;">
								<u-tag :text=" item.tab.text" mode="dark" shape="circle" :bg-color="item.tab.color" v-if="item.tab" />
							</view>
						</view>
					</view>
				</view>
			</view>
			
		</view>
		
			<u-skeleton :loading="loading" :animation="true"></u-skeleton>
		
		
		
	</view>
</template>

<script>
	
	export default {
		name:"zetank-list-news",
		data() {
			return {
				
			};
		},
		props:{
			list:{
				style:Array,
				default(){
					return null
					/* 
					 [{
						 title:'标题',
						 subTitle:'副标题',
						 imgurl:'',
						 tab:{
							 text:'标签',
							 color:''
						 }
					 }]
					 
					 */
				}
			},
			loading:{
				style:Boolean,
				default:true
			},
			showsubTitle:{
				style:Boolean,
				default:true
			},
			showtab:{
				style:Boolean,
				default:true
			},
		},
		async created() {
			console.log('list',this.list);
		},
		methods:{
			clicklist(){
				this.$emit("clicklist")
			}
		}
	}
</script>

<style lang="scss">
	.list{
		box-shadow: 0upx 0upx 10upx  #eee;
		width: 100%;
		
	}
	.list-cell{
		background-color: #ffffff;
		overflow: hidden;
		
	}
	.list-image{
		width: 300rpx;
		height: 168rpx;
		float: left;
		margin: 20rpx;
		margin-right: 50rpx;
		border-radius: 20rpx;
		background-color: #ededed;
	}
	.list-tile{

		margin-bottom: 10rpx;
		margin-top: 20rpx;
		margin-right: 20rpx;
		color: $uni-text-color;
	}
	
	.list-content{
		width: 100%;
		display: flex;
		flex-direction:row;
		padding-right:50rpx;
		justify-content:space-between;
		padding-top: 5rpx;
		.baoming{
			color: $uni-text-color-grey;
			font-size: 18rpx;
			line-height: 50rpx;
			text-align: left;
		}

	}
	.content{
		width: 100%;
		background-color: #f8f8f8;
		
	}
	
	.userinfo-avatar {
		width: 128rpx;
		height: 128rpx;
		margin: 20rpx;
		border-radius: 50%;
	}
</style>
