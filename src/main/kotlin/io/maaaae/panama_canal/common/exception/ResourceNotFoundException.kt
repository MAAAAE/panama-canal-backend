package io.maaaae.panama_canal.common.exception

class ResourceNotFoundException(message: String) : RuntimeException(message)
class NonNullableFieldException(message: String) : RuntimeException(message)