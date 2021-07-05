package io.todak.study.springbootmaster.reactive3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class HackingSpringBootApplicationPlainBlockHound {

	public static void main(String[] args) {
		// 이렇게 실행하면, 모든 블로킹 코드에서 에러를 터트린다.
		BlockHound.install();
		SpringApplication.run(HackingSpringBootApplicationPlainBlockHound.class, args);
	}
}