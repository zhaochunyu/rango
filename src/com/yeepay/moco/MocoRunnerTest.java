package com.yeepay.moco;
import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.runner;

import com.github.dreamhead.moco.HttpProtocolVersion;
import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;


public class MocoRunnerTest {
   
	
	
 public static void main(String[] args) {
	 Runner runner;
	 int p=12422;
	 String url="/server.action";
	 String re="椿椿欢迎你";
	   HttpServer server = httpserver(p);
//        server.response("foo");re
        server.get(by(uri(url))).response(re);
        server.get(by(uri("/foo2"))).response("bar32");
     /*  server.request(and(by(uri("/target")), by(version(HttpProtocolVersion.VERSION_1_0)))).response(with(text("foo")), header("Content-Type", "text/html"));
        server.request(by(version("HTTP/1.0"))).response("version");
        server.request(xml(text("<request><parameters><id>1</id></parameters></request>"))).response("foo");*/
        runner = runner(server);
        runner.start();

}



}