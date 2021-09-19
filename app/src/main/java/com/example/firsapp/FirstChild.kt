package com.example.firsapp

class FirstChild(name: String, group: String): Parent(), First {
    override var Name: String = name
    override var Group: String = group
    var line: String = Sum(name,group)
}
