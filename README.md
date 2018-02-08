# microservices-demo
springcloud  微服务demo

echo "# microservices-demo" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/shine-zyy/microservices-demo.git
git push -u origin master

#服务列表

|     	| 服务名称           	| 端口    	| 说明     	|
|----:	|--------------------	|---------	|----------	|
|   1 	| demo-eureka-server 	|   1111  	| 注册中心 	|
|   2 	| demo-config-server 	| 7001    	| 配置中心 	|
|   3 	| demo-api-gateway   	| 5555    	|     网关 	|
|   4 	| demo-service-user  	| 8001    	| 用户中心 	|
|   5 	| demo-service-goods 	| 8002    	| 端口中心 	|