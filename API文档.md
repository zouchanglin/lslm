# 普通用户
## 活动相关

### 1、查看审核通过的活动

```
GET /buckmoo/user/activity/list
```

参数

```
无
```

返回值

```json
{
	"code":0,
	"data":[
		{
			"activityAbstract":"欣赏终南山美景，品尝终南山的美食！",
			"activityAddress":"终南山",
			"activityApply":0,
			"activityAudit":1,
			"activityEnd":1560234128000,
			"activityGeneralize":2000,
			"activityId":"1560234127693562090",
			"activityLink":"https://s2.ax1x.com/2019/06/20/VxE6AS.png",
			"activityLogo":"https://s2.ax1x.com/2019/06/20/VxVF9H.png",
			"activityMain":"1560234861488151072",
			"activityMainName":"比特科技",
			"activityMax":50,
			"activityMode":1,
			"activityName":"钟南山一日游",
			"activityStart":1560234128000,
			"activityUnMainName":"比特科技",
			"activityUnmain":"1560234861488151072"
		},
		{
			"activityAbstract":"欣赏XPU美景，品尝终南山的美食！",
			"activityAddress":"西安工程大学",
			"activityApply":0,
			"activityAudit":1,
			"activityEnd":1560234128000,
			"activityGeneralize":50,
			"activityId":"1560234127693562091",
			"activityLink":"https://s2.ax1x.com/2019/06/20/VxEHNF.png",
			"activityLogo":"https://s2.ax1x.com/2019/06/20/VxVF9H.png",
			"activityMain":"1560234861488151073",
			"activityMainName":"骊山鹿鸣",
			"activityMax":50,
			"activityMode":1,
			"activityName":"XPU一日游",
			"activityStart":1560234128000,
			"activityUnMainName":"比特科技",
			"activityUnmain":"1560234861488151072"
		}
	],
	"msg":"成功"
}
```



## 兼职相关
## 账户相关

# 活动发布方
## 活动相关

## 账户相关

## 支付相关

# 超级管理员
## 活动相关

### 1、活动列表(分状态展示)

```
GET /buckmoo/admin/activity/show
```

参数

```
type:Integer 活动的状态(见如下代码)
```

Java中活动状态的枚举

```
NEW(0, "未审核"),
PASS(1, "审核通过"),
NOT_PASS(2, "审核未通过"),
FINISH(3, "活动结束")
```

返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "activityId": "1560234127693562090",
            "activityName": "钟南山一日游",
            "activityMain": "1560234861488151072",
            "activityUnmain": "1560234861488151072",
            "activityAddress": "终南山",
            "activityStart": "2019-06-11T06:22:08.000+0000",
            "activityEnd": "2019-06-11T06:22:08.000+0000",
            "activityMax": 50,
            "activityMode": 1,
            "activityGeneralize": 2000,
            "activityLink": "https://s2.ax1x.com/2019/06/20/VxE6AS.png",
            "activityAbstract": "欣赏终南山美景，品尝终南山的美食！",
            "activityLogo": "https://s2.ax1x.com/2019/06/20/VxVF9H.png",
            "activityAudit": 1,
            "activityUpdate": "2019-06-21T04:09:00.000+0000",
            "activityCreate": "2019-06-11T19:22:07.000+0000",
            "activityApply": 0
        }
    ]
}
```

错误返回（活动状态参数错误，即不存在于枚举中）：

```json
{
    "timestamp": "2019-06-22T02:25:15.998+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "参数不正确",
    "path": "/buckmoo/admin/activity/show"
}
```

### 2、根据Id删除活动

```
GET /buckmoo/admin/activity/delete
```

参数：

```
activityId:String 活动的Id
```

返回值：

```json
{
    "code": 0,
    "msg": "成功"
}
```

失败时的返回值(只有可能是参数对应的活动那个信息不存在)：

```json
{
    "timestamp": "2019-06-22T02:44:39.502+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "活动信息不正确",
    "path": "/buckmoo/admin/activity/delete"
}
```

### 3、修改活动信息

```
GET /buckmoo/admin/activity/update
```

参数（activityId是必传，只传单个属性就只会改单个值）

```
activityId String 活动Id
activityName String 活动名称
activityMain String 主办方Id
activityUnmain String 协办方Id
activityAddress String 活动地点
activityStart Long 开始时间
activityEnd Long 结束时间
activityMax Integer 最大人数
activityMode Integer 活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他
activityGeneralize Integer 推广力度
activityLink String 活动链接
activityAbstract String 活动简介
activityLogo String 活动宣传图
```

### 4、新建活动信息

```
POST /buckmoo/admin/activity/add
```

参数

```
activityId String 活动Id
activityName String 活动名称
activityMain String 主办方Id
activityUnmain String 协办方Id
activityAddress String 活动地点
activityStart Long 开始时间
activityEnd Long 结束时间
activityMax Integer 最大人数
activityMode Integer 活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他
activityGeneralize Integer 推广力度
activityLink String 活动链接
activityAbstract String 活动简介
activityLogo String 活动宣传图
```

返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
        "activityId": "1561204690446267854",
        "activityName": "AAA",
        "activityMain": "1560234861488151072",
        "activityUnmain": "1560234861488151072",
        "activityAddress": "XPU",
        "activityStart": "1970-01-19T01:40:03.760+0000",
        "activityEnd": "1970-01-19T01:40:03.760+0000",
        "activityMax": 111,
        "activityMode": 1,
        "activityGeneralize": 100,
        "activityLink": "https://www.baidu.com",
        "activityAbstract": "描述",
        "activityLogo": "https://lpogo.png",
        "activityAudit": 0
    }
}
```

### 5、审核活动信息

```
GET /buckmoo/admin/activity/audit
```

参数

```
activityId String 活动Id
activityAudit Integer 活动状态（0）未审核 （1）通过（2）未通过 (3)已经结束
```

返回值

```json
{
    "code": 0,
    "msg": "成功"
}
```

失败返回

```json
{
    "timestamp": "2019-06-22T12:56:29.279+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "参数不正确",
    "path": "/buckmoo/admin/activity/audit"
}
```

## 兼职相关
## 账户相关
## 支付相关