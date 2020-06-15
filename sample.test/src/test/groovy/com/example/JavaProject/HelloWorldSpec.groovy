package com.example.JavaProject
import spock.lang.*

class HelloWorldSpec extends Specification {
    @Shared message = 'Hello world!'
    def "The world can say hello using when and then"() {
        when:
        def newMessage = message
        then:
        newMessage == 'Hello world!'
    }
    def "The world can say hello using expect"(){
        expect:
        message == 'Hello world!'
    }
}
