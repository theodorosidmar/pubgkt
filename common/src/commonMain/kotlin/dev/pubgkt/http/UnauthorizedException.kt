package dev.pubgkt.http

import kotlin.js.JsExport

/**
 * Thrown when the PUBG API returns HTTP 401 (Unauthorized).
 * Provide a working API Key.
 */
@JsExport
public class UnauthorizedException : Exception("API Key invalid")
