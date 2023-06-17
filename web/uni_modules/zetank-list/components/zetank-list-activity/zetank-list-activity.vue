<template>
	<view class="u-skeleton">
		<view class="list" @tap="$emit('click',list)" v-for="(item,index) in list":key = "index">
		  <image class="listImage u-skeleton-fillet" :src="item.imgurl"
		    mode="scaleToFill" :lazy-load="true"></image>
		  <view class="right">
		    <view class="listName u-skeleton-fillet">{{item.title}}</view>
		    <view>
				<u-icon name="clock" color="#8F8F94" size="36" class="u-skeleton-fillet"></u-icon>
		      <text class="time u-skeleton-fillet">{{item.date}}</text>
		    </view>
		    <view>
				<u-icon name="map" color="#8F8F94" size="36" class="u-skeleton-fillet"></u-icon>
		      <text class="time">{{item.adress}}</text>
		    </view>
			  <view class="u-skeleton-fillet tab" style="width: 200rpx;height: 50rpx;">
			  	<u-tag :text="item.tab.text" mode="dark" shape="circle" :bg-color="item.tab.color" v-if="item.tab" />
			  </view>
		
		  </view>
		</view>
		<u-skeleton :animation="true" :loading="loading"></u-skeleton>
	</view>
  
</template>

<script>
 import timestampFormat from "../../js_sdk/timestampFormat.js"

  /**  
   * 文章展示卡组件
   * @property {Object} list 信息数据  
   * @event {Function} click 展示卡 点击事件  
   */
  export default {
    name: 'zetank-list-card',

    props: {
      /**
       * 信息数据 
       */
      list: {
        type: Array,
        default: () => {
          return []
		  /* 
			[{
			  title: "汉韵端午  相约万达!汉韵端午  相约万达",
			  imgurl: "http://pic.hanfugou.com/web/2019/5/30/21/370ab5b6f99a41c0adb2a31d48e756c1.jpeg",
			  adress: "安徽,淮南",
			  date: 1630117856516,
			  tab:{
							text:'报名中',
							color:'#9acedc'
						}
			}]
		  */
        }
      },
	  loading:{
		  style:Boolean,
		  default:true
	  }
    },
	created() {
		
	},
    watch: {
      list(newval,oldval){
		  for(let i =0;i<this.list.length;i++){
		  	this.list[i].date= this.calDatetime(this.list[i].date)
		  	this.list[i].adress =  this.calAddress(this.list[i].adress)
		  }
	  }
    },
	methods:{
		/// 时间格式 2019-12-03 20:12
		calDatetime(date) {
				  
		  let time = timestampFormat.timestampFormat(date)
		  console.log(time);
		  return time
		},
		/// 地址逗号换空格 
		calAddress(adress) {
		  return !!adress ? adress.split(',').join(' ') : '未知 未知'
		}
	}

  }
</script>

<style lang="scss" scoped>
  /*活动结束状态*/
  .org-state {
    position: absolute;
    bottom: 0;
    right: 0;
    height: 120rpx;
    width: 150rpx;
  }
  .list{
	  width: 100%;
	  background: #ffffff;
	  padding: 18rpx;
	  margin-bottom: 18rpx;
	  display: flex;
	  box-shadow: 0upx 0upx 8upx #eee;
  }
  .listImage{
	  height: 200rpx;
	  width: 200rpx;
	   border-radius: 8rpx;
	   margin-right: 18rpx;
  }
  .right{
	  flex: 1;
	  position: relative;
	  display: flex;
	  flex-direction: column;
	  justify-content: space-around;
	  
  }
  .listName{
	  font-size: 32rpx;
	  color: #555;
	  height: 112rpx;
	font-weight: bold;
  }
  .time{
	  font-size: 28rpx;
	  color: gray;
	  margin: 18rpx;
	  height: 50rpx;
	  width: 80rpx;
  }
  .tab{
	  position: absolute;
	  bottom: 0;
	  right: 0;
	  padding: 0 18rpx;
  }
</style>
