package com.example

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.services.lambda.runtime.Context
import io.micronaut.function.aws.proxy.MicronautLambdaHandler
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class PingControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    MicronautLambdaHandler handler = new MicronautLambdaHandler()

    @Shared
    Context lambdaContext = new MockLambdaContext()

    void "ping v1"() {
        when:
        AwsProxyRequest request = new AwsProxyRequestBuilder("/ping", HttpMethod.GET.name())
                .build()
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext)

        then:
        HttpStatus.OK.code == response.statusCode
        response.body
        response.body == 'pong v1'
    }

    void "ping v2"() {
        when:
        AwsProxyRequest request = new AwsProxyRequestBuilder("/ping", HttpMethod.GET.name())
            .header('X-API-VERSION', '2')
            .build()
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext)

        then:
        HttpStatus.OK.code == response.statusCode
        response.body
        response.body == 'pong v2'

    }
}
