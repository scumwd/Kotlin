package com.example.firsapp

class SecondChild(name:String, phone: String): Parent(),Second {
    override var Name: String = name
    override var PhoneNumber: String= phone
    var line: String = PhoneBook(name,phone)
}
