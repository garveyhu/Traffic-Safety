<template>
	<view class="u-skeleton list">
		<view class="item" style="overflow: hidden;border-radius: 8rpx;padding: 20rpx;" v-for="(item,index) in list":key = "index">
			
			<view style="width: 320rpx;height: 300rpx;" class="u-skeleton-fillet">
				 <image class="album-cover" @tap="$emit('clickimg',item)" :src="item.img" mode="scaleToFill" :lazy-load="true"></image>
				<view class="tab " >
				  	<u-tag :text="item.tab.text" mode="dark" :bg-color="item.tab.color" v-if="item.tab" />
				</view>
			</view>
		 
		  <view class="bottom">
		    <u-avatar :src="item.User?item.User.avatar:''" @click="$emit('user',item)" size="60" class="u-skeleton-circle"></u-avatar>
			  <view class=" username u-skeleton-fillet" @tap="$emit('clickuser',item)">
				<text>{{item.User?item.User.nickName:'昵称'}}</text>
			  </view>
		    <view class=" icon" @tap="$emit('clickicon',item)">
				<u-icon name="star-fill" color="#8F8F94" size="36" class="u-skeleton-fillet"></u-icon>
		      <text class="Count u-skeleton-fillet">{{item.Count?item.Count:0}}</text>
		    </view>
		  </view>
		</view>
		<u-skeleton :loading="loading" :animation="true"></u-skeleton>
	</view>
  
</template>

<script>
 
  export default {
    name: 'zetank-list-card',

    props: {
      list: {
        type: Array,
        default: () => {
          return []
		  /* 
			{
				img:"图片",
				User:{
					avatar:'头像',
					nickName:'昵称'
				},
				Count:50,
						tab:{
							text:'标签',
							color:'#9acedc'
						}
			 
			}
		  */
        }
      },
	  loading:{
	  		  style:Boolean,
	  		  default:true
	  }
    },

  }
</script>

<style>
  /* 摄影封面 */
  .album-cover {
    height: 300rpx;
    width: 100%;
    display: block;
	border-radius: 8rpx;
  }
  .bottom{
	  display: flex;
	  align-items: center;
	  margin-top: 8rpx;
	  padding: 18rpx;
	  
  }
  .username{
	  width: 95rpx;
	  flex-grow: 1;
	  font-size: 30rpx;
	  margin: 0 15rpx;
	  color: #555;
	 white-space: nowrap;
	  overflow: hidden;
	  text-overflow: ellipsis;
	 
	  
  }
  .item{
	  box-shadow: 0upx 0upx 8upx #eee;
  }
  .icon{
	  align-items: center;
	  display: flex;
	  
  }
  .Count{
	  font-size: 28rpx;
	  color: gray;
	  margin: 8rpx;
  }
  .list{
	  display: flex;
	  flex-direction: row;
	  justify-content: space-around;
	  flex-wrap: wrap;
	  background: #ffffff;
  }

  .tab{
	 position: relative;
	 z-index: 9;
	top: -50rpx;
	text-align: right;
	 
  }

</style>
