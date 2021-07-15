package io.todak.study.springbootmaster.reactive5;

import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

/**
 * HttpTraceWrapperRepository가 논블로킹 패러다임을 사용하지 않기 때문에,
 * 굳이 리액티브 스트림 방식을 적용하지 않는다.
 */
public interface HttpTraceWrapperRepository extends Repository<HttpTraceWrapper, String> {

    Stream<HttpTraceWrapper> findAll();

    void save(HttpTraceWrapper trace);
}