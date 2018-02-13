package com.zyy.springcloud.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 *Zipkin 是Twitter的一个开源项目，它基于google dapper实现。我们可以用它来收集
 *各个服务器上请求链路的跟踪数据，并通过它提供的rest api接口来辅助查询跟踪数据
 *以实现对分布式系统的监控程序，从而及时发现系统中出现的延迟升高问题并找出系统性能
 *瓶颈的根源。除了面向开发的API接口之外，它还提供了方便的UI组件来帮助我们直观地
 *搜索跟踪信息和分析请求链路明细，比如可以查询某段时间内各用户请求的处理时间等。
 *
 * @author zhangyanyan
 * @date 2018/1/9
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
