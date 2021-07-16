package io.todak.study.springbootmaster.reactive5;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SpringDataHttpTraceRepository implements HttpTraceRepository {

	private final HttpTraceWrapperRepository repository;

	public SpringDataHttpTraceRepository(HttpTraceWrapperRepository repository) {
		this.repository = repository;
		// 생성자를 통해, HttpTraceRepository 인스턴스를 주입받음.
	}

	@Override
	public List<HttpTrace> findAll() {
		return repository.findAll() //
				.map(HttpTraceWrapper::getHttpTrace) // <2>
				.collect(Collectors.toList());
	}

	@Override
	public void add(HttpTrace trace) {
		repository.save(new HttpTraceWrapper(trace)); // <3>
	}
}