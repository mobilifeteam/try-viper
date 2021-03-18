package com.mobilife.employyim.data.remote

import android.util.Log
import com.mobilife.employyim.data.remote.model.ServerError
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler {

    fun extract(e: Throwable): ServerError {
        var error = ServerError()

        when (e) {
//            is ConnectException -> {
//                error.code = ErrorHandlerCode.CONNECT_EXCEPTION
//                error.message = "การเชื่อมต่อเครือข่ายไม่เสถียร กรุณาลองใหม่อีกครั้ง หากยังพบปัญหากรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//            }
//            is HttpException -> {
//                try {
//                    val body = e.response().errorBody()
//                    val errorConverter = ServiceFactory.retrofit!!
//                            .responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls(0))
//                    // Convert the error body into our ServerError type.
//                    body?.let {
//                        val errorResponse = errorConverter.convert(body)
//                        if (errorResponse?.error != null) {
//                            error = errorResponse.error
//                        } else {
//                            error.code = ErrorHandlerCode.HTTP_EXCEPTION
//                            error.message = "ไม่สามารถเชื่อมต่อเซิร์ฟเวอร์ได้ กรุณาลองใหม่อีกครั้ง หากยังพบปัญหากรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//                        }
//                    }
//                } catch (e: Exception) {
//                    error.code = ErrorHandlerCode.HTTP_EXCEPTION
//                    error.message = "ไม่สามารถเชื่อมต่อเซิร์ฟเวอร์ได้ กรุณาลองใหม่อีกครั้ง หากยังพบปัญหากรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//                    Timber.tag("StackTrace:").e(Log.getStackTraceString(e))
//                }
//            }
//            is ServerTimeoutException -> {
//                error.code = ErrorHandlerCode.SERVER_TIMEOUT_EXCEPTION
//                error.message = "ไม่สามารถเชื่อมต่อเซิร์ฟเวอร์ได้ กรุณาลองใหม่อีกครั้ง หากยังพบปัญหากรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//            }
//            is UnknownServiceException -> {
//                //occur when connecting to HTTP server, Error base on Network security configuration
//                error.code = ErrorHandlerCode.UNKNOWN_SERVICE_EXCEPTION
//                error.message = "การเชื่อมต่อของคุณไม่ปลอดภัย กรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//            }
//            is BadDecryptException -> {
//                error.code = ErrorHandlerCode.BAD_DECRYPT_EXCEPTION
//                error.message = "หมดเวลาทำรายการ กรุณาเข้าระบบใหม่"
//            }
//            is InvalidVersionException -> {
//                error.code = ErrorHandlerCode.INVALID_VERSION_EXCEPTION
//                error.message = "กรุณาติดต่อ Call Center เพื่อทำการอัพเดตเวอร์ชัน"
//            }
//            is NullPointerException -> {
//                error.code = ErrorHandlerCode.NULL_POINTER_EXCEPTION
//                error.message = "ระบบขัดข้อง กรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//            }
//            is SessionExpiredException -> {
//                error.code = ErrorHandlerCode.SESSION_EXPIRED_EXCEPTION
//                error.message = "หมดเวลาทำรายการ กรุณาเข้าระบบใหม่"
//            }
//            is InternalServerErrorException -> {
//                error.code = ErrorHandlerCode.INTERNAL_SERVER_ERROR_EXCEPTION
//                error.message = "ระบบขัดข้อง กรุณาติดต่อเจ้าหน้าที่ดูแลระบบ"
//            }
//            is NoConnectivityException -> {
//                error.code = ErrorHandlerCode.NO_CONNECTIVITY_EXCEPTION
//                EventBus.getDefault().post(NoConnectivityEvent())
//                error.message = "คุณไม่ได้ต่ออินเทอร์เน็ต กรุณาต่ออินเตอร์เน็ตเพื่อใช้งาน"
//            }
//            is SSLHandshakeException -> {
//                error = ServerError()
//                error.code = ErrorHandlerCode.SSL_HANDSHAKE_EXCEPTION
//                error.message = "ระบบขัดข้อง กรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//            }
//            is JsonSyntaxException -> {
//                error = ServerError()
//                error.code = ErrorHandlerCode.JSON_SYNTAX_EXCEPTION
//                error.message = "ระบบขัดข้อง กรุณาติดต่อ ${Constant.CALL_CENTER_TEL_NUMBER}"
//            }
            is SocketTimeoutException -> {
                error = ServerError()
                error.code = "2"
                error.message = "ไม่สามารถเชื่อมต่อเซิร์ฟเวอร์ได้ กรุณาตรวจสอบอินเทอร์เน็ตของท่าน หากยังพบปัญหากรุณาติดต่อ call center"
            }
            is UnknownHostException -> {
                error = ServerError()
                error.code = "1"
                error.message = "ไม่สามารถเชื่อมต่อเซิร์ฟเวอร์ได้ กรุณาตรวจสอบอินเทอร์เน็ตของท่าน หากยังพบปัญหากรุณาติดต่อ call center"
            }
            else -> {
                error = ServerError()
                error.code = "0"
                error.message = "ระบบขัดข้อง กรุณาติดต่อ call center"
            }
        }
        Log.e("StackTrace:", Log.getStackTraceString(e))
        return error
    }
}