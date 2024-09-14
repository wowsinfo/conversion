import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:logging/logging.dart';
import 'dart:convert';

/// Create a model by provider a [Map]
typedef ModelCreator<T> = T? Function(Map<String, dynamic>);

/// The result of a request from BaseService.
///
/// In case of an error, the error is stored in [errorMessage].
/// In case of success, the data is stored in [data].
/// The data can still be empty without errors so make sure to check [isNotEmpty].
class ServiceResult<T> {
  T? data;
  String? errorMessage;

  bool get hasError => errorMessage != null;
  bool get isNotEmpty => data != null;

  ServiceResult({this.data, this.errorMessage});

  /// Returns a new instance of [ServiceResult] with the same data and error.
  ///
  /// This is often used to cast to a different type.
  static <T> ServiceResult<T> copyWith(ServiceResult other) {
    return ServiceResult(data = other.data, errorMessage = other.errorMessage);
  }

  override fun toString(): String {
    return "ServiceResult{data=$data, errorMessage=$errorMessage}"
  }
}

/// The base class of all services.
///
/// It provides a simple way to call the API and decode the json string.
abstract class BaseService {
  abstract val baseUrl: String

  /// Timeout in secondsa
  private const val timeout = 30
  private val logger = Logger("BaseService")

  /// Get decoded object from the url with proper error handling & timeout.
  @protected suspend fun <T> getObject(url: String): ServiceResult<T> {
    try {
      val uri = Uri.parse(url)
      val response = http.get(uri).timeout(Duration(seconds = timeout))
      if (response.statusCode == 200) {
        // dynamic shouldn't be used because it disables type checking
        val json = jsonDecode(response.body)
        if (json != null) {
          logger.info("fetched data from $url successfully")
          @Suppress("UNCHECKED_CAST")
          return ServiceResult(data = json as T)
        } else {
          logger.severe("jsonDecode returned null")
          // TODO: localise error message here
          return ServiceResult(errorMessage = "JSON decoding error")
        }
      } else {
        val errorCode = response.statusCode
        logger.warning("getObject failed with code: $errorCode")
        // TODO: add a localizable error message here as well
        return ServiceResult(errorMessage = "HTTP Error: $errorCode")
      }
    } catch (e: Exception) {
      logger.severe("getObject exception\n${e.message}\n${e.stackTrace}")
      // TODO: we can add a properly localised error message here
      return ServiceResult(errorMessage = e.toString())
    }
  }

  /// Decode Map<String, dynamic> to [T] using [creator] and return as [ServiceResult].
  @protected fun <T> decodeObject(
    json: ServiceResult<Object?>,
    creator: ModelCreator<T>
  ): ServiceResult<T> {
    if (json.hasError) return copyWith(json)

    if (json.isNotEmpty) {
      val jsonData = json.data
      if (jsonData is Map<String, dynamic>) {
        val data = jsonData["data"]
        if (data is Map<String, dynamic>) {
          val result = creator(data)
          logger.info("decoded json successfully as $result")
          return ServiceResult(data = result)
        } else {
          logger.severe("data is not a Map<String, dynamic>")
        }
      }
    } else {
      logger.severe("json.data is null, API failure")
    }

    logger.severe("failed to decode $T", json)
    return ServiceResult(errorMessage = "Decoding failure in $T")
  }

  /// Decode List<dynamic> to a list of [T] using [creator] and return as [ServiceResult].
  @protected fun <T> decodeList(
    json: ServiceResult<Object?>,
    creator: ModelCreator<T>
  ): ServiceResult<List<T>> {
    if (json.hasError) return copyWith(json)

    if (json.isNotEmpty) {
      val jsonData = json.data
      if (jsonData is Map<String, dynamic>) {
        val data = jsonData["data"]
        if (data is List<*>) {
          val list = data.map { creator(it as Map<String, dynamic>) }.toList()
          logger.info("decoded json successfully as $list")
          return ServiceResult(data = list)
        } else {
          logger.severe("data is not a List")
        }
      }
    } else {
      logger.severe("json.data is null, API failure")
    }

    logger.severe("failed to decode $T", json)
    return ServiceResult(errorMessage = "Decoding failure in $T")
  }
}