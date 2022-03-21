package com.sschmitz.kmm.domain

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}

