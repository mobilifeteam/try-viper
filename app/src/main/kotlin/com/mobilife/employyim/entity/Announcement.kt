package com.mobilife.employyim.entity

class Announcement(
        var code: String,
        var type: String? = null,
        var title: String? = null,
        var message: String,
        var startTimestamp: String,
        var finishTimestamp: String
)