package com.alibaba.rsocket.graphql.book.graphqljava;

import com.alibaba.rsocket.RSocketService;
import com.alibaba.rsocket.graphql.GraphqlRSocketSupport;
import com.alibaba.rsocket.graphql.book.BookGraphqlService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;


@RSocketService(serviceInterface = BookGraphqlService.class)
@Component
@Profile("graphqljava")
public class BookGraphqlServiceImpl extends GraphqlRSocketSupport implements BookGraphqlService {
    @Autowired
    private GraphQL graphQL;

    @Override
    public Mono<ExecutionResult> execute(ExecutionInput executionInput) {
        CompletableFuture<ExecutionResult> future = graphQL.executeAsync(executionInput);
        return Mono.fromFuture(future);
    }
}
