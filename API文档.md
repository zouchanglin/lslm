# 一、普通用户
## 活动相关
## 兼职相关

### 1、兼职列表（审核通过的兼职）
请求地址
```
GET http://tim.natapp1.cc/buckmoo/user/part/list
```
参数
```
pageindex : Integer 分页索引（从0开始）
```
返回值示例
```json
{
	"code":0,
	"data":[
		{
			"partCategory":{    //兼职分类信息
				"categoryId":1, //兼职分类Id
				"categoryName":"其他", //兼职分类名称
				"categoryNum":0,    //此分类下的兼职数量
				"createTime":1560308075000, //创建时间
				"updateTime":1563299669000  //最后更新时间
			},
			"partInfoList":[]
		},
		{
			"partCategory":{
				"categoryId":2,
				"categoryName":"代课/会议",
				"categoryNum":1,
				"createTime":1563310081000,
				"updateTime":1563310112000
			},
			"partInfoList":[
				{
					"createTime":1560307214000, //此条兼职信息创建时间
					"partAddress":"西安工程大学临潼校区A326", //兼职地点
					"partCategory":2,   //分类Id
					"partCreator":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY", //兼职发布者的微信openid
					"partEnd":1563255614000, //兼职结束时间
					"partId":"1560260414182640052", //兼职信息Id
					"partMoney":30, //兼职费用
					"partName":"周四大学英语代课", //兼职名称
					"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326", //兼职详情描述
					"partStart":1563457214000,  //兼职开始时间
					"partStatus":2, //兼职状态
					"partTime":"只要点名了就可以走，记住时间是周四34节",  //备注之类的信息
					"updateTime":1563313667000  //最后更新时间
				},
				{
					"createTime":1560307214000,
					"partAddress":"西安工程大学临潼校区A326",
					"partCategory":2,
					"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
					"partEnd":1563255614000,
					"partId":"1560260414182640053",
					"partMoney":30,
					"partName":"周四大学英语代课",
					"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326",
					"partStart":1563457214000,
					"partStatus":2,
					"partTime":"只要点名了就可以走，记住时间是周四34节",
					"updateTime":1563313669000
				}
			]
		},
		{
			"partCategory":{
				"categoryId":3,
				"categoryName":"传单",
				"categoryNum":0,
				"createTime":1563310103000,
				"updateTime":1563310103000
			},
			"partInfoList":[
				{
					"createTime":1560307214000,
					"partAddress":"西安工程大学临潼校区A326",
					"partCategory":3,
					"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
					"partEnd":1563255614000,
					"partId":"1560260414182640054",
					"partMoney":30,
					"partName":"周四大学英语代课",
					"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326",
					"partStart":1563457214000,
					"partStatus":2,
					"partTime":"只要点名了就可以走，记住时间是周四34节",
					"updateTime":1563313671000
				},
				{
					"createTime":1560307214000,
					"partAddress":"西安工程大学临潼校区A326",
					"partCategory":3,
					"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
					"partEnd":1563255614000,
					"partId":"1560260414182640055",
					"partMoney":30,
					"partName":"周四大学英语代课",
					"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326",
					"partStart":1563457214000,
					"partStatus":2,
					"partTime":"只要点名了就可以走，记住时间是周四34节",
					"updateTime":1563313675000
				}
			]
		},
		{
			"partCategory":{
				"categoryId":4,
				"categoryName":"配送",
				"categoryNum":0,
				"createTime":1563310126000,
				"updateTime":1563310126000
			},
			"partInfoList":[]
		},
		{
			"partCategory":{
				"categoryId":5,
				"categoryName":"抄写",
				"categoryNum":0,
				"createTime":1563310148000,
				"updateTime":1563310148000
			},
			"partInfoList":[]
		}
	],
	"msg":"成功"
}
```

## 账户相关
### 1、用户基本信息获取
```
GET http://tim.natapp1.cc/buckmoo/user/info/show
```
参数
```
openid : String
```
返回值
```json
{
	"code":1,
	"msg":"用户信息为空"
}

{
	"code":0,
	"data":{
		"createTime":1563307248000, //用户注册时间
		"openId":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk", //用户openid
		"updateTime":1563307248000,	//最后更新时间
		"userAddress":"泽西岛",	//地点
		"userGrade":0, //用户积分
		"userIcon":"http://thirdwx.qlogo.cn/mmopen/vi_32/bxVEQxwmOLibgHtYurJxvW0yicXLVcTCUiaDQDqibEyoIKwS7ZRdOsZL02RibF79vdNt6cFEYU1v53r1plygOAL60hw/132", 	//用户头像
		"userMember":0,	//会员级别
		"userName":"Tim",	//用户昵称
		"userSex":1	//性别
	},
	"msg":"成功"
}
```


# 二、活动发布方
## 活动相关
## 账户相关
## 支付相关

# 三、超级管理员
## 活动相关
## 兼职相关
## 账户相关
## 支付相关