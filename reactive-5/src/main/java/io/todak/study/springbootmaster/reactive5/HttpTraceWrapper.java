package io.todak.study.springbootmaster.reactive5;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.data.annotation.Id;

public class HttpTraceWrapper {

    /**
     * HttpTrace에는 키로 사용할만한 속성이 없어, 몽고디비에 따로 저장할 수는 없다.
     * 따라서, 본 객체를 활용해 저장한다.
     */

    private @Id
    String id; // <1>
    
    private HttpTrace httpTrace; // <2>

    public HttpTraceWrapper(HttpTrace httpTrace) { // <3>
        this.httpTrace = httpTrace;
    }

    public HttpTrace getHttpTrace() { // <4>
        return httpTrace;
    }
}