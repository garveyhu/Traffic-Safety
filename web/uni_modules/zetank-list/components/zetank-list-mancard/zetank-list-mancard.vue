<template>
	<view class="u-skeleton">
		<view class="list " @click="clicklist(item)" v-for="(item,index) in list":key='item._id'>
			<view class="list-cell">
				<view class="image">
					<view>
						<image mode="aspectFill" class="list-image u-skeleton-fillet" v-bind:src="item.imgurl || ''"></image>
					</view>
				</view>
				<view class="right">
					<view class="name">
						<text>{{item.name}}</text>
					</view>
					<view class="introduce u-line-2">
						<text>{{item.introduce}}</text>
					</view>
				</view>
			</view>
			<view class="footer" v-if="showtab">
				<view v-for="(tabitem,footerindex) in item.tab":key = "footerindex" class="cell" v-if="item.tab.length != 0">
					<u-icon :custom-prefix="tabitem.icon.customprefix" :name="tabitem.icon.name" :color="tabitem.color" size="20"></u-icon>
					{{tabitem.text}}
				</view>
			</view>
		</view>
		
			<u-skeleton :loading="loading" :animation="true"></u-skeleton>
		
		
		
	</view>
</template>

<script>
	
	export default {
		name:"zetank-list-mancard",
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
						 name:'名字',
						 introduce:'简介简介简介',
						 imgurl:'头像',
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
			showtab:{
				style:Boolean,
				default:false
			},
		},
		async created() {
			// console.log('list',this.list);
		},
		methods:{
			clicklist(item){
				this.$emit("clicklist",item)
			}
		}
	}
</script>

<style lang="scss">
	.list{
		width: 100%;
		box-shadow: 0upx 0upx 10upx  #eee;
		margin-bottom: 0px;
	}
	.list-cell{
		background-color: #ffffff;
		overflow: hidden;
		padding: 10rpx 20rpx;
		display: flex;
	}
	.image{
		height: 200rpx;
		width: auto;
	}
	.list-image{
		width: 150rpx;
		height: 150rpx;
		border-radius:100%;
		float: left;
		margin: 20rpx;
		margin-right: 50rpx;
		background-color: #ffffff;
	}
	.right{
		width: 70%;
		margin: 30rpx;
		.name{
			font-weight: bold;
			text-align: left;
			font-size: 36rpx;
			margin-top: 5rpx;
		}
		.introduce{
			color: #9c9c9c;
			text-align: left;
			font-size: 20rpx;
			margin-top: 15rpx;
		}
	}
	.footer{
		display: flex;
		justify-content: space-evenly;
		.cell{
			border: 1px solid #f6f6f6;
			width: 100%;
			justify-content: space-evenly;
			display: flex;
			padding: 10px 5px;
			color: #9c9c9c;
			font-size: 24rpx;
		}
	}
</style>
