package com.example.newsappbymishu.states

import java.lang.RuntimeException

sealed class DataSourceException(val messageResource: Any?): RuntimeException() {
    class Connection(messageResource: Int): DataSourceException(messageResource)
    class Unexpected(messageResource: Any): DataSourceException(messageResource)
    class TimeOut(messageResource: Int): DataSourceException(messageResource)
}