package pubgkt

/**
 * Thrown when the PUBG API returns HTTP 401 (Unauthorized).
 * Provide a working API Key.
 */
public class UnauthorizedException : Exception("API Key invalid")
