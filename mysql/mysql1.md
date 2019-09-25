# Mysql常用命令

一、修改连接地址

    update mysql.user set host = '%' where user = 'username';
    flush privileges; 
    
    use mysql;
    grant all privileges  on *.* to root@'%' identified by 'yourpassword';
    flush privileges;

二、Mysql常用命令
    
    1.mysql出现group by 语句不兼容问题解决方案
    
    修改sqlmodel命令：
    
    set @@global.sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
    
    上面是改变了全局sql_mode，对于新建的数据库有效。对于已存在的数据库，则需要在对应的数据下执行
    
    set sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
    
    获取今天时间的数据
    date_format(数据库时间字段,'%Y-%m-%d') =date_format(now(),'%Y-%m-%d');
    
    获取数据库时间并添加8小时
    CONVERT_TZ(数据库时间字段, '+00:00', '+8:00')
    
    建表时间类型
	`create_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	
	Tinyint(1) --0代表FALSE
	delete_status tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除状态:0删除1没删除',
	
	mysql field自定义排序函数的格式
    order by field(value,str1,str2,str3,str4,,,,,,strn)
    其中value后面的参数自定义，不限制参数个数，含义将获取出来的数据根据str1,str2,str3,str4等的顺序排序
    
    mysql在查询结果列表前添加一列递增的序号列（最简）
    select (@rowNO := @rowNo+1) AS 序号,a.* from  t_user a,(select @rowNO :=0) b
    
    MySQL按天，按周，按月，按时间段统计数据 时间段添加where即可
    select DATE_FORMAT(create_time,'%Y%m%d') days,count(caseid) count from tc_case group by days;
    select DATE_FORMAT(create_time,'%Y%u') weeks,count(caseid) count from tc_case group by weeks;
    select DATE_FORMAT(create_time,'%Y%m') months,count(caseid) count from tc_case group by months;
    
    添加字段
    ALTER TABLE 表明
    add COLUMN 字段名 VARCHAR(125) DEFAULT NULL COMMENT '字段描述';
    
    特殊符号
    >= : <![CDATA[>= ]]>
    <  : <![CDATA[< ]]>
    
    MySQL 为日期增加一个时间间隔：date_add(时间字段,interval 1 day)
    单位：day天， hour小时，minute分钟，second秒，microsecond毫秒，week周，month月，quarter季，year年
    MySQL 为日期减去一个时间间隔：date_sub(时间字段，函数)
    
    mysql 一个字段有多个值，同时count出来
    eg：
        -- ：0=待审核 1=已审核 2=已驳回'
        select count(1) as '待审核',spi.reply_flag from supplier_plan_info spi GROUP BY spi.reply_flag;
        
        select 
        count(reply_flag=0 or null) as 0pendingreview,
        count(reply_flag=1 or null) as 1reviewed,
        count(reply_flag=2 or null) as 2rejected
        from supplier_plan_info;
        
    like拼接：
    like concat(concat("%",#{supplier_name}),"%")
    
    查询字段值判断
    (
		case 
			when spi.reply_flag is null then -1
			when spi.reply_flag =0 then 0	
			when spi.reply_flag =1 then 1
			when spi.reply_flag =2 then 2	
		end
	) as  reply_flag
	
	count计算总数去重：
	SELECT COUNT(DISTINCT 字段名) FROM 表名

	同时统计一个字段不同值的总条数使用CASE WHEN语法
	SELECT
	sum( CASE WHEN `字段` = 0 THEN 1 ELSE 0 END ) AS status_0,
	sum( CASE WHEN `字段` = 5 OR `字段` = 6 OR `字段` = 7 THEN 1 ELSE 0 END ) AS status_567
	FROM `表名` WHERE 条件
