package com.example.android

class person {
    var id = 0
    var hospitalname: String
    var bloodgroup: String
    internal constructor(name: String,group: String) {
        this.hospitalname = name
        this.bloodgroup = group
    }
    internal constructor(id: Int, name: String, group: String) {
        this.id = id
        this.hospitalname = name
        this.bloodgroup = group
    }
}